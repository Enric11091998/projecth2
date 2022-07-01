package io.company;

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

//    public Optional<Pokemon> findPokemonByName(String Name){
//
//        return pokemonRepository.findPokemonByName(Name);
//    }

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

//    public void deletePokemonByName(String name){
//        if(findPokemonByName(name).isPresent()){
//            Optional<Pokemon> deletedPokemon = pokemonRepository.deletePokemonByName(name);
//        }
//    }

    public Pokemon updatePokemon (Pokemon pokemon){



        return pokemonRepository.save(pokemon);
    }

}
