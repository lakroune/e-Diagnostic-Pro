package ma.youcode.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import ma.youcode.model.enums.Role;

@Entity
@Table(name = "medecins_generalistes")
@PrimaryKeyJoinColumn(name = "id")
public class MedecinGeneraliste extends Utilisateur {

    @Column(nullable = false, unique = true)
    private String matriculeOrdre;

    @OneToMany(mappedBy = "medecinGeneraliste", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Consultation> consultations = new ArrayList<>();

    public MedecinGeneraliste() {
        super();
        this.setRole(Role.GENERALISTE);
    }

    public MedecinGeneraliste(Long id, String nom, String prenom, String email, String motDePasse,
            String telephone, boolean actif, String matriculeOrdre) {
        super(id, nom, prenom, email, motDePasse, telephone, Role.GENERALISTE, actif);
        this.matriculeOrdre = matriculeOrdre;
    }

    public String getMatriculeOrdre() {
        return matriculeOrdre;
    }

    public void setMatriculeOrdre(String matriculeOrdre) {
        this.matriculeOrdre = matriculeOrdre;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }

    public void addConsultation(Consultation consultation) {
        consultations.add(consultation);
        consultation.setMedecinGeneraliste(this);
    }

    public void removeConsultation(Consultation consultation) {
        consultations.remove(consultation);
        consultation.setMedecinGeneraliste(null);
    }

}