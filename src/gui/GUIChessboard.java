package gui;
import javax.swing.*;

import utils.Calculator;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import logic.Chessboard;
import logic.Pawn;
import logic.Piece;

public class GUIChessboard extends JPanel implements MouseListener {


    private MainScreen aMain;
    private MovesPanel movesPanel;
    private IOFileHandler io;
    private Chessboard chessboard; 
    private Piece tempPiece;  
    private Player playerToMove;  
    private Player opponent;


    private BufferedImage OSC;    
    
    private int curC, curR, prevC, prevR;
    private boolean readyToMove = false;
    private boolean drawPieces = false;
    private boolean mustDrawBoard = true;
    private boolean gameStarted = false; 
                               
    private final Color OPEN_YELLOW = new Color(255,250,150,255); 
    private final Color OPEN_BLUE = new Color(160,200,250,255);  

    private Color whiteColor, blackColor;
    private int squareSize;
    private int[][] availableMoves; 
    

    public GUIChessboard(){
        playerToMove = new Player("White"); 
        opponent = new Player("Black");
        squareSize = Calculator.getSQUARE_SIZE();
        this.setBounds(0,0,640,1000);
        this.setColors(Color.WHITE, Color.GRAY);
        OSC = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        DrawBoard();
        
    }    
    
    public void setChessboard(Chessboard c){
    	chessboard = c;
    }
    
    public void setMain(MainScreen m){
    	aMain = m;
    }
    
    public void setMovesPanel(MovesPanel mp){
    	movesPanel = mp;
    }
    
    public void initDrawBoard(){
    	mustDrawBoard = true;
    }
    
    public void setIO(IOFileHandler io){
    	this.io = io;
    }
    
    public void setPieceArray(Piece[][] pieceArray){
    	chessboard.setPieceArray(pieceArray);
    }
    
    public String getPlayerWhoPlays(){
    	return playerToMove.getPlayerTeam();
    }
    
    public void showPromotionFrame(int row, int column, Pawn pawn){
    	aMain.setEnabled(false);
    	PromotionScreen pf = new PromotionScreen(aMain, this, chessboard, row, column, pawn);
    }

	//public void setDrawPieces(boolean drawPieces) {
	//	this.drawPieces = drawPieces;
	//}


