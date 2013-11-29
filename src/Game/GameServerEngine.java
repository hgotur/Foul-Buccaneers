package Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import network.*;

public class GameServerEngine {
  private GameController game;
  private ArrayList<String> availableCommands;
  private ArrayList<Command> activeCommands;
  private int commandsSent = 0;
  ArrayList<Player> players;
  
  public boolean started = false;
  public int currentLevel;
  
  Random random = new Random();
  
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
      this.currentLevel = 1;
      game.server.sendPreparingLevel(currentLevel);
      this.getNewLevelSetup(currentLevel);
    }
  }
  
  public void getNewLevelSetup(int level) {
    ArrayList<String> levelCommands = new ArrayList<String>(0);
    try {
      File levelCommandsFile = new File(getClass().getResource("levelCommands" + level + ".txt").toURI());
      try{
        Scanner input = new Scanner(levelCommandsFile);
        while(input.hasNext()) {
          levelCommands.add(input.nextLine());
        }
      }
      catch (FileNotFoundException e) {
        System.out.println("File Not Found");
      }
    }
    catch (URISyntaxException e) {
      System.out.println("Incorrect URI format");
    }
    
    availableCommands = new ArrayList<String>(0);
    
    for(Player player : players) {
      int [] availableButtons = new int [4];
      for(int i = 0; i < 4; i++){
        int index = random.nextInt(levelCommands.size());
        availableCommands.add(levelCommands.remove(index));
        availableButtons[i] = index;
      }
      game.server.sendLevelButtons(player.name, availableButtons);
    }
    
    game.server.sendStartLevel();
    for(Player player : players) {
      this.generateCommand(player.name);
    }
  }
  
  public void generateCommand(String player) {
    int commandIndex = random.nextInt(availableCommands.size());
    activeCommands.add(new Command(player, commandIndex));
    game.server.sendCommand(player, commandIndex);
    this.commandsSent++;
  }
  
  public class Command {
    public String player;
    public int index;
    
    public Command(String thePlayer, int theIndex) {
      player = thePlayer;
      index = theIndex;
    }
  }
  
  public void recievedButton(int ID) {
    for(int i = 0; i < activeCommands.size(); i++) {
      if(activeCommands.get(i).index == ID) {
        Command complete = activeCommands.remove(i);
        
      }
    }
  }
}
