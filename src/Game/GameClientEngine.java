package Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
	ArrayList<String> levelTextArray;
	ArrayList<Integer> levelTimer;
	ArrayList<Integer> levelShipDamage;
	int curLevel;
	int curCommandIndex;
	
	public GameClientEngine(GameController theGame) {
	  game = theGame;
	
	  
		  levelCommands = new ArrayList<String>(0);
		    levelButtons = new ArrayList<String>(0);
		    playerButtons = new ArrayList<String>(0);
	    InputStream levelCommandsFile = getClass().getResourceAsStream("levelCommands.txt");
	    InputStream levelButtonsFile = getClass().getResourceAsStream("levelButtons.txt");
	    InputStream levelTextFile = getClass().getResourceAsStream("levelText.txt");
	    InputStream clientLevelSettingsFile = getClass().getResourceAsStream("clientLevelSettings.txt");
	    Scanner input = new Scanner(levelCommandsFile);
	    while(input.hasNext()) {
	      levelCommands.add(input.nextLine());
	    }
	    input = new Scanner(levelButtonsFile);
	    while(input.hasNext()) {
	      levelButtons.add(input.nextLine());
	    }
	    input = new Scanner(levelTextFile);
	    while (input.hasNext()) {
	    	levelTextArray.add(input.nextLine());
	    }
	    input = new Scanner(clientLevelSettingsFile);
	    while (input.hasNext()) {
	    	levelTimer.add(input.nextInt());
	    	levelShipDamage.add(input.nextInt());
	    }
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
		game.waitingRoom.setVisible(false);
	}
	
	public void getNewLevelSetup(int level) {
		curLevel = level;
		game.gameStartScreen = new GameStartScreen(level, levelTextArray.get(level - 1));
	    
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
		game.gameStartScreen.setVisible(false);
		levelScreen.setVisible(true);
	}
	
	public void readCommand(String username, int command) {
		if (!isUser(username)) return;
		levelScreen.newCommand(levelCommands.get(command));
		curCommandIndex = command;
	}
	
	public void sendButtonInput(int buttonIndex) {
		game.client.sendButtonInput(buttonIndex);		
	}
	
	public void sendCommandFailed() {
		game.client.sendCommandFailed(curCommandIndex);
	}
	
	public void setShipDamage(int damage) {
	  levelScreen.setShipDamage(damage);
	}
}
