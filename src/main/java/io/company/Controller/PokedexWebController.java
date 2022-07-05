package io.company.Controller;

import com.github.javafaker.Faker;
import io.company.Service.PokemonService;
import io.company.model.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/pipo")
public class PokedexWebController {

    @Autowired
    PokemonService pokemonService;

    @RequestMapping("/index")
    public String getWeb (Model containerToView) {

        containerToView.addAttribute("pokemonsfromController",
                pokemonService.getAllPokemons());

        return "index";
    }

    @RequestMapping("/fakerPokemon")
    public String createFakePokemon(@RequestParam("qtyPokemon") int qty) {

        Faker faker = new Faker();
        int numSwitch;
        String use = null;

        for (int i = 0; i <qty ; i++ ){
            String name = faker.pokemon().name();
            String location = faker.pokemon().location();
            int number = faker.number().numberBetween(1, 151);
            numSwitch = faker.number().numberBetween(1, 3);
            int height = faker.number().numberBetween(5, 1200);
            int kg = faker.number().numberBetween(2, 12000);
            switch (numSwitch){
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
                default: {
                    use = "combat";
                }
            }
            System.out.println(i+" "+name);
            pokemonService.createPokemon(new Pokemon(name, location,number,height,kg, use));
        }


        return "redirect:index";

    }

//    @RequestMapping("/deletePokemonByName")
//    public String deletePokemonByName(@RequestParam("PokemonName") String pokemonName){
//        pokemonService.deletePokemonByName(pokemonName);
//        System.out.println("PokemonName" + pokemonName);
//
//        return "redirect:index";
//
//    }

    @RequestMapping("/deletePokemonByNumber")
    public String deletePokemonByNumber(@RequestParam("PokemonNumber") int pokemonNumber){
        pokemonService.deletePokemonByNumber(pokemonNumber);
        System.out.println("PokemonNumber" + pokemonNumber);

        return "redirect:index";

    }

    @RequestMapping("/deletePokemonById")
    public String deletePokemonById(@RequestParam("pokemonIdFromView") Long id){
        pokemonService.deletePokemonById(id);

        return "redirect:index";

    }



//    @RequestMapping("/findPokemonByName")
//    public String findPokemonByName(@RequestParam("PokemonName") String pokemonName){
//        pokemonService.findPokemonByName(pokemonName);
//        System.out.println("PokemonName" + pokemonName);
//
//        return "redirect:index";
//
//    }

    @RequestMapping("/findPokemonByNumber")
    public String findPokemonByNumber(@RequestParam("PokemonNumber") int pokemonNumber){
        pokemonService.findPokemonByNumber(pokemonNumber);
        System.out.println("PokemonNumber" + pokemonNumber);

        return "redirect:index";

    }

    @RequestMapping("/createPokemon")
    public String createPokemon(Model containerToView){
        containerToView.addAttribute("pokemonFromController", pokemonService.getAllPokemons());
        return "createPokemon";

    }
    @RequestMapping("/addPokemon")
    public String addPokemon(@RequestParam("Name") String Name, @RequestParam("location") String location, @RequestParam("number") int number, @RequestParam("high") int high, @RequestParam("kg") int kg, @RequestParam("use") String use) {


        pokemonService.createPokemon(new Pokemon(Name, location, number, high, kg, use));
        return "redirect:index";
    }
    @RequestMapping("/updatePokemon")
    public String updatePokemon(@RequestParam("pokemonIdFromView") Long id, Model pokemonfromController) {
        pokemonfromController.addAttribute("pokemonfromController",
                pokemonService.findPokemonById(id).get());
        return "updatePokemon";
    }

    @RequestMapping("/deleteAllPokemon")
    public String deleteAllPokemon(){

        pokemonService.deleteAllPokemon();

        return "redirect:index";
    }

    @RequestMapping("/showAllPokemonData")
    public String showAllPokemonData(Model containerToView){

        return "showAllPokemonData";
    }

    @PostMapping("/replacePokemon/{idFromView}")
    public String replacePokemon(@PathVariable("idFromView") Long id, Pokemon pokemon,
                              Model pokemonfromController) {
        pokemonfromController.addAttribute("pokemonfromController",
                pokemonService.findPokemonById(id).get());
        Optional<Pokemon> newPokemon = pokemonService.findPokemonById(id);
        if (newPokemon.isPresent()) {
            pokemon = new Pokemon(pokemon.getPokemonId(), pokemon.getName(), pokemon.getLocation(), pokemon.getNumber(), pokemon.getHigh(), pokemon.getKg(), pokemon.getUse());
            pokemonService.createPokemon(pokemon);
            return "redirect:/pipo/index";
        } else return "error";

    }



}