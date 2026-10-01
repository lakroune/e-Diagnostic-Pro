package ma.youcode.dao;

import java.util.List;
import java.util.Optional;

import ma.youcode.model.MedecinGeneraliste;

public interface MedecinGeneralisteDAO {

    MedecinGeneraliste save(MedecinGeneraliste a);

    Optional<MedecinGeneraliste> findById(Long id);

    MedecinGeneraliste update(MedecinGeneraliste m);

    boolean delete(MedecinGeneraliste m);

    List<MedecinGeneraliste> findAll();
}
