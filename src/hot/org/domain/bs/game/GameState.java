package org.domain.bs.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameState {

  private Long id;
  private List<String> players = new ArrayList<String>();
  private Map<String, Hand> playerHands = new HashMap<String, Hand>();
  private List<String> gameLog = new ArrayList<String>();

  private String hostUser;

  private Deck deck = new Deck();
  private DiscardPile discard = new DiscardPile();

  private int previousPlayQuantity;

  private boolean challengeAvailable = false;

  public boolean isChallengeAvailable() {
    return challengeAvailable;
  }
  public Deck getDeck() {
    return deck;
  }

  public void currentPlayerFailedChallenge() throws Exception {
    List<Card> l = discard.drawWholePile();
    playerHands.get(getCurrentPlayer()).drawIntoHand(l);
    challengeAvailable = false;
  }

  public void previousPlayerFailedChallenge() throws Exception {
    List<Card> l = discard.drawWholePile();
    playerHands.get(getPreviousPlayer()).drawIntoHand(l);
    challengeAvailable = false;
  }

  public DiscardPile getDiscard() {
    return discard;
  }
  private int turn = 0;

  public String getPreviousCardValue() throws Exception {
    if (turn == 0)
      throw new Exception("There was no previous card");
    return Deck.STANDARDCARDORDER[(turn - 1) % Deck.STANDARDCARDORDER.length];
  }
  public String getCurrentCardValue() {
    return Deck.STANDARDCARDORDER[turn % Deck.STANDARDCARDORDER.length];
  }

  public String getPreviousPlayer() throws Exception {
    if (turn == 0)
      throw new Exception("There was no previous player");
    return players.get((turn - 1) % players.size());
  }
  public String getCurrentPlayer() {
    return players.get(turn % players.size());
  }

  public Hand getPlayerHand(String name) {
    return playerHands.get(name);
  }
  public void incrementTurn() {
    challengeAvailable = true;
    turn++;
  }
  /**
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @param id
   *          the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @return the players
   */
  public List<String> getPlayers() {
    return players;
  }

  /**
   * @param players
   *          the players to set
   */
  public void setPlayers(List<String> players) {
    this.players = players;
  }

  /**
   * @return the gameLog
   */
  public List<String> getGameLog() {
    return gameLog;
  }


  /**
   * @return the hostUser
   */
  public String getHostUser() {
    return hostUser;
  }

  /**
   * @param hostUser
   *          the hostUser to set
   */
  public void setHostUser(String hostUser) {
    this.hostUser = hostUser;
  }

  /**
   * @return the turn
   */
  public int getTurn() {
    return turn;
  }


  public GameState(List<String> pl, Long i, String host) {
    players = pl;
    id = i;
    hostUser = host;
    for (String p : players) {
      playerHands.put(p, new Hand());
    }
    deck.shuffleRemaining();

    dealWholeDeck();
  }

  private void dealWholeDeck() {
    int playercounter = 0;
    while(!deck.isEmpty()) {
      Card c = deck.draw();
      String player = players.get(playercounter % players.size());
      playerHands.get(player).drawIntoHand(c);
      playercounter++;
    }
  }
  public GameState() {
  }

  /**
   * @return the previousPlayQuantity
   */
  public int getPreviousPlayQuantity() {
    return previousPlayQuantity;
  }

  /**
   * @param previousPlayQuantity
   *          the previousPlayQuantity to set
   */
  public void setPreviousPlayQuantity(int previousPlayQuantity) {
    this.previousPlayQuantity = previousPlayQuantity;
  }

}
