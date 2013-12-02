package view;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Game.GameController;

/**
 * WaitingScreen
 * This screen is the waiting room screen.
 * It appears while players are joining a game
 * and allows players to confirm they are ready
 * and start a game.
 * @author Pratik
 */
public class WaitingScreen extends JFrame{
	
  GameController game;
  
	String IPaddress;
	
	JLabel [] playerLabels;
	JLabel [] statusLabels;
	JLabel [] checkMarks;
	
	JLabel IPaddr;
	JButton startGame;
	JButton ready;
	int playerStatus;
	JPanel status;
	JPanel GetReady;
	WaitingListener listen;
	
	/**
	 * Constructor
	 * Sets up the waiting room and prints the
	 * IP address for the server.
	 * @param theGame
	 * @param addr
	 */
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
		
		playerLabels = new JLabel[4];
		statusLabels = new JLabel[4];
		checkMarks = new JLabel[4];
		
		JPanel playerBoard = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		for (int i = 0; i < playerLabels.length; i++) {
			JLabel playerPicture = new JLabel(new ImageIcon(getClass().getResource("/view/images/pirate" + (i + 1) + ".jpg")));
			playerPicture.setPreferredSize(new Dimension(100, 70));
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = i;
			c.ipadx = 5;
			c.ipady = 5;
			playerBoard.add(playerPicture, c);
			
			JPanel playerStatus = new JPanel();
			playerStatus.setLayout(new FlowLayout());
			
			playerLabels[i] = new JLabel("");
			playerLabels[i].setFont(new Font("serif", Font.PLAIN, 25));
			statusLabels[i] = new JLabel("");
			statusLabels[i].setFont(new Font("serif", Font.PLAIN, 25));
			checkMarks[i] = new JLabel("");
			
			playerStatus.add(playerLabels[i]);
			playerStatus.add(statusLabels[i]);
			playerStatus.add(checkMarks[i]);
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
		
		
		ready = new JButton("Ready to Start");
		ready.setFont(new Font("serif", Font.PLAIN, 30));
		ready.addActionListener(new ReadyListener());
		playerStatus = 0;
		JPanel bottom = new JPanel(new FlowLayout());
		bottom.add(ready);
		add(bottom, BorderLayout.SOUTH);
		
		setSize(850,700);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * ReadyListener
	 * Action listener that is called when a player
	 * says he's ready to start playing.
	 */
	public class ReadyListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (playerStatus == 0) {
				ready.setText("NOT Ready to Start");
				playerStatus = 1;
			}
			else {
				ready.setText("Ready to Start");
				playerStatus = 0;
			}
			game.updatePlayerStatus(playerStatus);
			
		}
	}
	
	/**
	 * WaitingListener
	 * An action listener called if a player
	 * decides he's not ready.
	 */
	public class WaitingListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == startGame){
				setVisible(false);
			}
		}
		
	}
	
	/**
	 * AddPlayer
	 * Adds a new player to the waiting room.
	 * @param username
	 * @param status
	 * @param playerPosition
	 */
	public void addPlayer(String username, int status, int playerPosition) {
		playerLabels[playerPosition].setText(username);
		if(status > 0) {
		  statusLabels[playerPosition].setText("is aboard!");
		  checkMarks[playerPosition].setIcon(new ImageIcon(getClass().getResource("/view/images/checkmark.png")));
		}
		else {
		  statusLabels[playerPosition].setText("is not aboard!");
		  checkMarks[playerPosition].setIcon(null);
		}
	}
	
	/**
	 * ClearsWaitingRoom
	 * Clears the waiting room. Used when refreshing the
	 * waiting room.
	 */
	public void clearWaitingRoom() {
	  for(JLabel playerLabel : playerLabels) {
	    playerLabel.setText("");
	  }
	  for(JLabel statusLabel : statusLabels) {
	    statusLabel.setText("");
	  }
	  for (JLabel checkMark : checkMarks) {
		  checkMark.setIcon(null);
	  }
	}
}