package io.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

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


}