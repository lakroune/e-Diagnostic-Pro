package ma.youcode.controller;

import java.io.IOException;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ma.youcode.model.DemandeTeleExpertise;
import ma.youcode.model.Utilisateur;
import ma.youcode.model.enums.Role;
import ma.youcode.service.DemandeTeleExpertiseService;

@WebServlet("/specialiste/demande/*")
public class SpecialisteDemandeServlet extends HttpServlet {

    private DemandeTeleExpertiseService demandeTeleExpertiseService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.demandeTeleExpertiseService = new DemandeTeleExpertiseService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Utilisateur user = (session != null) ? (Utilisateur) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        if (user.getRole() != Role.SPECIALISTE) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String path = request.getPathInfo();
        if (path == null || path.equals("/")) {
            response.sendRedirect(request.getContextPath() + "/specialiste/dashboard");
            return;
        }

        Long demandeId;
        try {
            demandeId = Long.parseLong(path.substring(1));
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/specialiste/dashboard");
            return;
        }

        Optional<DemandeTeleExpertise> demandeOpt = demandeTeleExpertiseService.findById(demandeId);
        if (demandeOpt.isEmpty()) {
            request.setAttribute("message", "Demande introuvable.");
            request.getRequestDispatcher("/WEB-INF/views/specialiste/demande-introuvable.jsp").forward(request, response);
            return;
        }

        DemandeTeleExpertise demande = demandeOpt.get();
        if (demande.getMedecinSpecialiste() == null || demande.getMedecinSpecialiste().getId() == null
                || !demande.getMedecinSpecialiste().getId().equals(user.getId())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Cette demande ne vous appartient pas.");
            return;
        }

        request.setAttribute("specialiste", user);
        request.setAttribute("demande", demande);
        request.getRequestDispatcher("/WEB-INF/views/specialiste/demande-detail.jsp").forward(request, response);
    }
}
