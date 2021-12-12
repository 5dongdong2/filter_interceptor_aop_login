package com.study.login.aspect.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        if (requestURI.contains("/css")) {
            chain.doFilter(request, response);
            return;
        }

        String uuid = UUID.randomUUID().toString();
        request.setAttribute("uuid", uuid);

        try {
            log.info("Request Filter [UUID={}][URI={}]", uuid, requestURI);
            chain.doFilter(request, response);
            log.info("Response Filter [UUID={}][URI={}]", uuid, requestURI);
        } catch (IOException e) {
            throw e;
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("LogFilter init");
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        log.info("LogFilter destroy");
        Filter.super.destroy();
    }
}
