package com.epam.epamlabgymCRMapp.utils;

import com.epam.epamlabgymCRMapp.config.ApplicationContextProvider;
//import com.epam.epamlabgymCRMapp.config.InMemoryStorage;
import com.epam.epamlabgymCRMapp.service.CustomerService;

import com.epam.epamlabgymCRMapp.service.TrainerService;
import com.epam.epamlabgymCRMapp.service.TrainingService;
import org.springframework.context.ApplicationContext;

public class BeanProvider {

    private static final ApplicationContext context = ApplicationContextProvider.getContext();

    public static TrainerService getTrainerService() {
        return context.getBean(TrainerService.class);
    }

    public static CustomerService getCustomerService() {
        return context.getBean(CustomerService.class);
    }

    public static TrainingService getTrainingService() {
        return context.getBean(TrainingService.class);
    }
}
