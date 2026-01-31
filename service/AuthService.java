package com.canteen.canteen_backend.service;

import com.canteen.canteen_backend.entity.Admin;
import com.canteen.canteen_backend.entity.Customer;
import com.canteen.canteen_backend.entity.Staff;
import com.canteen.canteen_backend.exception.UnauthorizedException;
import com.canteen.canteen_backend.repository.AdminRepository;
import com.canteen.canteen_backend.repository.CustomerRepository;
import com.canteen.canteen_backend.repository.StaffRepository;
import com.canteen.canteen_backend.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final CustomerRepository customerRepo;
    private final AdminRepository adminRepo;
    private final StaffRepository staffRepo;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    // ✅ Manual constructor
    public AuthService(CustomerRepository customerRepo,
                       AdminRepository adminRepo,
                       StaffRepository staffRepo,
                       JwtUtil jwtUtil,
                       BCryptPasswordEncoder passwordEncoder) {

        this.customerRepo = customerRepo;
        this.adminRepo = adminRepo;
        this.staffRepo = staffRepo;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(String email, String password) {

        // 🔐 CUSTOMER login
        Customer customer = customerRepo.findByEmail(email).orElse(null);
        if (customer != null) {

            if (!customer.getIsActive()) {
                throw new UnauthorizedException("Customer account is inactive");
            }

            if (!passwordEncoder.matches(password, customer.getPassword())) {
                throw new UnauthorizedException("Invalid credentials");
            }

            return jwtUtil.generateToken(email, "CUSTOMER");
        }

        // 🔐 ADMIN login
        Admin admin = adminRepo.findByEmail(email).orElse(null);
        if (admin != null) {

            if (!admin.getIsActive()) {
                throw new UnauthorizedException("Admin account is inactive");
            }

            if (!passwordEncoder.matches(password, admin.getPassword())) {
                throw new UnauthorizedException("Invalid credentials");
            }

            return jwtUtil.generateToken(email, "ADMIN");
        }

        // 🔐 STAFF login
        Staff staff = staffRepo.findByEmail(email).orElse(null);
        if (staff != null) {

            if (!staff.getIsActive()) {
                throw new UnauthorizedException("Staff account is inactive");
            }

            if (!passwordEncoder.matches(password, staff.getPassword())) {
                throw new UnauthorizedException("Invalid credentials");
            }

            return jwtUtil.generateToken(email, "STAFF");
        }

        throw new UnauthorizedException("Invalid credentials");
    }
}
