package com.study.login.aspect.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uuid = (String) request.getAttribute("uuid");
        String requestURI = request.getRequestURI();
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
        }
        log.info("Interceptor preHandler [UUID={}][URI={}][handler={}]", uuid, requestURI, handler);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String uuid = (String) request.getAttribute("uuid");
        String requestURI = request.getRequestURI();
        log.info("Interceptor postHandler [UUID={}][URI={}][ModelAndView={}]", uuid, requestURI, modelAndView);
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String uuid = (String) request.getAttribute("uuid");
        String requestURI = request.getRequestURI();
        log.info("Interceptor afterCompletion [UUID={}][URI={}]", uuid, requestURI);
        if (ex != null) {
            log.info("Exception={}", ex);
        }
    }
}
