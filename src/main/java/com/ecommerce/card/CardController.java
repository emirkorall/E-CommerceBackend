package com.ecommerce.card;


import com.ecommerce.card.dto.CardRequest;
import com.ecommerce.card.dto.CardResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/card")
public class CardController {

    private final CardService cardService;


    public CardController(CardService cardService, CardConverter cardConverter) {
        this.cardService = cardService;
    }

    @GetMapping

    public ResponseEntity<List<CardResponse>> getAllCards() {
        return ResponseEntity.ok(cardService.findAllCards());
    }

    @GetMapping("/{id}")

    public ResponseEntity<CardResponse> getCardById(@PathVariable long id) {
        return ResponseEntity.ok(cardService.findCardById(id));

    }

    @PostMapping

    public ResponseEntity<CardResponse> createCard(@Valid @RequestBody CardRequest request) {
        CardResponse response = cardService.saveCard(request);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{id}")

    public ResponseEntity<CardResponse> updateCard(@PathVariable long id, @Valid @RequestBody CardRequest request) {
        return ResponseEntity.ok(cardService.updateCard(id, request));
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<CardResponse> deleteCard(@PathVariable long id) {
        return ResponseEntity.ok(cardService.deleteCardById(id));
    }
}
