package com.demoaop.aspect;

import com.demoaop.annotation.Log;
import com.demoaop.entity.SysLog;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Log4j2
@Aspect
@Component
public class LogAspect {
    // 定义了一个切点，选择了被 @Log 注解标记的方法作为切点
    @Pointcut("@annotation(com.demoaop.annotation.Log)")
    public void Pointcut() {}

    // 环绕通知，指定切点，在被 @Log 注解标记的方法上应用这个环绕通知
    @Around("Pointcut()")
    public Object around(ProceedingJoinPoint point) {
        Object result = null;
        long beginTime = Instant.now().toEpochMilli();
        try {
            // 调用被切点方法的逻辑
            result = point.proceed();
        } catch (Throwable e) {
            log.error(e);
        }
        // 计算切点方法执行所花费的时间（毫秒）
        long time = Instant.now().toEpochMilli() - beginTime;
        // 保存日志
        saveLog(point, time);
        return result;
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLog sysLog = new SysLog();
        Optional<Log> optionalLog = Optional.ofNullable(method.getAnnotation(Log.class));
        // 如果 optionalLog 中存在 Log 注解，则 map 方法会将其转换为注解上的描述值，否则返回 null
        sysLog.setOperation(optionalLog.map(Log::value).orElse(null));
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        // 请求的方法参数值
        Object[] args = joinPoint.getArgs();
        // 请求的方法参数名称
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        if (args != null && paramNames != null) {
            StringBuilder params = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                params.append("  ").append(paramNames[i]).append(": ").append(args[i]);
            }
            sysLog.setParams(params.toString());
        }
        // 获取request
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        sysLog.setIp(getIpAddr(request));
        // 模拟一个用户名
        sysLog.setUserName("Anna");
        sysLog.setTime((int) time);
        sysLog.setCreateTime(LocalDateTime.now());
    }

    private static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }
}
