package ma.youcode.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;
import ma.youcode.dao.PatientDAO;
import ma.youcode.dao.impl.PatientDAOImpl;
import ma.youcode.model.Patient;

public class PatientService {

    private final PatientDAO patientDAO;

    public PatientService() {
        this.patientDAO = new PatientDAOImpl();
    }

    public PatientService(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    public Patient create(Patient patient) {
        return addToWaitingList(patient);
    }

    public Patient addToWaitingList(Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient ne peut pas être null");
        }
        if (patient.getDateEnregistrement() == null) {
            patient.setDateEnregistrement(LocalDateTime.now());
        }

        if (patient.getId() == null) {
            return patientDAO.save(patient);
        }
        return patientDAO.update(patient);
    }

    public Optional<Patient> findByNumSecuriteSociale(String numSecuriteSociale) {
        if (numSecuriteSociale == null || numSecuriteSociale.isBlank()) {
            return Optional.empty();
        }
        return patientDAO.findByNumSecuriteSociale(numSecuriteSociale.trim());
    }

    public Patient update(Long id, Patient patientDetails) {
        Patient exist = patientDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient non trouve : " + id));

        exist.setNom(patientDetails.getNom());
        exist.setPrenom(patientDetails.getPrenom());
        exist.setDateNaissance(patientDetails.getDateNaissance());
        exist.setNumSecuriteSociale(patientDetails.getNumSecuriteSociale());
        exist.setTelephone(patientDetails.getTelephone());
        exist.setAdresse(patientDetails.getAdresse());
        exist.setMutuelle(patientDetails.getMutuelle());
        exist.setAntecedents(patientDetails.getAntecedents());
        exist.setAllergies(patientDetails.getAllergies());
        exist.setTraitementsEnCours(patientDetails.getTraitementsEnCours());

        return patientDAO.update(exist);
    }

    public Optional<Patient> findById(long id) {
        return patientDAO.findById(id);

    }

    public boolean delete(Long id) {
        Patient patient = patientDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient non trouvé avec l'ID : " + id));
        return patientDAO.delete(patient);
    }

    public List<Patient> findAll() {
        return patientDAO.findAll();
    }

    public List<Patient> getListAttente() {

        return patientDAO.getListAttente();
    }

    public List<Patient> getPatientsDuJour() {
        LocalDate today = LocalDate.now();

        return patientDAO.findAll().stream()
                .filter(Objects::nonNull)
                .filter(patient -> patient.getDateEnregistrement() != null
                        && patient.getDateEnregistrement().toLocalDate().equals(today))
                .sorted(Comparator.comparing(Patient::getDateEnregistrement))
                .toList();
    }
}