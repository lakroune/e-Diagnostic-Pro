package ma.youcode.service;

import java.time.LocalDateTime;
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
        patient.setDateEnregistrement(LocalDateTime.now());
        return patientDAO.save(patient);
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
        exist.setEnAttente(patientDetails.isEnAttente());

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
}