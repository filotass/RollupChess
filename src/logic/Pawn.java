package logic;

import utils.Calculator;
import gui.GUIChessboard;

public class Pawn extends Piece {
	
	private GUIChessboard test;
	
	public Pawn(GUIChessboard test, String team) {
		this.test = test;
		this.setName("P");
		this.setTeam(team);
		this.loadImage(5);
	}
	
	@Override
	public void updateAvailableMoves(Chessboard chessboard, int row, int column) {
		int[][] temp = new int[8][8];
		int move;
		
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++){
				temp[i][j]=0;
			}
		
		if (this.getTeam().equals("White"))
			move = -1;
		else
			move=1;
		
		if(Calculator.isOnBoard(row+move, column))
		if (chessboard.getPiece(row+move, column)==null){
			temp[row+move][column] = 1;
			if (Calculator.isOnBoard(row+2*move, column))
				if (chessboard.getPiece(row+2*move, column)==null)
					if ((move==-1 && row==6) || (move==1 && row==1))
						temp[row+2*move][column] = 1;		
		}
	
		if (Calculator.isOnBoard(row+move, column-1))
			if (chessboard.getPiece(row+move, column-1)!=null && chessboard.getPiece(row+move, column-1).getTeam()!=this.getTeam())
				temp[row+move][column-1]=2;
		
		
		if (Calculator.isOnBoard(row+move, column+1))
			if (chessboard.getPiece(row+move, column+1)!=null && chessboard.getPiece(row+move, column+1).getTeam()!=this.getTeam())
				temp[row+move][column+1]=2;
		
			//elegxos an pasan		
		if ((row==3 && move==-1)||(row==4 && move==1)){
			if (chessboard.isAnpasan() && column+1==chessboard.getAnpasan_column())
				temp[row+move][column+1]=2;
			if (chessboard.isAnpasan() && column-1==chessboard.getAnpasan_column())
				temp[row+move][column-1]=2;
		}
		
		this.setAvailableMoves(temp);
		
	}
	
	public void move(Chessboard chessboard,int prev_row, int prev_column, int row, int column){
		chessboard.setPiece(null, prev_row, prev_column);
		chessboard.setPiece(this, row, column);
		if (chessboard.isAnpasan() && column==chessboard.getAnpasan_column()){
			if ((prev_row==3 && this.getTeam().equals("White")) || (prev_row==4 && this.getTeam().equals("Black"))){
				chessboard.setPiece(null,prev_row,column);
				test.initDrawBoard();
			}
		}else if((row==0 && this.getTeam().equals("White")) || (row==7 && this.getTeam().equals("Black"))){
			//edo tha mpei Methodos parathiroy dialogoy
			test.showPromotionFrame(row,column,this);		
		}
		
		if (Math.abs(prev_row-row)==2){
			chessboard.setAnpasan(true);
			chessboard.setAnpasan_column(column);
		}else{
			chessboard.setAnpasan(false);
		}
	}
	
	public void removeCheckSquares (Chessboard chessboard,String team, int row, int column){
		Piece tempPiece,tempPiece2=null; 
		

		
		
		for (int i=0; i<8; i++){
		    for (int j=0; j<8; j++){
				if (this.getAvailableMove(i, j) == 1){
					chessboard.setPiece(null, row, column);
					chessboard.setPiece(this, i, j);
				    if (chessboard.isCheck(team)){
					   this.setAvailableMove(0,i,j); 				      
				    }
				    chessboard.setPiece(this, row, column);
					chessboard.setPiece(null, i, j);	   
				}
				else if (this.getAvailableMove(i, j) == 2){
					if (chessboard.isAnpasan() && j==chessboard.getAnpasan_column()){
						if ((row==3 && this.getTeam().equals("White")) || (row==4 && this.getTeam().equals("Black"))){
							tempPiece2=chessboard.getPiece(row, j);
							chessboard.setPiece(null,row,j);
						}
					}
				
					tempPiece = chessboard.getPiece(i, j);
					chessboard.setPiece(null, row, column);
					chessboard.setPiece(this, i, j);
				    if (chessboard.isCheck(team)){
					   this.setAvailableMove(0,i,j); 				      
				    }
				    chessboard.setPiece(tempPiece, i, j);
				    chessboard.setPiece(this, row, column);		
				    
					if (chessboard.isAnpasan() && j==chessboard.getAnpasan_column()){
						if ((row==3 && this.getTeam().equals("White")) || (row==4 && this.getTeam().equals("Black")))
						chessboard.setPiece(tempPiece2,row,j);
					}
			    }
		    }
		}
	}


}
