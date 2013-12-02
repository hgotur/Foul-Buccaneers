package Game;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import view.*;

public class GameClientEngine {
  private GameController game;

  private ArrayList<String> levelCommands;
  private ArrayList<String> levelButtons;
  private ArrayList<String> levelTextArray;
  private ArrayList<Integer> levelTimer;
  private ArrayList<Integer> levelShipDamage;
  
  private int curLevel;
  private int curCommandIndex;

  public GameClientEngine(GameController theGame) {
    game = theGame;

    Scanner input;
    
    // populate level commands array
    levelCommands = new ArrayList<String>(0);
    InputStream levelCommandsFile = getClass().getResourceAsStream("levelCommands.txt");
    input = new Scanner(levelCommandsFile);
    while (input.hasNext()) {
      levelCommands.add(input.nextLine());
    }

    // populate level buttons array
    levelButtons = new ArrayList<String>(0);
    InputStream levelButtonsFile = getClass().getResourceAsStream("levelButtons.txt");
    input = new Scanner(levelButtonsFile);
    while (input.hasNext()) {
      levelButtons.add(input.nextLine());
    }
    
    // populate level text array
    levelTextArray = new ArrayList<String>(0);
    InputStream levelTextFile = getClass().getResourceAsStream("levelText.txt");
    input = new Scanner(levelTextFile);
    while (input.hasNext()) {
      levelTextArray.add(input.nextLine());
    }
    
    // populate level timer and level ship damage arrays
    levelTimer = new ArrayList<Integer>(0);
    levelShipDamage = new ArrayList<Integer>(0);    
    InputStream clientLevelSettingsFile = getClass().getResourceAsStream("levelSettings.txt");
    input = new Scanner(clientLevelSettingsFile);
    while (input.hasNext()) {
      levelTimer.add(input.nextInt());
      levelShipDamage.add(input.nextInt());
      input.nextInt(); input.nextInt(); // not used by the client
    }
  }

  /*
   * Display list of players in the waiting room along with their status.
   */
  public void addPlayersToWaitingRoom(ArrayList<Player> players) {
    game.waitingRoom.clearWaitingRoom();
    int i = 0;
    for (Player player : players) {
      game.waitingRoom.addPlayer(player.name, player.status, i);
      i++;
    }
  }

  /*
   * Leave the waiting screen.
   */
  public void letsGetStarted() {
    game.waitingRoom.setVisible(false);
  }

  /*
   * Load the next level screen. Initialize level data. Stop timer on old level.
   */
  public void getNewLevelSetup(int level) {
    curLevel = level;
    if (game.levelScreen != null) {
      game.levelScreen.timer.stop();
      game.levelScreen.setVisible(false);
    }
    game.gameStartScreen = new GameStartScreen(level, levelTextArray.get(level - 1));
  }

  /*
   * Returns if this client is the one that a command refers to.
   */
  public boolean isUser(String username) {
    if (game.player.name.equals(username)) {
      return true;
    }
    return false;
  }

  /*
   * Add buttons to the level screen.
   */
  public void addButtonsToLevel(String username, ArrayList<Integer> buttons) {
    if (!isUser(username)) return;
    game.levelScreen = new LevelScreen(this, curLevel, levelTimer.get(curLevel - 1),
        levelShipDamage.get(curLevel - 1));
    ArrayList<String> buttonText = new ArrayList<String>(0);
    for (int i : buttons) {
      buttonText.add(levelButtons.get(i));
    }

    game.levelScreen.addButtons(buttonText, buttons);
  }

  /*
   * Hide the level start screen and show the level screen.
   */
  public void levelStart() {
    game.gameStartScreen.setVisible(false);
    game.levelScreen.setVisible(true);
  }

  /*
   * Read the next instruction passed to the client by the server.
   * Calls levelScreen.newCommand to display on level screen.
   */
  public void readCommand(String username, int command) {
    if (!isUser(username)) return;
    game.levelScreen.newCommand(levelCommands.get(command));
    curCommandIndex = command;
  }

  /*
   * Sends client button input to server.
   */
  public void sendButtonInput(int buttonIndex) {
    game.client.sendButtonInput(buttonIndex);
  }

  /*
   * Sends that instruction was not completed to the server.
   */
  public void sendCommandFailed() {
    game.client.sendCommandFailed(curCommandIndex);
  }

  /*
   * Displays the new ship damage on the level screen.
   */
  public void setShipDamage(int damage) {
    game.levelScreen.setShipDamage(damage);
  }

  /*
   * Displays the Game Over (players lost) screen
   */
  public void endGame() {
    game.levelScreen.setVisible(false);
    game.levelScreen.timer.stop();
    game.gameOverScreen = new GameOverScreen();
  }

  /*
   * Displays the Game Won (players won) screen
   */
  public void gameWon() {
    game.levelScreen.setVisible(false);
    game.levelScreen.timer.stop();
    game.gameWonScreen = new GameWonScreen();
  }
}
