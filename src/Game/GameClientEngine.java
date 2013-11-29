package Game;

import java.util.ArrayList;
import network.Client;

public class GameClientEngine {
	private GameController game;
	
	public GameClientEngine(GameController theGame) {
	  game = theGame;
	}
	
//	public void waitingStatus(String [] players, boolean [] isReady) {
//		game.waitingRoom.waitingStatus(players, isReady);
//	}
	
	public void addPlayersToWaitingRoom(ArrayList<Player> players) {
	  System.out.println("Adding players to room");
	  game.waitingRoom.clearWaitingRoom();
	  int i = 0;
	  for(Player player : players) {
	    game.waitingRoom.addPlayer(player.name, player.status , i);
	    i++;
	  }
	}
	
	public void letsGetStarted() {
	  game.letsGetStarted();
	}
	
	public void addButtonsToLevel(ArrayList<Integer> buttons) {
		
	}
}
