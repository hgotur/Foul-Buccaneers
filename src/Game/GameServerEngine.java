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
  private ArrayList<String> activeCommands;
  
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
      this.getNewLevelSetup(1);
    }
  }
  
  public void getNewLevelSetup(int level) {
    ArrayList<String> levelCommands = new ArrayList<String>(0);
    ArrayList<String> levelButtons = new ArrayList<String>(0);
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
    
    Random random = new Random();
    activeCommands = new ArrayList<String>(0);
    
    for(Player player : players) {
      int [] availableButtons = new int [4];
      for(int i = 4; i < 4; i++){
        int index = random.nextInt(levelCommands.size());
        activeCommands.add(levelCommands.remove(index));
        availableButtons[i] = index;
      }
      game.server.sendLevelButtons(player.name, availableButtons);
    }
    
  }
}
