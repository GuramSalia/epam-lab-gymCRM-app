package com.epam.epamlabgymCRMapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class Customer extends User{
    private int id;
    private Date dob;
    private String address;
    private User user;
}
