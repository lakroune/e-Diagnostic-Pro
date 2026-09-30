package ma.youcode.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.youcode.model.Patient;

@WebServlet("/patients")
public class PatientServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Patient> patients = new ArrayList<>();

        patients.add(new Patient("ismail", "ismail", "299999"));
        patients.add(new Patient("ismail", "ismail", "299999"));

        request.setAttribute("patients", patients);

        request.getRequestDispatcher("/WEB-INF/views/patients.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String telephone = request.getParameter("telephone");

        Patient patient = new Patient(nom, prenom, telephone);

        response.setContentType("text/plain");
        response.getWriter().println("Patient ajouté avec succès");
        response.getWriter().println("Nom : " + patient.getNom());
        response.getWriter().println("Prénom : " + patient.getPrenom());
        response.getWriter().println("Téléphone : " + patient.getTelephone());
    }

}