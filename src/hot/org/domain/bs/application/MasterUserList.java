package org.domain.bs.application;

import java.util.HashMap;
import java.util.Map;

import org.domain.bs.User;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;

@Name("masterUserList")
@Scope(ScopeType.APPLICATION)
@Synchronized(timeout = 1000)
public class MasterUserList {

  private Map<String, User> users = new HashMap<String, User>();

  public Map<String, User> getUsers() {
    return users;
  }

  public boolean userExists(String username) {
    return users.containsKey(username);
  }

  public void addUser(String username, User u) {
    users.put(username, u);
  }

  public void removeUser(String username) {
    users.remove(username);
  }

}
