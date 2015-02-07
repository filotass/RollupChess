package gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;

public class SavedGamesList extends JFrame implements ListSelectionListener , ActionListener{
	private JList savedGames;
	private JScrollPane scrollPane;
	private JButton loadBtn, removeBtn;
	private String fileToLoad;
	private DefaultListModel listModel;
	private MainScreen main;
	private IOFileHandler io;
	
	public SavedGamesList(MainScreen main, IOFileHandler io){
		this.main = main;
		this.io = io;
		this.setLocationRelativeTo(main);
		this.setLayout(null);
		this.setSize(255, 300);
		this.setTitle("Saved Games");
		
		listModel = new DefaultListModel();
		
		loadSavedGames();
		
		savedGames = new JList(listModel);
		savedGames.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		savedGames.addListSelectionListener(this);
		scrollPane = new JScrollPane(savedGames);
		loadBtn = new JButton("load");
		loadBtn.addActionListener(this);
		removeBtn = new JButton("remove");
		removeBtn.addActionListener(this);
	
		scrollPane.setBounds(10,10 , 225, 210);
		loadBtn.setBounds(18,230 , 100 ,22);
		removeBtn.setBounds(128, 230, 100, 22);
		
		add(scrollPane);
		add(loadBtn);
		add(removeBtn);
		
		this.setVisible(true);
		
	}
	
	private void loadSavedGames(){
		BufferedReader reader;
		String temp;
		
		try{
			reader = new BufferedReader(new FileReader("Save/game.sav"));

			int i = 0;
			
			while( (temp = reader.readLine()) != null){
				listModel.add(i, temp);
				System.out.println(temp);
				i++;
			}
			reader.close();

		}catch(IOException e){
			JOptionPane.showMessageDialog(null,"There are no saved games!");
		}
	
	}


	public void valueChanged(ListSelectionEvent e) {
		int i = savedGames.getSelectedIndex();
		
		fileToLoad = (String) listModel.getElementAt(i);
	}

	
	public void actionPerformed(ActionEvent e) {
		if ( e.getSource() == loadBtn){
			io.loadGame(fileToLoad);
			this.dispose();
		}else{
		
		}
	}
}
