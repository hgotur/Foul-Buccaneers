package Game;

import java.util.ArrayList;

import network.*;

public class GameServerEngine {
  private GameController game;
  
  ArrayList<Player> players;
  
  public boolean started = false;
  
  public GameServerEngine(GameController theGame) {
    game = theGame;
    players = new ArrayList<Player>(0);
  }
  
  public void addPlayer(String playerName, int status) {
	  Player player = new Player(playerName, status);
	  players.add(player);

	  game.server.sendPlayers(players);
  }
  
  public void waitingStateChange(String playerName, int status) {
    for(Player player : players) {
      if(player.name.equals(playerName)) {
        player.status = status;
      }
    }
    game.server.sendPlayers(players);
  }
}
