package com.epam.epamlabgymCRMapp;

import com.epam.epamlabgymCRMapp.config.ApplicationContextProvider;
import com.epam.epamlabgymCRMapp.config.InMemoryStorage;
import com.epam.epamlabgymCRMapp.service.CustomerService;
import com.epam.epamlabgymCRMapp.service.TrainerService;
import com.epam.epamlabgymCRMapp.service.TrainingService;
import com.epam.epamlabgymCRMapp.utils.BeanProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class GymCrmApplication {

	public static void main(String[] args) {

		SpringApplication.run(GymCrmApplication.class, args);

		InMemoryStorage inMemoryStorage = BeanProvider.getInMemoryStorage();
		TrainerService trainerService = BeanProvider.getTrainerService();
		CustomerService traineeService = BeanProvider.getCustomerService();
		TrainingService trainingService = BeanProvider.getTrainingService();

		log.info("\n\n>>>> START  ==============\n");
		inMemoryStorage.getCustomers().values().stream().forEach(x -> log.info(x.toString()));
		log.info("^^^^ TRAINEES\n");
		inMemoryStorage.getTrainers().values().stream().forEach(x -> log.info(x.toString()));
		log.info("^^^^ TRAINERS\n");
		inMemoryStorage.getTrainings().values().stream().forEach(x -> log.info(x.toString()));
		log.info("^^^^ TRAININGS\n");

		log.info("\n\tSTART\n");
		log.info("list of TRAINEES:");
		inMemoryStorage.getCustomers().values().stream().forEach(x -> log.info(x.toString()));
		log.info("list of TRAINERS:");
		inMemoryStorage.getTrainers().values().stream().forEach(x -> log.info(x.toString()));
		log.info("list of TRAININGS:");
		inMemoryStorage.getTrainings().values().stream().forEach(x -> log.info(x.toString()));


	}

}
