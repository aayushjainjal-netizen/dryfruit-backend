package com.dryfruit.backend.service;

import com.dryfruit.backend.model.Customer;
import com.dryfruit.backend.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // ADD CUSTOMER
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // GET ALL CUSTOMERS
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // GET CUSTOMER BY ID
    public Customer getCustomerById(String customerId) {
        return customerRepository.findById(customerId)
                .orElse(null);
    }

    // UPDATE CUSTOMER
    public Customer updateCustomer(String customerId, Customer updatedCustomer) {
        Customer existing = getCustomerById(customerId);
        if (existing == null) return null;

        existing.setName(updatedCustomer.getName());
        existing.setEmail(updatedCustomer.getEmail());
        existing.setPhone(updatedCustomer.getPhone());
        existing.setAddress(updatedCustomer.getAddress());

        return customerRepository.save(existing);
    }

    // DELETE CUSTOMER
    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }
}