     public void DrawBoard(){
        Graphics osg = OSC.getGraphics();
        

        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                if ( (i+j) % 2 == 0)
                    osg.setColor(whiteColor);
                else
                    osg.setColor(blackColor);

                osg.fillRect(i*squareSize,j*squareSize,squareSize-1,squareSize-1);
            }
        }
        osg.dispose();
    }
     
     public void DrawPieces(){
    	 Graphics osg = OSC.getGraphics();

         Piece[][] pieceArray = chessboard.getPieceArray();
         for(int i=0; i<8; i++)
             for (int j=0; j<8; j++)
                 if (pieceArray[i][j] != null)
                     osg.drawImage(pieceArray[i][j].getImage(),
                             	  j*squareSize+squareSize/10,
                             	  i*squareSize+squareSize/5,
                             	  squareSize-squareSize/5,
                             	  squareSize-squareSize/5,
                             	  null);
         mustDrawBoard = false;

         osg.dispose();
     }
     
     public void checkSituation(){
         boolean check;
         Graphics osg = OSC.getGraphics();
         if (check = chessboard.isCheck(opponent.getPlayerTeam())){
             osg.setColor(Color.RED);
             osg.fillRect(Calculator.rcTOxy(chessboard.getKingColumn(playerToMove.getPlayerTeam())),
                     Calculator.rcTOxy(chessboard.getKingRow(playerToMove.getPlayerTeam())),squareSize-1,
                     squareSize-1);
             
             osg.drawImage(chessboard.getPiece(chessboard.getKingRow(playerToMove.getPlayerTeam()),chessboard.getKingColumn(playerToMove.getPlayerTeam())).getImage(),
            		  chessboard.getKingColumn(playerToMove.getPlayerTeam())*squareSize+squareSize/10,
                 	  chessboard.getKingRow(playerToMove.getPlayerTeam())*squareSize+squareSize/5,
                 	  squareSize-squareSize/5,
                 	  squareSize-squareSize/5,
                 	  null);
             
         }else{
        	 if ( (chessboard.getKingColumn(playerToMove.getPlayerTeam()) + chessboard.getKingRow(playerToMove.getPlayerTeam()))%2 == 0)
        		 osg.setColor(whiteColor);
        	 else
        		 osg.setColor(blackColor);
             osg.fillRect(Calculator.rcTOxy(chessboard.getKingColumn(playerToMove.getPlayerTeam())),
                     Calculator.rcTOxy(chessboard.getKingRow(playerToMove.getPlayerTeam())),squareSize-1,
                     squareSize-1);
             
             osg.drawImage(chessboard.getPiece(chessboard.getKingRow(playerToMove.getPlayerTeam()),chessboard.getKingColumn(playerToMove.getPlayerTeam())).getImage(),
            		  chessboard.getKingColumn(playerToMove.getPlayerTeam())*squareSize+squareSize/10,
                 	  chessboard.getKingRow(playerToMove.getPlayerTeam())*squareSize+squareSize/5,
                 	  squareSize-squareSize/5,
                 	  squareSize-squareSize/5,
                 	  null);
         }
         
         
         if (!chessboard.isTherreAnyAvailableMove(playerToMove.getPlayerTeam())){   //////////////////////////////
             if (check){
             	aMain.sendWinner(opponent.getPlayerTeam());
             }else{
             	aMain.sendWinner("Stalemn");
             }
         }else if (chessboard.getPieceCounter() == 2){
         	aMain.sendWinner("Stalemn");
         }
         osg.dispose();
     }

    public void setColors(Color whiteColor, Color blackColor){
        this.whiteColor = whiteColor;
        this.blackColor = blackColor;
        repaint();
    }

    public void startTheGame(){     
    	if (!gameStarted){
    		this.addMouseListener(this);
    		gameStarted = true;
    	}
        chessboard.initBoard();   
        playerToMove.setPlayerTeam("White");
        opponent.setPlayerTeam("Black");
        drawPieces = true;
        this.mustDrawBoard = true;
        repaint();
    }
    
    public void resumeGame(){
    	if (!gameStarted){
    		this.addMouseListener(this);
    		gameStarted = true;
    	}
    	
    	if (io.getMovesList().size()%2==0){
            playerToMove.setPlayerTeam("White");
            opponent.setPlayerTeam("Black");
    	}else{
            playerToMove.setPlayerTeam("Black");
            opponent.setPlayerTeam("White");
    	}
    }    

    public void setGameStarted(boolean gameStarted) {
		this.gameStarted = gameStarted;
	}

	public void paint(Graphics g){
        if (mustDrawBoard){
            DrawBoard();
            if(drawPieces){
            	DrawPieces();
            }
        }
        g.drawImage(OSC,0,0,null);
    }
    public void selectBox(int r, int c, Color color){
    	
        Graphics g = OSC.getGraphics();

        g.setColor(color);
        g.fillRect(Calculator.rcTOxy(c),Calculator.rcTOxy(r),squareSize-1,squareSize-1);
        if(chessboard.getPiece(r, c)!=null)
        	if (r==curR && c==curC)
        		g.drawImage(chessboard.getPiece(r,c).getImage(),c*squareSize, r*squareSize,squareSize,squareSize,null);
        	else
                g.drawImage(chessboard.getPiece(r,c).getImage(),
                  	  c*squareSize+squareSize/10,
                  	  r*squareSize+squareSize/5,
                  	  squareSize-squareSize/5,
                  	  squareSize-squareSize/5,
                  	  null);
        		
        g.dispose();
        
    }
    
    public void runPieceSelection(){
    	 
    	selectBox(curR, curC, OPEN_YELLOW);
    	
        tempPiece = chessboard.getPiece(curR, curC);
        tempPiece.updateAvailableMoves(chessboard, curR,curC);
        tempPiece.removeCheckSquares(chessboard, opponent.getPlayerTeam(), curR, curC);
        availableMoves = tempPiece.getAvailableMoves();
       for (int r=0; r<8; r++)
        	for (int c=0; c<8; c++)
        		if (availableMoves[r][c] == 1)
        			selectBox(r,c, OPEN_YELLOW);
        		else if (availableMoves[r][c] == 2)
        				selectBox(r,c, OPEN_BLUE);

    }
    
    public void unSelectBox(int r, int c, Color color){
    	//WARNING ANPASAN AND ROKE NOT APPLICABLE YET
        Graphics g = OSC.getGraphics();

        g.setColor(color);
        g.fillRect(Calculator.rcTOxy(c),Calculator.rcTOxy(r),squareSize-1,squareSize-1);
        if(chessboard.getPiece(r, c)!=null)
                g.drawImage(chessboard.getPiece(r,c).getImage(),
                  	  c*squareSize+squareSize/10,
                  	  r*squareSize+squareSize/5,
                  	  squareSize-squareSize/5,
                  	  squareSize-squareSize/5,
                  	  null);
        		
        g.dispose();
        
    }
    
    public void runPieceUnSelection(){
    	if((curR + curC) %2 == 0)
    		unSelectBox(curR, curC,  whiteColor);
    	else
    		unSelectBox(curR, curC,  blackColor);
    	
    	if((prevR + prevC) %2 == 0)
    		unSelectBox(prevR, prevC,  whiteColor);
    	else
    		unSelectBox(prevR, prevC,  blackColor);
    
    	for (int r=0; r<8; r++)
    		for (int c=0; c<8; c++)
    			if (availableMoves[r][c] != 0){
    		    	if((r + c) %2 == 0)
    		    		unSelectBox(r, c, whiteColor);
    		    	else
    		    		unSelectBox(r, c, blackColor);
    			}
    	
    	unSelectBox(0, 0, whiteColor);
    	unSelectBox(7,7,whiteColor);
    	unSelectBox(0, 7, blackColor);
    	unSelectBox(7,0,blackColor);
   }
    

    public void switchPlayerToMove(){
    	Player temp;
    	temp = playerToMove;
    	playerToMove=opponent;
    	opponent=temp;
    	/*chessboard.setPieceArray(Calculator.reverse(chessboard.getPieceArray()));
    	 * KingColumn and row and roke 
    	 */
    		
    }

    public void mousePressed(MouseEvent e) {

    	
        curC = Calculator.xyTOrc(e.getX());
        curR = Calculator.xyTOrc(e.getY());
        
        if (Calculator.isOnBoard(curC,curR)){
        	if (!readyToMove){
        		prevC = curC;
        		prevR = curR;        		
        	}

        	if (chessboard.getPiece(prevR,prevC) !=  null &&
        	    chessboard.getPiece(prevR,prevC).getTeam().equals(playerToMove.getPlayerTeam())){
        		if (!readyToMove){
        			runPieceSelection();
        			readyToMove = true;
        			
        		}else{
        			if (tempPiece.canMove(curR,curC)){
        				tempPiece.move(chessboard ,prevR, prevC, curR, curC);
        				movesPanel.addMove(Calculator.getMove(tempPiece, prevR, prevC, curR,curC));
        				io.addMove(Calculator.getMove(tempPiece, prevR, prevC, curR, curC));
        				switchPlayerToMove();
        				checkSituation();
        			}
        			runPieceUnSelection();
        			readyToMove = false;
        		}
        		repaint();
        	}
        }
    }
    
    public void mouseClicked(MouseEvent mouseEvent) {}
    public void mouseReleased(MouseEvent mouseEvent) {}
    public void mouseEntered(MouseEvent mouseEvent) {}
    public void mouseExited(MouseEvent mouseEvent) {}
}
