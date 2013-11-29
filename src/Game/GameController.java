package Game;

import java.net.InetAddress;
import java.net.UnknownHostException;

import view.*;
import network.*;


public class GameController {
	//Views
  protected StartServerScreen serverScreen;
	protected WaitingScreen waitingRoom;
	protected JoinGameScreen joinGameScreen;
	//Networks
	protected Server theServer;
	protected Client theClient;
	//Engine
	private GameClientEngine clientEngine;
	private GameServerEngine serverEngine;
	
	// Load the whats it called
	public void start() {
		SplashScreen screen1;
		screen1 = new SplashScreen(this);
	}
	
	public void getNewGameInfo() {
		serverScreen = new StartServerScreen(this);
	}
	
	public void getJoinGameInfo() {
		joinGameScreen = new JoinGameScreen(this);
	}
	
	public void newGame() {
	  serverEngine = new GameServerEngine(this);
  	theServer = new Server(serverEngine);
    Thread serverThread = new Thread(theServer);
    serverThread.start();
    
    try {
      joinGame(InetAddress.getLocalHost().getHostAddress(), serverScreen.getUsername());
    } catch (UnknownHostException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
	}
	
	public void joinGame(String ipaddress, String username) {
	  waitingRoom = new WaitingScreen(ipaddress);
	  theClient = new Client(this, ipaddress);
    theClient.sendUsername(username);
    
	}
}
