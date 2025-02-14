package com.example.UserService.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MetroCardResponseDTO {
    private Long id;
    private String cardType;

    public MetroCardResponseDTO(Long id, String cardType) {
        this.id = id;
        this.cardType = cardType;
    }

    public MetroCardResponseDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
}
