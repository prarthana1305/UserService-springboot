package com.example.UserService.repository;


import com.example.UserService.model.MetroCard;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.UserService.model.MetroCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MetroCardRepository extends JpaRepository<MetroCard, Long> {
    // Fetch all metro cards for a user, ordered by purchase date descending
    List<MetroCard> findByUserIdOrderByPurchaseDateDesc(Long userId);
}
