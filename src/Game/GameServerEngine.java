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
}
