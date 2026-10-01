package ma.youcode.dao;

import java.util.List;
import java.util.Optional;

import ma.youcode.model.Creneau;

public interface CreneauDAO {

    Creneau save(Creneau a);

    Optional<Creneau> findById(Long id);

    Creneau update(Creneau Creneau);

    void delete(Creneau Creneau);

    List<Creneau> findAll();
}
