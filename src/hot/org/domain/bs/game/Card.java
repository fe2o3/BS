package org.domain.bs.game;

public class Card {

  private String suit;
  private String value;

  public Card(String s, String v) {
    suit = s;
    value = v;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof Card)) {
      return false;
    }
    Card other = (Card) obj;
    if (getKey() == null) {
      if (other.getKey() != null) {
        return false;
      }
    } else if (!getKey().equals(other.getKey())) {
      return false;
    }
    return true;

  }

  public String getKey() {
    return value + suit;
  }

  /**
   * @return the suit
   */
  public String getSuit() {
    return suit;
  }

  /**
   * @param suit
   *          the suit to set
   */
  public void setSuit(String suit) {
    this.suit = suit;
  }

  /**
   * @return the value
   */
  public String getValue() {
    return value;
  }

  /**
   * @param value
   *          the value to set
   */
  public void setValue(String value) {
    this.value = value;
  }

}
