package Game;

import view.*;
import network.*;

public class GameController {
	private StartServerScreen serverScreen;
	private WaitingScreen waitingRoom;
	private JoinGameScreen joinGame;
	// Load the whats it called
	public void start() {
		SplashScreen screen1;
		screen1 = new SplashScreen(this);
	}
	
	public void getNewGameInfo() {
		serverScreen = new StartServerScreen(this);
	}
	
	public void getJoinGameInfo() {
		joinGame = new JoinGameScreen(this);
	}
	
	public void newGame() {
		waitingRoom = new WaitingScreen(serverScreen.getUsername(), new String(),new String(),new String("127.0.0.1"));
		
		Server theServer = new Server();
    	Thread serverThread = new Thread(theServer);
    	serverThread.start();
	}
}
