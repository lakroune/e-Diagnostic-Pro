package ma.youcode.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.youcode.dao.impl.CreneauDAOImpl;
import ma.youcode.dao.impl.DemandeTeleExpertiseDAOImpl;
import ma.youcode.model.Consultation;
import ma.youcode.model.Creneau;
import ma.youcode.model.DemandeTeleExpertise;
import ma.youcode.model.MedecinSpecialiste;
import ma.youcode.model.Patient;
import ma.youcode.model.enums.PrioriteExpertise;
import ma.youcode.model.enums.SpecialiteMedicale;
import ma.youcode.model.enums.StatutConsultation;
import ma.youcode.model.enums.StatutCreneau;
import ma.youcode.model.enums.StatutExpertise;
import ma.youcode.service.MedecinSpecialisteService;
import ma.youcode.service.PatientService;

@WebServlet({ "/medecin/tele-expertise", "/medecin/tele-expertise/*" })
public class MedecinTeleExpertiseServlet extends HttpServlet {

    private PatientService patientService;
    private MedecinSpecialisteService medecinSpecialisteService;
    private DemandeTeleExpertiseDAOImpl demandeTeleExpertiseDAO;
    private CreneauDAOImpl creneauDAO;
    private ma.youcode.dao.impl.ConsultationDAOImpl consultationDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.patientService = new PatientService();
        this.medecinSpecialisteService = new MedecinSpecialisteService();
        this.demandeTeleExpertiseDAO = new DemandeTeleExpertiseDAOImpl();
        this.creneauDAO = new CreneauDAOImpl();
        this.consultationDAO = new ma.youcode.dao.impl.ConsultationDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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

        String selectedSpecialiteParam = request.getParameter("specialite");
        String selectedSpecialistIdParam = request.getParameter("specialisteId");

        List<MedecinSpecialiste> specialists = medecinSpecialisteService.findAll().stream()
                .filter(sp -> selectedSpecialiteParam == null || selectedSpecialiteParam.isBlank()
                        || sp.getSpecialite() != null && sp.getSpecialite().name().equals(selectedSpecialiteParam))
                .sorted(Comparator.comparingDouble(
                        sp -> sp.getTarifExpertise() == null ? Double.MAX_VALUE : sp.getTarifExpertise().doubleValue()))
                .collect(Collectors.toList());

        MedecinSpecialiste selectedSpecialist = null;
        List<Creneau> availableSlots = List.of();
        List<Creneau> reservedOrOldSlots = List.of();

        if (selectedSpecialistIdParam != null && !selectedSpecialistIdParam.isBlank()) {
            try {
                Long selectedSpecialistId = Long.parseLong(selectedSpecialistIdParam);
                selectedSpecialist = medecinSpecialisteService.findById(selectedSpecialistId).orElse(null);
                if (selectedSpecialist != null) {
                    List<Creneau> creneaux = selectedSpecialist.getCreneaux() == null
                            ? List.of()
                            : selectedSpecialist.getCreneaux();

                    availableSlots = creneaux.stream()
                            .filter(c -> c != null && c.getStatut() == StatutCreneau.DISPONIBLE)
                            .filter(c -> c.getDateHeureDebut() != null
                                    && !c.getDateHeureDebut().isBefore(LocalDateTime.now()))
                            .sorted(Comparator.comparing(Creneau::getDateHeureDebut))
                            .collect(Collectors.toList());

                    reservedOrOldSlots = creneaux.stream()
                            .filter(c -> c != null && (c.getStatut() != StatutCreneau.DISPONIBLE
                                    || (c.getDateHeureDebut() != null
                                            && c.getDateHeureDebut().isBefore(LocalDateTime.now()))))
                            .sorted(Comparator.comparing(Creneau::getDateHeureDebut))
                            .collect(Collectors.toList());
                }
            } catch (NumberFormatException ignored) {
                selectedSpecialist = null;
            }
        }

        request.setAttribute("patient", patientOpt.get());
        request.setAttribute("specialites", SpecialiteMedicale.values());
        request.setAttribute("specialiteLibelles", buildSpecialiteLibelles());
        request.setAttribute("heuresJour", buildHeuresJour());
        request.setAttribute("specialists", specialists);
        request.setAttribute("selectedSpecialite", selectedSpecialiteParam);
        request.setAttribute("selectedSpecialist", selectedSpecialist);
        request.setAttribute("availableSlots", availableSlots);
        request.setAttribute("reservedOrOldSlots", reservedOrOldSlots);
        request.setAttribute("priorites", PrioriteExpertise.values());

