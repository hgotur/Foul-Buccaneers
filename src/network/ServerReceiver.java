package network;

import static java.lang.System.out;
import Game.GameServerEngine;

public class ServerReceiver {
	private ClientServerSocket receiverSocket;

	public ServerReceiver(ClientServerSocket inSocket) {
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
		
	}
}
