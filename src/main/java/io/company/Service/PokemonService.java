package io.company.Service;

import io.company.Repository.PokemonRepository;
import io.company.model.Pokemon;
import io.company.model.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PokemonService {

    @Autowired
    PokemonRepository pokemonRepository;

    public Iterable<Pokemon> getAllPokemons() {

        return pokemonRepository.findAll();
    }

    public Pokemon createPokemon (Pokemon pokemon){

       return pokemonRepository.save(pokemon);
    }

    public Optional<Pokemon> findPokemonById(Long id){

        return pokemonRepository.findById(id);
    }

    public Optional<Pokemon> findPokemonByNumber(int number){
        return pokemonRepository.findPokemonByNumber(number);
    }

    public void deletePokemonByNumber(int number){
        if(findPokemonByNumber(number).isPresent()){
            Optional<Pokemon> deletedPokemon = pokemonRepository.deleteByNumber(number);
        }
    }

    public void deletePokemonById(Long id){
        pokemonRepository.deleteById(id);
    }

    public void deleteAllPokemon(){
        pokemonRepository.deleteAll();
    }

    public Pokemon updatePokemon (Pokemon pokemon){
        return pokemonRepository.save(pokemon);
    }

    public void addTrainerToPokemon (Pokemon pokemon) { pokemonRepository.save(pokemon); }

}
