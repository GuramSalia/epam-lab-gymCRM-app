package com.epam.epamlabgymCRMapp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class User {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean isActive;
}
