package com.ecommerce.card;

import com.ecommerce.card.dto.CardRequest;
import com.ecommerce.card.dto.CardResponse;
import com.ecommerce.user.User;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/card")
public class CardController {

  private final CardService cardService;

  public CardController(CardService cardService, CardConverter cardConverter) {
    this.cardService = cardService;
  }

  @GetMapping
  public ResponseEntity<List<CardResponse>> getAllCards(@AuthenticationPrincipal User user) {
    return ResponseEntity.ok(cardService.findAllCards(user));
  }

  @GetMapping("/{id}")
  public ResponseEntity<CardResponse> getCardById(
      @PathVariable long id, @AuthenticationPrincipal User user) {
    return ResponseEntity.ok(cardService.findCardById(id, user));
  }

  @PostMapping
  public ResponseEntity<CardResponse> createCard(
      @RequestBody CardRequest request, @AuthenticationPrincipal User user) {
    return ResponseEntity.status(201).body(cardService.saveCard(request, user));
  }

  @PutMapping("/{id}")
  public ResponseEntity<CardResponse> updateCard(
      @PathVariable long id, @RequestBody CardRequest request, @AuthenticationPrincipal User user) {
    return ResponseEntity.ok(cardService.updateCard(id, request, user));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<CardResponse> deleteCard(
      @PathVariable long id, @AuthenticationPrincipal User user) {
    return ResponseEntity.ok(cardService.deleteCardById(id, user));
  }
}
