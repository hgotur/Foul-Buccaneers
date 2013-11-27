package network;
import static java.lang.System.out;

public class Receiver implements Runnable {
	private ClientServerSocket receiverSocket;

	public Receiver(ClientServerSocket inSocket) {
		receiverSocket = inSocket;
	}

	public void run() {
		String recvdStr;

		while(true) {
	      recvdStr = receiverSocket.recvString();
	      out.println(recvdStr);
	    }
	}
}