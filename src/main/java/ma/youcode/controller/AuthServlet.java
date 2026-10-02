package ma.youcode.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.youcode.model.Utilisateur;
import ma.youcode.model.enums.Role;
import ma.youcode.service.AuthService;

@WebServlet("/login")
public class AuthServlet extends HttpServlet {
    private AuthService authService;

    @Override
    public void init() throws ServletException {
        this.authService = new AuthService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            Utilisateur uOptional = authService.login(email, password);
            if (uOptional != null) {
                request.getSession().setAttribute("user", uOptional);
                if (uOptional.getRole().equals(Role.ADMIN)) {
                    response.sendRedirect(request.getContextPath() + "/admin/dashboard");
                } else if (uOptional.getRole().equals(Role.GENERALISTE)) {
                    response.sendRedirect(request.getContextPath() + "/medecin/dashboard");
                } else if (uOptional.getRole().equals(Role.SPECIALISTE)) {
                    response.sendRedirect(request.getContextPath() + "/specialiste/dashboard");
                }else if (uOptional.getRole().equals(Role.INFIRMIER)) {
                    response.sendRedirect(request.getContextPath() + "/infirmier/dashboard");
                } else {
                    response.sendRedirect(request.getContextPath() + "/login");
                }
            } else {
                request.setAttribute("error", "Identifiants incorrects.");
                request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
            }
        } catch (Exception e) {

            request.setAttribute("error", "Il y a eu une erreur lors de la connexion. Veuillez réessayer.");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        } finally {
        }

    }

    // public static void main(String[] args) {
    //     AuthService authService = new AuthService();

    //     try {
    //         Utilisateur user = new Utilisateur(
    //                 null,
    //                 "hamza",
    //                 "hamza",
    //                 "hamza@gmail.com",
    //                 "12345678",
    //                 "0612345618",
    //                 Role.INFIRMIER,
    //                 true);

    //         Utilisateur registeredUser = authService.register(user);

    //         System.out.println("User registered: " + registeredUser.getNomComplet());
    //         System.out.println("Email: " + registeredUser.getEmail());

    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }


}
