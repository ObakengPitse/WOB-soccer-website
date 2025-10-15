package com.wob.WestvilleOldBoysClub.dto;

public class AuthResponseDTO {
    private Long userId;
    private String fullName;
    private String emailAddress;
    private String role;

    public AuthResponseDTO() {
    }

    public AuthResponseDTO(Long userId, String fullName, String emailAddress, String role) {
        this.userId = userId;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
