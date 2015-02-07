package logic;

public class Queen extends Piece{
	
	public Queen(String team){
		this.setName("Q");
		this.setTeam(team);
		this.loadImage(4);
	}

	@Override
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
		
		
		j = column;
		foundEnemy = false;
		foundAlly = false;
		
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
		
		while(j>0 && !foundEnemy && !foundAlly){		//������� ��� �������� ��� ������ ��� ���������� ��������
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
		
		while(i>0 && !foundEnemy && !foundAlly){		//������� ���� ��� ��� ����� ��� ���������� ��������
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
		
		foundEnemy = false;
		foundAlly  = false;
		i = row;
		
		while(i<7 && !foundEnemy && !foundAlly){		//������� ���� ��� ��� ����� ��� ���������� ��������.
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
/*		Piece[][] tempPieceArray = new Piece[8][8];
		tempPieceArray = chessboard.getPieceArray();

		for (i=0; i<8; i++){
		    for (j=0; j<8; j++){
				if (availableMoves[i][j] != 0){
				   this.move(chessboard, row, column, i, j);
				   if (chessboard.isCheck(this.getTeam())){
				      availableMoves[i][j] = 0;  
				   }
				   chessboard.setPieceArray(tempPieceArray);
				}
		    }
		}*/
		
		this.setAvailableMoves(availableMoves);
		
	}
	

}
