package view;

import Game.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;



import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

public class SplashScreen extends JFrame {
	
	JLabel title;
	JLabel background;
	JButton joinGame;
	JButton newGame;
	JPanel buttons;
	SplashScreenListener listen;
	public boolean joinGamepressed = false;
	public boolean newGamepressed = false;
	private GameController game;
	
	
	public SplashScreen(GameController thegame){
		super("Avast ye Mateys");
		
		game = thegame;
		
		setLayout(new BorderLayout());
		title = new JLabel("Mateys", SwingConstants.CENTER);
		title.setForeground(Color.RED);
		title.setSize(20,20);
		listen = new SplashScreenListener(game);
		
		background = new JLabel(new ImageIcon(getClass().getResource("/view/images/ocean.jpeg")));
		joinGame = new JButton("Join Game");
		newGame = new JButton("New Game");
		buttons = new JPanel(new GridLayout(1,2,10,0));
		
		buttons.add(newGame);
		buttons.add(joinGame);
		
		add(background);
		add(buttons,BorderLayout.SOUTH);
		add(title,BorderLayout.NORTH);		
		
		newGame.addActionListener(listen);
		joinGame.addActionListener(listen);
		
		setSize(800,600);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
		
		
	}
	
	public class SplashScreenListener implements ActionListener{
		private GameController game;
		public SplashScreenListener(GameController thegame){
			game = thegame;
		}
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == joinGame){
				System.out.println("Join Game Pressed");
				joinGamepressed = true;
				setVisible(false);
				game.getJoinGameInfo();
			}
			if(e.getSource() == newGame){
				System.out.println("New Game Pressed");
				newGamepressed = true;
				setVisible(false);
				game.getNewGameInfo();
			}
			
		}
		
	}

}
