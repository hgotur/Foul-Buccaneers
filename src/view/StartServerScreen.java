package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Game.GameController;
import Game.Player;

/**
 * StartServerScreen
 * A JDialog to collect information about starting
 * a new server.
 * @author Pratik
 */
public class StartServerScreen extends JDialog {

  JTextField usernameField;
  JButton enter;
  JLabel enterUsername;
  JPanel serv;

  /**
   * EnterListener
   * An action listener for the enter button.
   * Checks to make sure input is correct and then
   * runs the appropriate function.
   */
  public class EnterListener implements ActionListener {
    private GameController game;

    public EnterListener(GameController theGame) {
      game = theGame;
    }

    public void actionPerformed(ActionEvent e) {
      if (!getUsername().equals("")) {
        setVisible(false);
        game.player = new Player(usernameField.getText().replaceAll(" ", "_"), 0);
        game.newGame();
      } else {
        JOptionPane.showMessageDialog(null, "D'ya have name matey?",
            "Connection Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  /**
   * Constructor
   * Sets up the JDialog to collect info.
   * @param game
   * @param splashScreen
   */
  public StartServerScreen(GameController game, SplashScreen splashScreen) {
    super(splashScreen, "Create Server", true);
    setLayout(new BorderLayout());
    
    usernameField = new JTextField(50);
    enter = new JButton("Start Server");
    enter.setFont(new Font("serif", Font.PLAIN, 20));
    enterUsername = new JLabel("Enter Username");
    serv = new JPanel(new GridLayout(1, 2, 10, 10));

    enter.addActionListener(new EnterListener(game));

    serv.add(enterUsername);
    serv.add(usernameField);
    serv.setBorder(new EmptyBorder(10, 10, 10, 10));

    add(serv, BorderLayout.NORTH);
    add(enter, BorderLayout.SOUTH);

    setSize(400, 100);
    setLocation(200, 200);
    setResizable(false);
    setVisible(true);
  }

  /**
   * GetUsername
   * Gets the username from the text field.
   * @return
   */
  public String getUsername() {
    return usernameField.getText();
  }
}