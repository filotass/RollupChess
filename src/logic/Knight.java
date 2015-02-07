package logic;

import utils.Calculator;

public class Knight extends Piece {

	public Knight(String team) {
		this.setName("N");
		this.setTeam(team);
		this.loadImage(1);
	}
	
	private int whatToDo(Chessboard chessboard, int row, int column){
		if (chessboard.getPiece(row, column)== null)
			return 1;
		else if (chessboard.getPiece(row, column).getTeam().equals(this.getTeam()))
			return 0;
		else
			return 2;
	}
	
	@Override
	public void updateAvailableMoves(Chessboard chessboard, int row, int column) {
		int [][] temp = new int[8][8];
		
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++){
				temp[i][j]=0;
			}
		
		if (Calculator.isOnBoard(row+2,column-1))
			temp[row+2][column-1]= whatToDo(chessboard,row+2,column-1);
		
		if (Calculator.isOnBoard(row+2,column+1))
			temp[row+2][column+1]= whatToDo(chessboard,row+2,column+1);
		
		if (Calculator.isOnBoard(row-2,column-1))
			temp[row-2][column-1]= whatToDo(chessboard,row-2,column-1);
		
		if (Calculator.isOnBoard(row-2,column+1))
			temp[row-2][column+1]= whatToDo(chessboard,row-2,column+1);
		
		if (Calculator.isOnBoard(row-1,column+2))
			temp[row-1][column+2]= whatToDo(chessboard,row-1,column+2);
		
		if (Calculator.isOnBoard(row+1,column+2))
			temp[row+1][column+2]= whatToDo(chessboard,row+1,column+2);
		
		if (Calculator.isOnBoard(row-1,column-2))
			temp[row-1][column-2]= whatToDo(chessboard,row-1,column-2);
		
		if (Calculator.isOnBoard(row+1,column-2))
			temp[row+1][column-2]= whatToDo(chessboard,row+1,column-2);
		
		this.setAvailableMoves(temp);
	}

}
