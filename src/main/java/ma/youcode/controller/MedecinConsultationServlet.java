package ma.youcode.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.youcode.dao.impl.ConsultationDAOImpl;
import ma.youcode.model.Consultation;
import ma.youcode.model.Patient;
import ma.youcode.model.enums.StatutConsultation;
import ma.youcode.service.FileAttenteService;
import ma.youcode.service.PatientService;

@WebServlet("/medecin/consultation")
public class MedecinConsultationServlet extends HttpServlet {

    private PatientService patientService;
    private FileAttenteService fileAttenteService;
    private ConsultationDAOImpl consultationDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.patientService = new PatientService();
        this.fileAttenteService = new FileAttenteService();
        this.consultationDAO = new ConsultationDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/medecin/dashboard");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String patientIdParam = request.getParameter("patientId");
        if (patientIdParam == null || patientIdParam.isBlank()) {
            response.sendRedirect(request.getContextPath() + "/medecin/dashboard");
            return;
        }

        Long patientId;
        try {
            patientId = Long.parseLong(patientIdParam);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/medecin/dashboard");
            return;
        }

        Optional<Patient> patientOpt = patientService.findById(patientId);
        if (patientOpt.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/medecin/dashboard");
            return;
        }

        Consultation consultation = new Consultation();
        consultation.setPatient(patientOpt.get());
        consultation.setDateConsultation(LocalDateTime.now());
        consultation.setMotif(request.getParameter("motif"));
        consultation.setExamenClinique(request.getParameter("examenClinique"));

        String symptomes = request.getParameter("symptomes");
        String observations = request.getParameter("observations");
        if (symptomes != null && !symptomes.isBlank()) {
            if (observations == null || observations.isBlank()) {
                observations = symptomes;
            } else {
                observations = symptomes + "\n" + observations;
            }
        }
        consultation.setObservations(observations);

        consultation.setDiagnostic(request.getParameter("diagnostic"));
        consultation.setOrdonnance(request.getParameter("ordonnance"));

        String action = request.getParameter("action");
        String statutParam = request.getParameter("statut");

        if ("cloturer".equalsIgnoreCase(action)) {
            consultation.setStatut(StatutConsultation.TERMINEE);
        } else if ("demander_avis_specialiste".equalsIgnoreCase(action)) {
            consultation.setStatut(StatutConsultation.EN_ATTENTE_AVIS_SPECIALISTE);
        } else {
            try {
                consultation.setStatut(StatutConsultation.valueOf(statutParam));
            } catch (Exception e) {
                consultation.setStatut(StatutConsultation.EN_COURS);
            }
        }

        consultationDAO.save(consultation);
        fileAttenteService.prendreEnCharge(patientId);

        if ("demander_avis_specialiste".equalsIgnoreCase(action)) {
            response.sendRedirect(request.getContextPath() + "/medecin/tele-expertise?patientId=" + patientId);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/medecin/dossier/" + patientId);
    }
}
