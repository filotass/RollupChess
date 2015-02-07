package logic;


public class Rook extends Piece{
		
	public Rook(String team){
		this.setName("R");
		this.setTeam(team);
		this.loadImage(0);
	}
	
	public void updateAvailableMoves(Chessboard chessboard, int row, int column) {
			int[][] availableMoves = new int[8][8];
			boolean foundEnemy = false;
			boolean foundAlly = false;
			int i,j;
			
			for( i=0; i<8; i++){			
				for( j=0; j<8; j++){
						availableMoves[i][j] = 0;
				}
			}
			
			j = column;
			
			while(j<7 && !foundEnemy && !foundAlly){		
				j++;
				if( chessboard.getPiece(row, j) == null ){
					availableMoves[row][j] = 1;
				}else if( !chessboard.getPiece(row, j).getTeam().equals(this.getTeam())){
					availableMoves[row][j] = 2;
					foundEnemy = true;
				}else{
					foundAlly = true;
				}
			}
			
			j = column;
			foundEnemy = false;
			foundAlly = false;
			
			while(j>0 && !foundEnemy && !foundAlly){		
				j--;
				if( chessboard.getPiece(row, j) == null ){
					availableMoves[row][j] = 1;
				}else if( !chessboard.getPiece(row, j).getTeam().equals(this.getTeam())){
					availableMoves[row][j] = 2;
					foundEnemy = true;
				}else{
					foundAlly = true;
				}
			}
			
			 i = row;
			foundEnemy = false;
			foundAlly = false;
			
			while(i>0 && !foundEnemy && !foundAlly){		
				i--;
				if( chessboard.getPiece(i, column) == null ){
					availableMoves[i][column] = 1;
				}else if( !chessboard.getPiece(i, column).getTeam().equals(this.getTeam())){
					availableMoves[i][column] = 2;
					foundEnemy = true;
				}else{
					foundAlly = true;
				}
			}
			
			i = row;
			foundEnemy = false;
			foundAlly  = false;
			
			while(i<7 && !foundEnemy && !foundAlly){	
				i++;
				if( chessboard.getPiece(i, column) == null ){
					availableMoves[i][column] = 1;
				}else if( !chessboard.getPiece(i, column).getTeam().equals(this.getTeam())){
					availableMoves[i][column] = 2;
					foundEnemy = true;
				}else{
					foundAlly = true;
				}
			}
			
			this.setAvailableMoves(availableMoves);    
			
	}
	
	public void move(Chessboard chessboard,int prev_row, int prev_column, int row, int column){
		super.move(chessboard, prev_row, prev_column, row, column);
		
		if (this.getTeam().equals("White")){
			if (prev_row==7 && prev_column==0)
				chessboard.setWhiteQueenSideCastle(false);
			else if (prev_row==7 && prev_column==7)
				chessboard.setWhiteKingSideCastle(false);		
		}else{
			if (prev_row==0 && prev_column==0)
				chessboard.setBlackQueenSideCastle(false);
			else if (prev_row==0 && prev_column==7)
				chessboard.setBlackKingSideCastle(false);
			
		}
	}
}
