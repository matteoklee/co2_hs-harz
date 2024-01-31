package de.kleemann.co2_hsharz.api.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Class "APIIntercept" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 31.01.2024
 */
@Slf4j
public class APIIntercept implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Intercepting request from remote address {}", request.getRemoteAddr());
        log.info("Accepting request: " + request.getRemoteAddr().matches(request.getLocalAddr()));
        return request.getRemoteAddr().matches(request.getLocalAddr()); //TODO Testen
    }
}
