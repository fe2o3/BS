package org.domain.bs.session;

import org.domain.bs.User;
import org.domain.bs.application.MasterGameList;
import org.domain.bs.application.MasterUserList;
import org.domain.bs.game.GameState;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("userBean")
@Scope(ScopeType.SESSION)
public class UserBean {

  private String username;

  @In(create = true)
  MasterGameList masterGameList;

  @In(create = true)
  MasterUserList masterUserList;



  public User getUser() {
    return masterUserList.getUsers().get(username);
  }

  public GameState getGameState() {
    if (getGameId() == null)
      return new GameState();
    return masterGameList.getGameState(getGameId());
  }

  /**
   * @return the gameId
   */
  public Long getGameId() {
    return getUser().getGameId();
  }

  public void setGameId(Long gid) {
    getUser().setGameId(gid);
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
