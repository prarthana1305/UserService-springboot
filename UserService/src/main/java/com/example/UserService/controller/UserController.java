package com.example.UserService.controller;
import com.example.UserService.dto.CardStatusDTO;
import com.example.UserService.dto.UserProfileResponseDTO;
import com.example.UserService.model.MetroCardRequest;
import com.example.UserService.model.TravelHistory;
import com.example.UserService.model.UserProfile;
import com.example.UserService.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // New endpoint for posting user data (creating a user profile)
    @PostMapping("/profile")
    public ResponseEntity<UserProfileResponseDTO> createUserProfile(@RequestBody UserProfile userProfile) {
        UserProfileResponseDTO createdProfile = userService.createUserProfile(userProfile);
        return ResponseEntity.ok(createdProfile);
    }
    @PostMapping("/card/buy/{id}")
    public ResponseEntity<String> buyMetroCard(@RequestBody MetroCardRequest request) {
        userService.buyMetroCard(request);
        return ResponseEntity.ok(" card purchased successfully");
    }

    @GetMapping("/travel-history/{userId}")
    public ResponseEntity<List<TravelHistory>> getTravelHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getTravelHistory(userId));
    }

    // New endpoint to fetch the card status from the User Service
    @GetMapping("/card/status/{userId}")
    public ResponseEntity<CardStatusDTO> getCardStatus(@PathVariable Long userId) {
        CardStatusDTO status = userService.getCardStatus(userId);
        return ResponseEntity.ok(status);
    }





    @PostMapping("/card/cancel")
    public ResponseEntity<String> cancelPass(@RequestParam Long userId) {
        userService.cancelPass(userId);
        return ResponseEntity.ok("Metro pass cancelled");
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<UserProfileResponseDTO> getProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserProfile(userId));
    }
}