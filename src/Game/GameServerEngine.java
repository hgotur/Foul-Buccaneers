package Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import network.*;

public class GameServerEngine {
  private GameController game;
  private ArrayList<Integer> availableCommands;
  private ArrayList<Command> activeCommands;
  ArrayList<Player> players;
  private ArrayList<String> levelCommands;
  private ArrayList<Integer> levelButtonSettings;
  private ArrayList<Integer> levelShipDamage;
  private ArrayList<Integer> levelWinSettings;
  
  public boolean started = false;
  private int commandsSent;
  public int currentLevel;
  public int shipDamage;
  public int winMoves;
  
  Random random = new Random();
  
  public GameServerEngine(GameController theGame) {
    game = theGame;
    players = new ArrayList<Player>(0);
    activeCommands = new ArrayList<Command>(0);
    levelCommands = new ArrayList<String>(0);
    levelButtonSettings = new ArrayList<Integer>(0);
    levelShipDamage = new ArrayList<Integer>(0);
    levelWinSettings = new ArrayList<Integer>(0);
    
    InputStream levelCommandsFile = getClass().getResourceAsStream("levelCommands.txt");
    InputStream levelSettingsFile = getClass().getResourceAsStream("levelSettings.txt");
    Scanner input = new Scanner(levelCommandsFile);
    while(input.hasNext()) {
      levelCommands.add(input.nextLine());
    }
    input = new Scanner(levelSettingsFile);
    while(input.hasNext()) {
      input.nextInt();
      levelShipDamage.add(input.nextInt());
      levelButtonSettings.add(input.nextInt());
      levelWinSettings.add(input.nextInt());
    }
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
    if(readyPlayers == players.size() && players.size() > 1) {
      game.server.sendStartGame();
      this.started = true;
      this.currentLevel = 1;
      game.server.sendPreparingLevel(currentLevel);
      this.getNewLevelSetup(currentLevel);
    }
  }
  
  public void getNewLevelSetup(int level) {
    shipDamage = levelShipDamage.get(level-1);
    winMoves = levelWinSettings.get(level-1);
    commandsSent = 0;
    
    availableCommands = new ArrayList<Integer>(0);
    ArrayList<Integer> commandIndexes = new ArrayList<Integer>(levelCommands.size());
    for(int i = 0; i < levelCommands.size(); i++) {
      commandIndexes.add(i);
    }
    
    for(Player player : players) {
      int [] availableButtons = new int [levelButtonSettings.get(level-1)];
      for(int i = 0; i < availableButtons.length; i++){
        int index = random.nextInt(commandIndexes.size());
        int commandIndex = commandIndexes.remove(index);
        availableCommands.add(commandIndex);
        availableButtons[i] = commandIndex;
      }
      game.server.sendLevelButtons(player.name, availableButtons);
    }
    
    for(int command : availableCommands) {
      System.out.println(levelCommands.get(command));
    }
    
    try {
      Thread.sleep(4000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    game.server.sendStartLevel();
    for(Player player : players) {
      this.generateCommand(player.name);
    }
  }
  
  public void generateCommand(String player) {
    int commandIndex = availableCommands.get(random.nextInt(availableCommands.size()));
    activeCommands.add(new Command(player, commandIndex));
    game.server.sendCommand(player, commandIndex);
    this.commandsSent++;
    if(this.commandsSent > winMoves) {
      currentLevel++;
      if(currentLevel > levelWinSettings.size()){
        game.server.sendGameWin();
      }
      else{
        game.server.sendPreparingLevel(currentLevel);
        getNewLevelSetup(currentLevel);
      }
    }
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
        i--;
        game.server.sendCommandComplete(complete.player, complete.index);
        this.generateCommand(complete.player);
      }
    }
  }
  
  public void commandFailed(int ID) {
    for(int i = 0; i < activeCommands.size(); i++) {
      if(activeCommands.get(i).index == ID) {
        Command failed = activeCommands.remove(i);
        this.shipDamage--;
        if(this.shipDamage == 0) {
          game.server.sendGameOver();
          return;
        }
        game.server.sendCommandFailed(failed.player, failed.index, this.shipDamage);
        this.generateCommand(failed.player);
      }
    }
  }
}
