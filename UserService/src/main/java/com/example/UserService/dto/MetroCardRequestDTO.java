package com.example.UserService.dto;
import lombok.Data;

@Data
public class MetroCardRequestDTO {
    public String getCardType() {
        return cardType;
    }

    public MetroCardRequestDTO(String cardType) {
        this.cardType = cardType;
    }

    public MetroCardRequestDTO() {

    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }


    private String cardType;
}