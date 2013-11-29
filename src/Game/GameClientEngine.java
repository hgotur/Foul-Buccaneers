package Game;

import java.util.ArrayList;
import network.Client;

public class GameClientEngine {
	private GameController game;
	private Client client;
	
	public GameClientEngine(GameController theGame, Client theClient) {
	  game = theGame;
	  client = theClient;
	}
	
	public void waitingStatus(String [] players, boolean [] isReady) {
		game.waitingRoom.waitingStatus(players, isReady);
	}
	
	public void addPlayersToWaitingRoom(ArrayList<Player> players) {
	  game.waitingRoom.clearWaitingRoom();
	  int i = 0;
	  for(Player player : players) {
	    game.waitingRoom.addPlayer(player.name, i);
	    i++;
	  }
	}
}
