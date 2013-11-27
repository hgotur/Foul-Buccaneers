package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;


public class Screen extends JFrame {
	
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	JLabel command;
	JLabel time;
	JLabel action;
	JPanel buttons;
	JPanel labels;
	ScreenListener listen;
	int timer;
	
	public Screen(){
	
		super("Screen One");
		
		
		timer = 10;
		
		listen = new ScreenListener();
		
		button1 = new JButton("Button One");
		button1.setBorder(BorderFactory.createLineBorder(Color.YELLOW,10));
		button2 = new JButton("Button Two");
		button2.setBorder(BorderFactory.createLineBorder(Color.YELLOW,10));
		button3 = new JButton("Button Three");
		button3.setBorder(BorderFactory.createLineBorder(Color.YELLOW,10));
		button4 = new JButton("Button Four");
		button4.setBorder(BorderFactory.createLineBorder(Color.YELLOW,10));
		command = new JLabel("Commands come in here");//will be updated by server
		time = new JLabel(timer + " seconds");
		action = new JLabel("action");
		buttons = new JPanel(new GridLayout(2,2));
		labels = new JPanel(new BorderLayout());
		
		buttons.add(button1);
		buttons.add(button2);
		buttons.add(button3);
		buttons.add(button4);
		labels.add(command, BorderLayout.WEST);
		labels.add(time, BorderLayout.EAST);
		labels.add(action, BorderLayout.SOUTH);
		
		setLayout(new BorderLayout());
		add(buttons, BorderLayout.NORTH);
		add(labels, BorderLayout.SOUTH);
		
		button1.addActionListener(listen);
		button2.addActionListener(listen);
		button3.addActionListener(listen);
		button4.addActionListener(listen);
		
		setSize(800,450);
		setVisible(true);
		
		
		
		
	}
	
	public class ScreenListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == button1){
				System.out.println("Button One Pressed");
				action.setText("Button One Pressed");
				
			}
			if(e.getSource() == button2){
				System.out.println("Button Two Pressed");
				action.setText("Button Two Pressed");
			}
			if(e.getSource() == button3){
				System.out.println("Button Three Pressed");
				action.setText("Button Three Pressed");
			}
			if(e.getSource() == button4){
				System.out.println("Button Four Pressed");
				action.setText("Button Four Pressed");
				
			}
			
			
			
		}

	}
	
	
}
