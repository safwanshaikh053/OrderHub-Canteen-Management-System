package com.canteen.canteen_backend.service;

import com.canteen.canteen_backend.entity.Staff;
import com.canteen.canteen_backend.repository.StaffRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {

    private final StaffRepository staffRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public StaffService(StaffRepository staffRepository,
                        BCryptPasswordEncoder passwordEncoder) {
        this.staffRepository = staffRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ============================
    // LOGIN STAFF / ADMIN
    // ============================
    public Staff login(String email, String rawPassword) {

        Staff staff = staffRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Invalid email or password"));

        if (!Boolean.TRUE.equals(staff.getIsActive())) {
            throw new RuntimeException("Staff account is inactive");
        }

        if (!passwordEncoder.matches(rawPassword, staff.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return staff;
    }

    // ============================
    // CRUD OPERATIONS
    // ============================
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    public Staff getStaffById(Integer id) {
        return staffRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Staff not found with id: " + id));
    }

    public Staff createStaff(Staff staff) {

        if (staffRepository.findByEmail(staff.getEmail()).isPresent()) {
            throw new RuntimeException("Staff email already exists");
        }

        staff.setPassword(
                passwordEncoder.encode(staff.getPassword())
        );

        return staffRepository.save(staff);
    }

    public Staff updateStaff(Integer id, Staff updatedStaff) {

        Staff existing = getStaffById(id);

        existing.setName(updatedStaff.getName());
        existing.setEmail(updatedStaff.getEmail());
        existing.setRole(updatedStaff.getRole());
        existing.setPhone(updatedStaff.getPhone());
        existing.setShift(updatedStaff.getShift());
        existing.setJoinDate(updatedStaff.getJoinDate());
        existing.setIsActive(updatedStaff.getIsActive());

        if (updatedStaff.getPassword() != null &&
            !updatedStaff.getPassword().isBlank()) {

            existing.setPassword(
                    passwordEncoder.encode(updatedStaff.getPassword())
            );
        }

        return staffRepository.save(existing);
    }

    public Staff toggleStaffStatus(Integer id) {
        Staff staff = getStaffById(id);
        staff.setIsActive(!staff.getIsActive());
        return staffRepository.save(staff);
    }

    public void deleteStaff(Integer id) {
        staffRepository.deleteById(id);
    }
}
