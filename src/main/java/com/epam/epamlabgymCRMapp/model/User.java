package com.epam.epamlabgymCRMapp.model;

import lombok.Data;

@Data
public abstract class User {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean isActive;
}
