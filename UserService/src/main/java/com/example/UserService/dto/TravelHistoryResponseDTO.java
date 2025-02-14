package com.example.UserService.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TravelHistoryResponseDTO {
    private Long id;
    private Long userId;
    private String tripDetails;

    public TravelHistoryResponseDTO(Long id, Long userId, String tripDetails, LocalDateTime timestamp) {
        this.id = id;
        this.userId = userId;
        this.tripDetails = tripDetails;
        this.timestamp = timestamp;
    }

    public TravelHistoryResponseDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTripDetails() {
        return tripDetails;
    }

    public void setTripDetails(String tripDetails) {
        this.tripDetails = tripDetails;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    private LocalDateTime timestamp;
}