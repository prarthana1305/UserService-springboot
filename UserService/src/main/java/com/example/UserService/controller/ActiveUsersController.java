package com.example.UserService.controller;


import com.example.UserService.dto.UserStatusDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/active-users")

public class ActiveUsersController {

    private final RestTemplate restTemplate;

    public ActiveUsersController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }



    @GetMapping
    public ResponseEntity<List<UserStatusDTO>> getActiveUsers() {
        // URL of the external service running on port 8081
        String url = "http://localhost:8080/active-users";

        // Retrieve the response as an array of UserStatusDTO
        ResponseEntity<UserStatusDTO[]> responseEntity = restTemplate.getForEntity(url, UserStatusDTO[].class);
        UserStatusDTO[] userStatusArray = responseEntity.getBody();

        // Convert the array to a List
        List<UserStatusDTO> users = Arrays.asList(userStatusArray);

        return ResponseEntity.ok(users);
    }
}
