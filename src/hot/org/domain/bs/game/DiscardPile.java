package org.domain.bs.game;

import java.util.ArrayList;
import java.util.List;

public class DiscardPile {

  List<Card> pile = new ArrayList<Card>();

  public DiscardPile() {
  }

  public void discardIntoPile(List<Card> cards) {
    pile.addAll(cards);
  }

  public int getSize() {
    return pile.size();
  }

  public List<Card> viewTopNCards(int n) {
    if (n > pile.size())
      return pile;
    int maxIndex = pile.size() - 1;
    return pile.subList(maxIndex - n + 1, maxIndex);
  }

  public List<Card> drawWholePile() {
    List<Card> l = pile;
    clear();
    return l;
  }
  public void clear() {
    pile = new ArrayList<Card>();
  }

}
