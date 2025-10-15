package com.wob.WestvilleOldBoysClub.service;

import com.wob.WestvilleOldBoysClub.model.Admin;
import com.wob.WestvilleOldBoysClub.model.User;
import com.wob.WestvilleOldBoysClub.repository.AdminRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<List<?>> getAllAdmins() {
        return ResponseEntity.ok(adminRepository.findAll());
    }

    public ResponseEntity<?> createAdmin(Admin admin) {
        try {
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));

            Optional<Admin> existingEmail = adminRepository.findByEmailAddress(admin.getEmailAddress());
            if (existingEmail.isPresent()) {
                return ResponseEntity.badRequest().body("Admin with same Email Address already exists!");
            }
            admin.setRole("Admin");
            adminRepository.save(admin);

            return ResponseEntity.ok("Admin added Successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Adding Admin failed due to an internal error.");
        }
    }

    public  ResponseEntity<?> updateAdmin(Admin admin) {
        Optional<Admin> optionalAdmin = adminRepository.findById(admin.getId());
        if(optionalAdmin.isPresent()) {
            Admin foundAdmin = optionalAdmin.get();

            foundAdmin.setFullName(admin.getFullName());
            foundAdmin.setEmailAddress(admin.getEmailAddress());
            foundAdmin.setPassword(passwordEncoder.encode(admin.getPassword()));

            adminRepository.save(foundAdmin);
            return ResponseEntity.ok("User updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    public ResponseEntity<?> getAdminById(Admin admin) {
        Optional<Admin> optionalAdmin = adminRepository.findById(admin.getId());
        if(optionalAdmin.isPresent()) {
            return ResponseEntity.ok(optionalAdmin.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
        }
    }

    public ResponseEntity<?> deleteAdminById(Admin admin) {
        Optional<Admin> optionalAdmin = adminRepository.findById(admin.getId());
        if(optionalAdmin.isPresent()) {
            adminRepository.delete(optionalAdmin.get());
            return ResponseEntity.ok("Admin with ID:"+ admin.getId()+" deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
        }
    }
}
