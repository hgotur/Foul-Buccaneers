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
    int readyPlayers = 0;
    for(Player player : players) {
      if(player.name.equals(playerName)) {
        player.status = status;
      }
      if(player.status == 1) {
        readyPlayers++;
      }
    }
    game.server.sendPlayers(players);
    if(readyPlayers == players.size()) {
      game.server.sendStartGame();
      this.started = true;
    }
  }
}
