package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * GameWonScreen
 * This view appears at the end of the game
 * once all levels have been completed successfully
 */
public class GameWonScreen extends JFrame {
  /**
   * Constructor
   * Simply loads the view.
   * Nothing interesting here.
   */
  public GameWonScreen() {
    super("You Won!");
    JLabel background = new JLabel(new ImageIcon(getClass().getResource("/view/images/youwin.png")));
    add(background);

    setSize(700, 600);
    setVisible(true);
    setResizable(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }
}
