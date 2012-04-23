package org.domain.bs.session;

import org.jboss.seam.annotations.Name;

@Name("refreshTimer")
public class RefreshTimer {

  public String getOneSecScript() {
    return "var t=setTimeout(\"refreshScreenComponents1sec()\"," + (1000) + "); ";
  }

  public String getFiveSecScript() {
    return "var t=setTimeout(\"refreshScreenComponents5sec()\"," + (5000) + "); ";
  }

}
