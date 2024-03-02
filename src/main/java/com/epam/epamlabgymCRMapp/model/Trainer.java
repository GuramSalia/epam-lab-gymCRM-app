package com.epam.epamlabgymCRMapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "Trainers")
@Setter
@Getter
public class Trainer extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TrainerId")
    private int trainerId;
    @ManyToOne
    @Column(name = "Specialization")
    private TrainingType specialization;

    @Override
    public String toString() {
        return "Trainer{" + "\n  id=" + trainerId + ", \n  name='" + getFirstName() + ' ' + getLastName() + '\'' + "," + " \n  username='" + getUsername() + '\'' + ", \n  spec.='" + getSpecialization() + '\'' + ", \n  " + "isActive='" + getIsActive() + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainer trainer = (Trainer) o;
        return getTrainerId() == trainer.getTrainerId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTrainerId(), getFirstName(), getLastName());
    }
}

