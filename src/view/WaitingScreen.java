package view;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JDialog;

import Game.GameController;

public class WaitingScreen extends JFrame{
	
  GameController game;
  
	String IPaddress;
	
	JLabel [] playerLabels;
	JLabel [] statusLabels;
	
	JLabel IPaddr;
	JButton startGame;
	JCheckBox ready;
	JPanel status;
	JPanel GetReady;
	WaitingListener listen;
	
	
	public WaitingScreen(GameController theGame, String addr){
		super("Game Lobby");
		
		game = theGame;
		
		IPaddress = addr;		
		setLayout(new BorderLayout());
		
		// title
		JPanel top = new JPanel();
		top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
		
		JLabel title = new JLabel("Waiting for Players");
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setFont(new Font("serif", Font.PLAIN, 50));
		top.add(title);
		
		top.add(Box.createRigidArea(new Dimension(0, 20)));
		
		IPaddr = new JLabel("IP Address of this server: " + IPaddress, SwingConstants.CENTER);
		IPaddr.setForeground(Color.BLUE);
		IPaddr.setAlignmentX(Component.CENTER_ALIGNMENT);
		IPaddr.setFont(new Font("serif", Font.PLAIN, 25));
		top.add(IPaddr);
		
		add(top, BorderLayout.NORTH);
		
		// body
		JPanel body = new JPanel();
		body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
		
		body.add(Box.createRigidArea(new Dimension(0, 40)));
		
		playerLabels = new JLabel[4];
		statusLabels = new JLabel[4];
		
		JPanel playerBoard = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		for (int i = 0; i < playerLabels.length; i++) {
			JLabel playerPicture = new JLabel(new ImageIcon(getClass().getResource("/view/images/pirate" + (i + 1) + ".jpg")));
			playerPicture.setPreferredSize(new Dimension(100, 70));
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = i;			
			playerBoard.add(playerPicture, c);
			
			JPanel playerStatus = new JPanel();
			playerStatus.setLayout(new FlowLayout());
			
			playerLabels[i] = new JLabel("");
			statusLabels[i] = new JLabel("");
			
			playerStatus.add(playerLabels[i]);
			//playerStatus.add(Box.createRigidArea(new Dimension(5,0)));
			playerStatus.add(statusLabels[i]);
			playerStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			playerStatus.setPreferredSize(new Dimension(100, 300));
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 1;
			c.gridy = i;
			
		    playerBoard.add(playerStatus, c);
		}
		body.add(playerBoard);
		listen = new WaitingListener();
		
		add(body, BorderLayout.CENTER);
		
		
		ready = new JCheckBox("I'm ready to play",false);
		JPanel bottom = new JPanel(new FlowLayout());
		bottom.add(ready);
		add(bottom, BorderLayout.SOUTH);
		
		//GetReady = new JPanel(new BorderLayout());
		
		ready.addItemListener(new ReadyListener());
		
		//GetReady.add(ready,BorderLayout.WEST);
		//GetReady.add(startGame, BorderLayout.EAST);

		//add(status, BorderLayout.NORTH);
		//add(GetReady, BorderLayout.SOUTH);
		
		setSize(850,700);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public class ReadyListener implements ItemListener {
		public void itemStateChanged(ItemEvent e){
		  if(e.getStateChange() == ItemEvent.SELECTED) {
		    game.updatePlayerStatus(1);
		  }
		  else {
		    game.updatePlayerStatus(0);
		  }
		}
	}
	
	public class WaitingListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == startGame){
				setVisible(false);
			}
		}
		
	}
	
	public void addPlayer(String username, int status, int playerPosition) {
		playerLabels[playerPosition].setText(username);
		if(status > 0) {
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
	
	public void clearWaitingRoom() {
	  for(JLabel playerLabel : playerLabels) {
	    playerLabel.setText("");
	  }
	  for(JLabel statusLabel : statusLabels) {
	    statusLabel.setText("");
	  }
	}
}