package org.domain.bs.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hand {

  Map<String, Card> cards = new HashMap<String, Card>();

  public Hand() {

  }

  private void discard(String key) throws Exception {
    if (!cards.containsKey(key))
      throw new Exception("Cant discard something you dont have");
    cards.remove(key);
  }

  public void discard(List<Card> cardsDiscarded) throws Exception {
    for (Card c : cardsDiscarded) {
      discard(c.getKey());
    }
  }

  public void drawIntoHand(Card c) {
    cards.put(c.getKey(), c);
  }

  public void drawIntoHand(List<Card> c) {
    for (int i = 0; i < c.size(); i++) {
      drawIntoHand(c.get(i));
    }
  }

  public Map<String, Card> getCards() {
    return cards;
  }

}
