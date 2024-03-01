package com.epam.epamlabgymCRMapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class JsonDataContainer {
    @JsonProperty("Trainer")
    public List<Trainer> trainers;
    @JsonProperty("Customer")
    public List<Customer> customers;
    @JsonProperty("Training")
    public List<Training> trainings;
}
