package view;

import java.awt.FlowLayout;

import javax.swing.*;

import Game.*;

public class GameStartScreen extends JFrame {
	private GameController game;
	
	public GameStartScreen() {
		super("Game Start");
		
		setLayout(new FlowLayout());
		
		JLabel background = new JLabel(new ImageIcon(getClass().getResource("/view/images/ocean.jpeg")));
		
		add(background);
		
		setSize(800,600);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
