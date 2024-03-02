package com.epam.epamlabgymCRMapp;

import com.epam.epamlabgymCRMapp.model.Trainer;
import com.epam.epamlabgymCRMapp.service.CustomerService;
import com.epam.epamlabgymCRMapp.service.TrainerService;
import com.epam.epamlabgymCRMapp.service.TrainingService;
import com.epam.epamlabgymCRMapp.utils.BeanProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@Slf4j
@SpringBootApplication
public class GymCrmApplication {

	public static void main(String[] args) {

		SpringApplication.run(GymCrmApplication.class, args);

		log.info("\n\n>>>> START  ==============\n");

//
//		InMemoryStorage storage = BeanProvider.getInMemoryStorage();
		TrainerService trainerService = BeanProvider.getTrainerService();
		CustomerService traineeService = BeanProvider.getCustomerService();
		TrainingService trainingService = BeanProvider.getTrainingService();

//		log.info("\n\n>>>> START  ==============\n");
//		storage.getCustomers().values().stream().forEach(x -> log.info(x.toString()));
//		log.info("^^^^ TRAINEES\n");
//		storage.getTrainers().values().stream().forEach(x -> log.info(x.toString()));
//		log.info("^^^^ TRAINERS\n");
//		storage.getTrainings().values().stream().forEach(x -> log.info(x.toString()));
//		log.info("^^^^ TRAININGS\n");
//
//		log.info("\n\tSTART\n");
//		log.info("list of TRAINEES:");
//		storage.getCustomers().values().stream().forEach(x -> log.info(x.toString()));
//		log.info("list of TRAINERS:");
//		storage.getTrainers().values().stream().forEach(x -> log.info(x.toString()));
//		log.info("list of TRAININGS:");
//		storage.getTrainings().values().stream().forEach(x -> log.info(x.toString()));

		Optional<Trainer> trainerOptional = trainerService.getByUsername("Tim.Smith");
        trainerOptional.ifPresent(trainer -> log.info(trainer.toString()));



	}

}
