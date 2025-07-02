package com.ecommerce.card;

import com.ecommerce.card.dto.CardRequest;
import com.ecommerce.card.dto.CardResponse;
import com.ecommerce.exception.ApiException;
import com.ecommerce.user.User;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImp implements CardService {

  private final CardRepository cardRepository;
  private final CardConverter cardConverter;

  public CardServiceImp(CardRepository cardRepository, CardConverter cardConverter) {
    this.cardRepository = cardRepository;
    this.cardConverter = cardConverter;
  }

  @Override
  public List<CardResponse> findAllCards(User user) {
    if (user.getAuthority().equals("ADMIN")) {
      return cardRepository.findAll().stream()
          .map(cardConverter::toResponse)
          .collect(Collectors.toList());
    } else {
      return cardRepository.findAllByUser(user).stream()
          .map(cardConverter::toResponse)
          .collect(Collectors.toList());
    }
  }

  @Override
  public CardResponse findCardById(long id, User user) {
    Card card =
        cardRepository
            .findByIdAndUser(id, user)
            .orElseThrow(() -> new ApiException("Card not found", HttpStatus.NOT_FOUND));
    return cardConverter.toResponse(card);
  }

  @Override
  public CardResponse saveCard(CardRequest request, User user) {
    Card card = cardConverter.toEntity(request);
    card.setUser(user);
    Card savedCard = cardRepository.save(card);
    return cardConverter.toResponse(savedCard);
  }

  @Override
  public CardResponse updateCard(long id, CardRequest request, User user) {
    Card existingCard =
        cardRepository
            .findByIdAndUser(id, user)
            .orElseThrow(() -> new ApiException("Card not found", HttpStatus.NOT_FOUND));
    cardConverter.updateEntity(existingCard, request);
    return cardConverter.toResponse(cardRepository.save(existingCard));
  }

  @Override
  public CardResponse deleteCardById(long id, User user) {
    Card card =
        cardRepository
            .findByIdAndUser(id, user)
            .orElseThrow(() -> new ApiException("Card not found", HttpStatus.NOT_FOUND));
    cardRepository.delete(card);
    return cardConverter.toResponse(card);
  }
}
