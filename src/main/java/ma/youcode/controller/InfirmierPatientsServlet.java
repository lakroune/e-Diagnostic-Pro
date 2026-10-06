package ma.youcode.controller;

import java.io.IOException;
import java.time.LocalDate;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getPathInfo();

        if (path == null || "/".equals(path)) {
            List<Patient> patients = patientService.findAll();
            request.setAttribute("patients", patients);
            request.getRequestDispatcher("/WEB-INF/views/infirmier/patients.jsp").forward(request, response);
            return;
        }

        try {
            String[] pathSplit = path.split("/");
            String idPart = pathSplit.length > 1 ? pathSplit[1] : null;

            if (idPart != null && !idPart.trim().isEmpty()) {
                Long idPatient = Long.parseLong(idPart);
                Optional<Patient> patient = patientService.findById(idPatient);

                if (patient.isPresent()) {
                    request.setAttribute("patient", patient.get());
                    request.getRequestDispatcher("/WEB-INF/views/infirmier/patient-detail.jsp")
                            .forward(request, response);
                    return;
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("ID Patient invalide dans l'URL : " + path);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Patient non trouvé");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        String idParam = request.getParameter("id");

        if ("delete".equalsIgnoreCase(action)) {
            deletePatient(request, response, idParam);
            return;
        }

        if (idParam != null && !idParam.trim().isEmpty()) {
            updatePatient(request, response, idParam);
            return;
        }

        createPatient(request, response);
    }

    private void createPatient(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Patient patient = buildPatientFromRequest(request);
        patientService.create(patient);
        response.sendRedirect(request.getContextPath() + "/infirmier/patients");
    }

    private void updatePatient(HttpServletRequest request, HttpServletResponse response, String idParam)
            throws IOException {
        try {
            Long id = Long.parseLong(idParam);
            Patient patient = buildPatientFromRequest(request);
            patientService.update(id, patient);
            response.sendRedirect(request.getContextPath() + "/infirmier/patients/" + id);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/infirmier/patients");
        }
    }

    private void deletePatient(HttpServletRequest request, HttpServletResponse response, String idParam)
            throws IOException {
        if (idParam == null || idParam.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/infirmier/patients");
            return;
        }

        try {
            Long id = Long.parseLong(idParam);
            patientService.delete(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/infirmier/patients");
    }

    private Patient buildPatientFromRequest(HttpServletRequest request) {
        Patient patient = new Patient();
        patient.setNom(request.getParameter("nom"));
        patient.setPrenom(request.getParameter("prenom"));
        patient.setNumSecuriteSociale(request.getParameter("numSecuriteSociale"));
        patient.setTelephone(request.getParameter("telephone"));
        patient.setAdresse(request.getParameter("adresse"));
        patient.setMutuelle(request.getParameter("mutuelle"));
        patient.setAntecedents(request.getParameter("antecedents"));
        patient.setAllergies(request.getParameter("allergies"));
        patient.setTraitementsEnCours(request.getParameter("traitementsEnCours"));

        String dateNaissanceStr = request.getParameter("dateNaissance");
        if (dateNaissanceStr != null && !dateNaissanceStr.trim().isEmpty()) {
            patient.setDateNaissance(LocalDate.parse(dateNaissanceStr));
        }

        return patient;
    }
}