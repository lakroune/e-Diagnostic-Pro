package ma.youcode.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.youcode.dao.AuthDAO;
import ma.youcode.model.Infirmier;
import ma.youcode.model.MedecinGeneraliste;
import ma.youcode.model.MedecinSpecialiste;
import ma.youcode.model.enums.Role;
import ma.youcode.model.enums.SpecialiteMedicale;
import ma.youcode.service.InfirmierService;
import ma.youcode.service.MedecinGeneralisteService;
import ma.youcode.service.MedecinSpecialisteService;

@WebServlet("/admin/utilisateurs/ajouter")
public class AdminAjouterUtilisateurServlet extends HttpServlet {

    private static final Role[] USER_ROLES = {
            Role.INFIRMIER,
            Role.GENERALISTE,
            Role.SPECIALISTE
    };

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("roles", USER_ROLES);
        request.setAttribute("specialites", SpecialiteMedicale.values());
        request.getRequestDispatcher("/WEB-INF/views/admin/ajouter-utilisateur.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("motDePasse");
        String telephone = request.getParameter("telephone");
        String roleParam = request.getParameter("role");

        request.setAttribute("specialites", SpecialiteMedicale.values());

        if (nom == null || nom.isBlank() || prenom == null || prenom.isBlank() || email == null || email.isBlank()
                || motDePasse == null || motDePasse.isBlank() || roleParam == null || roleParam.isBlank()) {
            request.setAttribute("error", "Veuillez remplir tous les champs obligatoires.");
            request.getRequestDispatcher("/WEB-INF/views/admin/ajouter-utilisateur.jsp").forward(request, response);
            return;
        }

        try {
            Role role = Role.valueOf(roleParam);
            AuthDAO authDAO = new AuthDAO();

            if (authDAO.existsByEmail(email)) {
                request.setAttribute("error", "Un utilisateur avec cet email existe déjà.");
                request.getRequestDispatcher("/WEB-INF/views/admin/ajouter-utilisateur.jsp").forward(request, response);
                return;
            }

            if (role == Role.INFIRMIER) {
                String matriculePro = request.getParameter("matriculePro");
                if (matriculePro == null || matriculePro.isBlank()) {
                    request.setAttribute("error", "Le matricule professionnel est obligatoire pour un infirmier.");
                    request.getRequestDispatcher("/WEB-INF/views/admin/ajouter-utilisateur.jsp").forward(request, response);
                    return;
                }

                Infirmier infirmier = new Infirmier();
                infirmier.setNom(nom);
                infirmier.setPrenom(prenom);
                infirmier.setEmail(email);
                infirmier.setMotDePasse(motDePasse);
                infirmier.setTelephone(telephone);
                infirmier.setActif(true);
                infirmier.setMatriculePro(matriculePro);

                new InfirmierService().save(infirmier);
                request.setAttribute("success", "Infirmier ajouté avec succès.");

            } else if (role == Role.GENERALISTE) {
                String matriculeOrdre = request.getParameter("matriculeOrdre");
                if (matriculeOrdre == null || matriculeOrdre.isBlank()) {
                    request.setAttribute("error", "Le matricule d'ordre est obligatoire pour un médecin généraliste.");
                    request.getRequestDispatcher("/WEB-INF/views/admin/ajouter-utilisateur.jsp").forward(request, response);
                    return;
                }

                MedecinGeneraliste medecinGeneraliste = new MedecinGeneraliste();
                medecinGeneraliste.setNom(nom);
                medecinGeneraliste.setPrenom(prenom);
                medecinGeneraliste.setEmail(email);
                medecinGeneraliste.setMotDePasse(motDePasse);
                medecinGeneraliste.setTelephone(telephone);
                medecinGeneraliste.setActif(true);
                medecinGeneraliste.setMatriculeOrdre(matriculeOrdre);

                new MedecinGeneralisteService().create(medecinGeneraliste);
                request.setAttribute("success", "Médecin généraliste ajouté avec succès.");

            } else if (role == Role.SPECIALISTE) {
                String specialiteParam = request.getParameter("specialite");
                if (specialiteParam == null || specialiteParam.isBlank()) {
                    request.setAttribute("error", "La spécialité est obligatoire pour un médecin spécialiste.");
                    request.getRequestDispatcher("/WEB-INF/views/admin/ajouter-utilisateur.jsp").forward(request, response);
                    return;
                }

                String tarifParam = request.getParameter("tarifExpertise");
                String dureeParam = request.getParameter("dureeConsultationMin");

                MedecinSpecialiste medecinSpecialiste = new MedecinSpecialiste();
                medecinSpecialiste.setNom(nom);
                medecinSpecialiste.setPrenom(prenom);
                medecinSpecialiste.setEmail(email);
                medecinSpecialiste.setMotDePasse(motDePasse);
                medecinSpecialiste.setTelephone(telephone);
                medecinSpecialiste.setActif(true);
                medecinSpecialiste.setSpecialite(SpecialiteMedicale.valueOf(specialiteParam));
                if (tarifParam != null && !tarifParam.isBlank()) {
                    medecinSpecialiste.setTarifExpertise(Double.parseDouble(tarifParam));
                }
                if (dureeParam != null && !dureeParam.isBlank()) {
                    medecinSpecialiste.setDureeConsultationMin(Integer.parseInt(dureeParam));
                }

                new MedecinSpecialisteService().create(medecinSpecialiste);
                request.setAttribute("success", "Médecin spécialiste ajouté avec succès.");

            } else {
                request.setAttribute("error", "Type de compte non pris en charge.");
                request.getRequestDispatcher("/WEB-INF/views/admin/ajouter-utilisateur.jsp").forward(request, response);
                return;
            }

            request.setAttribute("roles", USER_ROLES);
            request.setAttribute("specialites", SpecialiteMedicale.values());
            request.getRequestDispatcher("/WEB-INF/views/admin/ajouter-utilisateur.jsp")
                    .forward(request, response);

        } catch (IllegalArgumentException e) {
            request.setAttribute("error", "Type ou valeur invalide : " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/admin/ajouter-utilisateur.jsp").forward(request, response);
        }
    }
}
