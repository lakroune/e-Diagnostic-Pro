package ma.youcode.service;

import java.time.LocalDateTime;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import ma.youcode.dao.PatientDAO;
import ma.youcode.dao.impl.PatientDAOImpl;
import ma.youcode.model.Patient;

public class PatientService {

    private final PatientDAO patientDAO = new PatientDAOImpl();
    @PersistenceContext
    private EntityManager em;

    public Patient create(Patient patient) {
        patient.setDateEnregistrement(LocalDateTime.now());
        em.persist(patient);
        return patient;
    }

    public Patient update(Long id, Patient patientDetails) {
        Patient exist = em.find(Patient.class, id);
        if (exist == null) {
            throw new EntityNotFoundException("Patient non trouvé avec l'ID : " + id);
        }

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

        return em.merge(exist);
    }

    public void delete(Long id) {
        Patient patient = em.find(Patient.class, id);
        if (patient != null) {
            em.remove(patient);
        }
    }

}