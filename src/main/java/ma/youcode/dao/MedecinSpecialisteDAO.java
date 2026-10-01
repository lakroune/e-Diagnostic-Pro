package ma.youcode.dao;

import java.util.List;
import java.util.Optional;

import ma.youcode.model.MedecinSpecialiste;

public interface MedecinSpecialisteDAO {

    MedecinSpecialiste save(MedecinSpecialiste a);

    Optional<MedecinSpecialiste> findById(Long id);

    MedecinSpecialiste update(MedecinSpecialiste i);

    void delete(MedecinSpecialiste i);

    List<MedecinSpecialiste> findAll();
}
