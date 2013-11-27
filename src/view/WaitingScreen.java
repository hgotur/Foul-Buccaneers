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
		pOneReady = false;
		pTwoReady = false;
		pThreeReady = false;
		if(pOneReady == false){
			pOneStatus = new JLabel(playerOne+" is not Aboard",SwingConstants.CENTER);
		}
		else{
			pOneStatus = new JLabel(playerOne+" is Aboard!",SwingConstants.CENTER);
		}
		if(pTwoReady == false){
			pTwoStatus = new JLabel(playerTwo+" is not Aboard",SwingConstants.CENTER);
		}
		else{
			pTwoStatus = new JLabel(playerTwo+" is Aboard!",SwingConstants.CENTER);
		}
		if(pThreeReady == false){
			pThreeStatus = new JLabel(playerThree+" is not Aboard",SwingConstants.CENTER);
		}
		else{
			pThreeStatus = new JLabel(playerThree+" is Aboard!",SwingConstants.CENTER);
		}
		listen = new WaitingListener();
		
		IPaddr = new JLabel("IP Address of this server: "+IPaddress,SwingConstants.CENTER);
		IPaddr.setForeground(Color.BLUE);
		ready = new JCheckBox("I'm ready to play",false);
		startGame = new JButton("Start Game");
		startGame.setEnabled(false);
		startGame.addActionListener(listen);
		status = new JPanel(new GridLayout(4,1,0,10));
		GetReady = new JPanel(new BorderLayout());
		ready.addItemListener(new ItemListener(){
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
								});
		
		status.add(IPaddr);
		status.add(pOneStatus);
		status.add(pTwoStatus);
		status.add(pThreeStatus);
		GetReady.add(ready,BorderLayout.WEST);
		GetReady.add(startGame, BorderLayout.EAST);

		add(status, BorderLayout.NORTH);
		add(GetReady, BorderLayout.SOUTH);
		
		setSize(800,600);
		setVisible(true);
		setResizable(false);
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
	  switch(playerPosition) {
	  case 1:
	    break;
	  case 2:
	    break;
	  case 3:
	    break;
	  }
	}
	

}