package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

import Game.*;

public class GameStartScreen extends JFrame {
	private GameController game;
	
	public GameStartScreen(int levelNum, String levelText) {
		super("Game Start");
		
		setLayout(new FlowLayout());
		
		JLabel background = new JLabel(new ImageIcon(getClass().getResource("/view/images/levelload.jpg")));
		
		background.setLayout(new GridBagLayout());
		
		String text = "<html><body><div style='width:500px; text-align:center; padding:20px'><h1 style='font-size:60px; margin-bottom:50px'> Level " + levelNum + "</h1><p style='font-size:25px;'>" + levelText + "</p></div></body></html>";
		
		JLabel story = new JLabel(text);
		story.setForeground(Color.white);
		story.setAlignmentX(Component.CENTER_ALIGNMENT);
		story.setFont(new Font("serif", Font.PLAIN, 25));
		story.setBackground(new Color(0, 0, 0, 150));
		story.setOpaque(true);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		background.add(story, c);
		
		add(background);
		
		setSize(700,600);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
