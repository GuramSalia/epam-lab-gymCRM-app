package com.epam.epamlabgymCRMapp.model;

import lombok.Data;
import java.util.Date;

@Data
public class Training {
    private int id;
    private int trainerId;
    private int customerId;
    private String name;
    private TrainingType type;
    private Date date;
    private int durationInMinutes;
}
