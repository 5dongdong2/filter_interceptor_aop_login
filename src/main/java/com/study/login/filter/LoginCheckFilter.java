package com.study.login.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

    private static final String[] whitelist = {"/", "/logout", "/css/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();

        try {
            log.info("LoginCheckFilter IN [UUID={} / URI={}]", httpRequest.getAttribute("uuid"), requestURI);

            if (isLoginCheckPath(requestURI)) {
                log.info("Login Authentication [UUID={} / URI={}]", httpRequest.getAttribute("uuid"), requestURI);

                HttpSession session = httpRequest.getSession(false);
                if (session == null || session.getAttribute("member_idx") == null) {
                    log.info("Authentication failed [UUID={} / URI={}]", httpRequest.getAttribute("uuid"), requestURI);

                    httpResponse.sendRedirect("/login?redirectURI" + requestURI);
                    return;
                }
                log.info("Authentication Success [UUID={} / URI={}]", httpRequest.getAttribute("uuid"), requestURI);
            }
            chain.doFilter(request, response);

            chain.doFilter(request, response);
        } catch (IOException e) {
            throw e;
        } finally {
            log.info("LoginCheckFilter OUT [UUID={} / URI={}]", httpRequest.getAttribute("uuid"), requestURI);
        }
    }

    private boolean isLoginCheckPath(String requestURI){
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }
}
