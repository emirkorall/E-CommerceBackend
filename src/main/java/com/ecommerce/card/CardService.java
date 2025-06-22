package com.ecommerce.card;

import com.ecommerce.card.dto.CardRequest;
import com.ecommerce.card.dto.CardResponse;

import java.util.List;

public interface CardService {
    List<CardResponse> findAllCards();

    CardResponse findCardById(long id);

    CardResponse saveCard(CardRequest request);

    CardResponse updateCard(long id, CardRequest request);

    CardResponse deleteCardById(long id);
}

