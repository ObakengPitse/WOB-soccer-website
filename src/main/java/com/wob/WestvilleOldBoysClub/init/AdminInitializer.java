package com.wob.WestvilleOldBoysClub.init;

import com.wob.WestvilleOldBoysClub.model.Admin;
import com.wob.WestvilleOldBoysClub.repository.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer implements CommandLineRunner {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminInitializer(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        createAdminIfNotExists("admin@example.com", "Admin User", "Admin@123");
        createAdminIfNotExists("johndoe@wob.com", "John Doe", "#John@123");
    }

    private void createAdminIfNotExists(String email, String name, String rawPassword) {
        if (adminRepository.findByEmailAddress(email).isEmpty()) {
            Admin admin = new Admin();
            admin.setEmailAddress(email);
            admin.setFullName(name);
            admin.setRole("Admin");
            admin.setPassword(passwordEncoder.encode(rawPassword));
            adminRepository.save(admin);
            System.out.println("Employee " + name + " created.");
        } else {
            System.out.println("Employee with email " + email + " already exists.");
        }
    }
}
