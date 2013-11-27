package network;

import static java.lang.System.out;

import java.util.Scanner;

import Game.GameServerEngine;

public class ServerReceiver implements Runnable {
	private ClientServerSocket receiverSocket;
	private Server server;

	public ServerReceiver(Server theServer, ClientServerSocket inSocket) {
	    server = theServer;
		receiverSocket = inSocket;
	}
	
	public void run() {
		String recvdStr;

		while(true) {
	        recvdStr = receiverSocket.recvString();
	        out.println(recvdStr);
	        
	        decode_and_exec(recvdStr);
	    }
	}
	
	private void decode_and_exec(String str) {
		Scanner sc = new Scanner(str);
		
		int messageType;
		messageType = sc.nextInt();
		
		switch (messageType) {
			case 1: break;
			case 2: break;
			case 3: break;
			case 4: break;
			case 5: break;
			case 6: break;
			case 7: break;
			case 8: break;
			case 9: break;
			case 10: break;
			default:
				assert false;
			
 		}
	}
}
