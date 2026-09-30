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
@Table(name = "infirmiers")
@PrimaryKeyJoinColumn(name = "id")
public class Infirmier extends Utilisateur {

    @Column(nullable = false, unique = true)
    private String matriculePro;

    @OneToMany(mappedBy = "infirmier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SigneVital> signesVitaux = new ArrayList<>();

    public Infirmier() {
        super();
        this.setRole(Role.INFIRMIER);
    }

    public Infirmier(Long id, String nom, String prenom, String email, String motDePasse,
            String telephone, boolean actif, String matriculePro) {
        super(id, nom, prenom, email, motDePasse, telephone, Role.INFIRMIER, actif);
        this.matriculePro = matriculePro;
    }

    public String getMatriculePro() {
        return matriculePro;
    }

    public void setMatriculePro(String matriculePro) {
        this.matriculePro = matriculePro;
    }

    public List<SigneVital> getSignesVitaux() {
        return signesVitaux;
    }

    public void setSignesVitaux(List<SigneVital> signesVitaux) {
        this.signesVitaux = signesVitaux;
    }

    public void addSigneVital(SigneVital signeVital) {
        signesVitaux.add(signeVital);
        signeVital.setInfirmier(this);
    }

    public void removeSigneVital(SigneVital signeVital) {
        signesVitaux.remove(signeVital);
        signeVital.setInfirmier(null);
    }
}