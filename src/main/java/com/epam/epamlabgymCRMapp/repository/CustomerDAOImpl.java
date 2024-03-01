package com.epam.epamlabgymCRMapp.repository;

import com.epam.epamlabgymCRMapp.config.InMemoryStorage;
import com.epam.epamlabgymCRMapp.model.Customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class CustomerDAOImpl implements CustomerDAO {
    private final Map<Integer, Customer> customers;

    public CustomerDAOImpl(InMemoryStorage storage) {this.customers = storage.getCustomers();}

    public Optional<Customer> create(Customer customer) {
        int id = customer.getId();
        if (customers.containsKey(id)) {
            log.error("Customer with id {} already exists", id);
            return Optional.empty();
        } else {
            customers.put(id, customer);
            return Optional.of(customers.get(id));
        }
    }

    public Optional<Customer> update(Customer customer) {
        int id = customer.getId();
        if (customers.containsKey(id)) {
            customers.put(id, customer);
            return Optional.of(customers.get(id));
        } else {
            log.error("Customer with id {} does not exist", id);
            return Optional.empty();
        }
    }

    public boolean delete(int id) {
        customers.remove(id);
        // when connected to DB need to check if deletion was successful in DB.
        return !customers.containsKey(id);
    }

    public Optional<Customer> getById(int id) {return Optional.ofNullable(customers.get(id));}

    @Override
    public List<Customer> getCustomers() {return new ArrayList<>(customers.values());}
}
