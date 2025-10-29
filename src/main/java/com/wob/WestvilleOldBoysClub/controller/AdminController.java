package com.wob.WestvilleOldBoysClub.controller;

import com.wob.WestvilleOldBoysClub.model.Admin;
import com.wob.WestvilleOldBoysClub.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin/api/v1")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/get-all-admins")
    public ResponseEntity<?> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @PostMapping("/create-admin")
    public  ResponseEntity<?> createAdmin(@RequestBody Admin admin) {
        return adminService.createAdmin(admin);
    }

    @PostMapping("/update-admin")
    public  ResponseEntity<?> updateAdmin(@RequestBody Admin admin) {
        return adminService.updateAdmin(admin);
    }

    @PostMapping("/get-admin-by-id")
    public  ResponseEntity<?> getAdminById(@RequestBody Admin admin) {
        return adminService.getAdminById(admin);
    }

    @PostMapping("/delete-admin-by-id")
    public  ResponseEntity<?> deleteAdminById(@RequestBody Admin admin) {
        return adminService.deleteAdminById(admin);
    }
}
