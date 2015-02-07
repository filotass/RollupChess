package logic;

import gui.GUIChessboard;

public class Chessboard {
	
	
	public static String WHITE = "White";
	public static String BLACK = "Black";
	
	private Piece[][] pieceArray;	
	
    private boolean anpasan;
    private int anpasan_column;
    
    private boolean whiteKingSideCastle;
    private boolean whiteQueenSideCastle;
    private boolean blackKingSideCastle;
    private boolean blackQueenSideCastle;
    
    private int whiteKingRow;
    private int whiteKingColumn;
    private int blackKingRow;
    private int blackKingColumn;   
    private GUIChessboard guiChessboard;
    
    public Chessboard(){
    	
    }
    
    public void setGUIChessboard(GUIChessboard gc){
    	guiChessboard = gc;
    }
    
    
    public Chessboard(GUIChessboard guiChessboard){
    	this.setGUIChessboard(guiChessboard);
    }
    
    
    
    public void initBoard(){
    	pieceArray = new Piece[8][8];

        for(int i=2;i<6;i++)
            for (int j=2; j<6; j++)
                pieceArray[i][j] = null;

        for (int i=0; i<8; i++){
            setPiece(new Pawn(guiChessboard, WHITE),6,i);
            setPiece(new Pawn(guiChessboard, BLACK),1,i);
        }
        
        setPiece(new Rook(WHITE),7,0);
        setPiece(new Rook(WHITE),7,7);
        setPiece(new Rook(BLACK),0,0);
        setPiece(new Rook(BLACK),0,7);
        
        setPiece(new Knight(WHITE),7,1);
        setPiece(new Knight(WHITE),7,6);
        setPiece(new Knight(BLACK),0,1);
        setPiece(new Knight(BLACK),0,6);
        
        setPiece(new Bishop(WHITE),7,2);
        setPiece(new Bishop(WHITE),7,5);
        setPiece(new Bishop(BLACK),0,2);
        setPiece(new Bishop(BLACK),0,5);
        
        setPiece(new Queen(WHITE),7,3);
        setPiece(new Queen(BLACK),0,3);
        
        setPiece(new King(WHITE),7,4);
        setPiece(new King(BLACK),0,4);
         
        anpasan = false;    
        whiteKingSideCastle=true;
        whiteQueenSideCastle=true;
        blackKingSideCastle=true;
        blackQueenSideCastle=true;

        whiteKingRow=7;
        whiteKingColumn=4;
        blackKingRow=0;
        blackKingColumn=4;    
    }
    
    public int getPieceCounter(){
    	int counter = 0;
    	for(int i=0; i<8; i++)
    		for(int j=0; j<8; j++)
    			if (pieceArray[i][j] != null)
    				counter ++;
    	return counter;
    }
    
    
	public void setPiece(Piece piece, int row, int column){
		pieceArray[row][column] = piece;
	}

	public Piece getPiece( int row, int column){
		return pieceArray[row][column];
	}
	
	public void setPieceArray(Piece[][] temp){
		pieceArray = temp;
	}
	
	public Piece[][] getPieceArray(){
		return pieceArray;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////

	public void setAnpasan(boolean value){
		anpasan=value;
	}
	
	public boolean isAnpasan() {
		return anpasan;
	}
	
	public int getAnpasan_column() {
		return anpasan_column;
	}

	public void setAnpasan_column(int anpasan_column) {
		this.anpasan_column = anpasan_column;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean isWhiteKingSideCastle() {
		return whiteKingSideCastle;
	}

	public void setWhiteKingSideCastle(boolean whiteKingSideCastle) {
		this.whiteKingSideCastle = whiteKingSideCastle;
	}

	public boolean isWhiteQueenSideCastle() {
		return whiteQueenSideCastle;
	}

	public void setWhiteQueenSideCastle(boolean whiteQueenSideCastle) {
		this.whiteQueenSideCastle = whiteQueenSideCastle;
	}

	public boolean isBlackKingSideCastle() {
		return blackKingSideCastle;
	}

	public void setBlackKingSideCastle(boolean blackKingSideCastle) {
		this.blackKingSideCastle = blackKingSideCastle;
	}

	public boolean isBlackQueenSideCastle() {
		return blackQueenSideCastle;
	}

	public void setBlackQueenSideCastle(boolean blackQueenSideCastle) {
		this.blackQueenSideCastle = blackQueenSideCastle;
	}
	
/////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean isCheck(String team){
		int tempKingRow,tempKingColumn;
		int i=0,j=0;
		
		if (team.equals(WHITE)){
			tempKingRow = blackKingRow;
			tempKingColumn = blackKingColumn;
		}else{
			tempKingRow = whiteKingRow;
			tempKingColumn = whiteKingColumn;
		}
		
		while (i<8){
			j=0;
			while ( j<8){
				if (pieceArray[i][j]!=null && pieceArray[i][j].getTeam().equals(team)){
					pieceArray[i][j].updateAvailableMoves(this, i, j);
					if (pieceArray[i][j].getAvailableMove(tempKingRow, tempKingColumn)==2){
						//check=true;
						return true;
					}
					
				}
				j++;
			}
			i++;
		}
		return false;
	}
	
	public boolean isTherreAnyAvailableMove(String team){
		int i=0,j=0;
		boolean found = false;
		String team2;
	
		if (team.equals(WHITE))
			team2=BLACK;		
		else
			team2 = WHITE;
		
		
		for(i=0;i<8;i++){
			for(j=0;j<8;j++){
				if (pieceArray[i][j] != null && pieceArray[i][j].getTeam().equals(team)){
					pieceArray[i][j].updateAvailableMoves(this, i, j);
					pieceArray[i][j].removeCheckSquares(this, team2, i, j);
				}
			}
		}
		
		i = 0;
		while (i<8 && !found){
			j = 0;
			while (j<8 && !found){
				if (pieceArray[i][j] != null && pieceArray[i][j].getTeam().equals(team)){
					int k=0, l=0;
		
					while (k<8 && !found){
						l = 0;
						while (l<8 && !found){
							if (pieceArray[i][j].getAvailableMove(k, l) != 0){
								found = true;
							}
							l++;
						}
						k++;
					}
				}
				j++;
			}
			i++;
	}
		return found;
		
	
	}
	
	public int getKingRow(String team) {
		if (team.equals(WHITE))
				return whiteKingRow;
		return blackKingRow;
	}

	public void setKingRow(String team, int row) {
		if (team.equals(WHITE))
			whiteKingRow=row;
		else
			blackKingRow=row;
	}

	public int getKingColumn(String team) {
		if (team.equals(WHITE))
			return whiteKingColumn;
		return blackKingColumn;
	}

	public void setKingColumn(String team, int column) {

		if (team.equals(WHITE))
			whiteKingColumn=column;
		else
			blackKingColumn=column;
	
	}		
		

}
