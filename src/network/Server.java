package network;

import static java.lang.System.out;

import java.io.IOException;
import java.net.ServerSocket;
import Game.*;

public class Server implements Runnable {
  private ClientServerSocket server1;
  private ClientServerSocket server2;
  private ClientServerSocket server3;
  private ClientServerSocket server4;
  private GameController game;

  public Server(GameController theGame) {
      server1 = new ClientServerSocket("127.0.0.1", 45001);
      server2 = new ClientServerSocket("127.0.0.1", 45001);
      server3 = new ClientServerSocket("127.0.0.1", 45001);
      server4 = new ClientServerSocket("127.0.0.1", 45001);
      game = theGame;
  }

  public void run() {
    try {
    // add an array
    // while (game not started) {
        // if numPlayers < 4 accept connection
      ServerSocket serverSocket = new ServerSocket(45001);
      out.println("Waiting for client1 to connect...");
      server1.startServer(serverSocket);
      Thread serverReceiver1 = new Thread(new Receiver(server1));
      Thread serverSender = new Thread(new ServerSender(server1, server2, server3, server4));
      serverReceiver1.start();
      serverSender.start();
      
      out.println("Waiting for client2 to connect...");
      server2.startServer(serverSocket);
      Thread serverReceiver2 = new Thread(new Receiver(server2));
      serverReceiver2.start();
      
      out.println("Waiting for client3 to connect...");
      server3.startServer(serverSocket);
      Thread serverReceiver3 = new Thread(new Receiver(server3));
      serverReceiver3.start();
      
      out.println("Waiting for client4 to connect...");
      server4.startServer(serverSocket);
      Thread serverReceiver4 = new Thread(new Receiver(server4));
      serverReceiver4.start();
    }
    catch (IOException ioe) {
      out.println("ERROR: Caught exception starting server");
      out.println(ioe.getMessage());
      System.exit(7);
    }
  }

}
