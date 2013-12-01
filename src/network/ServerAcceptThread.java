package network;

import java.net.ServerSocket;

import Game.GameController;

public class ServerAcceptThread implements Runnable {
  
  private Server server;
  private ServerSocket serverSocket;
  
  public ServerAcceptThread(Server theServer, ServerSocket theSocket) {
    server = theServer;
    serverSocket = theSocket;
  }
  
  public void run() {
    System.out.println("Starting accepting connections.");
    while (server.sockets.size() < 4 && !server.hasGameStarted()) {
  	  // break out when game starts
  	  if (server.sockets.size() < 4) {
  		  ClientServerSocket clientServerSocket = new ClientServerSocket("127.0.0.1", GameController.PORT);
    	  if(!server.hasGameStarted()) {
    	    clientServerSocket.startServer(serverSocket);
    	    server.sockets.add(clientServerSocket);
    	    Thread serverReceiver = new Thread(new ServerReceiver(server, clientServerSocket, server.sockets.size()));
          serverReceiver.start();
    	  }
    	  else {
    	    break;
    	  }
  	  }
    }
  }
}
