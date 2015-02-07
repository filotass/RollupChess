package gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.*;

import utils.Calculator;

import logic.Chessboard;

public class MainScreen extends JFrame implements ActionListener{
	private JPanel toolsPanel;
	private PlayersPanel playersPanel;
	private MovesPanel movesPanel;
	private JMenuBar menuBar;
	private JMenu gameMenu;
	private JMenu optionsMenu;
	private JMenu helpMenu;
	private JMenuItem newGameItem;
	private JMenuItem exitGameItem;
	private JMenuItem soundsItem;
	private JMenuItem saveItem;
	private JMenuItem loadItem;
	private JMenuItem colorItem;
	private  boolean end = false;
	private String result;
	private long time;
	private Timer timer;
	private GUIChessboard mainPanel;
	private IOFileHandler io;
	private ColorFrame colorFrame;
	private Chessboard chessboard;
	private int bonus;
	private boolean playerChanged = true, firstTime = true;
	
	private String fileName;

    public MainScreen(){
    	movesPanel = new MovesPanel(this);
    	mainPanel = new GUIChessboard();    	
    	playersPanel = new PlayersPanel(this);
    	colorFrame = new ColorFrame(this);
    	chessboard = new Chessboard();
    	io = new IOFileHandler(playersPanel, movesPanel, mainPanel, chessboard);
    	
    	
    	movesPanel.setGUIChessboard(mainPanel);
    	    	
    	
    	mainPanel.setChessboard(chessboard);
    	mainPanel.setMovesPanel(movesPanel);
    	mainPanel.setMain(this);
    	mainPanel.setIO(io);
    	
    	
    	chessboard.setGUIChessboard(mainPanel);
    	

        
        timer = new Timer(1000,this);
        
        toolsPanel = new JPanel();
        toolsPanel.setLayout(null);         
        
        movesPanel.setBounds(20,10,180,330);
        playersPanel.setBounds(20,330,250,300);
        
        toolsPanel.add(movesPanel);
        toolsPanel.add(playersPanel);
        toolsPanel.setBounds(640,0,250, 1000);
        
        menuBar = new JMenuBar();
        //Game menu
        gameMenu = new JMenu("Game");
        newGameItem = new JMenuItem("New Game");
        newGameItem.addActionListener(this);
        exitGameItem = new JMenuItem("Exit Game");
        exitGameItem.addActionListener(this);
        saveItem = new JMenuItem("Save Game");
        saveItem.addActionListener(this);
        loadItem = new JMenuItem("Load Game");
        loadItem.addActionListener(this);
        gameMenu.add(newGameItem);
        gameMenu.add(saveItem);
        gameMenu.add(loadItem);
        gameMenu.add(exitGameItem);
       
        //Options menu
        optionsMenu = new JMenu("Options");
        soundsItem = new JMenuItem("Sounds");
        colorItem = new JMenuItem("Colors");
        colorItem.addActionListener(this);
        optionsMenu.add(colorItem);
        optionsMenu.add(soundsItem);
       
        
        menuBar.add(gameMenu);
        menuBar.add(optionsMenu);
       
        this.setJMenuBar(menuBar);
        
        this.setLayout(null);
        this.add(mainPanel);       
        this.add(toolsPanel);
        this.setTitle("Roll-up Chess");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(Calculator.getSQUARE_SIZE()*8+200,Calculator.getSQUARE_SIZE()*8+55);
        centerize();
        this.setResizable(false);
        this.setVisible(true);
        
        while(!end){}
        	JOptionPane.showMessageDialog(null, result);
    }

	public void setMainPanelColors(Color whiteColor, Color blackColor){
        mainPanel.setColors(whiteColor, blackColor);
    }
	
    public void sendWinner(String winner){              ///////////////////////////////////
    	EndScreen scr = new EndScreen(winner,this);
    }
    
    public void printResult(String result){
    	this.result = result;
    	end = true;
    }

    public void startTheGame(){    	
        mainPanel.startTheGame(); 
    }
    
    public void startTime(){
    	if (time != 0){
    		timer.start();
    		playersPanel.setInitialTime(time, bonus);
    	}
    }
    
    public void startClock(){
    		timer.start();
    }
    
    public void stopClock(){
    	timer.stop();
    }

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == newGameItem){
			NewGameScreen newGame = new NewGameScreen(playersPanel,movesPanel,this);
		}else if (e.getSource() == saveItem){
			fileName = JOptionPane.showInputDialog(this,"Please, give the file name:");
			io.saveGame(fileName);
		}else if( e.getSource() == loadItem){
			SavedGamesList savedGamesList = new SavedGamesList(this, io);
		}else if( e.getSource() == exitGameItem){
            System.exit(0);
		}else if (e.getSource() == colorItem){
            colorFrame.setVisible(true);
        }else if ( timer.isRunning()){
			if(mainPanel.getPlayerWhoPlays().equals("White")){
				if (playerChanged && !firstTime){
					playersPanel.addBonusWhite();
					playerChanged = false;
				}
				playersPanel.decreaseWhiteTime();
			}else{
				if(!playerChanged && !firstTime){
					playersPanel.addBonusBlack();
					playerChanged = true;
				}
				playersPanel.decreaseBlackTime();
				firstTime = false;
			}
		}
	}        		
	
	public void setTime(int time, int bonus){
		this.time = time * 60;
		this.bonus = bonus;
	}

    public void centerize(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width-getSize().width)/2, (dim.height-getSize().height)/2);
    }

}
