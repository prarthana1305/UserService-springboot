package com.example.UserService.repository;
import com.example.UserService.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    // Additional query methods can be defined here.
}
