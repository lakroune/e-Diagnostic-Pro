package ma.youcode.controller;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ma.youcode.model.Utilisateur;
import ma.youcode.model.enums.Role;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"/admin/*", "/infirmier/*", "/medecin/*", "/specialiste/*"})
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        if (path.equals("/login") || path.equals("/logout")) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = httpRequest.getSession(false);
        Utilisateur user = (session != null) ? (Utilisateur) session.getAttribute("user") : null;

        if (user == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            return;
        }

        if (path.startsWith("/admin/") && user.getRole() != Role.ADMIN) {
            redirectToRoleDashboard(httpRequest, httpResponse, user.getRole());
            return;
        }

        if (path.startsWith("/infirmier/") && user.getRole() != Role.INFIRMIER) {
            redirectToRoleDashboard(httpRequest, httpResponse, user.getRole());
            return;
        }

        if (path.startsWith("/medecin/") && user.getRole() != Role.GENERALISTE) {
            redirectToRoleDashboard(httpRequest, httpResponse, user.getRole());
            return;
        }

        if (path.startsWith("/specialiste/") && user.getRole() != Role.SPECIALISTE) {
            redirectToRoleDashboard(httpRequest, httpResponse, user.getRole());
            return;
        }

        chain.doFilter(request, response);
    }

    private void redirectToRoleDashboard(HttpServletRequest request, HttpServletResponse response, Role role)
            throws IOException {
        if (role == Role.ADMIN) {
            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
        } else if (role == Role.GENERALISTE) {
            response.sendRedirect(request.getContextPath() + "/medecin/dashboard");
        } else if (role == Role.SPECIALISTE) {
            response.sendRedirect(request.getContextPath() + "/specialiste/dashboard");
        } else if (role == Role.INFIRMIER) {
            response.sendRedirect(request.getContextPath() + "/infirmier/dashboard");
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    @Override
    public void destroy() {
    }
}
