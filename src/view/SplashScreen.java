package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

import Game.GameController;

/**
 * SplashScreen
 * The screen that appears when you
 * first start the game. With buttons
 * to start new games or join games.
 * @author Pratik
 */
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

  /**
   * Constructor
   * Sets up the splash screen and buttons
   * to get the games started.
   * @param thegame
   */
  public SplashScreen(GameController thegame) {
    super("Avast ye Mateys");

    game = thegame;

    setLayout(new BorderLayout());

    background = new JLabel(new ImageIcon(getClass().getResource(
        "/view/images/splash.png")));
    
    joinGame = new JButton("Join Game");
    joinGame.setFont(new Font("Serif", Font.PLAIN, 30));
    joinGame.addActionListener(new SplashScreenListener(game));
    newGame = new JButton("New Game");
    newGame.setFont(new Font("Serif", Font.PLAIN, 30));
    newGame.addActionListener(new SplashScreenListener(game));
    buttons = new JPanel(new GridLayout(1, 2, 0, 0));
    buttons.add(newGame);
    buttons.add(joinGame);

    add(background);
    add(buttons, BorderLayout.SOUTH);

    setSize(850, 700);
    setVisible(true);
    setResizable(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

  }

  /**
   * SplashScreenListener
   * An action listener that is called when
   * a button is clicked. It then runs the
   * required function to load the JDialogs
   */
  public class SplashScreenListener implements ActionListener {
    private GameController game;

    public SplashScreenListener(GameController thegame) {
      game = thegame;
    }

    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == joinGame) {
        joinGamepressed = true;
        game.getJoinGameInfo();
      }
      else if (e.getSource() == newGame) {
        newGamepressed = true;
        game.getNewGameInfo();
      }
    }
  }
}
