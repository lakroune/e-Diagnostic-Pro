package ma.youcode.dao;

import java.util.List;
import java.util.Optional;

import ma.youcode.model.Utilisateur;

public interface UtilisateurDAO {

    Utilisateur save(Utilisateur a);

    Optional<Utilisateur> findById(Long id);

    Utilisateur update(Utilisateur i);

    boolean delete(Utilisateur i);

    List<Utilisateur> findAll();
}
