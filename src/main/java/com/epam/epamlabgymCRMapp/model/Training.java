package com.epam.epamlabgymCRMapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Trainings")
@Setter
@Getter
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TrainingId")
    private int trainingId;
    @Column(name = "CustomerId")
    private int customerId;
    @Column(name = "TrainerId")
    private int trainerId;
    @Column(name = "TrainingName")
    private String trainingName;
    @ManyToOne
    @JoinColumn(name = "TrainingTypeId")
    private TrainingType type;
    @Column(name = "TrainingDate")
    private Date trainingDate;
    @Column(name = "TrainingDuration")
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
        return Objects.hash(getTrainingId(), getTrainingId(), getTrainerId(), getTrainingName(), getType(), getTrainingDate(), getDurationInMinutes());
    }

    @Override
    public String toString() {
        Calendar calendar = Calendar.getInstance();
        String dateString = "null";
        if (trainingDate != null) {
            calendar.setTime(trainingDate);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            dateString = year + "-" + month + "-" + day;
        }

        return "Training{" + "\n  id=" + trainingId + ", \n  traineeId=" + trainingId + ", \n  trainerId=" + trainerId + ", \n  name='" + trainingName + '\'' + ", \n  type=" + type + ", \n  date=" + dateString + ", \n  durationInMinutes=" + durationInMinutes + '}';
    }
}
