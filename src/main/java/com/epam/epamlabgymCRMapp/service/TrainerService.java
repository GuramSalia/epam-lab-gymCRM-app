package com.epam.epamlabgymCRMapp.service;

import com.epam.epamlabgymCRMapp.model.Trainer;
import com.epam.epamlabgymCRMapp.repository.TrainerRepository;
import com.epam.epamlabgymCRMapp.utils.RandomPasswordGenerator;
import com.epam.epamlabgymCRMapp.utils.UsernameGenerator;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class TrainerService {
    private final TrainerRepository trainerRepository;
    private final UsernameGenerator usernameGenerator;

    public TrainerService(TrainerRepository trainerRepository, UsernameGenerator usernameGenerator) {
        this.trainerRepository = trainerRepository;
        this.usernameGenerator = usernameGenerator;
    }

    @Transactional
    public Optional<Trainer> create(Trainer trainer) {

        if (UserService.isInvalid(trainer)) {
            return Optional.empty();
        }

        try {
            trainer.setUsername(usernameGenerator.generateUsername(trainer));
            trainer.setPassword(RandomPasswordGenerator.generateRandomPassword());
            log.info(">>>> Creating trainer with username: " + trainer.getUsername());
            return Optional.of(trainerRepository.save(trainer));
        } catch (Exception e) {
            log.error("error creating trainer", e);
            return Optional.empty();
        }
    }

    @Transactional
    public Optional<Trainer> update(Trainer trainer, String username, String password) {

        if (isInvalidUsernamePassword(trainer, username, password)) {
            log.error("invalid username or password");
            return Optional.empty();
        }

        if (UserService.isInvalid(trainer)) {
            return Optional.empty();
        }

        try {
            trainer.setUsername(usernameGenerator.generateUsername(trainer));
            log.info(">>>> Creating trainer with username: " + trainer.getUsername());
            return Optional.of(trainerRepository.save(trainer));
        } catch (Exception e) {
            log.error("error updating trainer", e);
            return Optional.empty();
        }
    }

    public Optional<Trainer> getByUsername(String username) {
        log.info(">>>> Getting trainer with username: " + username);
        return trainerRepository.findByUsername(username);
    }

    public boolean isValidUsernamePassword(Trainer trainer, String username, String password) {
        Trainer trainerInDb;
        try {
            Optional<Trainer> trainerOptional = trainerRepository.findByUsernameAndPassword(username, password);
            if (trainerOptional.isEmpty()) {
                log.error("Could not find the trainer");
                return false;
            }

            trainerInDb = trainerOptional.get();
        } catch (Exception e) {
            log.error("something went wrong", e);
            return false;
        }
        return trainerInDb.equals(trainer);
    }

    public boolean isInvalidUsernamePassword(Trainer trainer, String username, String password) {
        return !isValidUsernamePassword(trainer, username, password);
    }

    @Transactional
    public Optional<Trainer> updatePassword(Trainer trainer, String username, String currentPassword, String newPassword) {

        if (isInvalidUsernamePassword(trainer, username, currentPassword)) {
            log.error("invalid username or password");
            return Optional.empty();
        }

        try {
            trainer.setPassword(newPassword);
            return Optional.of(trainerRepository.save(trainer));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Transactional
    public boolean activateTrainer(Trainer trainer, String username, String password) {

        if (isInvalidUsernamePassword(trainer, username, password)) {
            log.error("invalid username or password");
            return false;
        }

        if (trainer.getIsActive()) {
            log.info("trainer is already active");
            return false;
        }
        try {
            trainer.setIsActive(true);
            trainerRepository.save(trainer);
            log.info("trainer is active");
            return true;
        } catch (Exception e) {
            log.error("couldn't activate the trainer");
            return false;
        }
    }

    @Transactional
    public boolean deactivateTrainer(Trainer trainer, String username, String password) {

        if (isInvalidUsernamePassword(trainer, username, password)) {
            log.error("invalid username or password");
            return false;
        }

        if (!trainer.getIsActive()) {
            log.info("trainer is already deactivated");
            return false;
        }

        try {
            trainer.setIsActive(false);
            trainerRepository.save(trainer);
            log.info("trainer is deactivated");
            return true;
        } catch (Exception e) {
            log.error("couldn't deactivate the trainer");
            return false;
        }
    }
}
