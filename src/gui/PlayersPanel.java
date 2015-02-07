package gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PlayersPanel extends JPanel{	
	private JLabel playerBlacklbl;
	private JLabel playerWhitelbl;
	private JLabel playerBlackName;
	private JLabel playerWhiteName;
	private JLabel playerBlackTime;
	private JLabel playerWhiteTime;
	private JLabel timelblWhite;
	private JLabel timelblBlack;
	private MainScreen main;
	private long whiteTime = 0, blackTime = 0;
	private int bonus;
	
		public PlayersPanel(MainScreen main){
			this.setLayout(null);
			this.main = main;
			playerBlacklbl = new JLabel("Black player: ");
			playerBlackName = new JLabel("");
			timelblWhite = new JLabel("TIME");
			playerWhitelbl = new JLabel("White player: ");
			playerWhiteName = new JLabel("");
			timelblBlack = new JLabel("TIME");			
			
			playerBlackTime = new JLabel("00:00:00");
			playerWhiteTime = new JLabel("00:00:00");
			
			playerWhitelbl.setBounds(0,0,80,30);
			playerWhiteName.setBounds(100,0,80,30);
			timelblWhite.setBounds(60,20 , 150 , 50);
			playerWhiteTime.setBounds(50, 50 , 100 , 30);
			
			playerBlacklbl.setBounds(0,100,80,30);
			playerBlackName.setBounds(100,100,80,30);
			timelblBlack.setBounds(60, 120 , 150 , 50);
			playerBlackTime.setBounds(50, 150, 100 , 30);
						
			add(playerWhitelbl);
			add(playerWhiteName);
			add(playerBlacklbl);
			add(playerBlackName);
			add(timelblWhite);
			add(playerWhiteTime);
			add(timelblBlack);
			add(playerBlackTime);			
		}
				
		public void setWhitePlayerName(String name){
			playerWhiteName.setText(name);
		}
		
		public void setBlackPlayerName(String name){
			playerBlackName.setText(name);
		}
		
		public void setInitialTime(long time, int bonus){
			this.blackTime = time;
			this.whiteTime = time;
			this.bonus = bonus;
		}
		public void decreaseWhiteTime(){
			whiteTime--;
			long hours = whiteTime / 3600;
			long min =  ( whiteTime / 60 ) % 60;
			long seconds = whiteTime % 60;
			
			String hoursStr, minStr, secStr;
			
			if ( hours < 9)
				hoursStr = "0" + hours;
			else 
				hoursStr = String.valueOf(hours);
			if ( min < 9 )
				minStr = "0" + min;
			else
				minStr = String.valueOf(min);
			
			if (seconds < 9)
				secStr = "0" + seconds;
			else
				secStr = String.valueOf(seconds);
			
			playerWhiteTime.setText(hoursStr + ":" + minStr + ":" + secStr);
            if (whiteTime == 0)
                JOptionPane.showMessageDialog(null, "Black wins the match!!!");
        }
		
		public void decreaseBlackTime(){
			blackTime--;
			long hours = blackTime / 3600;
			long min =  ( blackTime / 60 ) % 60;
			long seconds = blackTime % 60;
			
	        String hoursStr, minStr, secStr;
			
			if ( hours < 9)
				hoursStr = "0" + hours;
			else 
				hoursStr = String.valueOf(hours);
			if ( min < 9 )
				minStr = "0" + min;
			else
				minStr = String.valueOf(min);
			
			if (seconds < 9)
				secStr = "0" + seconds;
			else
				secStr = String.valueOf(seconds);
			
            if (whiteTime == 0)
                JOptionPane.showMessageDialog(null, "Black wins the match!!!");
            
			playerBlackTime.setText(hoursStr + ":" + minStr + ":" + secStr);
			
            if (blackTime == 0)
                JOptionPane.showMessageDialog(null, "White wins the match!!!");
        }
		
		public void addBonusWhite(){
			whiteTime+=bonus+1;
		}
		
		public void addBonusBlack(){
			blackTime+=bonus+1;
		} 	
		
		public String getWhiteName(){
			return playerWhiteName.getText();
		}
		
		public String getBlackName(){
			return playerBlackName.getText();
		}

		public long getWhiteTime() {
			return whiteTime;
		}

		public void setWhiteTime(long whiteTime) {
			this.whiteTime = whiteTime+1;
			decreaseWhiteTime();
		}

		public long getBlackTime() {
			return blackTime;
		}

		public void setBlackTime(long blackTime) {
			this.blackTime = blackTime+1;
			decreaseBlackTime();
		}		
}
