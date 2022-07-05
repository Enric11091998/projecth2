package io.company.Service;

import io.company.Repository.TrainerRepository;
import io.company.model.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrainerService {
    @Autowired
    TrainerRepository trainerRepository;

    public Iterable<Trainer> getAllTrainers() {

        return trainerRepository.findAll();
    }

    public Trainer createTrainer (Trainer trainer){

        return trainerRepository.save(trainer);
    }

    public Optional<Trainer> findTrainerById(Long id){

        return trainerRepository.findById(id);
    }

    public void deleteTrainerById(Long id){
        trainerRepository.deleteById(id);
    }

    public void deleteAllTrainer(){
        trainerRepository.deleteAll();
    }

    public Trainer updateTrainer (Trainer trainer){
        return trainerRepository.save(trainer);
    }

}

