package org.domain.bs.game;

import java.util.List;



public class GameManager {


  public static void takeTurn(Hand hand, GameState gameState, List<Card> cardsPlayed) throws Exception {
    if (gameState == null)
      throw new Exception("Null Game");
    if (hand == null)
      throw new Exception("Null hand");

    hand.discard(cardsPlayed);

    gameState.getDiscard().discardIntoPile(cardsPlayed);
    int qty = cardsPlayed.size();
    gameState.getGameLog().add(gameState.getCurrentPlayer() + " played " + qty + " " + gameState.getCurrentCardValue() + "s.");

    gameState.setPreviousPlayQuantity(qty);

    gameState.incrementTurn();

  }

  public static void challengePrevious(GameState gameState) throws Exception {
    if (gameState == null)
      throw new Exception("Null Game");



    if (!gameState.isChallengeAvailable())
      throw new Exception("Cant challenge");

    int n = gameState.getPreviousPlayQuantity();

    String val = gameState.getPreviousCardValue();
    List<Card> top = gameState.getDiscard().viewTopNCards(n);

    if (allCardsAreValue(top, val)) {
      // the previous player was honest. current player fails.
      gameState.currentPlayerFailedChallenge();
      gameState.getGameLog().add(gameState.getCurrentPlayer() + " challenged " + gameState.getPreviousPlayer() + " and lost.");
    } else {
      //
      gameState.previousPlayerFailedChallenge();
      gameState.getGameLog().add(gameState.getCurrentPlayer() + " challenged " + gameState.getPreviousPlayer() + " and won.");
    }

  }

  private static boolean allCardsAreValue(List<Card> cards, String val) {
    for (int i = 0; i < cards.size(); i++) {
      if (!cards.get(i).getValue().equals(val))
        return false;
    }
    return true;
  }

}
