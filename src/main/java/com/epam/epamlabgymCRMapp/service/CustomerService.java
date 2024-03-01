package com.epam.epamlabgymCRMapp.service;

import com.epam.epamlabgymCRMapp.model.Customer;
import com.epam.epamlabgymCRMapp.repository.CustomerDAO;
import com.epam.epamlabgymCRMapp.utils.RandomPasswordGenerator;
import com.epam.epamlabgymCRMapp.utils.UsernameGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CustomerService {
    private final CustomerDAO customerDAO;
    private final UsernameGenerator usernameGenerator;

    public CustomerService(CustomerDAO customerDAO, UsernameGenerator usernameGenerator) {
        this.customerDAO = customerDAO;
        this.usernameGenerator = usernameGenerator;
        log.info(">>>> CustomerService initialized");
    }

    // create/update/delete/select Customer profile
    public Optional<Customer> create(Customer customer) {
        customer.setUsername(usernameGenerator.generateUsername(customer));
        customer.setPassword(RandomPasswordGenerator.generateRandomPassword());
        log.info(">>>> Creating customer with username: " + customer.getUsername());
        return customerDAO.create(customer);
    }

    public Optional<Customer> update(Customer customer) {
        customer.setUsername(usernameGenerator.generateUsername(customer));
        return customerDAO.update(customer);
    }

    public boolean delete(int customerId) {
        log.info(">>>> Deleting customer with id: " + customerId);
        return customerDAO.delete(customerId);
    }

    public Optional<Customer> getById(int id) {
        log.info(">>>> Getting customer with id: " + id);
        return customerDAO.getById(id);
    }

    public List<Customer> getCustomers() {
        log.info(">>>> Getting customers");
        return customerDAO.getCustomers();
    }
}
