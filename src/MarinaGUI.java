import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import java.awt.*;
import java.awt.event.*;

public class MarinaGUI extends JFrame{
	
	String[] customerColumnNames = {"Customer ID", "First Name", "Last Name", "Payment Info", "Phone Number", "Street Address", "City", "State", "ZIP Code"};
	String[] boatColumnNames = {"Boat","Test"};
	String[] slipColumnNames = {"Test"};
	String[] leaseColumnNames = {"Test"};
	
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
	
	String[] columnNames = {"First Name",
            "Last Name",
            "Sport",
            "# of Years",
            "Vegetarian"};
	Object[][] data = {
		    {"Kathy", "Smith",
		     "Snowboarding", new Integer(5), new Boolean(false)},
		    {"John", "Doe",
		     "Rowing", new Integer(3), new Boolean(true)},
		    {"Sue", "Black",
		     "Knitting", new Integer(2), new Boolean(false)},
		    {"Jane", "White",
		     "Speed reading", new Integer(20), new Boolean(true)},
		    {"Joe", "Brown",
		     "Pool", new Integer(10), new Boolean(false)}
		};
	
	private JTable customerTable = new JTable(data, columnNames);
	private JTable boatTable = new JTable(null, boatColumnNames);
	private JTable slipTable = new JTable(null, slipColumnNames);
	private JTable leaseTable = new JTable(null, leaseColumnNames);
	
	private void buildGUI(){
		customerPanel.add(customerTable);
		
		tabbedPane.addTab("Customers", customerPanel);
		tabbedPane.addTab("Boats", boatPanel);
		tabbedPane.addTab("Slips", slipPanel);
		tabbedPane.addTab("Leases", leasePanel);
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
