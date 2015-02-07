package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class EndScreen extends JFrame implements MouseListener, ActionListener{
	
	private BufferedImage image;
	private FileInputStream input;
	private boolean isready = false;
	private int i=0;
	private String fileName;
	private MainScreen main;
	
	
	public EndScreen(String winTeam, MainScreen aMain){
		this.setSize(480, 350);
		this.setUndecorated(true);
		main = aMain;
		main.setEnabled(false);
	
		if (winTeam.equals("Black"))
			fileName = "BlackWins";
		else if (winTeam.equals("White"))
			fileName = "WhiteWins";
		else
			fileName = "Stalemn";
			
		
		try{
			input = new FileInputStream(new File("Images/Results/" + fileName + ".png"));
			image = ImageIO.read(input);
		
		}catch(IOException e){
			JOptionPane.showMessageDialog(null, "File does not contain a recognized image format.");
		}
	

		repaint();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((dim.width-getSize().width)/2, (dim.height-getSize().height)/2);
		this.addMouseListener(this);
		this.setVisible(true);
	}

	public void paint(Graphics g){
	   	 BufferedImage OSC = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
		 Graphics osg = OSC.getGraphics();
		 osg.drawImage(image,0,0, 480, 350 , null);
		 osg.dispose();
		 g.drawImage(OSC,0,0,null);
		 g.setColor(Color.WHITE);
		 g.setFont(new Font("SansSerif", Font.ITALIC + Font.BOLD, 17));
		 g.drawString("Click to continue...", 220 , 340);
		 g.dispose();
	}
	
	public boolean isReady(){
		return isready;
	}

	
	public void mouseClicked(MouseEvent e) {
			isready = true;
			main.setEnabled(true);
			this.dispose();
	}

	public void actionPerformed(ActionEvent e) {
			if (i == 4)
				isready = true;
			else{
				i++;
			}
	}
	
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
}
