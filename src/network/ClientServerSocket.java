package network;

import java.net.Socket;
import java.net.ServerSocket;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;
import javax.swing.JOptionPane;

/*
 * The ClientServerSocket is used as a socket for both client and server.
 * It is the code used from Professor Morgan's lecture slides.
 */
public class ClientServerSocket {
  private String ipAddr;
  private int portNum;
  private Socket socket;
  private DataOutputStream outData;
  private DataInputStream inData;

  public ClientServerSocket(String inIPAddr, int inPortNum) {
    ipAddr = inIPAddr;
    portNum = inPortNum;
    inData = null;
    outData = null;
    socket = null;
  }

  public void startClient() {
    try {
      socket = new Socket(ipAddr, portNum);
      outData = new DataOutputStream(socket.getOutputStream());
      inData = new DataInputStream(socket.getInputStream());
    } catch (IOException ioe) {
      JOptionPane.showMessageDialog(null,
          "Could not connect to the server. Are you sure its running?",
          "Connection Error", JOptionPane.ERROR_MESSAGE);
      System.exit(10);
    }
  }

  public void startServer(ServerSocket serverSock) {
    try {
      socket = serverSock.accept();
      outData = new DataOutputStream(socket.getOutputStream());
      inData = new DataInputStream(socket.getInputStream());
    } catch (IOException ioe) {
      JOptionPane.showMessageDialog(null,
          "Could not create a server. Are you connected to a network?",
          "Server Error", JOptionPane.ERROR_MESSAGE);
      System.exit(7);
    }
  }

  /*
   * Used to send messages to socket.
   */
  public boolean sendString(String strToSend) {
    boolean success = false;
    try {
      outData.writeBytes(strToSend);
      outData.writeByte(0); // send 0 to signal the end of the string
      success = true;
    } catch (IOException e) {
      JOptionPane.showMessageDialog(null,
          "Oh no! Someone left the game! Ye need all yer mateys to play!",
          "Game Over", JOptionPane.ERROR_MESSAGE);
      System.exit(-1);
    }
    return (success);
  }

  /*
   * Used to receive messages that are sent to socket.
   */
  public String recvString() {
    Vector<Byte> byteVec = new Vector<Byte>();
    byte[] byteAry;
    byte recByte;
    String receivedString = "";
    try {
      recByte = inData.readByte();
      while (recByte != 0) {
        byteVec.add(recByte);
        recByte = inData.readByte();
      }
      byteAry = new byte[byteVec.size()];
      for (int ind = 0; ind < byteVec.size(); ind++) {
        byteAry[ind] = byteVec.elementAt(ind).byteValue();
      }
      receivedString = new String(byteAry);
    } catch (IOException ioe) {
      JOptionPane.showMessageDialog(null,
          "Oh no! Someone left the game! Ye need all yer mateys to play!",
          "Game Over", JOptionPane.ERROR_MESSAGE);
      System.exit(8);
    }
    return (receivedString);
  }
}
