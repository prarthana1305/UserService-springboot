package com.example.UserService.repository;


import com.example.UserService.model.TravelHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelHistoryRepository extends JpaRepository<TravelHistory, Long> {
    List<TravelHistory> findTop10ByUserIdOrderByTimestampDesc(Long userId);
}
