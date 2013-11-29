package network;
import static java.lang.System.out;

import java.util.ArrayList;

import Game.*;

public class Client {
  private ClientServerSocket theClient;
  private GameClientEngine game;
  private Sender sender;
  private Receiver receiver;

  public Client(GameClientEngine theGame, String ipAddr) {
	game = theGame;
    theClient = new ClientServerSocket(ipAddr, 45001);
    theClient.startClient();
      
    receiver = new Receiver(this, theClient);
    sender = new Sender(theClient);
    Thread clientReceiver = new Thread(receiver);
    clientReceiver.start();
  }
  
  public void translate(String command, ArrayList<String> value) {
    switch (command) {
    case "U":   // list of users + statuses
    	ArrayList<Player> players = new ArrayList<Player>(0);
    	
    	for (int i = 0; i < value.size(); i+=2) {
    		Player player = new Player(value.get(i), Integer.parseInt(value.get(i + 1)));
    		players.add(player);
    	}
    	System.out.println("Client sending to add players");
    	game.addPlayersToWaitingRoom(players);
      break;
    case "P":   // players
    	break;
    case "GS":  // game start
      game.letsGetStarted();
      break;
    /*case "GE":  // game end
        break;
    case "LS":  // level start
        break;
    case "LE":  // level end
        break;
    case "NI":   // new instruction
        break;
    case "NS":   // new score
        break;*/
    
    }
  }
  
  public void sendUsername(String user, int status) {
    user = "U " + user + " " + status;
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
  
  public void sendWaitingStateChange(String user, int status) {
    user = "W " + user + " " + status;
    sender.sendString(user);
  }

}
