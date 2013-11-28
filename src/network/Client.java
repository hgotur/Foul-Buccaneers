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
    user = "U " + user;
    out.println("Sending String");
    sender.sendString(user);
  }
  
  public String encode(String [] args) {
      String message = "";
      
      for(String arg : args) {
          message += (" " + arg);
      }
      
      return message;
  }
  
  public void translate(String command, String [] value) {
    switch(command) {
    case "U":   // username
      break;
    case "P":   // players
    case "GS":  // game start
        break;
    case "GE":  // game end
        break;
    case "LS":  // level start
        break;
    case "LE":  // level end
        break;
    case "NI":   // new instruction
        break;
    case "NS":   // new score
        break;
    
    }
  }

}
