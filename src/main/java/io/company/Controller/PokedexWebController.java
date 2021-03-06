package io.company.Controller;

import com.github.javafaker.Faker;
import io.company.Service.PokemonService;
import io.company.Service.TrainerService;
import io.company.model.Pokemon;
import io.company.model.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pipo")
public class PokedexWebController {

    @Autowired
    PokemonService pokemonService;

    @Autowired
    TrainerService trainerService;

    @RequestMapping("/index")
    public String getWeb (Model containerToView, Model trainerToView) {

        containerToView.addAttribute("pokemonsfromController",
                pokemonService.getAllPokemons());

        trainerToView.addAttribute("trainerfromController",
                trainerService.getAllTrainers());

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
    @RequestMapping("/addTrainerToPokemon")
    public String addTrainerToPokemon(@RequestParam("trainerId") long trainerId, @RequestParam("pokemonId") long pokemonId){
        Optional<Trainer> trainer = trainerService.findTrainerById(trainerId);
        Trainer trainer1 = trainer.get();
        Optional<Pokemon> pokemon = pokemonService.findPokemonById(pokemonId);
        Pokemon pokemon1 = pokemon.get();
        pokemon1.setTrainer(trainer1);
        pokemonService.addTrainerToPokemon(pokemon1);
        return "redirect:index";
    }

    @RequestMapping("/findPokemonByNumber")
    public String findPokemonByNumber(@RequestParam("PokemonNumber") int pokemonNumber){
        pokemonService.findPokemonByNumber(pokemonNumber);
        System.out.println("PokemonNumber" + pokemonNumber);

        return "redirect:index";

    }

    @RequestMapping("/createPokemon")
    public String createPokemon(Model containerToView){
        containerToView.addAttribute("trainersFromController",
                trainerService.getAllTrainers());
        return "createPokemon";

    }
    @RequestMapping("/addPokemon")
    public String addPokemon(@RequestParam("Name") String Name, @RequestParam("location") String location, @RequestParam("number") int number, @RequestParam("high") int high, @RequestParam("kg") int kg, @RequestParam("use") String use, @RequestParam("trainerId") long trainerId) {

        Optional<Trainer> trainer = trainerService.findTrainerById(trainerId);
        pokemonService.createPokemon(new Pokemon(Name, location, number, high, kg, use ,trainer.get()));
        return "redirect:index";
    }
    @RequestMapping("/updatePokemon")
    public String updatePokemon(@RequestParam("pokemonIdFromView") Long id, Model pokemonfromController, Model trainerFromController) {
        pokemonfromController.addAttribute("pokemonfromController",
                pokemonService.findPokemonById(id).get());
        trainerFromController.addAttribute("trainerfromController",
                trainerService.getAllTrainers());
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

    @RequestMapping("/fakerTrainer")
    public String createFakeTrainer(@RequestParam("qtyTrainer") int qty) {

        Faker faker = new Faker();

        String use = null;

        for (int i = 0; i < qty; i++) {
            String name = faker.funnyName().name();
            String job = faker.job().field();
            int numberOfPokemons = faker.number().numberBetween(1, 7);
//            List<Pokemon> pokemons = new List<>();

            System.out.println(i+" "+name);
            trainerService.createTrainer(new Trainer(name, job,numberOfPokemons));
        }

        return "redirect:index";
    }

    @RequestMapping("/createTrainer")
    public String createTrainer(Model containerToView){
        containerToView.addAttribute("pokemonFromController", pokemonService.getAllPokemons());
        return "createTrainer";

    }
    @RequestMapping("/addTrainer")
    public String addTrainer(@RequestParam("name") String name, @RequestParam("job") String job, @RequestParam("numberOfPokemons") int numberOfPokemons) {

        trainerService.createTrainer(new Trainer(name, job, numberOfPokemons));

        return "redirect:index";

    }
    @RequestMapping("/updateTrainer")
    public String updateTrainer(@RequestParam("trainerIdFromView") Long id, Model trainerfromController) {
        trainerfromController.addAttribute("trainerfromController",
                trainerService.findTrainerById(id).get());
        return "updateTrainer";
    }

    @RequestMapping("/deleteAllTrainer")
    public String deleteAllTrainer(){

        trainerService.deleteAllTrainer();

        return "redirect:index";
    }

    @RequestMapping("/showAllTrainerData")
    public String showAllTrainerData(Model containerToView){

        return "showAllTrainerData";
    }

    @PostMapping("/replaceTrainer/{idFromView}")
    public String replaceTrainer(@PathVariable("idFromView") Long id, Trainer trainer,
                                 Model trainerfromController) {
        trainerfromController.addAttribute("trainerfromController",
                trainerService.findTrainerById(id).get());
        Optional<Trainer> newTrainer = trainerService.findTrainerById(id);
        if (newTrainer.isPresent()) {
            trainer = new Trainer(trainer.getTrainerId(), trainer.getName(), trainer.getJob(), trainer.getNumberOfPokemons(), trainer.getPokemons());
            trainerService.createTrainer(trainer);
            return "redirect:/pipo/index";
        } else return "error";

    }

    @RequestMapping("/deleteTrainerById")
    public String deleteTrainerById(@RequestParam("trainerIdFromView") Long id){
        trainerService.deleteTrainerById(id);

        return "redirect:index";

    }





}