package com.epam.bolotin.periodicals.controller.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Encoding filter.
 *
 * @author: Viacheslav Bolotin
 * @date: 02.01.2023
 */
public class EncodingFilter implements Filter {

    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String requestEncoding = servletRequest.getCharacterEncoding();

        if (requestEncoding == null) {
            servletRequest.setCharacterEncoding(encoding);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
