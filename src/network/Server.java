package network;

import static java.lang.System.out;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import Game.*;

public class Server implements Runnable {
//  private ClientServerSocket server1;
//  private ClientServerSocket server2;
//  private ClientServerSocket server3;
//  private ClientServerSocket server4;
  
  private ArrayList<ClientServerSocket> server;
  private GameController game;
  private ServerSender serverSender;

  public Server(GameController theGame) {
//      server1 = new ClientServerSocket("127.0.0.1", 45001);
//      server2 = new ClientServerSocket("127.0.0.1", 45001);
//      server3 = new ClientServerSocket("127.0.0.1", 45001);
//      server4 = new ClientServerSocket("127.0.0.1", 45001);
	  server = new ArrayList<ClientServerSocket>(0);
      game = theGame;
  }

  public void run() {
    try {
    // add an array
    // while (game not started) {
        // if numPlayers < 4 accept connection
      ServerSocket serverSocket = new ServerSocket(45001);
      
      int i = 0;
      while (true) {
    	  // break out when game starts
    	  if (server.size() < 4) {
    		  ClientServerSocket clientServerSocket = new ClientServerSocket("127.0.0.1", 45001);
        	  
        	  out.println("Waiting for client1 to connect...");
        	  clientServerSocket.startServer(serverSocket);
        	  
        	  
        	  
        	  Thread serverReceiver = new Thread(new ServerReceiver(clientServerSocket));
              //Thread serverSender = new Thread(new ServerSender(server1, server2, server3, server4));
              serverReceiver.start();  
    	  }
      }
      
      serverSender = new ServerSender(server);
//      Thread serverSenderThread = new Thread(serverSender);
//      serverSenderThread.start();
    }
    catch (IOException ioe) {
      out.println("ERROR: Caught exception starting server");
      out.println(ioe.getMessage());
      System.exit(7);
    }
  }

}
