package view;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JDialog;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

import Game.*;

public class LevelScreen extends JFrame{
	private GameClientEngine game;
	
	JPanel buttonPanel;
	JPanel timeInstr;
	
	JLabel timer;
	JLabel instructions;
	TitledBorder buttonBorder;
	
	ArrayList<JButton> buttons;
	
	public LevelScreen(){
		super("ARRGH");
	}

	public LevelScreen(GameClientEngine theGame, int level, int numButtons){
		super("ARRGH");
		
		game = theGame;
		
		timer = new JLabel("10 Seconds");
		buttonBorder = new TitledBorder("Actions");
		setSize(800,600);
		setResizable(false);
		setLayout(new BorderLayout());
		
		buttons = new ArrayList<JButton>(0);
		for(int i = 0; i < 4; i++) {
		  buttons.add(new JButton(""));
		}
		
		instructions = new JLabel("");
			
		buttonPanel = new JPanel(new GridLayout(2,2,10,10));
		for (JButton button: buttons) {
			buttonPanel.add(button);
		}
		buttonPanel.setBorder(buttonBorder);
		
		timeInstr = new JPanel(new GridLayout(1,2,10,0));
		timeInstr.add(timer);
		timeInstr.add(instructions);
			
		add(buttonPanel, BorderLayout.SOUTH);
		add(timeInstr, BorderLayout.NORTH);
		
		setVisible(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void newCommand(String command) {
		instructions.setText(command);
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
	
}

