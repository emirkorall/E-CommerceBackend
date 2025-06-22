package com.ecommerce.card;


import com.ecommerce.card.dto.CardRequest;
import com.ecommerce.card.dto.CardResponse;
import org.springframework.stereotype.Component;

@Component
public class CardConverter {

    public Card toEntity(CardRequest request) {
        Card card = new Card();
        card.setCardNo(request.cardNo());
        card.setCardName(request.cardName());
        card.setCardMonth(request.cardMonth());
        card.setCardYear(request.cardYear());
        return card;
    }


    public CardResponse toResponse(Card entity) {
        String maskedCardNo = maskCardNo(entity.getCardNo());
        return new CardResponse(entity.getId(),
                maskedCardNo,
                entity.getCardName(),
                entity.getCardMonth(),
                entity.getCardYear());
    }


    private String maskCardNo(String cardNo) {
        if (cardNo == null || cardNo.length() < 4) {
            return "****";
        }

        int length = cardNo.length();
        String lastFourDigits = cardNo.substring(length - 4);
        return "**** **** **** " + lastFourDigits;
    }
}
