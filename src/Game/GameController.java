package Game;

import java.net.InetAddress;
import java.net.UnknownHostException;

import view.*;
import network.*;

public class GameController {
	//Views
  protected SplashScreen splashScreen;
  protected StartServerScreen serverScreen;
	protected WaitingScreen waitingRoom;
	protected JoinGameScreen joinGameScreen;
	protected GameStartScreen gameStartScreen;
	protected GameOverScreen gameOverScreen;
	protected GameWonScreen gameWonScreen;
	//Networks
	protected Server server;
	protected Client client;
	public static final int PORT = 45001;
	//Engine
	private GameClientEngine clientEngine;
	private GameServerEngine serverEngine;
	
	public Player player;
	
	// Load the whats it called
	public void start() {
		splashScreen = new SplashScreen(this);
	}
	
	public void getNewGameInfo() {
		serverScreen = new StartServerScreen(this, splashScreen);
	}
	
	public void getJoinGameInfo() {
		joinGameScreen = new JoinGameScreen(this, splashScreen);
	}
	
	public void newGame() {
	  serverEngine = new GameServerEngine(this);
  	server = new Server(serverEngine);
    Thread serverThread = new Thread(server);
    serverThread.start();
    
    try {
      joinGame(InetAddress.getLocalHost().getHostAddress(), player.name);
    } catch (UnknownHostException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
	}
	
	public void joinGame(String ipaddress, String username) {
	  splashScreen.setVisible(false);
	  waitingRoom = new WaitingScreen(this, ipaddress);
	  clientEngine = new GameClientEngine(this);
	  client = new Client(clientEngine, ipaddress);
    client.sendUsername(username, 0);
	}
	
	public void updatePlayerStatus(int status) {
	  player.status = status;
	  client.sendWaitingStateChange(player.name, status);
	}
	
	
}
