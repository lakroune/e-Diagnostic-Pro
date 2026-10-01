package ma.youcode.dao;

import java.util.List;
import java.util.Optional;

import ma.youcode.model.SigneVital;

public interface SigneVitalDAO {

    SigneVital save(SigneVital a);

    Optional<SigneVital> findById(Long id);

    SigneVital update(SigneVital i);

    boolean delete(SigneVital i);

    List<SigneVital> findAll();
}
