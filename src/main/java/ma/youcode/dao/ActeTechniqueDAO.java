package ma.youcode.dao;

import java.util.List;
import java.util.Optional;

import ma.youcode.model.ActeTechnique;

public interface ActeTechniqueDAO {

    ActeTechnique save(ActeTechnique a);

    Optional<ActeTechnique> findById(Long id);

    ActeTechnique update(ActeTechnique ActeTechnique);

    void delete(ActeTechnique ActeTechnique);

    List<ActeTechnique> findAll();
}
