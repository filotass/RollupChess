package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ColorFrame extends JFrame implements ActionListener {

    private JLabel whiteLbl;
    private JLabel blackLbl;

    private JComboBox whiteBox;
    private JComboBox blackBox;

    private JButton ok;

    private MainScreen main;

    private String[] index = {"White","Black","Red","Blue","Yellow","Green","Magenda","Orange","Gray","Cyan"};

    public ColorFrame(MainScreen main){
        this.main = main;

        whiteLbl = new JLabel("White's color:");
        blackLbl = new JLabel("Black's color:");

        whiteLbl.setBounds(10,10,150,20);
        blackLbl.setBounds(10,50,150,20);

        whiteBox = new JComboBox(index);
        blackBox = new JComboBox(index);

        whiteBox.setBounds(130,10,90,20);
        whiteBox.setSelectedIndex(0);
        blackBox.setBounds(130,50,90,20);
        blackBox.setSelectedIndex(8);

        ok = new JButton("OK");
        ok.setBounds(75,90,100,20);
        ok.addActionListener(this);

        this.setLayout(null);
        this.add(whiteLbl);
        this.add(whiteBox);
        this.add(blackLbl);
        this.add(blackBox);
        this.add(ok);
        this.setResizable(false);
        this.setSize(260,150);
        this.setLocationRelativeTo(main);
        this.setVisible(false);

    }

    public Color getWhiteColor(){
        String temp = whiteBox.getSelectedItem().toString();
        
        if (temp.equals("White"))
            return Color.WHITE;
        if (temp.equals("Black"))
            return Color.BLACK;
        if (temp.equals("Red"))
            return Color.RED;
        if (temp.equals("Blue"))
            return Color.BLUE;
        if (temp.equals("Yellow"))
            return Color.YELLOW;
        if (temp.equals("Green"))
            return Color.GREEN;
        if (temp.equals("Magenda"))
            return Color.MAGENTA;
        if (temp.equals("Orange"))
            return Color.ORANGE;
        if (temp.equals("Gray"))
            return Color.GRAY;
        if (temp.equals("Cyan"))
            return Color.CYAN;

        return null;
    }

    public Color getBlackColor(){
        String temp = blackBox.getSelectedItem().toString();
        
        if (temp.equals("White"))
            return Color.WHITE;
        if (temp.equals("Black"))
            return Color.BLACK;
        if (temp.equals("Red"))
            return Color.RED;
        if (temp.equals("Blue"))
            return Color.BLUE;
        if (temp.equals("Yellow"))
            return Color.YELLOW;
        if (temp.equals("Green"))
            return Color.GREEN;
        if (temp.equals("Magenda"))
            return Color.MAGENTA;
        if (temp.equals("Orange"))
            return Color.ORANGE;
        if (temp.equals("Gray"))
            return Color.GRAY;
        if (temp.equals("Cyan"))
            return Color.CYAN;

        return null;
    }

    public void actionPerformed(ActionEvent e) {
    	if (!whiteBox.getSelectedItem().toString().equals(blackBox.getSelectedItem().toString())){
    		main.setMainPanelColors(getWhiteColor(), getBlackColor());
    		this.setVisible(false);
    	}else
    		JOptionPane.showMessageDialog(this, "Please chose different colors");
        
    }
}
