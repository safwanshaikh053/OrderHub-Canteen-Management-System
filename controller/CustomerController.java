package com.canteen.canteen_backend.controller;

import com.canteen.canteen_backend.dto.LoginRequest_old;
import com.canteen.canteen_backend.entity.Customer;
import com.canteen.canteen_backend.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // ✅ REGISTER CUSTOMER
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    // ✅ GET ALL
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // ✅ GET BY ID
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Integer id) {
        return customerService.getCustomerById(id);
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public Customer updateCustomer(
            @PathVariable Integer id,
            @RequestBody Customer updatedCustomer) {

        Customer existing = customerService.getCustomerById(id);

        existing.setName(updatedCustomer.getName());
        existing.setEmail(updatedCustomer.getEmail());
        existing.setPhone(updatedCustomer.getPhone());
        existing.setIsActive(updatedCustomer.getIsActive());

        if (updatedCustomer.getPassword() != null &&
            !updatedCustomer.getPassword().isBlank()) {
            existing.setPassword(updatedCustomer.getPassword());
        }

        return customerService.updateCustomer(id, existing);
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
    }

    // ✅ CUSTOMER LOGIN
    @PostMapping("/login")
    public Customer login(@RequestBody LoginRequest_old req) {
        return customerService.login(req.getEmail(), req.getPassword());
    }
}
