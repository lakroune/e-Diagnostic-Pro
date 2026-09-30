package ma.youcode.dao;

import ma.youcode.model.Patient;
import java.util.List;

public interface PatientDAO {
    void save(Patient patient);

    List<Patient> findAll();
}