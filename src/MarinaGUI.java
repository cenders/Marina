import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MarinaGUI extends JFrame{
	
	String[] columnNames = {"Boat","Test"};
	
	private JLabel searchTerm = new JLabel();
	
	private JButton clearButton = new JButton("Clear");
	private JButton createButton = new JButton("Create");
	private JButton deleteButton = new JButton("deleteButton");
	private JButton findButton = new JButton("Find");
	private JButton editButton = new JButton("Edit");
	
	private JPanel tablePanel = new JPanel();
	private JPanel actionsPanel = new JPanel();
	
	private JTable infoTable = new JTable(null, columnNames);
	
	
	public MarinaGUI(){
		super("Marina");
		setLayout(new GridLayout(1, 2, 1, 1));
		
		setSize(512,512);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		MarinaGUI gui = new MarinaGUI();

	}

}
