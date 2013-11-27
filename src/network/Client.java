package network;
import static java.lang.System.out;
import Game.*;

public class Client implements Runnable {
  private ClientServerSocket theClient;
  private GameController game;

  public Client(GameController theGame, String ipAddr) {
	  game = theGame;
      theClient = new ClientServerSocket(ipAddr, 45001);
  }

  public void run() {    
    theClient.startClient();
    
    Receiver receiver = new Receiver(theClient);
    Sender sender = new Sender(theClient);

    Thread clientSender = new Thread(sender);
    Thread clientReceiver = new Thread(receiver);
    
    clientSender.start();
    clientReceiver.start();
  }

}
