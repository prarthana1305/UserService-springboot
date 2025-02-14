package com.example.UserService.dto;


import lombok.Data;

@Data
public class CardStatusDTO {
    private Long userId;

    public CardStatusDTO(Long userId, String cardType) {
        this.userId = userId;
        this.cardType = cardType;
    }

    public CardStatusDTO() {

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    private String cardType; // For example: "metro" or "qr"
}
