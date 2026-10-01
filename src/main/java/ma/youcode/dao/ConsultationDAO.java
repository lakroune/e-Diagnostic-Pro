package ma.youcode.dao;

import java.util.List;
import java.util.Optional;

import ma.youcode.model.Consultation;

public interface ConsultationDAO {

    Consultation save(Consultation a);

    Optional<Consultation> findById(Long id);

    Consultation update(Consultation Consultation);

    void delete(Consultation Consultation);

    List<Consultation> findAll();
}
