package view;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JDialog;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

public class GameScreen1 extends JFrame{
	
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	JButton button5;
	JButton button6;
	
	JPanel buttons;
	JPanel timeInstr;
	
	JLabel timer;
	JLabel instructions;
	TitledBorder buttonBorder;
	
	public GameScreen1(){
		super("ARRGH");
	}
	public GameScreen1(int level){
		
		super("ARRGH");
		timer = new JLabel("10 Seconds");
		buttonBorder = new TitledBorder("Actions");
		setSize(800,600);
		setResizable(false);
		setLayout(new BorderLayout());
		
		
		
		if(level == 1){
			
			button1 = new JButton("Man the Riggings");
			button2 = new JButton("Fire the Cannon");
			button3 = new JButton("Swab the Poop Deck");
			button4 = new JButton("Raise the Anchor");
			
			instructions = new JLabel("Veer Starboard");
			
			buttons = new JPanel(new GridLayout(2,2,10,10));
			buttons.add(button1);
			buttons.add(button2);
			buttons.add(button3);
			buttons.add(button4);
			buttons.setBorder(buttonBorder);
			timeInstr = new JPanel(new GridLayout(1,2,10,0));
			timeInstr.add(timer);
			timeInstr.add(instructions);
			
			add(buttons, BorderLayout.SOUTH);
			add(timeInstr, BorderLayout.NORTH);
			
		}
		if(level == 2){
			
		}
		if(level == 3){
			
			
		}
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
		
	public class Game1Listener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			
			if(e.getSource() == button1){
				
			}
			if(e.getSource() == button2){
				
			}
			if(e.getSource() == button3){
				
			}
			if(e.getSource() == button4){
				
			}
			if(e.getSource() == button5){
				
			}
			if(e.getSource() == button6){
				
			}
		}
	}
	
}

