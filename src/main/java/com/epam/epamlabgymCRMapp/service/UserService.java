package com.epam.epamlabgymCRMapp.service;

import com.epam.epamlabgymCRMapp.model.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserService {

    public static boolean isValid(User user) {
        if (user == null) {
            log.error("Customer is null");
            return false;
        }

        boolean valid = true;
        if (user.getFirstName() == null) {
            valid = false;
            log.error("First name cannot be null");
        }
        if (user.getLastName() == null) {
            valid = false;
            log.error("Last name cannot be null");
        }
        if (user.getUsername() == null) {
            valid = false;
            log.error("Username cannot be null");
        }
        if (user.getPassword() == null) {
            valid = false;
            log.error("Password cannot be null");
        }
        if (user.getIsActive() == null) {
            valid = false;
            log.error("isActive cannot be null");
        }

        return valid;
    }

    public static boolean isInvalid(User user) {
        return !isValid(user);
    }
}
