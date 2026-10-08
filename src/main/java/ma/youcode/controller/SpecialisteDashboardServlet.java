package ma.youcode.controller;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

@WebServlet("/specialiste/dashboard")
public class SpecialisteDashboardServlet extends HttpServlet {

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

        List<DemandeTeleExpertise> demandes = demandeTeleExpertiseService.findAll().stream()
                .filter(d -> d.getMedecinSpecialiste() != null
                        && d.getMedecinSpecialiste().getId() != null
                        && d.getMedecinSpecialiste().getId().equals(user.getId()))
                .sorted(Comparator.comparing(
                        DemandeTeleExpertise::getDateDemande,
                        Comparator.nullsLast(Comparator.reverseOrder())))
                .collect(Collectors.toList());

        request.setAttribute("specialiste", user);
        request.setAttribute("demandes", demandes);
        request.getRequestDispatcher("/WEB-INF/views/specialiste/dashboard.jsp").forward(request, response);
    }
}
