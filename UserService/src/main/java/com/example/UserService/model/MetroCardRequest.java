package com.example.UserService.model;



import lombok.Data;

@Data
public class MetroCardRequest {
    private Long userId;
    private String cardType;

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
}
