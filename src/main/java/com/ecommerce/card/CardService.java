package com.ecommerce.card;

import com.ecommerce.card.dto.CardRequest;
import com.ecommerce.card.dto.CardResponse;
import com.ecommerce.user.User;
import java.util.List;

public interface CardService {
  List<CardResponse> findAllCards(User user);

  CardResponse findCardById(long id, User user);

  CardResponse saveCard(CardRequest request, User user);

  CardResponse updateCard(long id, CardRequest request, User user);

  CardResponse deleteCardById(long id, User user);
}
