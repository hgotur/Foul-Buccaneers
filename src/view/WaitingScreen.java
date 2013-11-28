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
	
	boolean pOneReady;
	boolean pTwoReady;
	boolean pThreeReady;
	String playerOne;
	String playerTwo;
	String playerThree;
	String IPaddress;
	JLabel [] players;
	JLabel [] statuses;
	JLabel pOneStatus;
	JLabel pTwoStatus;
	JLabel pThreeStatus;
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
		players = new JLabel[4];
		statuses = new JLabel[4];
		
		IPaddr = new JLabel("IP Address of this server: "+IPaddress,SwingConstants.CENTER);
		IPaddr.setForeground(Color.BLUE);
		status.add(IPaddr);
		for (int i = 0; i < players.length; i++) {
		    players[i] = new JLabel("");
		    statuses[i] = new JLabel("");
		    status.add(players[i]);
		    status.add(statuses[i]);
		}
		listen = new WaitingListener();
		
		
		ready = new JCheckBox("I'm ready to play",false);
		startGame = new JButton("Start Game");
		startGame.setEnabled(false);
		startGame.addActionListener(listen);
		status = new JPanel(new GridLayout(4,1,0,10));
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
			if(pOneReady == false){
				pOneReady = true;
				pOneStatus.setText(playerOne+" is Aboard!");
				System.out.println(pOneReady);
			}
			else{
				pOneReady = false;
				pOneStatus.setText(playerOne+" is not Aboard");
			}
			if(pOneReady == true && pTwoReady == true && pThreeReady == true){
				startGame.setEnabled(true);
			}
			else{
				startGame.setEnabled(false);
			}
			
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
	  players[playerPosition].setText(username);
	  statuses[playerPosition].setText("is not Aboard!");
	}
	
	public void changeStatus(int playerPosition, Boolean status) {
	  if(status) {
	    statuses[playerPosition].setText("is Aboard!");
	  }
	  else {
	    statuses[playerPosition].setText("is not Aboard!");
	  }
	}
}