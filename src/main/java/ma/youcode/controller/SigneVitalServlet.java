package ma.youcode.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.youcode.model.Infirmier;
import ma.youcode.model.Patient;
import ma.youcode.model.SigneVital;
import ma.youcode.service.PatientService;
import ma.youcode.service.SigneVitalService;

@WebServlet(name = "SigneVitalServlet", urlPatterns = {
        "/patients/signeVitaux",
        "/patients/signeVitaux/delete",
        "/patients/signeVitaux/update"
})
public class SigneVitalServlet extends HttpServlet {

    private SigneVitalService signeVitalService;
    private PatientService patientService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.signeVitalService = new SigneVitalService();
        this.patientService = new PatientService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String servletPath = request.getServletPath();

        if ("/patients/signeVitaux/delete".equals(servletPath)) {
            doDeleteSigneVital(request, response);
            return;
        }

        if ("/patients/signeVitaux/update".equals(servletPath)) {
            doUpdateSigneVital(request, response);
            return;
        }

        doCreateSigneVital(request, response);
    }

    public String resolvePatientRedirectUrl(HttpServletRequest request, Long patientId) {
        String contextPath = request.getContextPath();
        if (patientId == null) {
            return contextPath + "/infirmier/patients";
        }
        return contextPath + "/infirmier/patients/" + patientId;
    }

    private void doCreateSigneVital(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String patientIdStr = request.getParameter("patientId");

        if (patientIdStr != null && !patientIdStr.trim().isEmpty()) {
            try {
                Long patientId = Long.parseLong(patientIdStr);
                Optional<Patient> patientOpt = patientService.findById(patientId);

                if (patientOpt.isPresent()) {
                    SigneVital signeVital = buildSigneVitalFromRequest(request);
                    signeVital.setDatePrise(LocalDateTime.now());
                    signeVital.setPatient(patientOpt.get());

                    Infirmier infirmierConnecte = (Infirmier) request.getSession().getAttribute("infirmier");
                    signeVital.setInfirmier(infirmierConnecte);

                    signeVitalService.create(signeVital);
                    response.sendRedirect(resolvePatientRedirectUrl(request, patientId));
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect(request.getContextPath() + "/infirmier/patients");
    }

    private void doUpdateSigneVital(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String idStr = request.getParameter("id");
        String patientIdStr = request.getParameter("patientId");

        if (idStr != null && !idStr.trim().isEmpty()) {
            try {
                Long id = Long.parseLong(idStr);
                Optional<SigneVital> existingOpt = signeVitalService.findById(id);

                if (existingOpt.isPresent()) {
                    SigneVital signeVital = existingOpt.get();
                    signeVital.setTensionArterielle(request.getParameter("tensionArterielle"));
                    signeVital.setFrequenceCardiaque(parseInteger(request.getParameter("frequenceCardiaque")));
                    signeVital.setTemperatureCorporelle(parseDouble(request.getParameter("temperatureCorporelle")));
                    signeVital.setFrequenceRespiratoire(parseInteger(request.getParameter("frequenceRespiratoire")));
                    signeVital.setPoidsKg(parseDouble(request.getParameter("poidsKg")));
                    signeVital.setTailleCm(parseDouble(request.getParameter("tailleCm")));

                    signeVitalService.update(id, signeVital);
                    response.sendRedirect(resolvePatientRedirectUrl(request, parseLongOrNull(patientIdStr)));
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect(request.getContextPath() + "/infirmier/patients");
    }

    private void doDeleteSigneVital(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String idStr = request.getParameter("id");
        String patientIdStr = request.getParameter("patientId");

        if (idStr != null && !idStr.trim().isEmpty()) {
            try {
                Long id = Long.parseLong(idStr);
                signeVitalService.delete(id);
                response.sendRedirect(resolvePatientRedirectUrl(request, parseLongOrNull(patientIdStr)));
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect(request.getContextPath() + "/infirmier/patients");
    }

    private SigneVital buildSigneVitalFromRequest(HttpServletRequest request) {
        SigneVital sv = new SigneVital();
        sv.setTensionArterielle(request.getParameter("tensionArterielle"));
        sv.setFrequenceCardiaque(parseInteger(request.getParameter("frequenceCardiaque")));
        sv.setTemperatureCorporelle(parseDouble(request.getParameter("temperatureCorporelle")));
        sv.setFrequenceRespiratoire(parseInteger(request.getParameter("frequenceRespiratoire")));
        sv.setPoidsKg(parseDouble(request.getParameter("poidsKg")));
        sv.setTailleCm(parseDouble(request.getParameter("tailleCm")));
        return sv;
    }

    private Integer parseInteger(String value) {
        return (value != null && !value.trim().isEmpty()) ? Integer.parseInt(value) : null;
    }

    private Double parseDouble(String value) {
        return (value != null && !value.trim().isEmpty()) ? Double.parseDouble(value) : null;
    }

    private Long parseLongOrNull(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}