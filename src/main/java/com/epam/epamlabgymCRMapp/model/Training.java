package com.epam.epamlabgymCRMapp.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Setter
@Getter
public class Training {
    private int trainingId;
    private int trainerId;
    private int customerId;
    private String name;
    private TrainingType type;
    private Date date;
    private int durationInMinutes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Training training = (Training) o;
        return getTrainingId() == training.getTrainingId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTrainingId(), getTrainingId(), getTrainerId(), getName(), getType(), getDate(), getDurationInMinutes());
    }

    @Override
    public String toString() {
        Calendar calendar = Calendar.getInstance();
        String dateString = "null";
        if (date != null) {
            calendar.setTime(date);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            dateString = year + "-" + month + "-" + day;
        }

        return "Training{" + "\n  id=" + trainingId + ", \n  traineeId=" + trainingId + ", \n  trainerId=" + trainerId + ", \n  name='" + name + '\'' + ", \n  type=" + type + ", \n  date=" + dateString + ", \n  durationInMinutes=" + durationInMinutes + '}';
    }
}
