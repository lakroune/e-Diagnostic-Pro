package ma.youcode.service;

import java.util.Optional;

import ma.youcode.dao.AuthDAO;
import ma.youcode.model.Utilisateur;

public class AuthService {
    private final AuthDAO authDAO = new AuthDAO();

    public Utilisateur register(Utilisateur user) throws Exception {
        if (authDAO.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email is already registered!");
        }

        user.setActif(true);

        return authDAO.save(user);
    }

    public Utilisateur login(String email, String password) throws Exception {
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("email et  motpass  obligatoire.");
        }

        Optional<Utilisateur> userOpt = authDAO.login(email, password);

        if (userOpt.isPresent()) {
            return userOpt.get();
        } else {
            throw new Exception("Identifiants incorrects .");
        }
    }
}
