package network;
import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Scanner;

public class ServerSender {
	private ArrayList <ClientServerSocket> sockets;

	public ServerSender(ArrayList <ClientServerSocket> theSockets) {
		sockets = theSockets;
	}

	// shouldn't be run. This should be a function called encode and send that gets called by the game controller?
	public void sendToAll(String theString) {
	  for(ClientServerSocket socket : sockets) {
	    socket.sendString(theString);
	  }
	}
	
	public void sendToPlayer(int playerIndex, String theString) {
	  sockets.get(playerIndex).sendString(theString);
	}
}