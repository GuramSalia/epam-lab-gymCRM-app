package com.epam.epamlabgymCRMapp.service;

import com.epam.epamlabgymCRMapp.model.Trainer;
import com.epam.epamlabgymCRMapp.model.User;
import com.epam.epamlabgymCRMapp.repository.TrainerDAO;
import com.epam.epamlabgymCRMapp.utils.RandomPasswordGenerator;
import com.epam.epamlabgymCRMapp.utils.UsernameGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TrainerService {
    private final TrainerDAO trainerDAO;
    private final UsernameGenerator usernameGenerator;

    public TrainerService(TrainerDAO trainerDAO, UsernameGenerator usernameGenerator) {
        this.trainerDAO = trainerDAO;
        this.usernameGenerator = usernameGenerator;
    }

    // create/update/select Trainer profile
    public Optional<Trainer> create(Trainer trainer) {
        trainer.setPassword(RandomPasswordGenerator.generateRandomPassword());
        trainer.setUsername(usernameGenerator.generateUsername(trainer));
        return trainerDAO.create(trainer);
    }

    public Optional<Trainer> update(Trainer trainer) {
        trainer.setUsername(usernameGenerator.generateUsername(trainer));
        return trainerDAO.update(trainer);
    }

    public Optional<Trainer> getById(int id) {
        log.info(">>>> Getting trainer with id: " + id);
        return trainerDAO.getById(id);
    }

    public List<User> getTrainers() {
        log.info(">>>> Getting trainers");
        return trainerDAO.getTrainers();
    }
}
