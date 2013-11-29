package network;

import static java.lang.System.out;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Scanner;

import Game.*;

public class Server implements Runnable {
//  private ClientServerSocket server1;
//  private ClientServerSocket server2;
//  private ClientServerSocket server3;
//  private ClientServerSocket server4;
  
  protected ArrayList<ClientServerSocket> sockets;
  private GameServerEngine game;
  private ServerSender serverSender;
  private Thread serverAcceptThread;
  
  public Server(GameServerEngine theGame) {
//      server1 = new ClientServerSocket("127.0.0.1", 45001);
//      server2 = new ClientServerSocket("127.0.0.1", 45001);
//      server3 = new ClientServerSocket("127.0.0.1", 45001);
//      server4 = new ClientServerSocket("127.0.0.1", 45001);
	  sockets = new ArrayList<ClientServerSocket>(0);
    game = theGame;
  }

  public void run() {
    try {
    // add an array
    // while (game not started) {
        // if numPlayers < 4 accept connection
      ServerSocket serverSocket = new ServerSocket(45001);
      
      serverAcceptThread = new Thread(new ServerAcceptThread(this, serverSocket));
      serverAcceptThread.start();
      
      serverSender = new ServerSender(sockets);
//      Thread serverSenderThread = new Thread(serverSender);
//      serverSenderThread.start();
    }
    catch (IOException ioe) {
      out.println("ERROR: Caught exception starting server");
      out.println(ioe.getMessage());
      System.exit(7);
    }
  }
  
  public Boolean hasGameStarted() {
      return game.started;
  }
  
  public void translate(String command, ArrayList<String> values, int clientID) {  
	char c = command.charAt(0);
    switch (c) {
      case 'U': 
        game.addPlayer(values.get(0), clientID);
        break;
      default:
        assert false;
      
    }
  }

}
