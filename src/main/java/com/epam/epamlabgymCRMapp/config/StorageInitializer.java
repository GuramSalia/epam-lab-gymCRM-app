package com.epam.epamlabgymCRMapp.config;

import com.epam.epamlabgymCRMapp.model.Customer;
import com.epam.epamlabgymCRMapp.model.JsonDataContainer;
import com.epam.epamlabgymCRMapp.model.Trainer;
import com.epam.epamlabgymCRMapp.model.Training;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
public class StorageInitializer {

    @Value("${file.path.initialData}")
    private Resource initialDataResource;

    private final InMemoryStorage storage;

    public StorageInitializer(InMemoryStorage storage) {
        this.storage = storage;
    }

    @PostConstruct
    public void initialize() {
        log.info(">>>> Storage Initialization");

        ObjectMapper om = new ObjectMapper();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        om.setDateFormat(dateFormat);

        try (InputStream inputStream = initialDataResource.getInputStream()) {
            JsonDataContainer root = om.readValue(inputStream, JsonDataContainer.class);

            storage.setTrainers(root.trainers.stream().collect(Collectors.toMap(Trainer::getId, Function.identity())));
            storage.setCustomers(root.customers.stream()
                                               .collect(Collectors.toMap(Customer::getId, Function.identity())));
            storage.setTrainings(root.trainings.stream()
                                               .collect(Collectors.toMap(Training::getId, Function.identity())));
        } catch (IOException e) {
            log.error("Error initializing InMemoryStorage", e);
        }
    }
}

