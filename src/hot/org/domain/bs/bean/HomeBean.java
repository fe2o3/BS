package org.domain.bs.bean;

import java.util.ArrayList;
import java.util.List;

import org.domain.bs.application.MasterGameList;
import org.domain.bs.application.MasterUserList;
import org.domain.bs.session.UserBean;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

@Name("homeBean")
// @Scope(ScopeType.SESSION)
public class HomeBean {

  private List<String> selectedUsers = new ArrayList<String>();


  /**
   * @return the selectedUsers
   */
  public List<String> getSelectedUsers() {
    return selectedUsers;
  }

  /**
   * @param selectedUsers
   *          the selectedUsers to set
   */
  public void setSelectedUsers(List<String> selectedUsers) {
    this.selectedUsers = selectedUsers;
  }

  @In(create = true)
  private MasterGameList masterGameList;
  @In(create = true)
  private MasterUserList masterUserList;
  @In(create = true)
  private UserBean userBean;



  public List<String> getAvailableUsers() {

    List<String> r = new ArrayList<String>();
    for (String s : masterUserList.getUsers().keySet()) {
      if (!s.equals(userBean.getUsername()))
        r.add(s);
    }
    // _lastAvailableUsers = r;
    return r;

  }

  public String beginGame() {
    if (selectedUsers.isEmpty())
      return null;

    Long id = masterGameList.createNewGame(selectedUsers, userBean.getUsername());
    userBean.setGameId(id);
    for (String s : selectedUsers) {
      masterUserList.getUsers().get(s).setGameId(id);
    }
    return "game";
  }

  public String joinGame() {
    return "game";
  }

  // public boolean getOpponentsChanged() {
  // List<String> last = new ArrayList<String>();
  // for (int i = 0; i < _lastAvailableUsers.size(); i++) {
  // last.add(_lastAvailableUsers.get(i));
  // }
  // List<String> curr = getAvailableUsers();
  // return !curr.containsAll(last) || !last.containsAll(curr);
  // }
}
