package com.bootstudy.study;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Slf4j
public class SuccessLoggingInterceptor extends HandlerInterceptorAdapter {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        if(log.isInfoEnabled()) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = ((HandlerMethod) handler).getMethod();
            log.info("[SUCCESS CONTROLLER] {}.{}", method.getDeclaringClass().getSimpleName(), method.getName());
        }
    }
}
