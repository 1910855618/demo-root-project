package com.demospringsecurity.config;

import com.demospringsecurity.filter.SmsCodeFilter;
import com.demospringsecurity.filter.ValidateCodeFilter;
import com.demospringsecurity.handler.MyAuthenticationAccessDeniedHandler;
import com.demospringsecurity.handler.MyAuthenticationFailureHandler;
import com.demospringsecurity.handler.MyAuthenticationSuccessHandler;
import com.demospringsecurity.handler.MyLogOutSuccessHandler;
import com.demospringsecurity.strategy.MySessionExpiredStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SmsCodeFilter smsCodeFilter;

    @Autowired
    private SmsAuthenticationConfig smsAuthenticationConfig;

    @Autowired
    private MySessionExpiredStrategy sessionExpiredStrategy;

    @Autowired
    private MyLogOutSuccessHandler logOutSuccessHandler;

    @Autowired
    private MyAuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class) // 添加验证码校验过滤器(这样添加验证码过滤器将在用户验证之前执行)
                .addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class) // 添加短信验证码校验过滤器
                // 表单验证
                .formLogin()
                // 登录跳转 URL
                .loginPage("/authentication/require")
                // 处理表单登录 URL
                .loginProcessingUrl("/login")
                // 处理登录成功
                .successHandler(authenticationSuccessHandler)
                // 登录失败
                .failureHandler(authenticationFailureHandler)
                .and()
                // 记住我功能配置
                .rememberMe()
                // 配置 token 持久化仓库
                .tokenRepository(persistentTokenRepository())
                // remember 过期时间，单为秒
                .tokenValiditySeconds(3600)
                // 处理自动登录逻辑
                .userDetailsService(userDetailService)
                .and()
                // 授权配置
                .authorizeRequests()
                // 无需验证的路径
                .antMatchers("/authentication/require", "/login.html", "/validate/code/image", "/validate/code/sms", "/session/invalid", "/signout/success").permitAll()
                // 所有请求都要验证
                .anyRequest().authenticated()
                // 添加 Session 管理器, 并在 Session 失效后跳转到指定链接
                .and().sessionManagement().invalidSessionUrl("/session/invalid")
                // 配置最大Session并发数量为1个
                .maximumSessions(1)
                // 禁止后登陆者挤退前登录者
                .maxSessionsPreventsLogin(true)
                // 并配置失效后的处理策略
                .expiredSessionStrategy(sessionExpiredStrategy).and()
                // 配置了退出登录 url
                .and().logout().logoutUrl("/signout")
                // 指定退出成功处理器
                .logoutSuccessHandler(logOutSuccessHandler)
                // 将成功 url 设置为 /signout/success
//                .logoutSuccessUrl("/signout/success")
                // 删除 JSESSIONID cookies
                .deleteCookies("JSESSIONID")
                // 将异常处理器添加到Spring Security配置链中
                .and().exceptionHandling().accessDeniedHandler(authenticationAccessDeniedHandler)
                // 关闭CSRF攻击防御(自定义登录页后不关闭会登录失败)
                .and().csrf().disable()
                // 将短信验证码认证配置加到 Spring Security 中
                .apply(smsAuthenticationConfig);
    }
}
