package network;

import java.util.ArrayList;

/*
 * Sends messages to the client. Called by the server.
 */
public class ServerSender {
  private ArrayList<ClientServerSocket> sockets;

  /*
   * Sets up references to accepted connections.
   */
  public ServerSender(ArrayList<ClientServerSocket> theSockets) {
    sockets = theSockets;
  }

  /*
   * Sends message to all clients.
   */
  public void sendToAll(String theString) {
    for (ClientServerSocket socket : sockets) {
      socket.sendString(theString);
    }
  }

  /*
   * Sends message to specified client (specified by clientID).
   */
  public void sendToPlayer(int playerIndex, String theString) {
    sockets.get(playerIndex).sendString(theString);
  }
}