package io.company;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PokemonService {

    @Autowired
    Repository repository;

    static List<Pokemon> pokemons = new ArrayList<Pokemon>();
    static {
        Pokemon pokemon1 = new Pokemon("paradise", "AmericanExpress",1, 300, 500, "Boing3412");
        Pokemon pokemon2 = new Pokemon("hellfire", "AmericanExpress",2,  85, 7, "charger");
        Pokemon pokemon3 = new Pokemon("ribombee", "AmericanExpress",3,   146, 23, "combat");


        pokemons.add(pokemon1);
        pokemons.add(pokemon2);
        pokemons.add(pokemon3);

        Faker faker = new Faker();

        for (int i = 0; i <1000 ; i++ ){
            pokemons.add(new Pokemon (faker.pokemon().name(), faker.pokemon().location(),faker.number().numberBetween(1, 856),
                    faker.number().numberBetween(5, 1200),faker.number().numberBetween(2, 12000),
                    faker.number().digits(5) + "-ERUMR-" + faker.number().digits(5)));
        }
    }
    public Iterable<Pokemon> getAllPokemons() {

        return repository.findAll();
    }

    public Pokemon createPokemon (Pokemon pokemon){

        return repository.save(pokemon);
    }

    public Optional<Pokemon> findPokemonById(String id){

        return repository.findById(id);
    }

    public Optional<Pokemon> findPokemonByNumber(String title){
        return repository.findPokemonByNumber(title);
    }

    public Pokemon deletePokemonByNumber(int number){
        //Find out IF this id-book IS in our DB
        Optional<Pokemon> deletedPokemon = repository.deleteByNumber(number);
        //
        return null;
    }

    public void deletePokemonById(String id){
        repository.deleteById(id);
    }

    public Pokemon updatePokemon (Pokemon book){
        return repository.save(book);
    }



    public List<Pokemon> queryPokemonsFromArray() {

        System.out.println("Pokemons" + pokemons);

        return pokemons;
    }



}