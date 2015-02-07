package gui;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import logic.Chessboard;

public class IOFileHandler{
	
	private static String moves="";
	private static ArrayList<String> movesList = new ArrayList<String>();
	private boolean special =true;
	
	private PlayersPanel playersPanel;
	private MovesPanel movesPanel;
	private GUIChessboard guiChessboard;
	private Chessboard chessboard;

	public IOFileHandler(PlayersPanel playersPanel, MovesPanel movesPanel, GUIChessboard guiChessboard, Chessboard chessboard){
		this.playersPanel = playersPanel;
		this.movesPanel = movesPanel;
		this.guiChessboard = guiChessboard;
		this.chessboard = chessboard;
	}		
	
	public void addMove(String moves) {
		this.moves += moves + "\n";
	}

	public void saveGame(String fileName){
		try{
			BufferedWriter buffWriter = new BufferedWriter(new FileWriter("Save/game.sav",true));
			buffWriter.write(fileName);
			buffWriter.newLine();
			buffWriter.close();
		}catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Couldn't save the name of  game!");
		}
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("Save/"+fileName+".rollup"));
			bw.write(playersPanel.getWhiteName());
			bw.newLine();
			bw.write(playersPanel.getBlackName());
			bw.newLine();
			bw.write(String.valueOf(playersPanel.getWhiteTime()));
			bw.newLine();
			bw.write(String.valueOf(playersPanel.getBlackTime()));
			bw.newLine();
			bw.write(moves);
			bw.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Couldn't save the game!");
		}
	}
	
	public void loadGame(String fileName){
		moves ="";		
		try{			
			BufferedReader br = new BufferedReader(new FileReader("Save/"+fileName+".rollup"));
			playersPanel.setWhitePlayerName(br.readLine());
			playersPanel.setBlackPlayerName(br.readLine());
			playersPanel.setWhiteTime(Long.valueOf(br.readLine()));
			playersPanel.setBlackTime(Long.valueOf(br.readLine()));
			movesPanel.setStarted(false);
			if (!special){
				movesPanel.setSpecial(true);
			}
			special = false;
			movesPanel.clearArea();
			chessboard.initBoard();
			while((moves = br.readLine())!= null){
				movesList.add(moves);
				movesPanel.addMove(moves);
			}
			movesPanel.setSpecial(false);
			br.close();		
			movesPanel.getNext().setEnabled(true);
			movesPanel.getPrev().setEnabled(false);
			movesPanel.getResume().setEnabled(true);			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Couldn't load the game!");
		}
		guiChessboard.removeMouseListener(guiChessboard);
		guiChessboard.setGameStarted(false);
		guiChessboard.initDrawBoard();
		guiChessboard.repaint();	
	}

	public static ArrayList<String> getMovesList() {
		return movesList;
	}	
}
