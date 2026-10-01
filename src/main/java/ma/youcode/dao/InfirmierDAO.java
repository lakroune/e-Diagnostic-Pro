package ma.youcode.dao;

import java.util.List;
import java.util.Optional;

import ma.youcode.model.Infirmier;

public interface InfirmierDAO {

    Infirmier save(Infirmier a);

    Optional<Infirmier> findById(Long id);

    Infirmier update(Infirmier i);

    boolean delete(Infirmier i);

    List<Infirmier> findAll();
}
