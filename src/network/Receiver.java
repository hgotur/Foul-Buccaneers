package network;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * Client's version of the receiver. It receives strings from the server
 * and calls Client.translate(). It is threaded.
 */
public class Receiver implements Runnable {
  private ClientServerSocket receiverSocket;
  private Client client;

  public Receiver(Client theClient, ClientServerSocket inSocket) {
    receiverSocket = inSocket;
    client = theClient;
  }

  public void run() {
    String recvdStr;

    while (true) {
      recvdStr = receiverSocket.recvString();

      Scanner sc = new Scanner(recvdStr);
      String command = sc.next();
      ArrayList<String> args = new ArrayList<String>(0);
      while (sc.hasNext()) {
        args.add(sc.next());
      }

      client.translate(command, args);
    }
  }
}