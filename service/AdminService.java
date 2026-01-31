package com.canteen.canteen_backend.service;

import com.canteen.canteen_backend.entity.Admin;
import com.canteen.canteen_backend.repository.AdminRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminService(AdminRepository adminRepository,
                        BCryptPasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // GET – All Admins
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    // GET – Admin by ID
    public Admin getAdminById(Integer id) {
        return adminRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Admin not found"));
    }

    // POST – Register Admin
    public Admin createAdmin(Admin admin) {

        if (adminRepository.findByEmail(admin.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setIsActive(true);

        return adminRepository.save(admin);
    }

    // PUT – Update Admin
    public Admin updateAdmin(Integer id, Admin updatedAdmin) {

        Admin admin = getAdminById(id);

        admin.setName(updatedAdmin.getName());
        admin.setEmail(updatedAdmin.getEmail());

        if (updatedAdmin.getPassword() != null &&
            !updatedAdmin.getPassword().isBlank()) {

            admin.setPassword(
                    passwordEncoder.encode(updatedAdmin.getPassword()));
        }

        return adminRepository.save(admin);
    }

    // PATCH – Toggle Active Status
    public Admin toggleAdminStatus(Integer id) {
        Admin admin = getAdminById(id);
        admin.setIsActive(!admin.getIsActive());
        return adminRepository.save(admin);
    }

    // DELETE – Remove Admin
    public void deleteAdmin(Integer id) {
        adminRepository.deleteById(id);
    }

    // ✅ SIMPLE LOGIN (EARLIER VERSION)
    public Admin login(String email, String password) {

        Admin admin = adminRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid email or password"));

        if (!passwordEncoder.matches(password, admin.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        if (!admin.getIsActive()) {
            throw new IllegalArgumentException("Admin account inactive");
        }

        admin.setPassword(null); // hide password
        return admin;
    }
}
