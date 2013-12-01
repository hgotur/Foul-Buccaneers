package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameWonScreen extends JFrame {
	public GameWonScreen() {
		super("You Won!");
		JLabel background = new JLabel(new ImageIcon(getClass().getResource("/view/images/gameover.png")));
		add(background);
		
		setSize(700,600);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
