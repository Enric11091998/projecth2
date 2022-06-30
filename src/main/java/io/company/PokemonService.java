package io.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PokemonService {

    @Autowired
    Repository repository;

    public Iterable<Pokemon> getAllPokemons() {

        return repository.findAll();
    }

    public Pokemon createPokemon (Pokemon pokemon){

       return repository.save(pokemon);
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
