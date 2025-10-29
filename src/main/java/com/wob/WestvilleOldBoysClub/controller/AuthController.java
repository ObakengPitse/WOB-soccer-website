package com.wob.WestvilleOldBoysClub.controller;

import com.wob.WestvilleOldBoysClub.dto.AuthRequestDTO;
import com.wob.WestvilleOldBoysClub.dto.AuthResponseDTO;
import com.wob.WestvilleOldBoysClub.model.Admin;
import com.wob.WestvilleOldBoysClub.model.User;
import com.wob.WestvilleOldBoysClub.repository.AdminRepository;
import com.wob.WestvilleOldBoysClub.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth/api/v1")
public class AuthController {
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder encoder;

    public AuthController(UserRepository userRepository, PasswordEncoder encoder, AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            user.setPassword(encoder.encode(user.getPassword()));

            Optional<User> existingEmail = userRepository.findByEmailAddress(user.getEmailAddress());
            if (existingEmail.isPresent()) {
                return ResponseEntity.badRequest().body("User with same Email Address already exists!");
            }
            user.setRole("User");
            userRepository.save(user);

            return ResponseEntity.ok("Registered Successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed due to an internal error.");
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO req) {
        Optional<User> userOpt = userRepository.findByEmailAddress(req.emailAddress);
        if (userOpt.isPresent() && encoder.matches(req.password, userOpt.get().getPassword())) {
            User user = userOpt.get();

            AuthResponseDTO authResponse = new AuthResponseDTO();
            authResponse.setUserId(user.getId());
            authResponse.setFullName(user.getFullName());
            authResponse.setEmailAddress(user.getEmailAddress());
            authResponse.setRole(user.getRole());

            return ResponseEntity.ok(authResponse);
        }
        return ResponseEntity.status(401).body("Invalid login credentials");
    }

    @PostMapping("/admin/login")
    public ResponseEntity<?> employeeLogin(@RequestBody AuthRequestDTO req) {
        Optional<Admin> adminOpt = adminRepository.findByEmailAddress(req.getEmailAddress());
        if (adminOpt.isPresent() && encoder.matches(req.getPassword(), adminOpt.get().getPassword())) {
            Admin admin = adminOpt.get();

            AuthResponseDTO authResponse = new AuthResponseDTO();
            authResponse.setUserId(admin.getId());
            authResponse.setFullName(admin.getFullName());
            authResponse.setEmailAddress(admin.getEmailAddress());
            authResponse.setRole(admin.getRole());

            return ResponseEntity.ok(authResponse);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

}
