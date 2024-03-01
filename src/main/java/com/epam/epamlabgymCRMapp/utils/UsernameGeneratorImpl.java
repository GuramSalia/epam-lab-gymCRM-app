package com.epam.epamlabgymCRMapp.utils;

import com.epam.epamlabgymCRMapp.model.User;
import com.epam.epamlabgymCRMapp.repository.CustomerDAO;
import com.epam.epamlabgymCRMapp.repository.TrainerDAO;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Slf4j
@Component
@Getter
@Setter
public class UsernameGeneratorImpl implements UsernameGenerator {

    private final TrainerDAO trainerDAO;
    private final CustomerDAO customerDAO;

    public UsernameGeneratorImpl(TrainerDAO trainerDAO, CustomerDAO customerDAO) {
        this.trainerDAO = trainerDAO;
        this.customerDAO = customerDAO;
    }

    public String generateUsername(User user) {
        String base = user.getFirstName() + "." + user.getLastName();
        int counter = 1;
        String username = base;
        Function<String, Boolean> isUniqueInTrainers = u -> isUnique(user, u, trainerDAO.getTrainers());
        Function<String, Boolean> isUniqueInTrainees = u -> isUnique(user, u, customerDAO.getCustomers());

        while (!isUniqueInTrainees.apply(username) || !isUniqueInTrainers.apply(username)) {
            username = base + counter++;
        }

        log.info(">>>> Generating username");
        return username;
    }

    private <T extends User> boolean isUnique(User targetUser, String username, List<T> users) {
        return users.stream().noneMatch(user -> user.getUsername().equals(username) && !user.equals(targetUser));
    }
}
