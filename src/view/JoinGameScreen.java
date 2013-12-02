package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Game.GameController;
import Game.Player;

/**
 * JoinGameScreen
 * This dialog is used to get information from
 * a player to join an existing game.
 */
public class JoinGameScreen extends JDialog {
  JButton enter;
  JTextField ip;
  JTextField username;
  JLabel enterUsername;
  JLabel enterIP;
  JPanel field;

  GameController game;
  
  /**
   * Constructor
   * Sets up the dialog to get information from the player.
   * Access outside the dialog is restricted while visible.
   * @param thegame
   * @param splashScreen
   */
  public JoinGameScreen(GameController thegame, SplashScreen splashScreen) {
    super(splashScreen, "Join Game", true);
    game = thegame;

    setLayout(new BorderLayout());
    enter = new JButton("Enter");
    enter.setFont(new Font("serif", Font.PLAIN, 20));
    enterUsername = new JLabel("Enter Your Username");
    enterIP = new JLabel("Enter IP Adress of target Server");
    ip = new JTextField();
    username = new JTextField();
    enter.addActionListener(new EnterListener(game));

    field = new JPanel(new GridLayout(2, 2, 10, 10));
    field.add(enterIP);
    field.add(ip);
    field.add(enterUsername);
    field.add(username);
    field.setBorder(new EmptyBorder(10, 10, 10, 10));
    add(field, BorderLayout.NORTH);
    add(enter, BorderLayout.SOUTH);

    setSize(400, 150);
    setLocation(200, 200);
    setVisible(true);
    setResizable(false);
  }

  /**
   * GetUsername
   * Gets the username from field
   * @return
   */
  public String getUsername() {
    return (username.getText());
  }

  /**
   * GetIP
   * Gets the IP address from field
   * @return
   */
  public String getIP() {
    return (ip.getText());
  }

  /**
   * EnterListener
   * The action listener for the enter button.
   */
  public class EnterListener implements ActionListener {
    private GameController game;

    public EnterListener(GameController thegame) {
      game = thegame;
    }

    public void actionPerformed(ActionEvent e) {
      //Check to make sure that username and IP aren't empty
      if (!ip.getText().equals("") && !getUsername().equals("")) {
        setVisible(false);
        //Create the new player and start joining the game.
        game.player = new Player(getUsername().replaceAll(" ", "_"), 0);
        game.joinGame(ip.getText(), getUsername().replaceAll(" ", "_"));
      } else if (ip.getText().equals("")) {
        JOptionPane.showMessageDialog(null, "Enter an IP Address matey.",
            "Connection Error", JOptionPane.ERROR_MESSAGE);
      } else {
        JOptionPane.showMessageDialog(null, "D'ya have name matey?",
            "Connection Error", JOptionPane.ERROR_MESSAGE);
      }

    }
  }
}
