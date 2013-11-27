package network;
import static java.lang.System.out;

import java.io.IOException;
import java.net.ServerSocket;

public class Server implements Runnable {
  private ClientServerSocket server1;
  private ClientServerSocket server2;
  private ClientServerSocket server3;
  private ClientServerSocket server4;

  public Server() {
      server1 = new ClientServerSocket("127.0.0.1", 45001);
      server2 = new ClientServerSocket("127.0.0.1", 45001);
      server3 = new ClientServerSocket("127.0.0.1", 45001);
      server4 = new ClientServerSocket("127.0.0.1", 45001);
  }

  public void run() {
    try {
      ServerSocket serverSocket = new ServerSocket(45001);
      out.println("Waiting for client1 to connect...");
      server1.startServer(serverSocket);
      Thread serverReceiver1 = new Thread(new Receiver(server1));
      Thread serverSender1 = new Thread(new Sender(server1));
      serverReceiver1.start();
      serverSender1.start();
      
      out.println("Waiting for client2 to connect...");
      server2.startServer(serverSocket);
      Thread serverReceiver2 = new Thread(new Receiver(server2));
      Thread serverSender2 = new Thread(new Sender(server2));
      serverReceiver2.start();
      serverSender2.start();
      
      out.println("Waiting for client3 to connect...");
      server3.startServer(serverSocket);
      Thread serverReceiver3 = new Thread(new Receiver(server3));
      Thread serverSender3 = new Thread(new Sender(server3));
      serverReceiver3.start();
      serverSender3.start();
      
      out.println("Waiting for client4 to connect...");
      server4.startServer(serverSocket);
      Thread serverReceiver4 = new Thread(new Receiver(server4));
      Thread serverSender4 = new Thread(new Sender(server4));
      serverReceiver4.start();
      serverSender4.start();
    }
    catch (IOException ioe) {
      out.println("ERROR: Caught exception starting server");
      out.println(ioe.getMessage());
      System.exit(7);
    }
  }

}
