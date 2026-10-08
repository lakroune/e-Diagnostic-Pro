package ma.youcode.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.youcode.model.Patient;
import ma.youcode.service.PatientService;

@WebServlet("/medecin/dashboard")
public class MedecinDashboardServlet extends HttpServlet {

    private final PatientService patientService = new PatientService();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<Patient> patientsEnAttente = patientService.getListAttente();
        request.setAttribute("patientsEnAttente", patientsEnAttente);
        request.setAttribute("patientsDuJour", patientsEnAttente);

        request.getRequestDispatcher(
                "/WEB-INF/views/medecin/dashboard.jsp").forward(request, response);
    }
}