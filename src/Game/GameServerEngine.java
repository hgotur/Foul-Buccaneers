package Game;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * GameServerEngine
 * This object handles the game logic for
 * the server, including generating commands,
 * starting and stopping games, assigning buttons
 * and any other server logic.
 * @author Pratik
 */
public class GameServerEngine {
  private GameController game;
  private ArrayList<Integer> availableCommands;
  private ArrayList<Command> activeCommands;
  ArrayList<Player> players;
  private ArrayList<String> levelCommands;
  private ArrayList<Integer> levelButtonSettings;
  private ArrayList<Integer> levelShipDamage;
  private ArrayList<Integer> levelWinSettings;

  public boolean gameWon = false;
  public boolean started = false;
  private int commandsSent;
  public int currentLevel;
  public int shipDamage;
  public int winMoves;

  Random random = new Random();

  /**
   * Constructor
   * Gets the level settings from files and 
   * sets up the some defaults.
   * @param theGame
   */
  public GameServerEngine(GameController theGame) {
    game = theGame;
    players = new ArrayList<Player>(0);
    activeCommands = new ArrayList<Command>(0);
    levelCommands = new ArrayList<String>(0);
    levelButtonSettings = new ArrayList<Integer>(0);
    levelShipDamage = new ArrayList<Integer>(0);
    levelWinSettings = new ArrayList<Integer>(0);

    InputStream levelCommandsFile = getClass().getResourceAsStream(
        "levelCommands.txt");
    InputStream levelSettingsFile = getClass().getResourceAsStream(
        "levelSettings.txt");
    Scanner input = new Scanner(levelCommandsFile);
    while (input.hasNext()) {
      levelCommands.add(input.nextLine());
    }
    input = new Scanner(levelSettingsFile);
    while (input.hasNext()) {
      input.nextInt();
      levelShipDamage.add(input.nextInt());
      levelButtonSettings.add(input.nextInt());
      levelWinSettings.add(input.nextInt());
    }
  }

  /**
   * AddsPlayer
   * Handles logic for adding a player
   * to the game.
   * @param playerName
   * @param status
   */
  public void addPlayer(String playerName, int status) {
    Player player = new Player(playerName, status);
    players.add(player);
    game.server.sendPlayers(players);
  }

  /**
   * WaitingStateChange
   * Handles logic for players in the waiting
   * room that are ready or not ready.
   * @param playerName
   * @param status
   */
  public void waitingStateChange(String playerName, int status) {
    int readyPlayers = 0;
    for (Player player : players) {
      if (player.name.equals(playerName)) {
        player.status = status;
      }
      if (player.status == 1) {
        readyPlayers++;
      }
    }
    game.server.sendPlayers(players);
    if (readyPlayers == players.size() && players.size() > 1) {
      game.server.sendStartGame();
      started = true;
      currentLevel = 1;
      game.server.sendPreparingLevel(currentLevel);
      getNewLevelSetup(currentLevel);
    }
  }

  /**
   * GetNewLevelSetup
   * Sets up the next level and 
   * sends out buttons to clients.
   * @param level
   */
  public void getNewLevelSetup(int level) {
    shipDamage = levelShipDamage.get(level - 1);
    winMoves = levelWinSettings.get(level - 1);
    commandsSent = 0;

    availableCommands = new ArrayList<Integer>(0);
    ArrayList<Integer> commandIndexes = new ArrayList<Integer>(
        levelCommands.size());
    for (int i = 0; i < levelCommands.size(); i++) {
      commandIndexes.add(i);
    }

    for (Player player : players) {
      int[] availableButtons = new int[levelButtonSettings.get(level - 1)];
      for (int i = 0; i < availableButtons.length; i++) {
        int index = random.nextInt(commandIndexes.size());
        int commandIndex = commandIndexes.remove(index);
        availableCommands.add(commandIndex);
        availableButtons[i] = commandIndex;
      }
      game.server.sendLevelButtons(player.name, availableButtons);
    }

    for (int command : availableCommands) {
      System.out.println(levelCommands.get(command));
    }

    //Pause for a few seconds so players
    //have a chance to read the game start screen.
    try {
      Thread.sleep(4000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    game.server.sendStartLevel();
    for (Player player : players) {
      generateCommand(player.name, false);
    }
  }

  /**
   * GenerateCommand
   * Gets a new command for the players to complete
   * and ends the level if they "win"
   * @param player
   * @param failed
   */
  public void generateCommand(String player, boolean failed) {
    if (!gameWon) {
      if (commandsSent > winMoves) {
        currentLevel++;
        if (currentLevel > levelWinSettings.size()) {
          game.server.sendGameWin();
          gameWon = true;
        } else {
          game.server.sendPreparingLevel(currentLevel);
          getNewLevelSetup(currentLevel);
        }
      } else {
        int commandIndex = availableCommands.get(random
            .nextInt(availableCommands.size()));
        activeCommands.add(new Command(player, commandIndex));
        game.server.sendCommand(player, commandIndex);
        if (!failed) {
          commandsSent++;
        }
      }
    }
  }

  /**
   * Command
   * A command object that stores information
   * about an active command.
   * @author Pratik
   */
  public class Command {
    public String player;
    public int index;

    public Command(String thePlayer, int theIndex) {
      player = thePlayer;
      index = theIndex;
    }
  }

  /**
   * ReceivedButton
   * Handles logic for a button clicked.
   * If the button completes a command it follows
   * the logic for the game.
   * @param ID
   */
  public void recievedButton(int ID) {
    for (int i = 0; i < activeCommands.size(); i++) {
      if (activeCommands.get(i).index == ID) {
        Command complete = activeCommands.remove(i);
        i--;
        game.server.sendCommandComplete(complete.player, complete.index);
        generateCommand(complete.player, false);
      }
    }
  }

  /**
   * CommandFailed
   * The logic for when a command is
   * returned as "failed" ie. the player
   * didn't complete the command in time.
   * @param ID
   */
  public void commandFailed(int ID) {
    for (int i = 0; i < activeCommands.size(); i++) {
      if (activeCommands.get(i).index == ID) {
        Command failed = activeCommands.remove(i);
        shipDamage--;
        if (shipDamage <= 0) {
          game.server.sendGameOver();
          return;
        }
        game.server.sendCommandFailed(failed.player, failed.index,
            shipDamage);
        generateCommand(failed.player, true);
      }
    }
  }
}
