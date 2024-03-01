package com.epam.epamlabgymCRMapp.repository;

import com.epam.epamlabgymCRMapp.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {
    public Optional<Customer> create(Customer customer);
    public Optional<Customer> update(Customer customer);
    public boolean delete(int id);
    public Optional<Customer>  getById(int id);
    List<Customer> getCustomers();
}
