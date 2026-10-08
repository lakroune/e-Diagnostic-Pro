package ma.youcode.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ma.youcode.model.Creneau;
import ma.youcode.model.MedecinSpecialiste;
import ma.youcode.model.Utilisateur;
import ma.youcode.model.enums.Role;
import ma.youcode.model.enums.StatutCreneau;
import ma.youcode.service.CreneauService;
import ma.youcode.service.MedecinSpecialisteService;

@WebServlet({ "/specialiste/creneau", "/specialiste/creneaux" })
public class SpecialisteCreneauServlet extends HttpServlet {

    private CreneauService creneauService;
    private MedecinSpecialisteService medecinSpecialisteService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.creneauService = new CreneauService();
        this.medecinSpecialisteService = new MedecinSpecialisteService();
    }

    private MedecinSpecialiste loadSpecialisteFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Utilisateur user = (session != null) ? (Utilisateur) session.getAttribute("user") : null;

        if (user == null || user.getRole() != Role.SPECIALISTE) {
            return null;
        }

        return medecinSpecialisteService.findById(user.getId()).orElse(null);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        MedecinSpecialiste medecinSpecialiste = loadSpecialisteFromSession(request);

        if (medecinSpecialiste == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        if (medecinSpecialiste.getCreneaux() == null) {
            medecinSpecialiste.setCreneaux(new ArrayList<>());
        } else {
            medecinSpecialiste.getCreneaux().size();
        }

        request.setAttribute("specialiste", medecinSpecialiste);
        request.setAttribute("creneaux", medecinSpecialiste.getCreneaux());
        request.getRequestDispatcher("/WEB-INF/views/specialiste/creneau.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        MedecinSpecialiste medecinSpecialiste = loadSpecialisteFromSession(request);

        if (medecinSpecialiste == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String dateDebutStr = request.getParameter("dateHeureDebut");
        String dateFinStr = request.getParameter("dateHeureFin");

        if (dateDebutStr == null || dateFinStr == null || dateDebutStr.isBlank() || dateFinStr.isBlank()) {
            if (medecinSpecialiste.getCreneaux() != null) {
                medecinSpecialiste.getCreneaux().size();
            }
            request.setAttribute("error", "Veuillez renseigner la date de début et la date de fin.");
            request.setAttribute("specialiste", medecinSpecialiste);
            request.setAttribute("creneaux", medecinSpecialiste.getCreneaux());
            request.getRequestDispatcher("/WEB-INF/views/specialiste/creneau.jsp").forward(request, response);
            return;
        }

        if (!dateDebutStr.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}")
                || !dateFinStr.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}")) {
            if (medecinSpecialiste.getCreneaux() != null) {
                medecinSpecialiste.getCreneaux().size();
            }
            request.setAttribute("error", "Format de date invalide.");
            request.setAttribute("specialiste", medecinSpecialiste);
            request.setAttribute("creneaux", medecinSpecialiste.getCreneaux());
            request.getRequestDispatcher("/WEB-INF/views/specialiste/creneau.jsp").forward(request, response);
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateDebut = LocalDateTime.parse(dateDebutStr, formatter);
        LocalDateTime dateFin = LocalDateTime.parse(dateFinStr, formatter);

        if (!dateFin.isAfter(dateDebut)) {
            if (medecinSpecialiste.getCreneaux() != null) {
                medecinSpecialiste.getCreneaux().size();
            }
            request.setAttribute("error", "La date de fin doit être supérieure à la date de début.");
            request.setAttribute("specialiste", medecinSpecialiste);
            request.setAttribute("creneaux", medecinSpecialiste.getCreneaux());
            request.getRequestDispatcher("/WEB-INF/views/specialiste/creneau.jsp").forward(request, response);
            return;
        }

        Creneau creneau = new Creneau();
        creneau.setDateHeureDebut(dateDebut);
        creneau.setDateHeureFin(dateFin);
        creneau.setStatut(StatutCreneau.DISPONIBLE);
        creneau.setMedecinSpecialiste(medecinSpecialiste);

        creneauService.create(creneau);
        response.sendRedirect(request.getContextPath() + "/specialiste/creneau");
    }
}
