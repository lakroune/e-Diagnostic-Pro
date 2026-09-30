package ma.youcode.controller;

import java.io.IOException;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.youcode.model.Patient;
import ma.youcode.service.PatientService;

@WebServlet("/patients")
public class PatientServlet extends HttpServlet {
  
    private PatientService patientService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String idStr = req.getParameter("id");
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String numSecu = req.getParameter("numSecu");
        String dateNaissanceStr = req.getParameter("dateNaissance");
        String telephone = req.getParameter("telephone");
        String adresse = req.getParameter("adresse");
        String mutuelle = req.getParameter("mutuelle");

        Patient patient = new Patient();
        patient.setNom(nom);
        patient.setPrenom(prenom);
        patient.setNumSecuriteSociale(numSecu);
        patient.setTelephone(telephone);
        patient.setAdresse(adresse);
        patient.setMutuelle(mutuelle);

        if (dateNaissanceStr != null && !dateNaissanceStr.trim().isEmpty()) {
            patient.setDateNaissance(LocalDate.parse(dateNaissanceStr));
        }

        if (idStr != null && !idStr.trim().isEmpty()) {
            Long id = Long.parseLong(idStr);
            patientService.update(id, patient);
        } else {
            patientService.create(patient);
        }

        // Redirection vers la liste après enregistrement (Pattern Post-Redirect-Get)
        resp.sendRedirect(req.getContextPath() + "/patients");
    }

}