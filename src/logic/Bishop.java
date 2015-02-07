package logic;

public class Bishop extends Piece{
	
	public Bishop(String team){
		this.setName("B");
		this.setTeam(team);
		this.loadImage(2);
	}

	public void updateAvailableMoves(Chessboard chessboard, int row, int column) {
		int[][] availableMoves = new int[8][8];
		boolean foundEnemy = false;
		boolean foundAlly = false;
		int i,j;
		
		for(i=0; i<8; i++){	
			for(j=0; j<8; j++){
					availableMoves[i][j] = 0;
			}
		}
		
		i = row;
		j = column;
		
		while( i >0 && j>0 && !foundEnemy && !foundAlly){ 
			i--;
			j--;
			if( chessboard.getPiece(i, j) == null){
				availableMoves[i][j] = 1;
			}else if( chessboard.getPiece(i, j).getTeam().equals(this.getTeam())){
				foundAlly = true;
			}else{
				foundEnemy = true;
				availableMoves[i][j] = 2;
			}
		}
		
		i = row;
		j = column;
		foundEnemy = false;
		foundAlly = false;
		
		while( i < 7 && j < 7 && !foundEnemy && !foundAlly){  
			i++;
			j++;
			if( chessboard.getPiece(i, j) == null){
				availableMoves[i][j] = 1;
			}else if(chessboard.getPiece(i, j).getTeam().equals(this.getTeam())){
				foundAlly = true;
			}else{
				foundEnemy = true;
				availableMoves[i][j] = 2;
			}
				
		}
		
		i = row;
		j = column;
		foundEnemy = false;
		foundAlly = false;
		
		while( i >0 && j < 7 && !foundEnemy && !foundAlly){ 
			i--;
			j++;
			if( chessboard.getPiece(i, j) == null){
				availableMoves[i][j] = 1;
			}else if( chessboard.getPiece(i, j).getTeam().equals(this.getTeam())){
				foundAlly = true;
			}else{
				foundEnemy = true;
				availableMoves[i][j] = 2;
			}
		}
		
		i = row;
		j = column;
		foundEnemy = false;
		foundAlly = false;
		
		while( i < 7 && j > 0 && !foundEnemy && !foundAlly){    
			i++;
			j--;
			if( chessboard.getPiece(i, j) == null){
				availableMoves[i][j] = 1;
			}else if( chessboard.getPiece(i, j).getTeam().equals(this.getTeam())){
				foundAlly = true;
			}else{
				foundEnemy = true;
				availableMoves[i][j] = 2;
			}
		}
		
		
		
		this.setAvailableMoves(availableMoves);
	}
}
