package org.domain.bs.application;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;

@Name("masterIdGenerator")
@Scope(ScopeType.APPLICATION)
@Synchronized(timeout = 1000)
public class MasterIdGenerator {

  private Long id = 0L;

  public Long getNextId() {
    return id++;
  }
}
