package network;

import static java.lang.System.out;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Scanner;

import Game.*;

public class Server implements Runnable {
  
  protected ArrayList<ClientServerSocket> sockets;
  private GameServerEngine game;
  private ServerSender serverSender;
  private Thread serverAcceptThread;
  
  public Server(GameServerEngine theGame) {
	  sockets = new ArrayList<ClientServerSocket>(0);
    game = theGame;
  }

  public void run() {
    try {
      ServerSocket serverSocket = new ServerSocket(45001);
      
      serverAcceptThread = new Thread(new ServerAcceptThread(this, serverSocket));
      serverAcceptThread.start();
      
      serverSender = new ServerSender(sockets);
    }
    catch (IOException ioe) {
      out.println("ERROR: Caught exception starting server");
      out.println(ioe.getMessage());
      System.exit(7);
    }
  }
  
  public boolean hasGameStarted() {
      return game.started;
  }
  
  public void sendPlayers(ArrayList<Player> players) {
	  String message =  "U";
	  
	  for (Player player: players) {
		  message += (" " + player.name + " " + player.status);
	  }
	  
	  serverSender.sendToAll(message);
  }
  
  public void translate(String command, ArrayList<String> values, int clientID) {  
    switch (command) {
      case "U": 
        game.addPlayer(values.get(0), Integer.parseInt(values.get(1)));
        break;
      default:
        assert false;
      
    }
  }

}
