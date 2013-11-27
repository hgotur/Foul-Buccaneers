package Game;

import java.net.InetAddress;
import java.net.UnknownHostException;

import view.*;
import network.*;

public class GameController {
	//Views
  private StartServerScreen serverScreen;
	private WaitingScreen waitingRoom;
	private JoinGameScreen joinGameScreen;
	//Networks
	private Server theServer;
	private Client theClient;
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
  	theServer = new Server(this);
    Thread serverThread = new Thread(theServer);
    serverThread.start();
    
    try {
      joinGame(InetAddress.getLocalHost().getHostAddress(), serverScreen.getName());
    } catch (UnknownHostException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
	}
	
	public void joinGame(String ipaddress, String username) {
	  waitingRoom = new WaitingScreen(username, new String(),new String(),new String(ipaddress));
	  theClient = new Client(this, ipaddress);
    theClient.sendUsername(username);
    
	}
}
