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
  
  public void addPlayer(String playerName, int playerPosition) {
	  Player player = new Player(playerName);
	  players.add(player);
	  
	  game.server.sendPlayers(players);
  }
}
