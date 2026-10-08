package ma.youcode.controller;

import java.io.IOException;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.youcode.model.Patient;
import ma.youcode.service.FileAttenteService;
import ma.youcode.service.PatientService;

@WebServlet({ "/medecin/dossier", "/medecin/dossier/*" })
public class MedecinDossierServlet extends HttpServlet {

    private PatientService patientService;
    private FileAttenteService fileAttenteService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.patientService = new PatientService();
        this.fileAttenteService = new FileAttenteService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getPathInfo();
        Long patientId = null;

        if (path != null && !"/".equals(path)) {
            String[] segments = path.split("/");
            String idPart = segments.length > 1 ? segments[1] : null;
            if (idPart != null && !idPart.isBlank()) {
                try {
                    patientId = Long.parseLong(idPart);
                } catch (NumberFormatException e) {
                    response.sendRedirect(request.getContextPath() + "/medecin/dashboard");
                    return;
                }
            }
        }

        if (patientId == null) {
            response.sendRedirect(request.getContextPath() + "/medecin/dashboard");
            return;
        }

        Optional<Patient> patientOpt = patientService.findById(patientId);
        if (patientOpt.isEmpty()) {
            request.setAttribute("errorMessage", "Patient introuvable.");
            response.sendRedirect(request.getContextPath() + "/medecin/dashboard");
            return;
        }

        fileAttenteService.prendreEnCharge(patientId);

        request.setAttribute("patient", patientOpt.get());
        request.getRequestDispatcher("/WEB-INF/views/medecin/generaliste-dossier.jsp")
                .forward(request, response);
    }

}
