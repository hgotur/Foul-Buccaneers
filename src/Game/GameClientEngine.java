package Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

import view.*;
import network.Client;

public class GameClientEngine {
	private GameController game;

	
	private LevelScreen levelScreen;
	private ArrayList<String> levelCommands;
	ArrayList<String> levelButtons;
	ArrayList<String> playerButtons;
	int curLevel;
	
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
	
	public void getNewLevelSetup(int level) {
		curLevel = level;
	    levelCommands = new ArrayList<String>(0);
	    levelButtons = new ArrayList<String>(0);
	    playerButtons = new ArrayList<String>(0);
	    try {
	      File levelCommandsFile = new File(getClass().getResource("levelCommands" + level + ".txt").toURI());
	      File levelButtonsFile = new File(getClass().getResource("levelButtons" + level + ".txt").toURI());
	      try{
	        Scanner input = new Scanner(levelCommandsFile);
	        while(input.hasNext()) {
	          levelCommands.add(input.nextLine());
	        }
	        input = new Scanner(levelButtonsFile);
	        while(input.hasNext()) {
	          levelButtons.add(input.nextLine());
	        }
	      }
	      catch (FileNotFoundException e) {
	        System.out.println("File Not Found");
	      }
	    }
	    catch (URISyntaxException e) {
	      System.out.println("Incorrect URI format");
	    }
	}
	
	public boolean isUser(String username) {
		if (game.player.name.equals(username)) {
			return true;
		}
		return false;
	}
	
	public void addButtonsToLevel(String username, ArrayList<Integer> buttons) {
		if (!isUser(username)) return;
		levelScreen = new LevelScreen(this, curLevel, buttons.size());
		ArrayList<String> buttonText = new ArrayList<String>(0);
		for (int i : buttons) {
			buttonText.add(levelButtons.get(i));
		}
		
		levelScreen.addButtons(buttonText, buttons);
		
	}
	
	public void levelStart() {
		levelScreen.setVisible(true);
	}
	
	public void readCommand(String username, int command) {
		if (!isUser(username)) return;
		levelScreen.newCommand(levelCommands.get(command));
	}
	
	public void sendButtonInput(int buttonIndex) {
		game.client.sendButtonInput(buttonIndex);		
	}
}
