package ma.youcode.dao;

import java.util.List;
import java.util.Optional;

import ma.youcode.model.Patient;

public interface PatientDAO {
    Patient save(Patient patient);

    Optional<Patient> findById(Long id);

    Optional<Patient> findByNumSecuriteSociale(String numSecuriteSociale);

    Patient update(Patient patient);

    boolean delete(Patient patient);

    List<Patient> findAll();

    List<Patient> getListAttente();
}