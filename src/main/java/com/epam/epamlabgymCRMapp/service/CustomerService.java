package com.epam.epamlabgymCRMapp.service;

import com.epam.epamlabgymCRMapp.model.Customer;
import com.epam.epamlabgymCRMapp.repository.CustomerRepository;
import com.epam.epamlabgymCRMapp.utils.RandomPasswordGenerator;
import com.epam.epamlabgymCRMapp.utils.UsernameGenerator;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

import java.util.Optional;

@Slf4j
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final UsernameGenerator usernameGenerator;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, UsernameGenerator usernameGenerator) {
        this.customerRepository = customerRepository;
        this.usernameGenerator = usernameGenerator;
    }

    @Transactional
    public Optional<Customer> create(Customer customer) {

        if (UserService.isInvalid(customer)) {
            return Optional.empty();
        }

        try {
            customer.setUsername(usernameGenerator.generateUsername(customer));
            customer.setPassword(RandomPasswordGenerator.generateRandomPassword());
            log.info(">>>> Creating customer with username: " + customer.getUsername());
            return Optional.of(customerRepository.save(customer));
        } catch (Exception e) {
            log.error("error creating customer", e);
            return Optional.empty();
        }
    }

    @Transactional
    public Optional<Customer> update(Customer customer, String username, String password) {

        if (isInvalidUsernamePassword(customer, username, password)) {
            log.error("invalid username or password");
            return Optional.empty();
        }

        if (UserService.isInvalid(customer)) {
            return Optional.empty();
        }
        try {
            customer.setUsername(usernameGenerator.generateUsername(customer));
            log.info(">>>> Creating customer with username: " + customer.getUsername());
            return Optional.of(customerRepository.save(customer));
        } catch (Exception e) {
            log.error("error updating customer", e);
            return Optional.empty();
        }
    }

    public Optional<Customer> getByUsername(String username) {
        log.info(">>>> Getting customer with username: " + username);
        return customerRepository.findByUsername(username);
    }

    public boolean isValidUsernamePassword(Customer customer, String username, String password) {
        Customer customerInDb;
        try {
            Optional<Customer> customerOptional = customerRepository.findByUsernameAndPassword(username, password);
            if (customerOptional.isEmpty()) {
                log.error("Could not find the customer");
                return false;
            }
            customerInDb = customerOptional.get();
        } catch (Exception e) {
            log.error("something went wrong", e);
            return false;
        }
        return customerInDb.equals(customer);
    }

    public boolean isInvalidUsernamePassword(Customer customer, String username, String password) {
        return !isValidUsernamePassword(customer, username, password);
    }

    @Transactional
    public Optional<Customer> updatePassword(Customer customer, String username, String currentPassword, String newPassword) {
        if (isInvalidUsernamePassword(customer, username, currentPassword)) {
            log.error("invalid username or password");
            return Optional.empty();
        }

        try {
            customer.setPassword(newPassword);
            return Optional.of(customerRepository.save(customer));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Transactional
    public boolean activateCustomer(Customer customer, String username, String password) {
        if (isInvalidUsernamePassword(customer, username, password)) {
            log.error("invalid username or password");
            return false;
        }

        if (customer.getIsActive()) {
            log.info("customer is already active");
            return false;
        }
        try {
            customer.setIsActive(true);
            customerRepository.save(customer);
            log.info("customer is active");
            return true;
        } catch (Exception e) {
            log.error("couldn't activate the customer");
            return false;
        }
    }

    @Transactional
    public boolean deactivateCustomer(Customer customer, String username, String password) {

        if (isInvalidUsernamePassword(customer, username, password)) {
            log.error("invalid username or password");
            return false;
        }

        if (!customer.getIsActive()) {
            log.info("customer is already deactivated");
            return false;
        }

        try {
            customer.setIsActive(false);
            customerRepository.save(customer);
            log.info("customer is deactivated");
            return true;
        } catch (Exception e) {
            log.error("couldn't deactivate the customer");
            return false;
        }
    }

    @Transactional
    public boolean delete(String username, String password) {
        Optional<Customer> optionalCustomer = getByUsername(username);
        if (optionalCustomer.isEmpty()) {
            log.error("wrong username or password");
            return false;
        }
        Customer customer = optionalCustomer.get();
        if (isInvalidUsernamePassword(customer, username, password)) {
            log.error("wrong username or password");
            return false;
        }
        try {
            log.info(">>>> Deleting customer with username: " + username);
            customerRepository.delete(customer);
            return true;
        } catch (Exception e) {
            log.error("couldn't delete the customer");
            return false;
        }
    }
}
