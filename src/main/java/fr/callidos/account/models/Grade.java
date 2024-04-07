package fr.callidos.account.models;

import jakarta.persistence.*;

@Entity
@Table(name = "grades")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Egrades name;

    public Grade(){}

    public Grade(Egrades name){
        this.name = name;
    }
}
