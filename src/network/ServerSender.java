package network;
import static java.lang.System.out;

import java.util.Scanner;

public class ServerSender implements Runnable {
	private ClientServerSocket senderSocket1;
	private ClientServerSocket senderSocket2;
	private ClientServerSocket senderSocket3;
	private ClientServerSocket senderSocket4;

	public ServerSender(ClientServerSocket inSocket1, ClientServerSocket inSocket2, 
				ClientServerSocket inSocket3, ClientServerSocket inSocket4) {
		senderSocket1 = inSocket1;
		senderSocket2 = inSocket2;
		senderSocket3 = inSocket3;
		senderSocket4 = inSocket4;
	}

	public void run() {
		Scanner sc = new Scanner(System.in);
		String input;

		while (true) {
	        input = sc.nextLine();
	        senderSocket1.sendString(input);
	        senderSocket2.sendString(input);
	        senderSocket3.sendString(input);
	        senderSocket4.sendString(input);
	        
	    }
	}
}