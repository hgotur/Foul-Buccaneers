package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import Game.*;

public class JoinGameScreen extends JDialog{
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
	
	public JoinGameScreen(GameController thegame, SplashScreen splashScreen){
		super(splashScreen, "Join Game", true);
		game = thegame;
		
		setLayout(new BorderLayout());
		enter = new JButton("Enter");
		enter.setFont(new Font("serif", Font.PLAIN, 20));
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
		field1.setBorder(new EmptyBorder(10,10,10,10));
		add(field1, BorderLayout.NORTH);
		add(enter, BorderLayout.SOUTH);
		
		setSize(400,150);
    setLocation(200, 200);
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
			    game.player = new Player(getUsername(), 0);
				game.joinGame(ip.getText(), getUsername());
			}
		}
	}
}
