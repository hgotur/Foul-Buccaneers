package network;
import static java.lang.System.out;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import Game.*;

public class Client {
  private ClientServerSocket theClient;
  private GameClientEngine game;
  private Sender sender;
  private Receiver receiver;

  public Client(GameClientEngine theGame, String ipAddr) {
	game = theGame;
    theClient = new ClientServerSocket(ipAddr, GameController.PORT);
    theClient.startClient();
      
    receiver = new Receiver(this, theClient);
    sender = new Sender(theClient);
    Thread clientReceiver = new Thread(receiver);
    clientReceiver.start();
  }
  
  public void translate(String command, ArrayList<String> value) {
	String username;
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
    case "LL":	//level load
    	game.getNewLevelSetup(Integer.parseInt(value.get(0)));
    	break;
    case "B":	// buttons
    	ArrayList<Integer> buttons = new ArrayList<Integer>(0);
    	username = value.remove(0);
    	for (String button: value) {
    		buttons.add(Integer.parseInt(button));
    	}
    	game.addButtonsToLevel(username, buttons);
    	break;
    case "LS":	// level start
    	game.levelStart();
    	// display screen
    	break;
    case "C":	// receive command
    	username = value.get(0);
    	game.readCommand(username, Integer.parseInt(value.get(1)));
    	break;
    case "SD":
      game.setShipDamage(Integer.parseInt(value.get(0)));
      break;
    case "GO":
    	game.endGame();
    	break;
    case "GW":
    	game.gameWon();
    	break;
    case "GAS":
    	 JOptionPane.showMessageDialog(null, "Argggh!! You're ship has already set sail. You're too late! Arrgghh!!.", "Connection Error", JOptionPane.ERROR_MESSAGE);
         System.exit(10);
         break;
    case "GF":
    	JOptionPane.showMessageDialog(null, "Arggghh!! Too many pirates on the ship. Start your own game. Arrgghh!!", "Connection Error", JOptionPane.ERROR_MESSAGE); 
        System.exit(10);
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
  
  public void sendButtonInput(int buttonIndex) {
	  String message = "B " + buttonIndex;
	  sender.sendString(message);
  }
  
  public void sendCommandFailed(int commandIndex) {
	  sender.sendString("F " + commandIndex);
  }

}
