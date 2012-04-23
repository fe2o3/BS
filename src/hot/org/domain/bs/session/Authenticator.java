package org.domain.bs.session;

import org.domain.bs.User;
import org.domain.bs.application.MasterUserList;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

@Name("authenticator")
public class Authenticator {
  @Logger
  private Log log;

  @In
  Identity identity;
  @In
  Credentials credentials;

  @In(create = true)
  UserBean userBean;

  @In(create = true)
  private MasterUserList masterUserList;

  public boolean authenticate() {
    if (masterUserList.userExists(credentials.getUsername()))
      return false;

    User u = new User(credentials.getUsername());
    masterUserList.addUser(credentials.getUsername(), u);
    userBean.setUsername(credentials.getUsername());
    return true;

  }

  public void logout() {
    masterUserList.removeUser(userBean.getUsername());
    identity.logout();
  }

}
