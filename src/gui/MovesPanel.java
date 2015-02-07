package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

import utils.Calculator;

import logic.Chessboard;

public class MovesPanel extends JPanel implements ActionListener{
		
		private MainScreen main;
		private GUIChessboard guiChessboard;
		private Chessboard chessboard;
		
		
		private JTextArea movesArea;
		private JButton next,prev,resume;
		private JScrollPane scroll;
		private JScrollBar scrollbar;
		private String allMoves = "";
		private int turn=0;
		
		private boolean started = false;
		private boolean special = false;
		private static int i=0;
				
		public MovesPanel(MainScreen main){
			scrollbar = new JScrollBar();
			
			this.setLayout(null);		
			
			this.main = main;
			next = new JButton("redo");
			next.addActionListener(this);
			prev = new JButton("undo");
			prev.addActionListener(this);
			resume = new JButton("resume");
			resume.addActionListener(this);
			
			next.setEnabled(false);
			prev.setEnabled(false);
			resume.setEnabled(false);			
			
			movesArea = new JTextArea();
			movesArea.setBounds(0,10,150, 250);
			movesArea.setEditable(false);
			movesArea.setLineWrap(true);
			
			scroll = new JScrollPane(movesArea);			
			scroll.setBounds(0,10,150,250);

			prev.setBounds(0, 275, 70, 20);
			next.setBounds(80, 275, 70, 20); 
			resume.setBounds(40, 300, 80, 20);
			
			allMoves = "      White         Black   \n";
			movesArea.setText(allMoves);
			this.add(scroll);
		    this.add(next);
		    this.add(prev);
		    this.add(resume);
						
		}		
		
		public JButton getNext() {
			return next;
		}

		public JButton getPrev() {
			return prev;
		}

		public JButton getResume() {
			return resume;
		}

		public void setStarted(boolean started) {
			this.started = started;
		}		

		public void setSpecial(boolean special) {
			this.special = special;
		}

		public void addMove(String move){
			if ( turn%2 == 0){
				allMoves += "\n" + "     " + move + "        ";
			}else{
				allMoves += move;
			}
			movesArea.setText(allMoves);
			turn++;
		}
		
		public void clearArea(){
			if (started || special){
				allMoves = "      White         Black   \n ";
			}
			started = true;
			movesArea.setText(allMoves);
		}
		
		public void actionPerformed(ActionEvent e) {			
			if (e.getSource() == next){				
				if (i < IOFileHandler.getMovesList().size() && i>=0){
					String temp = IOFileHandler.getMovesList().get(i);
					chessboard.getPiece(							
							Calculator.rowDecode(temp, 2), 
				            Calculator.columnDecode(temp, 1)).
				            move(chessboard, 
				            		
				            Calculator.rowDecode(temp, 2), 
				            Calculator.columnDecode(temp, 1), 
				            
				            Calculator.rowDecode(temp, 5), 
				            Calculator.columnDecode(temp, 4));

					guiChessboard.initDrawBoard();
					guiChessboard.repaint();
					i++;
					prev.setEnabled(true);
					if (i==IOFileHandler.getMovesList().size())
						next.setEnabled(false);
				}
			}else if (e.getSource() == prev){
				if (i <= IOFileHandler.getMovesList().size() && i>=0){ 
					chessboard.initBoard();
					int j=i;
					if (j==1)
						prev.setEnabled(false);
					i=0;
				while( i<j-1){
						String temp = IOFileHandler.getMovesList().get(i);
						chessboard.getPiece(							
							Calculator.rowDecode(temp, 2), 
				            Calculator.columnDecode(temp, 1)).
				            move(chessboard, 
				            		
				            Calculator.rowDecode(temp, 2), 
				            Calculator.columnDecode(temp, 1), 
				            
				            Calculator.rowDecode(temp, 5), 
				            Calculator.columnDecode(temp, 4));
						i++;
						next.setEnabled(true);
					}
				
					guiChessboard.initDrawBoard();
					guiChessboard.repaint();
				}
			}else{
				while (i < IOFileHandler.getMovesList().size()){
					String temp = IOFileHandler.getMovesList().get(i);
					chessboard.getPiece(							
							Calculator.rowDecode(temp, 2), 
				            Calculator.columnDecode(temp, 1)).
				            move(chessboard, 
				            		
				            Calculator.rowDecode(temp, 2), 
				            Calculator.columnDecode(temp, 1), 
				            
				            Calculator.rowDecode(temp, 5), 
				            Calculator.columnDecode(temp, 4));

					i++;
				}
				next.setEnabled(false);
				prev.setEnabled(false);
				resume.setEnabled(false);
				
				guiChessboard.resumeGame();
				
				main.startClock();
				
				guiChessboard.initDrawBoard();
				guiChessboard.repaint();
			}
		}
		
		public void setGUIChessboard(GUIChessboard gc){
			guiChessboard = gc;
		}
		
		public void setChessboard(Chessboard c){
			chessboard = c;
		}
}
