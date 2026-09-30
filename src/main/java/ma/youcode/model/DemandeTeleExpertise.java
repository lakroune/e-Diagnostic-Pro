package ma.youcode.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import ma.youcode.model.enums.PrioriteExpertise;
import ma.youcode.model.enums.StatutExpertise;

@Entity
@Table(name = "demandes_tele_expertise")
public class DemandeTeleExpertise implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateDemande;

    @Column(columnDefinition = "TEXT")
    private String question;

    @Column(columnDefinition = "TEXT")
    private String donneesAnalyses;

    @Enumerated(EnumType.STRING)
    private PrioriteExpertise priorite;

    @Enumerated(EnumType.STRING)
    private StatutExpertise statut;

    @Column(columnDefinition = "TEXT")
    private String avisExpert;

    @Column(columnDefinition = "TEXT")
    private String recommandations;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medecin_generaliste_id")
    private MedecinGeneraliste medecinGeneraliste;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultation_id")
    private Consultation consultation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medecin_specialiste_id")
    private MedecinSpecialiste medecinSpecialiste;

    // Relation : une demande peut réserver un Creneau
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creneau_id")
    private Creneau creneau;

    // Constructeur sans arguments (requis par JPA)
    public DemandeTeleExpertise() {
    }

    public DemandeTeleExpertise(Long id, LocalDateTime dateDemande, String question,
            String donneesAnalyses, PrioriteExpertise priorite,
            StatutExpertise statut, String avisExpert, String recommandations) {
        this.id = id;
        this.dateDemande = dateDemande;
        this.question = question;
        this.donneesAnalyses = donneesAnalyses;
        this.priorite = priorite;
        this.statut = statut;
        this.avisExpert = avisExpert;
        this.recommandations = recommandations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(LocalDateTime dateDemande) {
        this.dateDemande = dateDemande;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDonneesAnalyses() {
        return donneesAnalyses;
    }

    public void setDonneesAnalyses(String donneesAnalyses) {
        this.donneesAnalyses = donneesAnalyses;
    }

    public PrioriteExpertise getPriorite() {
        return priorite;
    }

    public void setPriorite(PrioriteExpertise priorite) {
        this.priorite = priorite;
    }

    public StatutExpertise getStatut() {
        return statut;
    }

    public void setStatut(StatutExpertise statut) {
        this.statut = statut;
    }

    public String getAvisExpert() {
        return avisExpert;
    }

    public void setAvisExpert(String avisExpert) {
        this.avisExpert = avisExpert;
    }

    public String getRecommandations() {
        return recommandations;
    }

    public void setRecommandations(String recommandations) {
        this.recommandations = recommandations;
    }

    public MedecinGeneraliste getMedecinGeneraliste() {
        return medecinGeneraliste;
    }

    public void setMedecinGeneraliste(MedecinGeneraliste medecinGeneraliste) {
        this.medecinGeneraliste = medecinGeneraliste;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    public MedecinSpecialiste getMedecinSpecialiste() {
        return medecinSpecialiste;
    }

    public void setMedecinSpecialiste(MedecinSpecialiste medecinSpecialiste) {
        this.medecinSpecialiste = medecinSpecialiste;
    }

    public Creneau getCreneau() {
        return creneau;
    }

    public void setCreneau(Creneau creneau) {
        this.creneau = creneau;
    }
}