package com.wob.WestvilleOldBoysClub.service;

import com.wob.WestvilleOldBoysClub.model.User;
import com.wob.WestvilleOldBoysClub.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public ResponseEntity<List<?>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    public ResponseEntity<?> createUser(User user) {
        try {
            user.setPassword(encoder.encode(user.getPassword()));

            Optional<User> existingEmail = userRepository.findByEmailAddress(user.getEmailAddress());
            if (existingEmail.isPresent()) {
                return ResponseEntity.badRequest().body("User with same Email Address already exists!");
            }
            user.setRole("User");
            userRepository.save(user);

            return ResponseEntity.ok("User added Successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Adding user failed due to an internal error.");
        }
    }

    public  ResponseEntity<?> updateUser(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if(optionalUser.isPresent()) {
            User foundUser = optionalUser.get();

            foundUser.setFullName(user.getFullName());
            foundUser.setEmailAddress(user.getEmailAddress());
            foundUser.setPassword(encoder.encode(user.getPassword()));

            userRepository.save(foundUser);
            return ResponseEntity.ok("User updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    public ResponseEntity<?> getUserById(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if(optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    public ResponseEntity<?> deleteUserById(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if(optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
            return ResponseEntity.ok("User with ID:"+ user.getId()+" deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}
