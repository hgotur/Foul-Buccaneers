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
    switch (command) {
    case "U":   // list of users + statuses
    	ArrayList<Player> players = new ArrayList<Player>(0);
    	
    	for (int i = 0; i < value.length; i+=2) {
    		Player player = new Player(value[i], Integer.parseInt(value[i + 1]));
    		players.add(player);
    	}
    	
    	game.addPlayersToWaitingRoom(players);
      break;
    case "P":   // players
    	break;
    /*case "GS":  // game start
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
        break;*/
    
    }
  }

}
