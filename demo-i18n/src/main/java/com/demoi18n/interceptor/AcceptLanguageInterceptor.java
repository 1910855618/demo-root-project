package com.demoi18n.interceptor;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Optional;

@Component
public class AcceptLanguageInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        String headerLang = Optional.ofNullable(request.getHeader("Accept-Language")).orElse("");
        if ("zh".equals(headerLang)) {
            LocaleContextHolder.setLocale(Locale.SIMPLIFIED_CHINESE);
        } else if ("en".equals(headerLang)) {
            LocaleContextHolder.setLocale(Locale.US);
        }
        String urlLang = Optional.ofNullable(request.getParameter("lang")).orElse("");
        if("zh".equals(urlLang)) {
            LocaleContextHolder.setLocale(Locale.SIMPLIFIED_CHINESE);
        } else if ("en".equals(urlLang)) {
            LocaleContextHolder.setLocale(Locale.US);
        }
        return true;
    }
}
