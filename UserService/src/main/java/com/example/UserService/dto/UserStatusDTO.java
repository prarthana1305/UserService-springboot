package com.example.UserService.dto;


import lombok.Data;

@Data
public class UserStatusDTO {
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserStatusDTO(Long userId, String checkInTime) {
        this.userId = userId;
        this.checkInTime = checkInTime;
    }
    public UserStatusDTO() {

    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    private String checkInTime;
}
