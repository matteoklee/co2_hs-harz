package de.kleemann.co2_hsharz.api.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * The {@link APISecurity}-Class is a {@link HandlerInterceptor}. <br>
 * It intercepts Http Requests and checks, whether or not this Request was send from the localhost. <br>
 * If the Request isn't send from localhost, it will terminate the request.
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 31.01.2024
 */
@Slf4j
public class APISecurity implements HandlerInterceptor {
	/**
	 * Intercepts Http Request before it was handled. <br>
	 * Checks if Request was send from localhost, if not it will not allow the request to be processed. <br>
	 * <hr>
	 * {@inheritDoc}
	 */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Intercepting request from remote address {}", request.getRemoteAddr());
        log.info("Accepting request: " + request.getRemoteAddr().matches(request.getLocalAddr()));
        return request.getRemoteAddr().matches(request.getLocalAddr());
    }
}
