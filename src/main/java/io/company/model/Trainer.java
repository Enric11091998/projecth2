package io.company.model;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
@Entity(name="Trainer")
@Table(name="TRAINER")
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="TRAINER_ID")
    private long trainerId;
    @Column(name="NAME")
    String name;
    @Column(name="JOB")
    String job;
    @Column(name="NUMBEROFPOKEMONS")
    int numberOfPokemons;
    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL)
    List<Pokemon> pokemons;

    public Trainer(String name, String job, int numberOfPokemons) {
        this.name = name;
        this.job = job;
        this.numberOfPokemons = numberOfPokemons;

    }

    public Trainer(String name, String job, int numberOfPokemons, List<Pokemon> pokemons) {
        this.trainerId = trainerId;
        this.name = name;
        this.job = job;
        this.numberOfPokemons = numberOfPokemons;
        this.pokemons = pokemons;
    }
}


