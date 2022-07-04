package io.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class PokedexRestController {
    @Autowired
    PokemonService pokemonService;

    //here we are creating our end-point rest API
    @GetMapping("pokemons")
    public Iterable<Pokemon> getAllPokemons() {
        return pokemonService.getAllPokemons();
    }

    @PutMapping("/updatePokemon/{id}")
    public ResponseEntity<Pokemon> updatePokemon (@PathVariable Long id, @RequestBody Pokemon dataPokemon) {
        String responseUpdate = "";
        Optional<Pokemon> pokemonFound = pokemonService.findPokemonById(id);
        Pokemon pokemonToUpdate =null;
        Pokemon pokemonUpdated =null;

        if (pokemonFound.isPresent()) {
            pokemonToUpdate = pokemonFound.get();
            //we are going to compare both pokemon:
            //pokemonFromRest vs pokemonToUpdate
            //we need to compare each field of our object
            responseUpdate += "pokemon found";
            boolean updated = false;
            //Name to update
            if  (dataPokemon.getName() != null) {
                responseUpdate += " - pokemom name value updated: " + dataPokemon.getName()  +  "( old value: " + pokemonToUpdate.getName() + ")" ;
                pokemonToUpdate.setName(dataPokemon.getName());
                updated = true;
            }
            //location to update
            if  (dataPokemon.getLocation() != null) {
                responseUpdate += " - location value updated: " + dataPokemon.getLocation()  +  "( old value: " + pokemonToUpdate.getLocation() + ")" ;
                pokemonToUpdate.setLocation(dataPokemon.getLocation());
                updated = true;
            }
            //number number
            if  (dataPokemon.getNumber() != 0) {
                responseUpdate += " - number int value updated: " + dataPokemon.getNumber() +  "( old value: " + pokemonToUpdate.getNumber()  + ")" ;
                pokemonToUpdate.setNumber(dataPokemon.getNumber());
                updated = true;
            }
            //update high
            if  (dataPokemon.getHigh() != 0) {
                responseUpdate += " - high int value updated: " + dataPokemon.getHigh() +  "( old value: " + pokemonToUpdate.getHigh()  + ")" ;
                pokemonToUpdate.setHigh(dataPokemon.getHigh());
                updated = true;
            }
            //update kg
            if  (dataPokemon.getKg() != 0) {
                responseUpdate += " - kg int value updated: " + dataPokemon.getKg() +  "( old value: " + pokemonToUpdate.getKg()  + ")" ;
                pokemonToUpdate.setKg(dataPokemon.getKg());
                updated = true;
            }
            //update use
            if  (dataPokemon.getUse() != null) {
                responseUpdate += " - use value updated: " + dataPokemon.getUse() +  "( old value: " + pokemonToUpdate.getUse()  + ")" ;
                pokemonToUpdate.setUse(dataPokemon.getUse());
                updated = true;
            }

            if (!updated) responseUpdate += " - try to update but any field updated - something wrong happened";
            else { pokemonUpdated = pokemonService.updatePokemon(pokemonToUpdate);}

        } else { responseUpdate = responseUpdate + "pokemon not found";}

        var headers = new HttpHeaders();
        headers.add("ResponseUpdate", "updatePokemon executed");
        headers.add("version", "1.0 Api Rest User Object");
        headers.add("Executed Output", responseUpdate);

        return ResponseEntity.accepted().headers(headers).body(pokemonUpdated);
    }


}