package network;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Scanner;

import Game.GameServerEngine;

public class ServerReceiver implements Runnable {
	private ClientServerSocket receiverSocket;
	private Server server;
	private int clientNumber;

	public ServerReceiver(Server theServer, ClientServerSocket inSocket, int clientID) {
	  server = theServer;
		receiverSocket = inSocket;
		clientNumber = clientID;
	}
	
	public void run() {
		String recvdStr;

		while(true) {
	        recvdStr = receiverSocket.recvString();
	        out.println(recvdStr);
	        Scanner sc = new Scanner(recvdStr);
	        String command = sc.next();
	        ArrayList<String> args = new ArrayList<String>(0);
	        while (sc.hasNext() ) {
	          args.add(sc.next());
	        }
	        server.translate(command, args, clientNumber);
	    }
	}
}
