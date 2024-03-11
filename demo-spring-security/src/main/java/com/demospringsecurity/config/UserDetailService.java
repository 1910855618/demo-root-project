package com.demospringsecurity.config;

import com.demospringsecurity.entity.MyUser;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Log4j2
@Configuration
public class UserDetailService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;

    // Bean 使用延时加载解决循环依赖
    @Autowired
    UserDetailService(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 模拟一个用户，替代数据库获取逻辑
        MyUser user = new MyUser();
        user.setUserName(username);
        user.setPassword(passwordEncoder.encode("123456"));
        // 输出加密后的密码
        log.info("加密结果: {}", user.getPassword());
//        return new User(username,
//                user.getPassword(),
//                user.isEnabled(),
//                user.isAccountNonExpired(),
//                user.isCredentialsNonExpired(),
//                user.isAccountNonLocked(),
//                // 权限不能为空
//                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        List<GrantedAuthority> authorities;
        if (username.equalsIgnoreCase("admin")) {
            authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
        } else {
            authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("test");
        }
        return new User(username, user.getPassword(), user.isEnabled(),
                user.isAccountNonExpired(), user.isCredentialsNonExpired(),
                user.isAccountNonLocked(), authorities);
    }
}
