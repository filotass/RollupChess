package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

import logic.Bishop;
import logic.Chessboard;
import logic.Knight;
import logic.Pawn;
import logic.Queen;
import logic.Rook;

public class PromotionScreen extends JFrame implements ActionListener{

	private JPanel panel;
	private ButtonGroup radioGroup;
	private JRadioButton queen;
	private JRadioButton rook;
	private JRadioButton knight;
	private JRadioButton bishop;
	private JButton ok;
    private JLabel promtLbl;
    private String promotedPieceName;
	
	private Chessboard chessboard;
	private GUIChessboard guiChessboard;
	private MainScreen aMain;
	private int row;
	private int column;
	private Pawn pawn;
	
	public PromotionScreen(MainScreen aMain, GUIChessboard guiChessboard, Chessboard chessboard, int row, int column, Pawn pawn){
		this.aMain = aMain;
		this.chessboard = chessboard;
		this.guiChessboard = guiChessboard;
		this.pawn = pawn;
		this.row = row;
		this.column = column;
		
		promotedPieceName = "Queen";
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createBevelBorder(0));

        promtLbl = new JLabel("Please choose a piece:");
        promtLbl.setForeground(Color.BLUE);
        promtLbl.setBounds(10,10,190,20);
        queen = new JRadioButton("Queen");
		queen.setBounds(60, 40, 100, 20);
		queen.setSelected(true);
		rook = new JRadioButton("Rook");
		rook.setBounds(60, 70, 100, 20);
		knight = new JRadioButton("Knight");
		knight.setBounds(60,100, 100, 20);
		bishop = new JRadioButton("Bishop");
		bishop.setBounds(60, 130, 100, 20);
		ok = new JButton("OK");
		ok.setBounds(25, 165, 150, 20);
		ok.addActionListener(this);
		
		radioGroup = new ButtonGroup();
		radioGroup.add(queen);
		radioGroup.add(rook);
		radioGroup.add(bishop);
		radioGroup.add(knight);

        panel.add(promtLbl);
        panel.add(queen);
		panel.add(rook);
		panel.add(knight);
		panel.add(bishop);
		panel.add(ok);
		
		this.setSize(200, 200);
        this.setLocation((guiChessboard.getWidth() - getWidth())/2 + aMain.getX(),
						 (guiChessboard.getHeight() - getHeight())/2 + aMain.getY()-130);
		this.setResizable(false);
		this.setContentPane(panel);	
		this.setUndecorated(true);
		this.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e){
		
		if (queen.isSelected())
			chessboard.setPiece(new Queen(pawn.getTeam()), row, column);
		else if (rook.isSelected())
			chessboard.setPiece(new Rook(pawn.getTeam()), row, column);
		else if (bishop.isSelected())
			chessboard.setPiece(new Bishop(pawn.getTeam()), row, column);
		else if (knight.isSelected())
			chessboard.setPiece(new Knight(pawn.getTeam()), row, column);
		
		guiChessboard.initDrawBoard();
		guiChessboard.repaint();
		aMain.setEnabled(true);
		this.dispose();		
	}
	
}
