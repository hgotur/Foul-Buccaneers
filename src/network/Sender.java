package network;
import static java.lang.System.out;

import java.util.Scanner;

public class Sender {
	private ClientServerSocket senderSocket;

	public Sender(ClientServerSocket inSocket) {
		senderSocket = inSocket;
	}

	public void sendString(String theString) {
		senderSocket.sendString(theString);
	}
}