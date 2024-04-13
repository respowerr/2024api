package fr.callidos.account.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "camions", uniqueConstraints = { @UniqueConstraint(columnNames = "plaque_immatriculation")})
public class CamionsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plaque_immatriculation")
    @Size(min = 9, max = 10)
    @NotBlank
    private String plaqueImmatriculation;

    @Column(name = "capacite")
    @NotBlank
    private int capacite;

    @Column(name = "tournee_id")
    private Long tourneeId;

    public CamionsModel() {
    }

    public CamionsModel(String plaqueImmatriculation, int capacite, Long tourneeId) {
        this.plaqueImmatriculation = plaqueImmatriculation;
        this.capacite = capacite;
        this.tourneeId = tourneeId;
    }

    public Long getId() {
        return id;
    }

    public String getPlaqueImmatriculation() {
        return plaqueImmatriculation;
    }

    public void setPlaqueImmatriculation(String plaqueImmatriculation) {
        this.plaqueImmatriculation = plaqueImmatriculation;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public Long getTourneeId() {
        return tourneeId;
    }

    public void setTourneeId(Long tourneeId) {
        this.tourneeId = tourneeId;
    }

}
