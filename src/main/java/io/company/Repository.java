package io.company;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

//@org.springframework.stereotype.Repository
public interface Repository extends CrudRepository<Pokemon, String> {

    Optional<Pokemon> findPokemonByNumber(int number);
    Optional<Pokemon> deleteByNumber(int number);




}
