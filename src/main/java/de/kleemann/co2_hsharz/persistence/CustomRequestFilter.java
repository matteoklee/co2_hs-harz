package de.kleemann.co2_hsharz.persistence;

import de.kleemann.co2_hsharz.Application;
import jakarta.servlet.*;

import java.io.IOException;

/**
 * Class "CustomRequestFilter" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 17.10.2023
 */
public class CustomRequestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //String remoteAddress = servletRequest.getRemoteAddr();
        //System.err.println(Application.getPREFIX() + "Zugriff auf API von IP-Adresse: " + remoteAddress);
        filterChain.doFilter(servletRequest, servletResponse);
        //TODO: Security outsource to APIIntercept
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
