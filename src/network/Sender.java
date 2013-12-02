package network;


/*
 * Sender is used by the client to send strings to the server.
 */
public class Sender {
	private ClientServerSocket senderSocket;

	public Sender(ClientServerSocket inSocket) {
		senderSocket = inSocket;
	}
	
	/*
	 * Sends string to server
	 */
	public void sendString(String theString) {
		senderSocket.sendString(theString);
	}
}