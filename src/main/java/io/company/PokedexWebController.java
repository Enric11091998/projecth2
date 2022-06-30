package io.company;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.Column;
import java.util.Random;

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
            int number = faker.number().numberBetween(1, 856);
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
            pokemonService.createPokemon(new Pokemon (name, location,number,height,kg, use));
        }


        return "redirect:index";

    }

    @RequestMapping("/deletePokemon")
    public String deleteBook(@RequestParam String titleFromView){

        System.out.println("pokemonName" + titleFromView);

        return "pokemondeleted";


    }


}