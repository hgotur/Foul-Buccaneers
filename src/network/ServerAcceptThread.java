package network;

import java.net.ServerSocket;
import Game.GameController;

/*
 * Accepts connections with up to 4 clients until the game starts.
 * For each accepted connection, sets up a ServerReceiver thread
 * that waits for messages from that client. Declines connections
 * for the duration of the game.
 */
public class ServerAcceptThread implements Runnable {
  private Server server;
  private ServerSocket serverSocket;

  public ServerAcceptThread(Server theServer, ServerSocket theSocket) {
    server = theServer;
    serverSocket = theSocket;
  }

  public void run() {
    while (true) {
      ClientServerSocket clientServerSocket = new ClientServerSocket(
          "127.0.0.1", GameController.PORT);
      clientServerSocket.startServer(serverSocket);

      if (!server.hasGameStarted() && server.sockets.size() < 4) {
        server.sockets.add(clientServerSocket);
        Thread serverReceiver = new Thread(new ServerReceiver(server,
            clientServerSocket, server.sockets.size()));
        serverReceiver.start();
      } else if (server.hasGameStarted()) {
        clientServerSocket.sendString("GAS");
      } else {
        clientServerSocket.sendString("GF");
      }
    }
  }
}
