package view;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JDialog;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import javax.swing.Timer;

import Game.*;

public class LevelScreen extends JFrame{
	private GameClientEngine game;
	
	JLabel mainPanel;
	JPanel buttonPanel;
	JPanel timeInstr;
	JPanel buttonGrid;
	
	JLabel timerLabel;
	JLabel shipDamage;
	JLabel instructions;
	TitledBorder buttonBorder;
	
	ArrayList<JButton> buttons;
	
	Timer timer;
	int timerTime;
	
	public LevelScreen(){
		super("ARRGH");
	}

	public LevelScreen(GameClientEngine theGame, int level, int time, int shipDamageNumber){
		super("ARRGH");
		
		game = theGame;
		timerTime = time;
		
		timerLabel = new JLabel("");
		timerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		timerLabel.setForeground(Color.white);
		timerLabel.setFont(new Font("serif", Font.BOLD, 30));
		
		setResizable(false);
		
		mainPanel = new JLabel(new ImageIcon(getClass().getResource("/view/images/gamebg.jpg")));
		mainPanel.setLayout(new GridBagLayout());
		
		buttons = new ArrayList<JButton>(0);
		
		instructions = new JLabel("");
		instructions.setForeground(Color.white);
		instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
		instructions.setFont(new Font("serif", Font.BOLD, 24));
		
		shipDamage = new JLabel("" + shipDamageNumber);
		shipDamage.setForeground(Color.white);
		shipDamage.setAlignmentX(Component.CENTER_ALIGNMENT);
		shipDamage.setFont(new Font("serif", Font.BOLD, 30));
		
		//int rows = buttons.size()/2;
		//int columns = buttons.size()/2;
		buttonGrid = new JPanel(new GridLayout(3, 2, 20, 20));
		buttonGrid.setOpaque(false);
		
		GridBagConstraints c = new GridBagConstraints();
		
		buttonPanel = new JPanel(new GridBagLayout());
		c.gridx = 1;
		c.gridy = 1;
		buttonPanel.add(buttonGrid, c);
		buttonPanel.setBorder(buttonBorder);
		buttonPanel.setOpaque(false);
		buttonPanel.setPreferredSize(new Dimension(700,480));
		
		timer = new Timer(1000, new TimerListener());
		
		JPanel timerPanel = new JPanel();
		timerPanel.setLayout(new BoxLayout(timerPanel, BoxLayout.Y_AXIS));
		JLabel timerTitle = new JLabel("Time Remaining");
		timerTitle.setFont(new Font("serif", Font.BOLD, 24));
		timerTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		timerTitle.setForeground(Color.white);
		timerPanel.add(timerTitle);
		JPanel innerTimerPanel = new JPanel(new FlowLayout());
		innerTimerPanel.add(new JLabel(new ImageIcon(getClass().getResource("/view/images/hourglass.png"))));
		innerTimerPanel.add(timerLabel);
		innerTimerPanel.setOpaque(false);
		timerPanel.add(innerTimerPanel);
		timerPanel.setOpaque(false);
		timerPanel.setPreferredSize(new Dimension(210, 170));
		
		JPanel commandPanel = new JPanel();
		commandPanel.setLayout(new BoxLayout(commandPanel, BoxLayout.Y_AXIS));
		JLabel shipWheel = new JLabel(new ImageIcon(getClass().getResource("/view/images/ship-wheel.png")));
		shipWheel.setAlignmentX(Component.CENTER_ALIGNMENT);
		commandPanel.add(shipWheel);
		commandPanel.add(instructions);
		commandPanel.setOpaque(false);
		commandPanel.setPreferredSize(new Dimension(420, 170));
		
		JPanel damagePanel = new JPanel();
		damagePanel.setLayout(new BoxLayout(damagePanel, BoxLayout.Y_AXIS));
		JLabel pirateShip = new JLabel(new ImageIcon(getClass().getResource("/view/images/pirateShip.png")));
    pirateShip.setAlignmentX(Component.CENTER_ALIGNMENT);
    JPanel innerDamagePanel = new JPanel(new FlowLayout());
    innerDamagePanel.add(pirateShip);
    innerDamagePanel.add(shipDamage);
    innerDamagePanel.setOpaque(false);
    JLabel damageTitle = new JLabel("Ship Damage");
    damageTitle.setFont(new Font("serif", Font.BOLD, 24));
    damageTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    damageTitle.setForeground(Color.white);
    damagePanel.add(damageTitle);
		damagePanel.add(innerDamagePanel);
		damagePanel.setOpaque(false);
		damagePanel.setPreferredSize(new Dimension(210, 170));
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		mainPanel.add(timerPanel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 1;
    c.gridy = 0;
    mainPanel.add(commandPanel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 2;
    c.gridy = 0;
    mainPanel.add(damagePanel, c);		
		
		c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 1;
    c.gridheight = 2;
    c.gridwidth = 3;
    mainPanel.add(buttonPanel, c);
		
    add(mainPanel);
		setSize(850,700);
		setVisible(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void newCommand(String command) {
		instructions.setText("<html><body style='text-align:center'>"+command+"</body></html>");
		timer.stop();
		timer = new Timer(1000, new TimerListener());
		timerLabel.setText("" + timerTime);
		timer.start();
	}
	
	public void setShipDamage(int newDamage) {
		shipDamage.setText("" + newDamage);
	}
	
	public void addButtons(ArrayList<String> buttonText, ArrayList<Integer> buttonIndex) {
	  buttons = new ArrayList<JButton>(0);
	  for(int i = 0; i < buttonText.size(); i++) {
  	  buttons.add(new JButton(buttonText.get(i)));
  	  buttons.get(i).addActionListener(new ButtonListener(buttonIndex.get(i)));
  	  buttons.get(i).setFont(new Font("serif", Font.PLAIN, 24));
  	  buttonGrid.add(buttons.get(i));
	  }
	}
		
	public class ButtonListener implements ActionListener{
		private int index;
		public ButtonListener(int buttonIndex) {
			index = buttonIndex;
		}
		public void actionPerformed(ActionEvent e){
			game.sendButtonInput(index);
		}
	}
	
	public class TimerListener implements ActionListener {
		private int elapsedSeconds = timerTime;
	    public void actionPerformed(ActionEvent evt){
	        elapsedSeconds--;
	        timerLabel.setText("" + elapsedSeconds);
	        if(elapsedSeconds == 0){
	            timer.stop();
	            game.sendCommandFailed();
	        }
	    }
	}
	
}

