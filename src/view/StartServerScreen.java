package view;

import Game.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class StartServerScreen extends JFrame{
	
	JTextField username;
	JButton enter;
	JLabel enterUsername;
	JPanel serv;
	
	public class EnterListener implements ActionListener {
		private GameController game;
		public EnterListener(GameController theGame){
			game = theGame;
		}
		public void actionPerformed(ActionEvent e){
			System.out.println("Enter Pressed");
			setVisible(false);
			game.newGame();
		}
	}
	
	public StartServerScreen(GameController game){
		
		super("Start New Game");
		setLayout(new BorderLayout());
		setSize(400,200);
		setResizable(false);
		username = new JTextField();
		enter = new JButton("Start Server");
		enterUsername = new JLabel("Enter Username");
		serv = new JPanel(new GridLayout(1,2,10,10));
		
		enter.addActionListener(new EnterListener(game));
		
		serv.add(enterUsername);
		serv.add(username);
		
		add(serv, BorderLayout.NORTH);
		add(enter, BorderLayout.SOUTH);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public String getUsername(){
		return username.getText();
	}
}