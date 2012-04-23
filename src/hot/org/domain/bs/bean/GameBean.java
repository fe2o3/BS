package org.domain.bs.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.domain.bs.application.MasterGameList;
import org.domain.bs.application.MasterUserList;
import org.domain.bs.game.Card;
import org.domain.bs.game.GameManager;
import org.domain.bs.game.Hand;
import org.domain.bs.session.UserBean;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

@Name("gameBean")
public class GameBean {


  @In(create = true)
  UserBean userBean;

  @In(create = true)
  MasterGameList masterGameList;

  @In(create = true)
  MasterUserList masterUserList;

  private String error;

  /**
   * @return the error
   */
  public String getError() {
    return error;
  }

  /**
   * @param error
   *          the error to set
   */
  public void setError(String error) {
    this.error = error;
  }

  private List<String> selectedCards = new ArrayList<String>();

  /**
   * @return the selectedCards
   */
  public List<String> getSelectedCards() {
    return selectedCards;
  }

  /**
   * @param selectedCards
   *          the selectedCards to set
   */
  public void setSelectedCards(List<String> selectedCards) {
    this.selectedCards = selectedCards;
  }

  public boolean getYourTurn() {
    return userBean.getGameState().getCurrentPlayer().equals(userBean.getUsername());

  }

  public void playTurn() {
    error = "";
    if (selectedCards.size() > 4) {
      error = " too many cards selected";
      return;
    }
    try {
      List<Card> selected = new ArrayList<Card>();
      for (String s : selectedCards) {
        selected.add(getMyHand().getCards().get(s));
      }
      GameManager.takeTurn(getMyHand(), userBean.getGameState(), selected);
    } catch (Exception e) {
      error = e.getMessage() + e.getCause();
    }
  }

  public void challenge() {
    error = "";
    try {
      GameManager.challengePrevious(userBean.getGameState());
    } catch (Exception e) {
      error = e.getMessage() + e.getCause();
    }
  }

  public boolean canChallenge() {
    return userBean.getGameState().isChallengeAvailable();
  }

  public List<String> getGameLog() {
    return userBean.getGameState().getGameLog();
  }

  public List<String> getPlayers() {
    return userBean.getGameState().getPlayers();
  }

  public String getCurrentPlayer() {
    return userBean.getGameState().getCurrentPlayer();
  }

  public String getCurrentCard() {
    return userBean.getGameState().getCurrentCardValue();
  }

  public Hand getMyHand() {
    return userBean.getGameState().getPlayerHand(userBean.getUsername());
  }

  public List<Card> getMyOrderedHand() {
    Hand h = userBean.getGameState().getPlayerHand(userBean.getUsername());
    List<Card> l = new ArrayList<Card>();
    List<String> keys = new ArrayList<String>();

    keys.addAll(h.getCards().keySet());
    Collections.sort(keys);
    Iterator it = h.getCards().keySet().iterator();
    for (String k : keys) {
      l.add(h.getCards().get(k));
    }
    return l;
  }

  public int getHandSize(String player) {
    return userBean.getGameState().getPlayerHand(player).getCards().keySet().size();
  }

  public int getPileSize()
  {
    return userBean.getGameState().getDiscard().getSize();
  }
}
