package org.domain.bs;

public class User {

  private String username;
  private Long gameId = null;

  public User(String name) {
    username = name;
  }

  /**
   * @return the gameId
   */
  public Long getGameId() {
    return gameId;
  }

  /**
   * @param gameId
   *          the gameId to set
   */
  public void setGameId(Long gameId) {
    this.gameId = gameId;
  }

  /**
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * @param username
   *          the username to set
   */
  public void setUsername(String username) {
    this.username = username;
  }

}
