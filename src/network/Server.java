package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import Game.*;

/*
 * Part of the network that interacts with the game server engine.
 * Receives information from the server, translates, and calls the
 * GameServerEngine to take action.
 */
public class Server {

  protected ArrayList<ClientServerSocket> sockets;
  private GameServerEngine game;
  private ServerSender serverSender;
  private Thread serverAcceptThread;

  /*
   * Sets up the server accept thread that accepts client connections to the
   * server. Also sets up the server socket and the server sender.
   */
  public Server(GameServerEngine theGame) {
    sockets = new ArrayList<ClientServerSocket>(0);
    game = theGame;

    try {
      ServerSocket serverSocket = new ServerSocket(GameController.PORT);

      serverAcceptThread = new Thread(
          new ServerAcceptThread(this, serverSocket));
      serverAcceptThread.start();

      serverSender = new ServerSender(sockets);
    } catch (IOException ioe) {
      JOptionPane.showMessageDialog(null,
          "Could not create a server. Are you connected to a network?",
          "Server Error", JOptionPane.ERROR_MESSAGE);
      System.exit(7);
    }
  }

  public boolean hasGameStarted() {
    return game.started;
  }

  /*
   * Translates messages from the client and calls gameServerEngine to take
   * appropriate action.
   */
  public synchronized void translate(String command, ArrayList<String> values,
      int clientID) {
    switch (command) {
    case "U":
      game.addPlayer(values.get(0), Integer.parseInt(values.get(1)));
      break;
    case "W":
      game.waitingStateChange(values.get(0), Integer.parseInt(values.get(1)));
      break;
    case "B":
      game.recievedButton(Integer.parseInt(values.get(0)));
      break;
    case "F":
      game.commandFailed(Integer.parseInt(values.get(0)));
      break;
    default:
      assert false;

    }
  }

  /*
   * The following functions create and send commands to the clients.
   */
  public void sendPlayers(ArrayList<Player> players) {
    String message = "U";
    for (Player player : players) {
      message += (" " + player.name + " " + player.status);
    }
    serverSender.sendToAll(message);
  }

  public void sendStartGame() {
    String message = "GS";
    serverSender.sendToAll(message);
  }

  public void sendPreparingLevel(int level) {
    serverSender.sendToAll("LL " + level);
  }

  public void sendStartLevel() {
    serverSender.sendToAll("LS");
  }

  public void sendCommand(String player, int index) {
    serverSender.sendToAll("C " + player + " " + index);
  }

  public void sendLevelButtons(String username, int[] buttons) {
    String message = "B " + username;
    for (int i = 0; i < buttons.length; i++) {
      message += " " + buttons[i];
    }

    serverSender.sendToAll(message);
  }

  public void sendCommandComplete(String player, int index) {
    serverSender.sendToAll("CC " + player + " " + index);
  }

  public void sendCommandFailed(String player, int index, int damage) {
    serverSender.sendToAll("SD " + damage);
  }

  public void sendGameOver() {
    serverSender.sendToAll("GO");
  }

  public void sendGameWin() {
    serverSender.sendToAll("GW");
  }

}
