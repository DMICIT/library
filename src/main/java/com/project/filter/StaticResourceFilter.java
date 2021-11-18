package com.project.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class StaticResourceFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestUrl = httpServletRequest.getRequestURI();
        if (requestUrl.endsWith(".jsp")||requestUrl.endsWith(".jpeg")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String contextPath = httpServletRequest.getContextPath();
        String path = requestUrl.substring(contextPath.length());
        String pathToForward = "/app" + path;
        httpServletRequest.getRequestDispatcher(pathToForward).forward(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
