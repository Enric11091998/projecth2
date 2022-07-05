package io.company.Repository;


import io.company.model.Trainer;
import org.springframework.data.repository.CrudRepository;



public interface TrainerRepository extends CrudRepository<Trainer, Long> {


}