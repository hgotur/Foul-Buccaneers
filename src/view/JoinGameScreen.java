package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;

import Game.*;

public class JoinGameScreen extends JFrame{
	JButton enter;
	JTextField ip;
	JTextField username;
	JLabel enterUsername;
	JLabel enterIP;
	JPanel field1;
	JPanel field2;
	WaitingScreen wait;
	IPListener listen;
	
	GameController game;
	
	public JoinGameScreen(GameController thegame){
		super("Enter IP Adress");
		game = thegame;
		setLayout(new BorderLayout());
		setSize(800,600);
		enter = new JButton("Enter");
		enterUsername = new JLabel("Enter Your Username");
		enterIP = new JLabel("Enter IP Adress of target Server");
		ip = new JTextField();
		username = new JTextField();
		listen = new IPListener(game);
		enter.addActionListener(listen);
		
		field1 = new JPanel(new GridLayout(2,2,10,10));
		
		
		field1.add(enterIP);
		field1.add(ip);
		field1.add(enterUsername);
		field1.add(username);
		add(field1, BorderLayout.NORTH);
		add(enter, BorderLayout.SOUTH);
		
		setVisible(true);
		setResizable(false);
		
		
	}
	
	public String getUsername(){
		return(username.getText());
	}
	
	public String getIP(){
		return(ip.getText());
	}
	
	public class IPListener implements ActionListener{
	    private GameController game;
	    public IPListener(GameController thegame){
	        game = thegame;
	    }
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == enter){
			    setVisible(false);
				game.joinGame(ip.getText(), getUsername());
			}
		}
	}
}
