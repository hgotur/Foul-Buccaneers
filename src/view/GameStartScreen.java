package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;

import Game.*;

public class GameStartScreen extends JFrame {
	private GameController game;
	
	public GameStartScreen(int levelNum, String levelText) {
		super("Game Start");
		
		setLayout(new FlowLayout());
		
		JLabel background = new JLabel(new ImageIcon(getClass().getResource("/view/images/splash.png")));
		
		background.setLayout(new FlowLayout());
		
		JLabel title = new JLabel("Level " + levelNum);
		
		String text = "<html><body><div style='width:500px; margin-top:100px; text-align:center'><h1 style='font-size:60px; margin-bottom:50px'> Level " + levelNum + "</h1><p style='font-size:25px;'>" + levelText + "</p></div></body></html>";
		
		JLabel story = new JLabel(text);
		story.setForeground(Color.WHITE);
		story.setAlignmentX(Component.CENTER_ALIGNMENT);
		story.setFont(new Font("serif", Font.PLAIN, 25));
		
		background.add(story);
		
		add(background);
		
		setSize(700,600);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
