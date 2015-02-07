package gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TimeScreen extends JFrame implements ActionListener{
	private JTextField hoursField;
	private JTextField minField;
	private JTextField bonusField;
	private JCheckBox bonusSec;
	private JLabel messagelbl;
	private JLabel hourslbl;
	private JLabel minlbl;
	private JButton okbtn;
	private boolean checked= false;
	private MainScreen mainWindow;
    private NewGameScreen newGameWindow;
    private int hours, min, bonus;
	
	public TimeScreen(MainScreen main, NewGameScreen newGameWindow){
		
		hoursField = new JTextField(2);
        minField = new JTextField(2);
        bonusField = new JTextField(2);
		bonusField.setEnabled(false);
		bonusField.setText("0");
		messagelbl = new JLabel("Please enter the game duration: ");
		bonusSec = new JCheckBox("Bonus seconds: ");
		bonusSec.addActionListener(this);
		hourslbl = new JLabel("Hours");
		minlbl = new JLabel("Min");
		okbtn = new JButton("OK");
		okbtn.addActionListener(this);
		
		messagelbl.setBounds(10, 10, 200, 30);
		hourslbl.setBounds(80, 40 , 50 , 30);
		minlbl.setBounds(140, 40 ,  50 , 30);
		hoursField.setBounds(85 , 70 , 20 , 20);
		minField.setBounds(140 , 70 , 20 ,20);
		bonusSec.setBounds(10 , 100 , 130 , 30);
		bonusField.setBounds(150 , 105 , 20 ,20);
		okbtn.setBounds(75, 150, 80, 20);
		
		add(messagelbl);
		add(hourslbl);
		add(minlbl);
		add(hoursField);
		add(minField);
		add(bonusSec);
		add(bonusField);
		add(okbtn);
		
        mainWindow = main;
        this.newGameWindow = newGameWindow;
        this.setLayout(null);
        this.setTitle("Time settings");
		this.setSize(250,220);
        this.setLocationRelativeTo(main);
        this.setResizable(false);
        this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if ( e.getSource() == bonusSec){
			checked = !checked;
			if (checked)
				bonusField.setEnabled(true);
			else
				bonusField.setEnabled(false);
		}else{
			try{
				hours = Integer.parseInt(hoursField.getText());
				min = Integer.parseInt(minField.getText());
				bonus = Integer.parseInt(bonusField.getText());
				
				if ( ( hours == 0 && min == 0) || ( hours < 0 || min < 0 ) || ( hours > 24 || min > 60)){
					JOptionPane.showMessageDialog(this, "Please enter an accepted time!");
				}else if (bonus > 30 || bonus < 0){
					JOptionPane.showMessageDialog(this, "Please enter for bonus time a number between 0 and 30");
				}else{
					hours = hours*60;
					mainWindow.setTime(hours + min,bonus);
                    this.dispose();
				}
				
			}catch(Exception ex){
				JOptionPane.showMessageDialog(this, "Please check the time...");
		    }
				
			
		}
	}
}
