package io.company;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

//@org.springframework.stereotype.Repository
public interface PokemonRepository extends CrudRepository<Pokemon, Long> {

    Optional<Pokemon> findPokemonByNumber(int number);
    Optional<Pokemon> deleteByNumber(int number);

//    Optional<Pokemon> findPokemonByName(String Name);
//    Optional<Pokemon> deletePokemonByName(String Name);




}
