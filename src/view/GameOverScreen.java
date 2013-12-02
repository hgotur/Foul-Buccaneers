package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * GameOverScreen
 * This screen appears when the players have failed and
 * the game is over.
 * @author Pratik
 */
public class GameOverScreen extends JFrame {
  /**
   * Constructor
   * Loads the screen and sets it to visible.
   * Nothing interesting here.
   */
  public GameOverScreen() {
    super("Game Over");
    JLabel background = new JLabel(new ImageIcon(getClass().getResource("/view/images/gameover.png")));
    add(background);

    setSize(700, 600);
    setVisible(true);
    setResizable(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }
}
