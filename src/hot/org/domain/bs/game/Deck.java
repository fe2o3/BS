package org.domain.bs.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {

  public static String STANDARDCARDORDER[] = {
      "2",
      "3",
      "4",
      "5",
      "6",
      "7",
      "8",
      "9",
      "10",
      "J",
      "Q",
      "K",
      "A"
  };

  private static Card CARDS[] = {
      new Card("Heart", "2"),
      new Card("Heart", "3"),
      new Card("Heart", "4"),
      new Card("Heart", "5"),
      new Card("Heart", "6"),
      new Card("Heart", "7"),
      new Card("Heart", "8"),
      new Card("Heart", "9"),
      new Card("Heart", "10"),
      new Card("Heart", "J"),
      new Card("Heart", "Q"),
      new Card("Heart", "K"),
      new Card("Heart", "A"),
      new Card("Spade", "2"),
      new Card("Spade", "3"),
      new Card("Spade", "4"),
      new Card("Spade", "5"),
      new Card("Spade", "6"),
      new Card("Spade", "7"),
      new Card("Spade", "8"),
      new Card("Spade", "9"),
      new Card("Spade", "10"),
      new Card("Spade", "J"),
      new Card("Spade", "Q"),
      new Card("Spade", "K"),
      new Card("Spade", "A"),
      new Card("Club", "2"),
      new Card("Club", "3"),
      new Card("Club", "4"),
      new Card("Club", "5"),
      new Card("Club", "6"),
      new Card("Club", "7"),
      new Card("Club", "8"),
      new Card("Club", "9"),
      new Card("Club", "10"),
      new Card("Club", "J"),
      new Card("Club", "Q"),
      new Card("Club", "K"),
      new Card("Club", "A"),
      new Card("Diamond", "2"),
      new Card("Diamond", "3"),
      new Card("Diamond", "4"),
      new Card("Diamond", "5"),
      new Card("Diamond", "6"),
      new Card("Diamond", "7"),
      new Card("Diamond", "8"),
      new Card("Diamond", "9"),
      new Card("Diamond", "10"),
      new Card("Diamond", "J"),
      new Card("Diamond", "Q"),
      new Card("Diamond", "K"),
      new Card("Diamond", "A")
  };
  private int order[] = new int[CARDS.length];

  private int currentCardPointer = 0; // zero is top of stack

  public Deck() {
    for (int i = 0; i < order.length; i++) {
      order[i] = i;
    }
  }

  public void resetDeck() {
    currentCardPointer = 0;
  }

  public void shuffleRemaining() {
    List<Integer> unUsedIndeces = new ArrayList<Integer>();
    for (int i = currentCardPointer; i < CARDS.length; i++) {
      unUsedIndeces.add(order[i]);
    }
    for (int i = currentCardPointer; i < CARDS.length; i++) {
      Random r = new Random();
      int rand = Math.abs(r.nextInt()) % unUsedIndeces.size();
      order[i] = unUsedIndeces.get(rand);
      unUsedIndeces.remove(rand);
    }
  }

  public Card draw() {
    if (isEmpty()) {
      resetDeck();
      shuffleRemaining();
    }
    return CARDS[order[currentCardPointer++]];
  }

  public Card peek() {
    return CARDS[order[currentCardPointer]];
  }

  public boolean isEmpty() {
    return currentCardPointer == CARDS.length;
  }
}
