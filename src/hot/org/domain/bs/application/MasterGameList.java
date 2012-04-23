package org.domain.bs.application;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.domain.bs.game.GameState;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("masterGameList")
@Scope(ScopeType.APPLICATION)
public class MasterGameList {

  private Map<Long, GameState> listOfGames = new ConcurrentHashMap<Long, GameState>();

  @In(create = true)
  private MasterIdGenerator masterIdGenerator;

  public Long createNewGame(List<String> players, String host) {
    Long id = masterIdGenerator.getNextId();
    if (!players.contains(host))
      players.add(host);
    GameState g = new GameState(players, id, host);
    listOfGames.put(id, g);
    return id;
  }

  public GameState getGameState(Long id) {
    return listOfGames.get(id);
  }

}
