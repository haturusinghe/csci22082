package com.example.blockbusterapiv3.service;

import com.example.blockbusterapiv3.model.Customer;
import com.example.blockbusterapiv3.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void saveAllCustomers(List<Customer> customers) {
        customerRepository.saveAll(customers);
    }

    public Customer updateCustomer(Long id, Customer customer) {
        Optional<Customer> existingOpt = customerRepository.findById(id);
        if (existingOpt.isEmpty()) {
            throw new IllegalArgumentException("Customer with ID " + id + " does not exist.");
        }
        Customer existing = existingOpt.get();
        // Only update allowed fields
        existing.setFirstName(customer.getFirstName());
        existing.setLastName(customer.getLastName());
        existing.setEmail(customer.getEmail());
        if (customer.getRegistrationDate() != null) {
            existing.setRegistrationDate(customer.getRegistrationDate());
        }
        if (customer.getAddress() != null) {
            existing.setAddress(customer.getAddress());
        }
        return customerRepository.save(existing);
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public List<Customer> getCustomersByName(String name) {
        return customerRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name);
    }

    public List<Customer> getCustomersByRegistrationDateBetween(LocalDate start, LocalDate end) {
        return customerRepository.findByRegistrationDateBetween(start, end);
    }

    public List<Customer> getCustomersRegisteredAfter(LocalDate date) {
        return customerRepository.findCustomersRegisteredAfter(date);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public List<Customer> getCustomersByCity(String city){
        return customerRepository.findByAddress_CityIgnoreCase(city);
    }

    public List<Object[]> getCustomerCountPerCity(){
        return customerRepository.CustomerCountPerCity();
    }
}
