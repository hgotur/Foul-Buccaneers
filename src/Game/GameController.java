package Game;

import java.net.InetAddress;
import java.net.UnknownHostException;

import view.*;

import network.Client;
import network.Server;

/**
 * GameController
 * Handles the views and setting up a new game with the server
 * and client. Doesn't handle game logic.
 */
public class GameController {
  // Views
  protected SplashScreen splashScreen;
  protected StartServerScreen serverScreen;
  protected WaitingScreen waitingRoom;
  protected JoinGameScreen joinGameScreen;
  protected GameStartScreen gameStartScreen;
  protected GameOverScreen gameOverScreen;
  protected GameWonScreen gameWonScreen;
  protected LevelScreen levelScreen;
  // Networks
  protected Server server;
  protected Client client;
  public static final int PORT = 45001;
  // Engine
  private GameClientEngine clientEngine;
  private GameServerEngine serverEngine;

  public Player player;

  /**
   * Start Starts the game up with the splash screen view.
   */
  public void start() {
    splashScreen = new SplashScreen(this);
  }

  /**
   * GetNewGameInfo Loads the JDialog for getting information to start a new
   * game server.
   */
  public void getNewGameInfo() {
    serverScreen = new StartServerScreen(this, splashScreen);
  }

  /**
   * GetJoinGameInfo Loads the JDialog for getting information to join a game
   * server.
   */
  public void getJoinGameInfo() {
    joinGameScreen = new JoinGameScreen(this, splashScreen);
  }

  /**
   * NewGame
   * Gets the server and Game engine
   * set up for a brand new game.
   * Then joins the server host to his
   * own game as a client.
   */
  public void newGame() {
    serverEngine = new GameServerEngine(this);
    server = new Server(serverEngine);
    
    //InetAddress is used to get the local IP Address
    //to make it easier for a player to know his server IP.
    try {
      joinGame(InetAddress.getLocalHost().getHostAddress(), player.name);
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
  }

  /**
   * JoinGame
   * Gets a player's client and Game Engine
   * set up so he can join a game.
   * @param ipaddress
   * @param username
   */
  public void joinGame(String ipaddress, String username) {
    splashScreen.setVisible(false);
    waitingRoom = new WaitingScreen(this, ipaddress);
    clientEngine = new GameClientEngine(this);
    client = new Client(clientEngine, ipaddress);
    client.sendUsername(username, 0);
  }

  /**
   * UpdatePlayerStatus
   * Changes a player's status in the
   * waiting room.
   * @param status
   */
  public void updatePlayerStatus(int status) {
    player.status = status;
    client.sendWaitingStateChange(player.name, status);
  }
}
