package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class NewGameScreen extends JFrame implements ActionListener{
	private JTextField whiteFieldName, blackFieldName;
	private JLabel whiteName, blackName, timelbl;
	private JButton startBtn, cancelBtn, setTimeBtn;
	private PlayersPanel playersPanel;
	private MainScreen mainWindow;
	private TimeScreen timeWindow;
	private MovesPanel movesPanel;

    public NewGameScreen(PlayersPanel playersPanel,MovesPanel movesPanel,MainScreen mainWindow){

		this.playersPanel = playersPanel;
		this.mainWindow = mainWindow;
		this.movesPanel = movesPanel;
		mainWindow.setEnabled(false);
		
		whiteName = new JLabel("White's name: ");
		blackName = new JLabel("Blacks' name: ");
		timelbl = new JLabel("Use time: ");
		whiteFieldName = new JTextField(10);
		blackFieldName = new JTextField(10);
		setTimeBtn = new JButton("set time");
		setTimeBtn.addActionListener(this);
		startBtn = new JButton("start");
		startBtn.addActionListener(this);
		cancelBtn = new JButton("cancel");
		cancelBtn.addActionListener(this);
		
		whiteName.setBounds(15,30, 100, 25);
		whiteFieldName.setBounds(115,35 , 100 ,18);
		blackName.setBounds(15,65, 100, 25);
		blackFieldName.setBounds( 115, 70 , 100, 18);
		timelbl.setBounds(15, 110, 100 , 25);
		setTimeBtn.setBounds( 115, 110 , 100 ,25);
		startBtn.setBounds(45 ,175, 100 , 25 );
		cancelBtn.setBounds(155, 175, 100, 25);
		
		add(whiteName);
		add(whiteFieldName);
		add(blackName);
		add(blackFieldName);
		add(timelbl);
		add(setTimeBtn);
		add(startBtn);
		add(cancelBtn);

        this.setLayout(null);
        this.setTitle("New game settings");
		this.setSize(300,250);
		this.setLocationRelativeTo(mainWindow);
        this.setVisible(true);		
	}

    public void actionPerformed(ActionEvent e) {
        if( e.getSource() == setTimeBtn){
            timeWindow = new TimeScreen(mainWindow, this);
        }
        else if (e.getSource() == startBtn){
                if (whiteFieldName.getText().equals("") || blackFieldName.getText().equals("")){
                    JOptionPane.showMessageDialog(this,"Please enter player names...");
                }else{
                    playersPanel.setWhitePlayerName(whiteFieldName.getText());
                    playersPanel.setBlackPlayerName(blackFieldName.getText());
                    movesPanel.clearArea();
                    mainWindow.startTime();
                    mainWindow.setEnabled(true);
                    mainWindow.startTheGame();
                    this.dispose();
                }
        }else{
			mainWindow.setEnabled(true);
			this.dispose();
		}
	}
}
