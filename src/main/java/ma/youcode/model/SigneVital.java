package ma.youcode.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "signes_vitaux")
public class SigneVital implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tensionArterielle;

    private Integer frequenceCardiaque;

    private Double temperatureCorporelle;

    private Integer frequenceRespiratoire;

    private Double poidsKg;

    private Double tailleCm;

    private LocalDateTime datePrise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "infirmier_id")
    private Infirmier infirmier;

    public SigneVital() {
    }

    public SigneVital(Long id, String tensionArterielle, Integer frequenceCardiaque,
            Double temperatureCorporelle, Integer frequenceRespiratoire,
            Double poidsKg, Double tailleCm, LocalDateTime datePrise) {
        this.id = id;
        this.tensionArterielle = tensionArterielle;
        this.frequenceCardiaque = frequenceCardiaque;
        this.temperatureCorporelle = temperatureCorporelle;
        this.frequenceRespiratoire = frequenceRespiratoire;
        this.poidsKg = poidsKg;
        this.tailleCm = tailleCm;
        this.datePrise = datePrise;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTensionArterielle() {
        return tensionArterielle;
    }

    public void setTensionArterielle(String tensionArterielle) {
        this.tensionArterielle = tensionArterielle;
    }

    public Integer getFrequenceCardiaque() {
        return frequenceCardiaque;
    }

    public void setFrequenceCardiaque(Integer frequenceCardiaque) {
        this.frequenceCardiaque = frequenceCardiaque;
    }

    public Double getTemperatureCorporelle() {
        return temperatureCorporelle;
    }

    public void setTemperatureCorporelle(Double temperatureCorporelle) {
        this.temperatureCorporelle = temperatureCorporelle;
    }

    public Integer getFrequenceRespiratoire() {
        return frequenceRespiratoire;
    }

    public void setFrequenceRespiratoire(Integer frequenceRespiratoire) {
        this.frequenceRespiratoire = frequenceRespiratoire;
    }

    public Double getPoidsKg() {
        return poidsKg;
    }

    public void setPoidsKg(Double poidsKg) {
        this.poidsKg = poidsKg;
    }

    public Double getTailleCm() {
        return tailleCm;
    }

    public void setTailleCm(Double tailleCm) {
        this.tailleCm = tailleCm;
    }

    public LocalDateTime getDatePrise() {
        return datePrise;
    }

    public void setDatePrise(LocalDateTime datePrise) {
        this.datePrise = datePrise;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Infirmier getInfirmier() {
        return infirmier;
    }

    public void setInfirmier(Infirmier infirmier) {
        this.infirmier = infirmier;
    }
}