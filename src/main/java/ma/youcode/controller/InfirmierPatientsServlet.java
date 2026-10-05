package ma.youcode.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.youcode.model.Patient;
import ma.youcode.service.PatientService;

@WebServlet("/infirmier/patients/*")
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

        String path = request.getPathInfo();

        if (path == null || path.equals("/")) {
            List<Patient> patients = patientService.findAll();
            request.setAttribute("patients", patients);
            request.getRequestDispatcher("/WEB-INF/views/infirmier/patients.jsp").forward(request, response);
            return;
        }
        try {

            String[] pathSplit = path.split("/");

            if (pathSplit.length > 1) {
                Long idPatient = Long.parseLong(pathSplit[1]);

                Optional<Patient> patient = patientService.findById(idPatient);

                if (patient.isPresent()) {
                    request.setAttribute("patient", patient.get());
                    request.getRequestDispatcher("/WEB-INF/views/infirmier/patient-detail.jsp")
                            .forward(request, response);
                    return;
                }
            }
        } catch (Exception e) {

        }
        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Patient non trouve");

    }
}