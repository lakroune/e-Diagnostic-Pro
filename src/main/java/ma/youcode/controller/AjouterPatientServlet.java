package ma.youcode.controller;

import java.io.IOException;
import java.time.LocalDate;
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

@WebServlet("/infirmier/patients/ajouter")
public class AjouterPatientServlet extends HttpServlet {

    private PatientService patientService;
    private SigneVitalService signeVitalService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.patientService = new PatientService();
        this.signeVitalService = new SigneVitalService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String numeroSecuriteSociale = request.getParameter("numSecuriteSociale");
        if (numeroSecuriteSociale != null && !numeroSecuriteSociale.isBlank()) {
            Optional<Patient> patient = patientService.findByNumSecuriteSociale(numeroSecuriteSociale);
            if (patient.isPresent()) {
                request.setAttribute("patient", patient.get());
                request.setAttribute("patientFound", true);
            } else {
                request.setAttribute("patientFound", false);
            }
        }

        request.getRequestDispatcher("/WEB-INF/views/infirmier/ajouter-patient.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String numSecu = request.getParameter("numSecuriteSociale");
        Optional<Patient> existingPatient = patientService.findByNumSecuriteSociale(numSecu);

        if ("add-to-waiting".equals(action) && existingPatient.isPresent()) {
            Patient patient = existingPatient.get();
            patientService.addToWaitingList(patient);
            redirectToPatientDetails(request, response, patient);
            return;
        }

        Patient patient = existingPatient.orElseGet(() -> createPatientFromRequest(request, numSecu));

        if (existingPatient.isPresent()) {
            patientService.addToWaitingList(patient);
        }

        SigneVital signeVital = buildSigneVital(request);
        if (signeVital != null) {
            saveSigneVital(patient, request, signeVital);
        }

        redirectToPatientDetails(request, response, patient);
    }

    private Patient createPatientFromRequest(HttpServletRequest request, String numSecu) {
        Patient patient = new Patient();
        patient.setNom(request.getParameter("nom"));
        patient.setPrenom(request.getParameter("prenom"));
        patient.setDateNaissance(parseDate(request.getParameter("dateNaissance")));
        patient.setNumSecuriteSociale(numSecu);
        patient.setTelephone(request.getParameter("telephone"));
        patient.setAdresse(request.getParameter("adresse"));
        patient.setMutuelle(request.getParameter("mutuelle"));
        patient.setAntecedents(request.getParameter("antecedents"));
        patient.setAllergies(request.getParameter("allergies"));
        patient.setTraitementsEnCours(request.getParameter("traitementsEnCours"));
        return patientService.create(patient);
    }

    private void saveSigneVital(Patient patient, HttpServletRequest request, SigneVital signeVital) {
        signeVital.setPatient(patient);
        Infirmier infirmierConnecte = (Infirmier) request.getSession().getAttribute("infirmier");
        signeVital.setInfirmier(infirmierConnecte);
        signeVital.setDatePrise(LocalDateTime.now());
        signeVitalService.create(signeVital);
    }

    private void redirectToPatientDetails(HttpServletRequest request, HttpServletResponse response, Patient patient)
            throws IOException {
        response.sendRedirect(request.getContextPath() + "/infirmier/patients/" + patient.getId());
    }

    private SigneVital buildSigneVital(HttpServletRequest request) {
        String tension = request.getParameter("tensionArterielle");
        String frequenceCardiaque = request.getParameter("frequenceCardiaque");
        String temperature = request.getParameter("temperatureCorporelle");
        String frequenceResp = request.getParameter("frequenceRespiratoire");
        String poids = request.getParameter("poidsKg");
        String taille = request.getParameter("tailleCm");

        boolean hasAny = (tension != null && !tension.isBlank())
                || (frequenceCardiaque != null && !frequenceCardiaque.isBlank())
                || (temperature != null && !temperature.isBlank())
                || (frequenceResp != null && !frequenceResp.isBlank())
                || (poids != null && !poids.isBlank())
                || (taille != null && !taille.isBlank());

        if (!hasAny) {
            return null;
        }

        SigneVital sv = new SigneVital();
        sv.setTensionArterielle(tension);
        sv.setFrequenceCardiaque(parseInteger(frequenceCardiaque));
        sv.setTemperatureCorporelle(parseDouble(temperature));
        sv.setFrequenceRespiratoire(parseInteger(frequenceResp));
        sv.setPoidsKg(parseDouble(poids));
        sv.setTailleCm(parseDouble(taille));
        return sv;
    }

    private LocalDate parseDate(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return LocalDate.parse(value);
    }

    private Integer parseInteger(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return Integer.parseInt(value);
    }

    private Double parseDouble(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return Double.parseDouble(value);
    }
}