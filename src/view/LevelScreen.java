package view;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
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
	
	JPanel buttonPanel;
	JPanel timeInstr;
	
	JLabel timerLabel;
	JLabel shipDamage;
	JLabel instructions;
	TitledBorder buttonBorder;
	
	ArrayList<JButton> buttons;
	
	Timer timer;
	
	public LevelScreen(){
		super("ARRGH");
	}

	public LevelScreen(GameClientEngine theGame, int level, int numButtons){
		super("ARRGH");
		
		game = theGame;
		
		timerLabel = new JLabel("");
		buttonBorder = new TitledBorder("Actions");
		setResizable(false);
		setLayout(new GridBagLayout());
		
		buttons = new ArrayList<JButton>(0);
		for(int i = 0; i < 4; i++) {
		  buttons.add(new JButton(""));
		}
		
		instructions = new JLabel("");
		shipDamage = new JLabel("10");
			
		buttonPanel = new JPanel(new GridLayout(2,2,10,10));
		for (JButton button: buttons) {
			buttonPanel.add(button);
		}
		buttonPanel.setBorder(buttonBorder);
		buttonPanel.setPreferredSize(new Dimension(700, 500));
		
		timer = new Timer(1000, new TimerListener());
		
		JPanel timerPanel = new JPanel();
		timerPanel.setLayout(new BoxLayout(timerPanel, BoxLayout.PAGE_AXIS));
		timerPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		timerPanel.add(new JButton("Image goes here"));
		timerPanel.add(timerLabel);
		timerPanel.setPreferredSize(new Dimension(210, 150));
		
		JPanel commandPanel = new JPanel(new FlowLayout());
		commandPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		commandPanel.add(instructions);
		commandPanel.setPreferredSize(new Dimension(420, 150));
		
		JPanel damagePanel = new JPanel();
		damagePanel.setLayout(new BoxLayout(damagePanel, BoxLayout.PAGE_AXIS));
		damagePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		damagePanel.add(new JButton("Image goes here."));
		damagePanel.add(shipDamage);
		damagePanel.setPreferredSize(new Dimension(210, 150));
		
		timeInstr = new JPanel(new GridBagLayout());
		timeInstr.setPreferredSize(new Dimension(850, 100));
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		add(timerPanel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 1;
    c.gridy = 0;
		add(commandPanel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 2;
    c.gridy = 0;
		add(damagePanel, c);		
		
		c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 1;
    c.gridheight = 2;
    c.gridwidth = 3;
		add(buttonPanel, c);
		
		setSize(850,700);
		setVisible(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void newCommand(String command) {
		instructions.setText(command);
		timer.stop();
		timer = new Timer(1000, new TimerListener());
		timerLabel.setText("" + 10);
		timer.start();
		
	}
	
	public void setShipDamage(int newDamage) {
		shipDamage.setText("" + newDamage);
	}
	
	public void addButtons(ArrayList<String> buttonText, ArrayList<Integer> buttonIndex) {
	  for(JButton button : buttons) {
  	  button.setText(buttonText.remove(0));
  	  button.addActionListener(new ButtonListener(buttonIndex.remove(0)));
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
		private int elapsedSeconds = 10;
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

