package logic;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;


import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public abstract class Piece{
	

	private String team;
	private String name;
	private BufferedImage image;
	private int[][] availableMoves;

	public String getTeam() {return team;}
	public void setTeam(String team){this.team = team;} 
	
	public String getName() {return name;}
	public void setName(String name){this.name =name;}
	
	public int[][] getAvailableMoves() {return availableMoves;}
	public void setAvailableMoves(int[][] availableMoves){
		this.availableMoves = availableMoves;
	}
	
	public int getAvailableMove(int row, int column){
		return availableMoves[row][column];
	}
	
	public void setAvailableMove(int value, int row, int column){
		availableMoves[row][column]=value;
	}
	
	public BufferedImage getImage() {return image;}
	public void loadImage(int i){
        File tempFile = new File("Images/" + team + "/Image" + i + ".png");
        FileInputStream tempStream;
		try {
            tempStream = new FileInputStream(tempFile);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                      "Sorry, but an error occurred while trying to open the file:\n" + e);
            return;
        }
        try {
            image = ImageIO.read(tempStream);
            if (image == null)
                throw new Exception("File does not contain a recognized image format.");
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,
            "Sorry, but an error occurred while trying to read the image:\n" + e);
        }
	}
	
	public boolean canMove(int row, int column){
		if (this.availableMoves[row][column]==0){
			return false;}
		return true;
	}
	
	public void move(Chessboard chessboard,int prev_row, int prev_column, int row, int column){
		chessboard.setPiece(null, prev_row, prev_column);
		chessboard.setPiece(this, row, column);
		chessboard.setAnpasan(false);
	}
	
	
	public abstract void updateAvailableMoves(Chessboard chessboard, int row, int column);

	
	public void removeCheckSquares (Chessboard chessboard,String team, int row, int column){
	Piece tempPiece;
	
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
				tempPiece = chessboard.getPiece(i, j);
				chessboard.setPiece(null, row, column);
				chessboard.setPiece(this, i, j);
			    if (chessboard.isCheck(team)){
				   this.setAvailableMove(0,i,j); 				      
			    }
			    chessboard.setPiece(tempPiece, i, j);
			    chessboard.setPiece(this, row, column);			    
		    }
	    }
	}
}

}
