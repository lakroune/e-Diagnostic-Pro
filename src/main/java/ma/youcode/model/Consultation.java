package ma.youcode.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import ma.youcode.model.enums.StatutConsultation;

@Entity
@Table(name = "consultations")
public class Consultation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateConsultation;

    private String motif;

    @Column(columnDefinition = "TEXT")
    private String examenClinique;

    @Column(columnDefinition = "TEXT")
    private String observations;

    @Column(columnDefinition = "TEXT")
    private String diagnostic;

    @Column(columnDefinition = "TEXT")
    private String ordonnance;

    @Enumerated(EnumType.STRING)
    private StatutConsultation statut;

    private Double coutBase = 150.0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medecin_generaliste_id")
    private MedecinGeneraliste medecinGeneraliste;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "consultation_id")
    private List<ActeTechnique> actesTechniques = new ArrayList<>();

    @OneToOne(mappedBy = "consultation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private DemandeTeleExpertise demandeTeleExpertise;

    public Consultation() {
    }

    public Consultation(Long id, LocalDateTime dateConsultation, String motif, String examenClinique,
            String observations, String diagnostic, String ordonnance,
            StatutConsultation statut, Double coutBase) {
        this.id = id;
        this.dateConsultation = dateConsultation;
        this.motif = motif;
        this.examenClinique = examenClinique;
        this.observations = observations;
        this.diagnostic = diagnostic;
        this.ordonnance = ordonnance;
        this.statut = statut;
        this.coutBase = (coutBase != null) ? coutBase : 150.0;
    }

    
    public Double calculerCoutTotal() {
        double total = (coutBase != null) ? coutBase : 150.0;
        if (actesTechniques != null) {
            for (ActeTechnique acte : actesTechniques) {
                if (acte != null && acte.getTarif() != null) {
                    total += acte.getTarif();
                }
            }
        }
        return total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateConsultation() {
        return dateConsultation;
    }

    public void setDateConsultation(LocalDateTime dateConsultation) {
        this.dateConsultation = dateConsultation;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getExamenClinique() {
        return examenClinique;
    }

    public void setExamenClinique(String examenClinique) {
        this.examenClinique = examenClinique;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getOrdonnance() {
        return ordonnance;
    }

    public void setOrdonnance(String ordonnance) {
        this.ordonnance = ordonnance;
    }

    public StatutConsultation getStatut() {
        return statut;
    }

    public void setStatut(StatutConsultation statut) {
        this.statut = statut;
    }

    public Double getCoutBase() {
        return coutBase;
    }

    public void setCoutBase(Double coutBase) {
        this.coutBase = coutBase;
    }

    public MedecinGeneraliste getMedecinGeneraliste() {
        return medecinGeneraliste;
    }

    public void setMedecinGeneraliste(MedecinGeneraliste medecinGeneraliste) {
        this.medecinGeneraliste = medecinGeneraliste;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<ActeTechnique> getActesTechniques() {
        return actesTechniques;
    }

    public void setActesTechniques(List<ActeTechnique> actesTechniques) {
        this.actesTechniques = actesTechniques;
    }

    public DemandeTeleExpertise getDemandeTeleExpertise() {
        return demandeTeleExpertise;
    }

    public void setDemandeTeleExpertise(DemandeTeleExpertise demandeTeleExpertise) {
        this.demandeTeleExpertise = demandeTeleExpertise;
    }

    public void addActeTechnique(ActeTechnique acte) {
        this.actesTechniques.add(acte);
    }

    public void removeActeTechnique(ActeTechnique acte) {
        this.actesTechniques.remove(acte);
    }
}