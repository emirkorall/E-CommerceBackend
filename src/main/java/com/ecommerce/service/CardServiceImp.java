package com.ecommerce.service;


import com.ecommerce.converter.CardConverter;
import com.ecommerce.dto.request.CardRequest;
import com.ecommerce.dto.response.CardResponse;
import com.ecommerce.entity.Card;
import com.ecommerce.exception.ApiException;
import com.ecommerce.repository.CardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class CardServiceImp implements CardService {

    private final CardRepository cardRepository;
    private final CardConverter cardConverter;

    public CardServiceImp(CardRepository cardRepository, CardConverter cardConverter) {
        this.cardRepository = cardRepository;
        this.cardConverter = cardConverter;
    }

    @Override
    public List<CardResponse> findAllCards() {
        return cardRepository.findAll().stream()
                .map(cardConverter::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CardResponse findCardById(long id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new ApiException("Card not found with id: " + id, HttpStatus.NOT_FOUND));
        return cardConverter.toResponse(card);

    }

    @Override
    public CardResponse saveCard(CardRequest request) {
        if (request == null) {
            throw new ApiException("Card data must not be null", HttpStatus.BAD_REQUEST);
        }
        Card card = cardConverter.toEntity(request);
        Card savedCard = cardRepository.save(card);
        return cardConverter.toResponse(savedCard);
    }

    @Override

    public CardResponse updateCard(long id, CardRequest request) {
        Card existingCard = cardRepository.findById(id)
                .orElseThrow(() -> new ApiException("Card not found with id: " + id, HttpStatus.NOT_FOUND));
        Card updated = cardConverter.toEntity(request);
        updated.setId(id);
        return cardConverter.toResponse(cardRepository.save(updated));
    }

    @Override
    public CardResponse deleteCardById(long id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new ApiException("Card not found with id: " + id, HttpStatus.NOT_FOUND));
        cardRepository.delete(card);
        return cardConverter.toResponse(card);
    }
}
