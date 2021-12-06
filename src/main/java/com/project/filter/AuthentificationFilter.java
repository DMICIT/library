package com.project.filter;

import com.project.web.DispatcherServlet;
import com.project.web.commands.Command;
import com.project.web.data.UserPrincipal;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class AuthentificationFilter implements Filter {

    private static Map<String, List<String>> pagesMap = new HashMap<>();

    static {
        pagesMap.put("user", Arrays.asList("orders", "books", "logout", "personal-account", "user-abonement"));
        pagesMap.put("admin", Arrays.asList("orders", "personal-account", "logout", "user-abonement", "admin-books", "admin-users", "admin-add-user", "user-list", "admin-edit-book"));
        pagesMap.put("librarian", Arrays.asList("orders", "personal-account", "user-abonement", "logout", "librarian-orders", "user-list"));
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path = getPath(request);
        if (path.endsWith("jpeg")) {
            filterChain.doFilter(request, response);
            return;
        }
        if (path.equals("books") || path.equals("login") || path.equals("registration") || path.equals("orders") || path.equals("ban-page") || path.equals("") || path.equals("language") || path.equals("error")) {
            filterChain.doFilter(request, response);
            return;
        }
        HttpSession session = request.getSession();
        UserPrincipal user = (UserPrincipal) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        String userRole = user.getRole();
        List<String> pages = pagesMap.get(userRole);
        if (pages != null && pages.contains(path)) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }

    @Override
    public void destroy() {

    }

    private String getPath(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        int lastSymbol = requestUri.lastIndexOf('/');
        return requestUri.substring(lastSymbol + 1);

    }
}
