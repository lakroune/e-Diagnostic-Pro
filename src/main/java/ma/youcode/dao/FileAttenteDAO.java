package ma.youcode.dao;

import java.util.List;
import java.util.Optional;

import ma.youcode.model.FileAttente;

public interface FileAttenteDAO {

    FileAttente save(FileAttente fileAttente);

    Optional<FileAttente> findById(Long id);

    List<FileAttente> findAll();

    List<FileAttente> findByPatientId(Long patientId);

    FileAttente update(FileAttente fileAttente);

    boolean delete(FileAttente fileAttente);
}
