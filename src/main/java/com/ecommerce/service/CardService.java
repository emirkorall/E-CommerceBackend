package com.ecommerce.service;

import com.ecommerce.dto.request.CardRequest;
import com.ecommerce.dto.response.CardResponse;
import com.ecommerce.entity.Card;

import java.util.List;

public interface CardService {
    List<CardResponse> findAllCards();

    CardResponse findCardById(long id);

    CardResponse saveCard(CardRequest request);

    CardResponse updateCard(long id, CardRequest request);

    CardResponse deleteCardById(long id);
}

