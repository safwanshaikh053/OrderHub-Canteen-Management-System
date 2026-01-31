package com.canteen.canteen_backend.service;

import com.canteen.canteen_backend.entity.Customer;
import com.canteen.canteen_backend.repository.CustomerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ============================
    // REGISTER CUSTOMER
    // ============================
    public Customer saveCustomer(Customer customer) {

        if (customer.getCustomerId() == null &&
            customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            throw new RuntimeException("Customer email already exists");
        }

        if (customer.getCustomerId() == null) {
            customer.setPassword(
                    passwordEncoder.encode(customer.getPassword())
            );
        }

        return customerRepository.save(customer);
    }

    // ============================
    // LOGIN CUSTOMER
    // ============================
    public Customer login(String email, String rawPassword) {

        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Invalid email or password"));

        if (!Boolean.TRUE.equals(customer.getIsActive())) {
            throw new RuntimeException("Customer account is inactive");
        }

        if (!passwordEncoder.matches(rawPassword, customer.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return customer;
    }

    // ============================
    // CRUD OPERATIONS
    // ============================
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found with id: " + id));
    }

    public Customer updateCustomer(Integer id, Customer updatedCustomer) {

        Customer existing = getCustomerById(id);

        existing.setName(updatedCustomer.getName());
        existing.setEmail(updatedCustomer.getEmail());
        existing.setPhone(updatedCustomer.getPhone());
        existing.setIsActive(updatedCustomer.getIsActive());

        if (updatedCustomer.getPassword() != null &&
            !updatedCustomer.getPassword().isBlank()) {

            existing.setPassword(
                    passwordEncoder.encode(updatedCustomer.getPassword())
            );
        }

        return customerRepository.save(existing);
    }

    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }
}
