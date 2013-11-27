package network;
import static java.lang.System.out;

import java.util.Scanner;

public class Sender implements Runnable {
	private ClientServerSocket senderSocket;

	public Sender(ClientServerSocket inSocket) {
		senderSocket = inSocket;
	}

	public void run() {
		Scanner sc = new Scanner(System.in);
		String input;

		while (true) {
	        input = sc.nextLine();
	        senderSocket.sendString(input);
	    }
	}
}