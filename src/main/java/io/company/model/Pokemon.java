package io.company.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Optional;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
@Entity(name="Pokemon")
@Table(name="POKEMON")
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="POKEMON_ID")
    private long pokemonId;
    @Column(name="POKEMON_NAME")
    String Name;
    @Column(name="POKEMON_LOCATION")
    String location;
    @Column(name="POKEMON_NUMBER")
    int number;
    @Column(name="POKEMON_HIGH")
    int high;
    @Column(name="POKEMON_KG")
    int kg;
    @Column(name="POKEMON_USE")
    String use;
    @ManyToOne
    @JoinColumn(name = "trainer_trainer_id")
    Trainer trainer;

    public Pokemon(String name, String location, int numberBetween, int numberBetween1, int numberBetween2, String use, Trainer trainer) {
        this.Name = name;
        this.location = location;
        this.number = numberBetween;
        this.high = numberBetween1;
        this.kg = numberBetween2;
        this.use = use;
        this.trainer = trainer;
    }

    public Pokemon(String name, String location, int numberBetween, int numberBetween1, int numberBetween2, String use) {
        this.Name = name;
        this.location = location;
        this.number = numberBetween;
        this.high = numberBetween1;
        this.kg = numberBetween2;
        this.use = use;

    }

    public Pokemon(Long pokemonId, String name, String location, int numberBetween, int numberBetween1, int numberBetween2, String use) {
        this.pokemonId = pokemonId;
        this.Name = name;
        this.location = location;
        this.number = numberBetween;
        this.high = numberBetween1;
        this.kg = numberBetween2;
        this.use = use;
    }

}
