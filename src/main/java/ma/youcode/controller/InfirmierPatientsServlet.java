package ma.youcode.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.youcode.service.PatientService;

@WebServlet("/infirmier/patients")
public class InfirmierPatientsServlet extends HttpServlet {
    private PatientService patientService;

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
                "/WEB-INF/views/infirmier/patients.jsp").forward(request, response);
    }
}