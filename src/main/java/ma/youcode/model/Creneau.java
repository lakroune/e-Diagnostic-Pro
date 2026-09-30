package ma.youcode.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import ma.youcode.model.enums.StatutCreneau;

@Entity
@Table(name = "creneaux")
public class Creneau implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateHeureDebut;

    private LocalDateTime dateHeureFin;

    @Enumerated(EnumType.STRING)
    private StatutCreneau statut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medecin_specialiste_id")
    private MedecinSpecialiste medecinSpecialiste;

    @OneToOne(mappedBy = "creneau", fetch = FetchType.LAZY)
    private DemandeTeleExpertise demandeTeleExpertise;

    public Creneau() {
    }

    public Creneau(Long id, LocalDateTime dateHeureDebut, LocalDateTime dateHeureFin, StatutCreneau statut) {
        this.id = id;
        this.dateHeureDebut = dateHeureDebut;
        this.dateHeureFin = dateHeureFin;
        this.statut = statut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateHeureDebut() {
        return dateHeureDebut;
    }

    public void setDateHeureDebut(LocalDateTime dateHeureDebut) {
        this.dateHeureDebut = dateHeureDebut;
    }

    public LocalDateTime getDateHeureFin() {
        return dateHeureFin;
    }

    public void setDateHeureFin(LocalDateTime dateHeureFin) {
        this.dateHeureFin = dateHeureFin;
    }

    public StatutCreneau getStatut() {
        return statut;
    }

    public void setStatut(StatutCreneau statut) {
        this.statut = statut;
    }

    public MedecinSpecialiste getMedecinSpecialiste() {
        return medecinSpecialiste;
    }

    public void setMedecinSpecialiste(MedecinSpecialiste medecinSpecialiste) {
        this.medecinSpecialiste = medecinSpecialiste;
    }

    public DemandeTeleExpertise getDemandeTeleExpertise() {
        return demandeTeleExpertise;
    }

    public void setDemandeTeleExpertise(DemandeTeleExpertise demandeTeleExpertise) {
        this.demandeTeleExpertise = demandeTeleExpertise;
    }
}