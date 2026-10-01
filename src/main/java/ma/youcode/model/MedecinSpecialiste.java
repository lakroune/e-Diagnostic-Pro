package ma.youcode.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import ma.youcode.model.enums.Role;
import ma.youcode.model.enums.SpecialiteMedicale;

@Entity 
@Table(name = "medecins_specialistes")
@PrimaryKeyJoinColumn(name = "id")
public class MedecinSpecialiste extends Utilisateur {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SpecialiteMedicale specialite;

    private Double tarifExpertise;

    private Integer dureeConsultationMin = 30;

    @OneToMany(mappedBy = "medecinSpecialiste", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DemandeTeleExpertise> demandesTraitees = new ArrayList<>();

    @OneToMany(mappedBy = "medecinSpecialiste", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Creneau> creneaux = new ArrayList<>();

    public MedecinSpecialiste() {
        super();
        this.setRole(Role.SPECIALISTE);
    }

    public MedecinSpecialiste(Long id, String nom, String prenom, String email, String motDePasse,
            String telephone, boolean actif, SpecialiteMedicale specialite,
            Double tarifExpertise, Integer dureeConsultationMin) {
        super(id, nom, prenom, email, motDePasse, telephone, Role.SPECIALISTE, actif);
        this.specialite = specialite;
        this.tarifExpertise = tarifExpertise;
        this.dureeConsultationMin = (dureeConsultationMin != null) ? dureeConsultationMin : 30;
    }

    public SpecialiteMedicale getSpecialite() {
        return specialite;
    }

    public void setSpecialite(SpecialiteMedicale specialite) {
        this.specialite = specialite;
    }

    public Double getTarifExpertise() {
        return tarifExpertise;
    }

    public void setTarifExpertise(Double tarifExpertise) {
        this.tarifExpertise = tarifExpertise;
    }

    public Integer getDureeConsultationMin() {
        return dureeConsultationMin;
    }

    public void setDureeConsultationMin(Integer dureeConsultationMin) {
        this.dureeConsultationMin = dureeConsultationMin;
    }

    public List<DemandeTeleExpertise> getDemandesTraitees() {
        return demandesTraitees;
    }

    public void setDemandesTraitees(List<DemandeTeleExpertise> demandesTraitees) {
        this.demandesTraitees = demandesTraitees;
    }

    public List<Creneau> getCreneaux() {
        return creneaux;
    }

    public void setCreneaux(List<Creneau> creneaux) {
        this.creneaux = creneaux;
    }

    public void addDemandeTraitee(DemandeTeleExpertise demande) {
        demandesTraitees.add(demande);
        demande.setMedecinSpecialiste(this);
    }

    public void removeDemandeTraitee(DemandeTeleExpertise demande) {
        demandesTraitees.remove(demande);
        demande.setMedecinSpecialiste(null);
    }

    public void addCreneau(Creneau creneau) {
        creneaux.add(creneau);
        creneau.setMedecinSpecialiste(this);
    }

    public void removeCreneau(Creneau creneau) {
        creneaux.remove(creneau);
        creneau.setMedecinSpecialiste(null);
    }
}