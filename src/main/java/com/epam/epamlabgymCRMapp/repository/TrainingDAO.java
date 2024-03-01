package com.epam.epamlabgymCRMapp.repository;



import com.epam.epamlabgymCRMapp.model.Training;

import java.util.List;
import java.util.Optional;

public interface TrainingDAO {
    public Optional<Training> create(Training training);
    public Optional<Training> getById(int id);

    public List<Training> getTrainings();
}
