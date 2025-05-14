package com.example.blockbusterapiv3.controller;

import com.example.blockbusterapiv3.model.Customer;
import com.example.blockbusterapiv3.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.saveCustomer(customer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomerDetails(@PathVariable Long id, @RequestBody Customer customerDetails){
        try {
            Customer updated = customerService.updateCustomer(id, customerDetails);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchCustomer(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String name) {
        if (email != null) {
            Optional<Customer> customer = customerService.getCustomerByEmail(email);
            return customer.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } else if (name != null) {
            List<Customer> customers = customerService.getCustomersByName(name);
            return ResponseEntity.ok(customers);
        } else if (city != null) {
            return ResponseEntity.ok(customerService.getCustomersByCity(city));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/search/registration-date")
    public List<Customer> getCustomersByRegistrationDateBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return customerService.getCustomersByRegistrationDateBetween(start, end);
    }

    @GetMapping("/search/registered-after")
    public List<Customer> getCustomersRegisteredAfter(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return customerService.getCustomersRegisteredAfter(date);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count/city")
    public ResponseEntity<List<Object[]>> getCustomerCountsPerCity(){
        return ResponseEntity.ok(customerService.getCustomerCountPerCity());
    }
}
