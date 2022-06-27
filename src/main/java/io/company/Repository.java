package io.company;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@org.springframework.stereotype.Repository
public class Repository implements CrudRepository<Pokemon,String> {
    @Override
    public <S extends Pokemon> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Pokemon> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Pokemon> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public Iterable<Pokemon> findAll() {
        return null;
    }

    @Override
    public Iterable<Pokemon> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    public Optional<Pokemon> findPokemonByNumber(String title) {

        return Optional.empty();
    }

    public Optional<Pokemon> deleteByNumber(int s) {

        return Optional.empty();
    }


    @Override
    public void delete(Pokemon entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Pokemon> entities) {

    }

    @Override
    public void deleteAll() {

    }


}