        request.getRequestDispatcher("/WEB-INF/views/medecin/tele-expertise.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String patientIdParam = request.getParameter("patientId");
        String specialistIdParam = request.getParameter("specialisteId");
        String heureDebutParam = request.getParameter("heureDebut");
        String question = request.getParameter("question");
        String prioriteParam = request.getParameter("priorite");

        if (patientIdParam == null || patientIdParam.isBlank()
                || specialistIdParam == null || specialistIdParam.isBlank()
                || heureDebutParam == null || heureDebutParam.isBlank()) {
            response.sendRedirect(request.getContextPath() + "/medecin/dashboard");
            return;
        }

        Long patientId;
        Long specialistId;
        try {
            patientId = Long.parseLong(patientIdParam);
            specialistId = Long.parseLong(specialistIdParam);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/medecin/dashboard");
            return;
        }

        Optional<Patient> patientOpt = patientService.findById(patientId);
        Optional<MedecinSpecialiste> specialistOpt = medecinSpecialisteService.findById(specialistId);

        if (patientOpt.isEmpty() || specialistOpt.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/medecin/dashboard");
            return;
        }

        LocalTime heureDebut;
        try {
            heureDebut = LocalTime.parse(heureDebutParam);
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/medecin/tele-expertise?patientId=" + patientId);
            return;
        }

        LocalDateTime debut = LocalDateTime.of(LocalDate.now(), heureDebut);
        LocalDateTime fin = debut.plusHours(1);

        boolean slotExists = creneauDAO.findAll().stream()
                .anyMatch(c -> c.getMedecinSpecialiste() != null
                        && c.getMedecinSpecialiste().getId() != null
                        && c.getMedecinSpecialiste().getId().equals(specialistId)
                        && c.getDateHeureDebut() != null
                        && c.getDateHeureDebut().toLocalDate().equals(debut.toLocalDate())
                        && c.getDateHeureDebut().toLocalTime().equals(debut.toLocalTime()));

        if (slotExists) {
            response.sendRedirect(request.getContextPath() + "/medecin/tele-expertise?patientId=" + patientId);
            return;
        }

        Creneau creneau = new Creneau();
        creneau.setMedecinSpecialiste(specialistOpt.get());
        creneau.setDateHeureDebut(debut);
        creneau.setDateHeureFin(fin);
        creneau.setStatut(StatutCreneau.RESERVE);
        creneau = creneauDAO.save(creneau);

        Consultation consultation = new Consultation();
        consultation.setPatient(patientOpt.get());
        consultation.setDateConsultation(LocalDateTime.now());
        consultation.setMotif("Demande d'avis spécialiste");
        consultation.setStatut(StatutConsultation.EN_ATTENTE_AVIS_SPECIALISTE);
        consultationDAO = new ma.youcode.dao.impl.ConsultationDAOImpl();
        consultationDAO.save(consultation);

        DemandeTeleExpertise demande = new DemandeTeleExpertise();
        demande.setDateDemande(LocalDateTime.now());
        demande.setQuestion(question);
        demande.setDonneesAnalyses("Consultation de suivi pour le patient " + patientOpt.get().getNom() + " "
                + patientOpt.get().getPrenom());
        demande.setPriorite(PrioriteExpertise.valueOf(prioriteParam != null ? prioriteParam : "NORMALE"));
        demande.setStatut(StatutExpertise.EN_ATTENTE);
        demande.setConsultation(consultation);
        demande.setMedecinSpecialiste(specialistOpt.get());
        demande.setCreneau(creneau);

        demandeTeleExpertiseDAO.save(demande);

        response.sendRedirect(request.getContextPath() + "/medecin/dossier/" + patientId);
    }

    private Map<String, String> buildSpecialiteLibelles() {
        Map<String, String> libelles = new HashMap<>();
        libelles.put("CARDIOLOGIE", "Cardiologie");
        libelles.put("PNEUMOLOGIE", "Pneumologie");
        libelles.put("NEUROLOGIE", "Neurologie");
        libelles.put("GASTRO_ENTEROLOGIE", "Gastro-entérologie");
        libelles.put("ENDOCRINOLOGIE", "Endocrinologie");
        libelles.put("DERMATOLOGIE", "Dermatologie");
        libelles.put("RHUMATOLOGIE", "Rhumatologie");
        libelles.put("PSYCHIATRIE", "Psychiatrie");
        libelles.put("NEPHROLOGIE", "Néphrologie");
        libelles.put("ORTHOPEDIE", "Orthopédie");
        return libelles;
    }

    private List<String> buildHeuresJour() {
        List<String> heures = new ArrayList<>();
        for (int heure = 0; heure < 24; heure++) {
            heures.add(String.format("%02d:00", heure));
        }
        return heures;
    }
}
