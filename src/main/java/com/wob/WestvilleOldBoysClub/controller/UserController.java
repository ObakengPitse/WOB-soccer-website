package com.wob.WestvilleOldBoysClub.controller;

import com.wob.WestvilleOldBoysClub.model.User;
import com.wob.WestvilleOldBoysClub.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/get-all-users")
    public ResponseEntity<?> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/create-user")
    public  ResponseEntity<?> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/update-user")
    public  ResponseEntity<?> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @PostMapping("/get-user-by-id")
    public  ResponseEntity<?> getUserById(@RequestBody User user) {
        return userService.getUserById(user);
    }

    @PostMapping("/delete-user-by-id")
    public  ResponseEntity<?> deleteUserById(@RequestBody User user) {
        return userService.deleteUserById(user);
    }

}
