package logic;

import utils.Calculator;

public class King extends Piece{

	public King(String team) {
		this.setName("K");
		this.setTeam(team);
		this.loadImage(3);
	}

	@Override
	public void updateAvailableMoves(Chessboard chessboard, int row, int column) {
		int [][] temp = new int[8][8];
		
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++){
				temp[i][j]=0;
			}
		
		for(int i=-1;i<=1;i++){
			for(int j=-1;j<=1;j++)
				if (Calculator.isOnBoard(row+i, column+j)){
					if (chessboard.getPiece(row+i, column+j)==null)
						temp[row+i][column+j]=1;
					else if (chessboard.getPiece(row+i, column+j).getTeam()==this.getTeam())
						temp[row+i][column+j]=0;
					else
						temp[row+i][column+j]=2;			            
				}
		}
		
		//ROKE
		if (this.getTeam().equals("White")){
			//Prepei na mpei elegxos an einai check kai an einai check sta tetragona apo ta opoia pernaei (xoris to teleutaio)
			if (chessboard.isWhiteKingSideCastle() && chessboard.getPiece(row, column+1)==null && chessboard.getPiece(row, column+2)==null){
				temp[row][column+2]=1;
			}
			if (chessboard.isWhiteQueenSideCastle() && chessboard.getPiece(row, column-1)==null && chessboard.getPiece(row, column-2)==null && chessboard.getPiece(row, column-3)==null){
				temp[row][column-2]=1;
			}
		}else{
			if (chessboard.isBlackKingSideCastle() && chessboard.getPiece(row, column+1)==null && chessboard.getPiece(row, column+2)==null){
				temp[row][column+2]=1;
			}
			if (chessboard.isBlackQueenSideCastle() && chessboard.getPiece(row, column-1)==null && chessboard.getPiece(row, column-2)==null && chessboard.getPiece(row, column-3)==null){
				temp[row][column-2]=1;
			}
		}
		
		this.setAvailableMoves(temp);
			
	}
	
	
	public void move(Chessboard chessboard,int prev_row, int prev_column, int row, int column){
		super.move(chessboard, prev_row, prev_column, row, column);
		
		if (prev_column - column==-2){ //Mikro Roke
			chessboard.setPiece(chessboard.getPiece(row, 7), row, column-1);
			chessboard.setPiece(null, row, 7);
		}else if(prev_column-column==2){  //Megalo Roke
			chessboard.setPiece(chessboard.getPiece(row, 0), row, column+1);
			chessboard.setPiece(null, row, 0);
		}
			
		
		if (this.getTeam().equals("White")){
			chessboard.setWhiteKingSideCastle(false);
			chessboard.setWhiteQueenSideCastle(false);
		}else{
			chessboard.setBlackKingSideCastle(false);
			chessboard.setBlackQueenSideCastle(false);
		}
		
		chessboard.setKingRow(this.getTeam(), row);
		chessboard.setKingColumn(this.getTeam(), column);
		
	}
	
	public void removeCheckSquares (Chessboard chessboard,String team, int row, int column){
		Piece tempPiece; 
		int x,y;
					
		x=chessboard.getKingRow(this.getTeam());
		y=chessboard.getKingColumn(this.getTeam());
		/////////////////////////////////////////////////////////////
		if (this.getTeam().equals("White")){
			if(chessboard.isWhiteKingSideCastle() && chessboard.isCheck(team))
				this.setAvailableMove(0, row, column+2);
		    if(chessboard.isWhiteQueenSideCastle() && chessboard.isCheck(team))
				this.setAvailableMove(0, row, column-2);
		}else if(this.getTeam().equals("Black")){
			if(chessboard.isBlackKingSideCastle() && chessboard.isCheck(team))
				this.setAvailableMove(0, row, column+2);
			if(chessboard.isBlackQueenSideCastle() && chessboard.isCheck(team))
				this.setAvailableMove(0, row, column-2);
		}
		if (this.getTeam().equals("White")){
			if (chessboard.isWhiteKingSideCastle() && this.getAvailableMove(row, column+1)==1){
				chessboard.setKingColumn(this.getTeam(),column+1);
				chessboard.setPiece(null, row, column);
				chessboard.setPiece(this, row, column+1);
			    if (chessboard.isCheck(team))
					   this.setAvailableMove(0,row,column+2); 
			    chessboard.setKingColumn(this.getTeam(),column);
				chessboard.setPiece(this, row, column);
				chessboard.setPiece(null, row, column+1);
			}
			if (chessboard.isWhiteQueenSideCastle() && this.getAvailableMove(row, column-1)==1){
				chessboard.setKingColumn(this.getTeam(),column-1);
				chessboard.setPiece(null, row, column);
				chessboard.setPiece(this, row, column-1);
			    if (chessboard.isCheck(team))
					   this.setAvailableMove(0,row,column-2); 
			    chessboard.setKingColumn(this.getTeam(),column);
				chessboard.setPiece(this, row, column);
				chessboard.setPiece(null, row, column-1);
			}
		}else if (this.getTeam().equals("Black")){
			if (chessboard.isBlackKingSideCastle() && this.getAvailableMove(row, column+1)==1){
				chessboard.setKingColumn(this.getTeam(),column+1);
				chessboard.setPiece(null, row, column);
				chessboard.setPiece(this, row, column+1);
			    if (chessboard.isCheck(team))
					   this.setAvailableMove(0,row,column+2); 
			    chessboard.setKingColumn(this.getTeam(),column);
				chessboard.setPiece(this, row, column);
				chessboard.setPiece(null, row, column+1);
			}
			if (chessboard.isBlackQueenSideCastle() && this.getAvailableMove(row, column-1)==1){
				chessboard.setKingColumn(this.getTeam(),column-1);
				chessboard.setPiece(null, row, column);
				chessboard.setPiece(this, row, column-1);
			    if (chessboard.isCheck(team))
					   this.setAvailableMove(0,row,column-2); 
			    chessboard.setKingColumn(this.getTeam(),column);
				chessboard.setPiece(this, row, column);
				chessboard.setPiece(null, row, column-1);
			}
			
		}
		
		//////////////////////////////////////////////////////////////////
		
		for (int i=0; i<8; i++){
		    for (int j=0; j<8; j++){
				if (this.getAvailableMove(i, j) == 1){
			    		
		    		chessboard.setKingRow(this.getTeam(),i);
		    		chessboard.setKingColumn(this.getTeam(),j);

					chessboard.setPiece(null, row, column);
					chessboard.setPiece(this, i, j);
				    if (chessboard.isCheck(team)){
					   this.setAvailableMove(0,i,j); 				      
				    }
				    chessboard.setPiece(this, row, column);
					chessboard.setPiece(null, i, j);	   
					
					chessboard.setKingRow(this.getTeam(),x);
			    	chessboard.setKingColumn(this.getTeam(),y);
				}
				else if (this.getAvailableMove(i, j) == 2){
			    		
		    		chessboard.setKingRow(this.getTeam(),i);
		    		chessboard.setKingColumn(this.getTeam(),j);

					tempPiece = chessboard.getPiece(i, j);
					chessboard.setPiece(null, row, column);
					chessboard.setPiece(this, i, j);
				    if (chessboard.isCheck(team)){
					   this.setAvailableMove(0,i,j); 				      
				    }
				    chessboard.setPiece(tempPiece, i, j);
				    chessboard.setPiece(this, row, column);		
				    
				    chessboard.setKingRow(this.getTeam(),x);
			    	chessboard.setKingColumn(this.getTeam(),y);
			    }
		    }
		}
	}
}
