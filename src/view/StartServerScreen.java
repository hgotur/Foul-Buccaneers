package view;

import Game.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

public class StartServerScreen extends JDialog {
	
	JTextField usernameField;
	JButton enter;
	JLabel enterUsername;
	JPanel serv;
	
	public class EnterListener implements ActionListener {
		private GameController game;
		public EnterListener(GameController theGame){
			game = theGame;
		}
		public void actionPerformed(ActionEvent e){
			if (getUsername().equals("")) {
				setVisible(false);
				game.player = new Player(usernameField.getText(), 0);
				game.newGame();
			}
			else {
				JOptionPane.showMessageDialog(null, "D'ya have name matey?.", "Connection Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public StartServerScreen(GameController game, SplashScreen splashScreen){
		super(splashScreen, "Create Server", true);
		setLayout(new BorderLayout());
		setResizable(false);
		usernameField = new JTextField(50);
		enter = new JButton("Start Server");
		enter.setFont(new Font("serif", Font.PLAIN, 20));
		enterUsername = new JLabel("Enter Username");
		serv = new JPanel(new GridLayout(1,2,10,10));
		
		enter.addActionListener(new EnterListener(game));
		
		serv.add(enterUsername);
		serv.add(usernameField);
		serv.setBorder(new EmptyBorder(10,10,10,10));
		
		add(serv, BorderLayout.NORTH);
		add(enter, BorderLayout.SOUTH);
		
		setSize(400,100);
    setLocation(200, 200);
		
		setVisible(true);
	}
	
	public String getUsername(){
		return usernameField.getText();
	}
	
	public String sayHello(){
		return "Hello World!";
	}
}