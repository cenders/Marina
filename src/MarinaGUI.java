import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import java.awt.*;
import java.awt.event.*;

public class MarinaGUI extends JFrame{
	
	String[] customerColumnNames = {"Customer ID", "First Name", "Last Name", "Payment Info", "Phone Number", "Street Address", "City", "State", "ZIP Code"};
	String[] boatColumnNames = {"Vin","Customer ID", "Make", "Model", "Color","Is Powered Boat"};
	String[] slipColumnNames = {"Slip ID", "Is Powered Slip?", "Is Occupied?"};
	String[] leaseColumnNames = {"Lease ID","Customer ID", "Vin","Slip ID","Lease Start Date", "Lease End Date"};
	
	private JButton createButton = new JButton("Create");
	private JButton deleteButton = new JButton("Delete");
	private JButton findButton = new JButton("Find");
	private JButton editButton = new JButton("Edit");
	
	private JPanel contentPanel = new JPanel();
	private JPanel tablePanel = new JPanel();
	private JPanel buttonPanel = new JPanel(new GridLayout(4,1,1,5));
	private JPanel searchPanel = new JPanel();
	private JPanel customerPanel = new JPanel();
	private JPanel boatPanel = new JPanel();
	private JPanel slipPanel = new JPanel();
	private JPanel leasePanel = new JPanel();
	
	private JTabbedPane tabbedPane = new JTabbedPane();
	
	Object[][] customerData = {
			{"1","Kathy", "Smith","XXXXXXXXXXXX1028","717-302-0001","3002 Front St.","Harrisburg","PA","17011"},
			{"2", "John", "Doe","XXXXXXXXXXXX3421", "717-998-0967", "1012 1st St.","Middletown","PA","17057"},
		    };
	private JTable customerTable = new JTable(customerData, customerColumnNames);
	
	Object[][] boatData = {
				    {"MAI5NS6TF708", "1","Ranger","Rt 188","White",new Boolean(true)},
				    {"ABC67689B606", "2","Wellcraft", "Martinique 3000","Black/Red", new Boolean(false)}
	};		
	private JTable boatTable = new JTable(boatData, boatColumnNames);
	
	Object[][] slipData = {
		    {"1A", new Boolean(true), new Boolean(true)},
		    {"1B", new Boolean(false), new Boolean(false)},
		    {"2A", new Boolean(false), new Boolean(false)},
		    {"2B", new Boolean(false), new Boolean(false)},
		    {"3A", new Boolean(true), new Boolean(false)},
		    {"3B", new Boolean(false), new Boolean(true)}
	};	
	private JTable slipTable = new JTable(slipData, slipColumnNames);
	
	Object[][] leaseData = {
		    {"001", "1", "MAI5NS6TF708", "1A","11/5/2017","11/6/2017"},
		    {"002", "2", "ABC67689B606", "3B","7/5/2017","12/28/2017"}
	};	
	private JTable leaseTable = new JTable(leaseData, leaseColumnNames);
	
	private void buildGUI(){
		customerPanel.add(customerTable);
		customerPanel.setLayout(new BorderLayout());
		customerPanel.add(customerTable.getTableHeader(), BorderLayout.PAGE_START);
		customerPanel.add(customerTable, BorderLayout.CENTER);
		
		
		tabbedPane.addTab("Customers", customerPanel);
		tabbedPane.addTab("Boats", boatPanel);
		boatPanel.setLayout(new BorderLayout());
		boatPanel.add(boatTable.getTableHeader(), BorderLayout.PAGE_START);
		boatPanel.add(boatTable, BorderLayout.CENTER);
		
		tabbedPane.addTab("Slips", slipPanel);
		slipPanel.setLayout(new BorderLayout());
		slipPanel.add(slipTable.getTableHeader(), BorderLayout.PAGE_START);
		slipPanel.add(slipTable, BorderLayout.CENTER);
		
		tabbedPane.addTab("Leases", leasePanel);
		leasePanel.setLayout(new BorderLayout());
		leasePanel.add(leaseTable.getTableHeader(), BorderLayout.PAGE_START);
		leasePanel.add(leaseTable, BorderLayout.CENTER);
		
		tabbedPane.setUI(new BasicTabbedPaneUI() {
	        private final Insets borderInsets = new Insets(0, 0, 0, 0);
	        @Override
	        protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
	        }
	        @Override
	        protected Insets getContentBorderInsets(int tabPlacement) {
	            return borderInsets;
	        }
	    });

		tablePanel.add(tabbedPane);
		
		buttonPanel.add(createButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(findButton);
		buttonPanel.add(editButton);
		
		contentPanel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridwidth = 10;
		constraints.gridheight = 10;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.ipadx = 500;
		contentPanel.add(tabbedPane, constraints);
		
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.ipadx = 0;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.gridx = 11;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		
		contentPanel.add(buttonPanel, constraints);
		add(contentPanel);
	}
	
	
	public MarinaGUI(){
		super("Marina");
		setLayout(new GridLayout(1, 2, 1, 1));
		buildGUI();
		setSize(768,512);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		MarinaGUI gui = new MarinaGUI();
		DatabaseManager db = new DatabaseManager();

	}

}
