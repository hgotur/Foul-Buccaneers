package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;

import Game.*;

public class GameStartScreen extends JFrame {
	private GameController game;
	
	public GameStartScreen(int levelNum, String levelText) {
		super("Game Start");
		
		setLayout(new FlowLayout());
		
		JLabel background = new JLabel(new ImageIcon(getClass().getResource("/view/images/splash.png")));
		
		background.setLayout(new BoxLayout(background, BoxLayout.PAGE_AXIS));
		JLabel title = new JLabel("Level " + levelNum);
		title.setForeground(Color.WHITE);
		JLabel story = new JLabel(levelText);
		story.setForeground(Color.WHITE);
		
		background.add(title);
		background.add(Box.createRigidArea(new Dimension(0,100)));
		background.add(story);
		
		add(background);
		
		setSize(800,600);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
