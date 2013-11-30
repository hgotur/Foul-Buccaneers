package view;

import Game.*;

import java.awt.BorderLayout;
import java.awt.Font;
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
		listen = new SplashScreenListener(game);
		
		background = new JLabel(new ImageIcon(getClass().getResource("/view/images/splash.png")));
		joinGame = new JButton("Join Game");
		joinGame.setFont(new Font("Serif", Font.PLAIN, 30));
		newGame = new JButton("New Game");
		newGame.setFont(new Font("Serif", Font.PLAIN, 30));
		buttons = new JPanel(new GridLayout(1,2,0,0));
		
		buttons.add(newGame);
		buttons.add(joinGame);
		
		add(background);
		add(buttons,BorderLayout.SOUTH);
		
		newGame.addActionListener(listen);
		joinGame.addActionListener(listen);
		
		setSize(850,700);
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
				game.getJoinGameInfo();
			}
			if(e.getSource() == newGame){
				System.out.println("New Game Pressed");
				newGamepressed = true;
				game.getNewGameInfo();
			}
			
		}
		
	}

}
