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

public class LevelScreen extends JFrame{	
	JPanel buttonPanel;
	JPanel timeInstr;
	
	JLabel timer;
	JLabel instructions;
	TitledBorder buttonBorder;
	
	public LevelScreen(){
		super("ARRGH");
	}

	public LevelScreen(int level, ArrayList<String> buttonStrings){
		super("ARRGH");
		timer = new JLabel("10 Seconds");
		buttonBorder = new TitledBorder("Actions");
		setSize(800,600);
		setResizable(false);
		setLayout(new BorderLayout());
		
		ArrayList<JButton> buttons = new ArrayList<JButton>(0);
		for (String buttonString: buttonStrings) {
			JButton button = new JButton(buttonString);
			buttons.add(button);
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
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void newCommand(String command) {
		instructions.setText(command);
	}
		
	public class GameListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			
		}
	}
	
}

