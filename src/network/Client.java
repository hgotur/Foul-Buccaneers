package network;
import static java.lang.System.out;

public class Client implements Runnable {
  private ClientServerSocket theClient;

  public Client(String ipAddr) {
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
