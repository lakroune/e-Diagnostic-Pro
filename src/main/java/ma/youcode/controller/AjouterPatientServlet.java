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

@WebServlet("/infirmier/patients/ajouter")
public class AjouterPatientServlet extends HttpServlet {

    private  PatientService patientService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.patientService = new PatientService();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                "/WEB-INF/views/infirmier/ajouter-patient.jsp").forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        Patient patient = new Patient();

        patient.setNom(request.getParameter("nom"));
        patient.setPrenom(request.getParameter("prenom"));
        patient.setDateNaissance(LocalDate.parse(request.getParameter("dateNaissance")));
        patient.setNumSecuriteSociale(request.getParameter("numSecuriteSociale"));
        patient.setTelephone(request.getParameter("telephone"));
        patient.setAdresse(request.getParameter("adresse"));
        patient.setMutuelle(request.getParameter("mutuelle"));
        patient.setAntecedents(request.getParameter("antecedents"));
        patient.setAllergies(request.getParameter("allergies"));
        patient.setTraitementsEnCours(request.getParameter("traitementsEnCours"));

        patientService.create(patient);
        request.setAttribute("patient", patient);

        response.sendRedirect(
                request.getContextPath() + "/infirmier/patients");
    }
}