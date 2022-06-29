package io.company;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@Service
public class PokemonService {

    @Autowired
    Repository repository;

    public Iterable<Pokemon> getAllPokemons() {

        return repository.findAll();
    }

    public void fakerPokemon() {

        Random random = new Random();
        Pokemon pokemon1 = new Pokemon("paradise", "AmericanExpress",1, 300, 500, "Boing3412");
        Pokemon pokemon2 = new Pokemon("hellfire", "AmericanExpress",2,  85, 7, "charger");
        Pokemon pokemon3 = new Pokemon("ribombee", "AmericanExpress",3,   146, 23, "combat");


        createPokemon(pokemon1);
        createPokemon(pokemon2);
        createPokemon(pokemon3);
        Faker faker = new Faker();
        int number;
        String use = null;
        for (int i = 0; i <1000 ; i++ ){
            number = random.nextInt(1, 3);
            switch (number){
                case 1:{
                    use = "combat";
                    break;
                }
                case 2: {
                    use = "contest";
                    break;
                }
                case 3:{
                    use = "walk";
                    break;
                }
            }
            createPokemon(new Pokemon (faker.pokemon().name(), faker.pokemon().location(), faker.number().numberBetween(1, 856),
                    faker.number().numberBetween(5, 1200),faker.number().numberBetween(2, 12000),
                    use));
        }

    }
    public void createPokemon (Pokemon pokemon){

        repository.save(pokemon);
    }

    public Optional<Pokemon> findPokemonById(String id){

        return repository.findById(id);
    }

    public Optional<Pokemon> findPokemonByNumber(int number){
        return repository.findPokemonByNumber(number);
    }

    public void deletePokemonByNumber(int number){
        if(findPokemonByNumber(number).isPresent()){
            Optional<Pokemon> deletedPokemon = repository.deleteByNumber(number);
        }
    }

    public void deletePokemonById(String id){
        if(findPokemonById(id).isPresent()){
//            Optional<Pokemon> deletedPokemon = repository.deleteById(id);
        }
    }

    public Pokemon updatePokemon (Pokemon pokemon){



        return repository.save(pokemon);
    }

}
