package network;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * Receives messages from the client. Then calls Server.translate()
 * to take proper action.
 */
public class ServerReceiver implements Runnable {
  private ClientServerSocket receiverSocket;
  private Server server;
  private int clientNumber;

  /*
   * Sets up references to the server and the socket. Also an ID that tells it
   * which client it is listening to.
   */
  public ServerReceiver(Server theServer, ClientServerSocket inSocket,
      int clientID) {
    server = theServer;
    receiverSocket = inSocket;
    clientNumber = clientID;
  }

  /*
   * Receive message from client. Parse string into (string) command and
   * ArrayList<String> arguments, call Server.translate()
   */
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
      server.translate(command, args, clientNumber);
    }
  }
}
