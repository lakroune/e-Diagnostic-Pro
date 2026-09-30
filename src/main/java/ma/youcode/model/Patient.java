package ma.youcode.model;

import jakarta.persistence.*;

@Entity
@Table(name = "patients")
@PrimaryKeyJoinColumn(name = "personne_id")
public class Patient extends Personne {

    private String telephone;

    public Patient() {
        super();
    }

    public Patient(String nom, String prenom, String telephone) {
        super(nom, prenom);
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}