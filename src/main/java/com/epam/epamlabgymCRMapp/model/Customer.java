package com.epam.epamlabgymCRMapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Customers")
@Setter
@Getter
public class Customer extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerId")
    private int customerId;
    @Column(name = "DateOfBirth")
    private Date dob;
    @Column(name = "Address")
    private String address;

    @Override
    public String toString() {
        String isActiveString = getIsActive() ? "true" : "false";
        Calendar calendar = Calendar.getInstance();
        String dobString = "null";
        if (dob != null) {
            calendar.setTime(dob);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            dobString = year + "-" + month + "-" + day;
        }
        return "Customer{" + "\n  id=" + customerId + ", \n  name='" + getFirstName() + ' ' + getLastName() + '\'' + ", \n  username='" + getUsername() + '\'' + ", \n  Address='" + getAddress() + '\'' + ", \n  dob=" + dobString + ", \n  isActive='" + isActiveString + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer trainee = (Customer) o;
        return getCustomerId() == trainee.getCustomerId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerId(), getFirstName(), getLastName());
    }
}
