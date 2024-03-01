package com.epam.epamlabgymCRMapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class Trainer extends User{
    private int id;
    private TrainingType specialization;
    private User user;
}

