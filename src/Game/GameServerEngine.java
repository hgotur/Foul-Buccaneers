package Game;

import java.util.ArrayList;

import network.*;

public class GameServerEngine {
  private GameController game;
  private Server server;
  
  ArrayList<Player> players;
  
  public Boolean started = false;
  
  public GameServerEngine(GameController theGame, Server theServer) {
    game = theGame;
    
    server = theServer;
    players = new ArrayList<Player>(0);
  }
  
  public void addPlayer(String playerName, int playerPosition) {
	  Player player = new Player(playerName);
	  players.add(player);
	  
	  
	  // send message to all clients
	  server.sendPlayers(players);
    //game.waitingRoom.addPlayer(playerName, playerPosition);
  }
}
