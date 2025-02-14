package com.example.UserService.service;

import com.example.UserService.dto.CardStatusDTO;
import com.example.UserService.dto.MetroCardRequestDTO;
import com.example.UserService.dto.MetroCardResponseDTO;
import com.example.UserService.dto.UserProfileResponseDTO;
import com.example.UserService.exception.MetroCardPurchaseException;
import com.example.UserService.exception.UserProfileCreationException;
import com.example.UserService.model.MetroCard;

import com.example.UserService.model.MetroCardRequest;
import com.example.UserService.model.TravelHistory;
import com.example.UserService.model.UserProfile;
import com.example.UserService.repository.MetroCardRepository;
import com.example.UserService.repository.TravelHistoryRepository;
import com.example.UserService.repository.UserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service


public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final MetroCardRepository metroCardRepository;
    private final TravelHistoryRepository travelHistoryRepository;
    private final UserProfileRepository userProfileRepository;
    private final RabbitTemplate rabbitTemplate;

    public UserService(MetroCardRepository metroCardRepository, TravelHistoryRepository travelHistoryRepository, UserProfileRepository userProfileRepository, RabbitTemplate rabbitTemplate) {
        this.metroCardRepository = metroCardRepository;
        this.travelHistoryRepository = travelHistoryRepository;
        this.userProfileRepository = userProfileRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    // Updated method: userId is provided separately and only cardType is in the request DTO
    public MetroCardResponseDTO buyMetroCard(Long userId, MetroCardRequestDTO requestDTO) {
        log.info("Processing buyMetroCard request for user: {}", userId);
        try {
            MetroCard metroCard = new MetroCard();
            metroCard.setUserId(userId);
            metroCard.setCardType(requestDTO.getCardType());
            metroCard.setPurchaseDate(LocalDateTime.now());

            MetroCard savedCard = metroCardRepository.save(metroCard);
            log.info("Metro card saved with id {} for user: {} with card type: {}", savedCard.getId(), userId, requestDTO.getCardType());

            // Publish an event to RabbitMQ
            rabbitTemplate.convertAndSend("user.exchange", "user.card.buy", savedCard);
            log.info("Published metro card buy event to RabbitMQ for user: {}", userId);

            // Build response DTO
            MetroCardResponseDTO responseDTO = new MetroCardResponseDTO();
            responseDTO.setId(savedCard.getId());
            responseDTO.setCardType(savedCard.getCardType());

            return responseDTO;
        } catch (Exception e) {
            log.error("Failed to purchase metro card for user: {}", userId, e);
            throw new MetroCardPurchaseException("Failed to purchase metro card for user: " + userId, e);
        }
    }


    // Retrieve the last 10 travel records for the user
    public List<TravelHistory> getTravelHistory(Long userId) {
        log.info("Fetching travel history for user: {}", userId);
        List<TravelHistory> history = travelHistoryRepository.findTop10ByUserIdOrderByTimestampDesc(userId);
        log.info("Fetched {} travel records for user: {}", history.size(), userId);
        return history;
    }

    public void cancelPass(Long userId) {
        log.info("Cancelling metro pass for user: {}", userId);
        log.info("Metro pass cancelled for user: {}", userId);
    }

    // Retrieve the user profile; if not found, return a default profile
    public UserProfileResponseDTO getUserProfile(Long userId) {
        log.info("Retrieving user profile for user: {}", userId);
        UserProfile profile = userProfileRepository.findById(userId)
                .orElse(new UserProfile(userId, "Unknown", "unknown@example.com"));
        UserProfileResponseDTO responseDTO = mapToUserProfileResponseDTO(profile);
        log.info("User profile retrieved for user: {}: {}", userId, responseDTO);
        return responseDTO;
    }

    // Create a new user profile using the request DTO and return a response DTO
    @Async
    public UserProfileResponseDTO createUserProfile(UserProfile requestDTO) {
        try {
            log.info("Creating new user profile for user: {}", requestDTO.getUserId());
            UserProfile userProfile = new UserProfile(requestDTO.getUserId(), requestDTO.getName(), requestDTO.getEmail());
            UserProfile createdProfile = userProfileRepository.save(userProfile);
            UserProfileResponseDTO responseDTO = mapToUserProfileResponseDTO(createdProfile);
            log.info("User profile created successfully: {}", responseDTO);
            return responseDTO;
        } catch (Exception e) {
            log.error("Failed to create user profile for user: {}", requestDTO.getUserId(), e);
            throw new UserProfileCreationException("Failed to create user profile for user: " + requestDTO.getUserId(), e);
        }
    }

    private UserProfileResponseDTO mapToUserProfileResponseDTO(UserProfile profile) {
        UserProfileResponseDTO dto = new UserProfileResponseDTO();
        dto.setUserId(profile.getUserId());
        dto.setName(profile.getName());
        dto.setEmail(profile.getEmail());
        return dto;
    }


    public CardStatusDTO getCardStatus(Long userId) {
        log.info("Fetching card status for user: {}", userId);
        List<MetroCard> cards = metroCardRepository.findByUserIdOrderByPurchaseDateDesc(userId);
        CardStatusDTO dto = new CardStatusDTO();
        dto.setUserId(userId);
        if (cards.isEmpty()) {
            log.info("No metro card record found for user: {}", userId);
            dto.setCardType("none");
        } else {
            MetroCard latestCard = cards.get(0);
            dto.setCardType(latestCard.getCardType());
            log.info("Fetched card status for user: {} is {}", userId, latestCard.getCardType());
        }
        return dto;
    }

    public void buyMetroCard(MetroCardRequest request) {
    }
}
