import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;
import java.awt.event.*;

public class MarinaGUI extends JFrame{
	private JLabel outOfLabel = new JLabel("0 of 0");
	
	private JButton previousButton = new JButton("<");
	private JButton nextButton = new JButton(">");
	private JButton createButton = new JButton("Create");
	private JButton deleteButton = new JButton("Delete");
	private JButton findButton = new JButton("Find");
	private JButton editButton = new JButton("Edit");
	private JButton updateButton = new JButton("Update");
	private JButton executeSearchButton = new JButton("Find");
	
	private JPanel contentPanel = new JPanel();
	private JPanel tablePanel = new JPanel();
	private JPanel buttonPanel = new JPanel(new GridLayout(5,1,1,5));
	private JPanel searchPanel = new JPanel();
	private JPanel customerPanel = new JPanel();
	private JPanel boatPanel = new JPanel();
	private JPanel slipPanel = new JPanel();
	private JPanel leasePanel = new JPanel();
	private JPanel topPanel = new JPanel();
	
	private JDialog searchDialog = new JDialog(this);
	
	private JTabbedPane tabbedPane = new JTabbedPane();
	
	private JTextField searchField = new JTextField(30);
	
	ChoiceListener listener = new ChoiceListener();
	
	private void buildGUI(){
		outOfLabel.setVerticalAlignment(JLabel.CENTER);
		outOfLabel.setHorizontalAlignment(JLabel.CENTER);
		
		searchPanel.setLayout(new BorderLayout());
		searchPanel.add(searchField, BorderLayout.CENTER);
		searchPanel.add(executeSearchButton, BorderLayout.EAST);
		
		topPanel.setLayout(new BorderLayout());
		topPanel.add(previousButton, BorderLayout.WEST);
		topPanel.add(outOfLabel, BorderLayout.CENTER);
		topPanel.add(nextButton, BorderLayout.EAST);
		
		customerPanel.setLayout(new BorderLayout());
		customerPanel.add(topPanel, BorderLayout.NORTH);
		
		tabbedPane.addTab("Customers", customerPanel);
		
		tabbedPane.addTab("Boats", boatPanel);
		boatPanel.setLayout(new BorderLayout());
		
		tabbedPane.addTab("Slips", slipPanel);
		slipPanel.setLayout(new BorderLayout());
		
		tabbedPane.addTab("Leases", leasePanel);
		leasePanel.setLayout(new BorderLayout());
		
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
		buttonPanel.add(updateButton);
		
		updateButton.setVisible(false);
		
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
		
		findButton.addActionListener(listener);
		editButton.addActionListener(listener);
		updateButton.addActionListener(listener);
		executeSearchButton.addActionListener(listener);
	}
	
	
	public MarinaGUI(){
		super("Marina");
		setLayout(new GridLayout(1, 2, 1, 1));
		buildGUI();
		setSize(768,512);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class ChoiceListener implements ActionListener{
		DatabaseManager db = new DatabaseManager();
		public void actionPerformed(ActionEvent event){
			if(event.getSource() == findButton){
				searchDialog.setTitle("Search");
				searchDialog.setLayout(new BorderLayout());
				searchDialog.add(searchField, BorderLayout.WEST);
				searchDialog.add(executeSearchButton, BorderLayout.EAST);
				searchDialog.pack();
				searchDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				searchDialog.setVisible(true);
				System.out.println("Find Button");
			}
			
			// Search is executed
			if(event.getSource() == executeSearchButton){
				int selection = tabbedPane.getSelectedIndex();
				
				switch(selection){
				case 0:
					Customer[] customerResults = db.findCustomers(searchField.getText());
					for(int i = 0; i < customerResults.length; i++){
						System.out.println(customerResults[i].toString());
					}
					break;
				case 1:
					Boat[] boatResults = db.findBoats(searchField.getText());
					for(int i = 0; i < boatResults.length; i++){
						System.out.println(boatResults[i].toString());
					}
					break;
				case 2:
					Slip[] slipResults = db.findSlips(searchField.getText());
					for(int i = 0; i < slipResults.length; i++){
						System.out.println(slipResults[i].toString());
					}
					break;
				case 3:
					Lease[] leaseResults = db.findLeases(searchField.getText());
					for(int i = 0; i < leaseResults.length; i++){
						System.out.println(leaseResults[i].toString());
					}
				}
			}
			
			// Edit button is pressed
			if(event.getSource() == editButton){
				updateButton.setVisible(true);
				int selection = tabbedPane.getSelectedIndex();
				// Do JTextField.setEditable(true) for each field
				switch(selection){
				case 0:
					
					break;
				case 1:
					
					break;
				case 2:
					
					break;
				case 3:
					
				}
			}
			
			// Update button is pressed
			if(event.getSource() == updateButton){
				updateButton.setVisible(false);
				int selection = tabbedPane.getSelectedIndex();
				// For each tab, create the respective object, populate it, and update the database
				switch(selection){
				case 0:
					Customer cust = new Customer();
					// Populate object
					db.updateCustomer(cust);
					break;
				case 1:
					Boat boat = new Boat();
					// Populate object
					db.updateBoat(boat);
					break;
				case 2:
					Slip slip = new Slip();
					// Populate object
					db.updateSlip(slip);
					break;
				case 3:
					Lease lease = new Lease();
					// Populate object
					db.updateLease(lease);
				}
			}
		}
	}

	public static void main(String[] args) {
		MarinaGUI gui = new MarinaGUI();
	}

}
