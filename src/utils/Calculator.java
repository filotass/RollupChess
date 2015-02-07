package utils;

import logic.Piece;

public class Calculator {

	private static final int SQUARE_SIZE=80;
		
	//Pixel Coordinates convert to ChessBoard coordinates
	public static int xyTOrc(int xy){
		return xy/SQUARE_SIZE;
	}
	
	//ChessBoard Coordinates convert to Pixel (Normalized) Coordinates 
	public static int rcTOxy(int rc){
		return rc*SQUARE_SIZE;
	}
		
	public static boolean isOnBoard(int row, int column){
		if (0<=row && row<8 && 0<=column && column<8)
			return true;
		return false;
	}
	
	public static String getMove(Piece piece, int prevRow, int prevColumn, int curRow, int curColumn){
		return piece.getName() + encode(prevRow, prevColumn)+"-"+encode(curRow, curColumn);
	}

	//Returns as a string the real ChessBoard coordinates
	public static String encode(int row, int column){
		String temp = null;
		if (column == 0) 
			temp = "a";
		else if (column == 1)
			temp = "b";
		else if (column == 2)
			temp = "c";
		else if (column == 3)
			temp = "d";
		else if (column == 4)
			temp = "e";
		else if (column == 5)
			temp = "f";
		else if (column == 6)
			temp = "g";
		else if (column == 7)
			temp = "h";
		
		if (row == 0) 
			temp += "8";
		else if (row == 1)
			temp += "7";
		else if (row == 2)
			temp += "6";
		else if (row == 3)
			temp += "5";
		else if (row == 4)
			temp += "4";
		else if (row == 5)
			temp += "3";
		else if (row == 6)
			temp += "2";
		else if (row == 7)
			temp += "1";
		
		return temp;
		
		}
	
	public static int columnDecode(String move, int i){
		
		String temp;
		int x=-1;
		temp = move.substring(i, i+1);

		if (temp.equals("a")) 
			x= 0;
		else if (temp.equals("b"))
			x= 1;
		else if (temp.equals("c"))
			x= 2;
		else if (temp.equals("d"))
			x= 3;
		else if (temp.equals("e"))
			x= 4;
		else if (temp.equals("f"))
			x= 5;
		else if (temp.equals("g"))
			x= 6;
		else if (temp.equals("h"))
			x= 7;
		return x;
	}
	
	public static int rowDecode(String move, int i){
		
		String temp;
		temp = move.substring(i, i+1);
		
		
		int x=-1;
		if (temp.equals("8")) 
			x= 0;
		else if (temp.equals("7"))
			x= 1;
		else if (temp.equals("6"))
			x= 2;
		else if (temp.equals("5"))
			x= 3;
		else if (temp.equals("4"))
			x = 4;
		else if (temp.equals("3"))
			x= 5;
		else if (temp.equals("2"))
			x= 6;
		else if (temp.equals("1"))
			x= 7;
		
		return x;
	}
		
	//Reverse the Pieces
	public static Piece[][] reverse(Piece[][] piece){
		Piece[][] temp = new Piece[8][8];
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++){
				temp[7-i][7-j]=piece[i][j];
			}
		return temp;
	}

	public static int getSQUARE_SIZE() {
		return SQUARE_SIZE;
	}
	
	
	
}
