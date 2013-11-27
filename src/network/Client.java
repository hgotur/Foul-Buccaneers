package network;
import static java.lang.System.out;
import Game.*;

public class Client {
  private ClientServerSocket theClient;
  private GameController game;
  private Sender sender;
  private Receiver receiver;

  public Client(GameController theGame, String ipAddr) {
	  game = theGame;
    theClient = new ClientServerSocket(ipAddr, 45001);
    theClient.startClient();
      
    receiver = new Receiver(theClient);
    sender = new Sender(theClient);
    Thread clientReceiver = new Thread(receiver);
    clientReceiver.start();
  }
  
  public void sendUsername(String user) {
    user = "P " + user;
    sender.sendString(user);
  }
  
  public void translate(String command, String [] value) {
    switch(command) {
    case "P": //do something
      break;
    }
  }

}
