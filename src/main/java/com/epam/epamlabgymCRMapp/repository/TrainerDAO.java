package com.epam.epamlabgymCRMapp.repository;



import com.epam.epamlabgymCRMapp.model.Trainer;
import com.epam.epamlabgymCRMapp.model.User;

import java.util.List;
import java.util.Optional;

public interface TrainerDAO {
    public Optional<Trainer> create(Trainer trainer);
    public Optional<Trainer> update(Trainer trainer);
    public Optional<Trainer> getById(int id);
    List<User> getTrainers();

    Optional<Trainer> getByUsername(String username);

}
