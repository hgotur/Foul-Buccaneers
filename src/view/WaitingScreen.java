package view;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JDialog;

public class WaitingScreen extends JFrame{
	
	String IPaddress;
	
	JLabel [] playerLabels;
	JLabel [] statusLabels;
	
	JLabel IPaddr;
	JButton startGame;
	JCheckBox ready;
	JPanel status;
	JPanel GetReady;
	WaitingListener listen;
	GameScreen1 game;
	
	
	public WaitingScreen(String addr){
		
		super("Game Lobby");
		IPaddress = addr;		
		setLayout(new BorderLayout());
		playerLabels = new JLabel[4];
		statusLabels = new JLabel[4];
		
		status = new JPanel(new GridLayout(4,1,0,10));
		IPaddr = new JLabel("IP Address of this server: "+IPaddress,SwingConstants.CENTER);
		IPaddr.setForeground(Color.BLUE);
		status.add(IPaddr);
		for (int i = 0; i < playerLabels.length; i++) {
			playerLabels[i] = new JLabel("");
			statusLabels[i] = new JLabel("");
		    status.add(playerLabels[i]);
		    status.add(statusLabels[i]);
		}
		listen = new WaitingListener();
		
		
		ready = new JCheckBox("I'm ready to play",false);
		startGame = new JButton("Start Game");
		startGame.setEnabled(false);
		startGame.addActionListener(listen);
		
		GetReady = new JPanel(new BorderLayout());
		
		ready.addItemListener(new ReadyListener());
		
		GetReady.add(ready,BorderLayout.WEST);
		GetReady.add(startGame, BorderLayout.EAST);

		add(status, BorderLayout.NORTH);
		add(GetReady, BorderLayout.SOUTH);
		
		setSize(800,600);
		setVisible(true);
		setResizable(false);
	}
	
	public class ReadyListener implements ItemListener {
		public void itemStateChanged(ItemEvent e){
			//send message to server
		}
	}
	
	public class WaitingListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == startGame){
				setVisible(false);
				game = new GameScreen1(1);
			}
		}
		
	}
	
	public void addPlayer(String username, int playerPosition) {
		playerLabels[playerPosition].setText(username);
		statusLabels[playerPosition].setText("is not Aboard!");
	}
	
	public void changeStatus(int playerPosition, Boolean status) {
	  if(status) {
		  statusLabels[playerPosition].setText("is Aboard!");
	  }
	  else {
		  statusLabels[playerPosition].setText("is not Aboard!");
	  }
	}
	
	public void waitingStatus(String [] waitingPlayers, boolean [] isReady) {
		for (int i = 0; i < waitingPlayers.length; i++) {
			playerLabels[i].setText(waitingPlayers[i]);
			if (isReady[i] == true) {
				statusLabels[i].setText("is aboard!");
			}
			else {
				statusLabels[i].setText("is not aboard!");
			}
		}
	}
}