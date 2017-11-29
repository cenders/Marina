import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JFormattedTextField;

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
	
	//customerPanel
	private JLabel fnameLB = new JLabel("First Name", SwingConstants.RIGHT);
	private JLabel lnameLB = new JLabel("Last Name", SwingConstants.RIGHT);
	private JLabel paymentLB = new JLabel("Payment Info", SwingConstants.RIGHT);
	private JLabel phoneLB = new JLabel("Phone Number", SwingConstants.RIGHT);
	private JLabel streetLB = new JLabel("Street", SwingConstants.RIGHT);
	private JLabel cityLB = new JLabel("City", SwingConstants.RIGHT);
	private JLabel stateLB = new JLabel("State", SwingConstants.RIGHT);
	private JLabel zipcodeLB = new JLabel("Zip Code", SwingConstants.RIGHT);
		
	private JTextField fnameTF = new JTextField(25);
	private JTextField lnameTF = new JTextField(25);
	private JTextField paymentTF = new JTextField(25);
	private JTextField phoneTF = new JTextField(25);
	private JTextField streetTF = new JTextField(25);
	private JTextField cityTF = new JTextField(25);
	private JTextField stateTF = new JTextField(25);
	private JTextField zipcodeTF = new JTextField(25);
	
	private JPanel customerLBPanel  = new JPanel();
	private JPanel customerTFPanel  = new JPanel();
	
	//boatPanel
	private JLabel customerIDLB = new JLabel("Customer ID", SwingConstants.RIGHT);
	private JLabel makeLB = new JLabel("Make", SwingConstants.RIGHT);
	private JLabel modelLB = new JLabel("Model", SwingConstants.RIGHT);
	private JLabel colorLB = new JLabel("Color", SwingConstants.RIGHT);
	private JLabel isPoweredBoatLB = new JLabel("Is Powered Boat", SwingConstants.RIGHT);
	
	private JTextField customerIDTF = new JTextField(25);
	private JTextField makeTF = new JTextField(25);
	private JTextField modelTF = new JTextField(25);
	private JTextField colorTF = new JTextField(25);
	private JTextField isPoweredBoatTF = new JTextField(25);
		
	private JPanel boatLBPanel  = new JPanel();
	private JPanel boatTFPanel  = new JPanel();
	
	//slipPanel
	private JLabel vinLB = new JLabel("Vin", SwingConstants.RIGHT);
	private JLabel isPoweredLB = new JLabel("Is Powered Slip?", SwingConstants.RIGHT);
	private JLabel isLeasedLB = new JLabel("Is Leased?", SwingConstants.RIGHT);
	private JLabel isOccupiedLB = new JLabel("Is Occupied?", SwingConstants.RIGHT);
	
	private JTextField vinTF = new JTextField(25);
	private JTextField isPoweredSlipTF = new JTextField(25);
	private JTextField isLeasedTF = new JTextField(25);
	private JTextField isOccupiedTF = new JTextField(25);

	private JPanel slipLBPanel  = new JPanel();
	private JPanel slipTFPanel  = new JPanel();
	
	//leasePanel
	private JLabel slipIDLB = new JLabel("Slip ID", SwingConstants.RIGHT);
	private JLabel leaseStartDateLB = new JLabel("Lease Start Date", SwingConstants.RIGHT);
	private JLabel leaseEndDateLB = new JLabel("Lease End Date", SwingConstants.RIGHT);
	
	private JTextField slipIDTF = new JTextField(25);	
//	private JTextField leaseStartDateTF = new JTextField(25);
//	private JTextField leaseEndDateTF = new JTextField(25);
	private JFormattedTextField leaseStartDateTF = new JFormattedTextField(new SimpleDateFormat("MM/dd/yyyy"));
	private JFormattedTextField leaseEndDateTF = new JFormattedTextField(new SimpleDateFormat("MM/dd/yyyy"));
	
	private JPanel leaseLBPanel  = new JPanel();
	private JPanel leaseTFPanel  = new JPanel();
	
	
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
		
		customerLBPanel.setLayout(new GridLayout(8,0,1,1));
		customerLBPanel.add(fnameLB);
		customerLBPanel.add(lnameLB);
		customerLBPanel.add(paymentLB);
		customerLBPanel.add(phoneLB);
		customerLBPanel.add(streetLB);
		customerLBPanel.add(cityLB);
		customerLBPanel.add(stateLB);
		customerLBPanel.add(zipcodeLB);
		
		customerPanel.add(customerLBPanel, BorderLayout.WEST);
		
		customerTFPanel.setLayout(new GridLayout(8,0,1,1));
		customerTFPanel.add(fnameTF);
		customerTFPanel.add(lnameTF);
		customerTFPanel.add(paymentTF);
		customerTFPanel.add(phoneTF);
		customerTFPanel.add(streetTF);
		customerTFPanel.add(cityTF);
		customerTFPanel.add(stateTF);
		customerTFPanel.add(zipcodeTF);

		customerPanel.add(customerTFPanel, BorderLayout.CENTER);

		tabbedPane.addTab("Customers", customerPanel);
				
		//boatPanel
		tabbedPane.addTab("Boats", boatPanel);
		boatPanel.setLayout(new BorderLayout());
		
		boatLBPanel.setLayout(new GridLayout(5,0,1,1));	
		boatLBPanel.add(customerIDLB); 
		boatLBPanel.add(makeLB);
		boatLBPanel.add(modelLB);
		boatLBPanel.add(colorLB);
		boatLBPanel.add(isPoweredBoatLB);
		
		boatPanel.add(boatLBPanel, BorderLayout.WEST);
		
		boatTFPanel.setLayout(new GridLayout(5,0,1,1));
		//boatTFPanel.add(vinTF);
		boatTFPanel.add(customerIDTF);
		boatTFPanel.add(makeTF);
		boatTFPanel.add(modelTF);
		boatTFPanel.add(colorTF);
		//boatTFPanel.add(isPoweredBoatTF);
		boatTFPanel.add(isPoweredBoatTF); //in slip panel!!
		
		boatPanel.add(boatTFPanel, BorderLayout.CENTER);			
				
		//slipPanel
		tabbedPane.addTab("Slips", slipPanel);
		slipPanel.setLayout(new BorderLayout());
		
		slipLBPanel.setLayout(new GridLayout(3,0,1,1));

		slipLBPanel.add(isPoweredLB);
		slipLBPanel.add(isLeasedLB);
		slipLBPanel.add(isOccupiedLB);
		
		slipPanel.add(slipLBPanel, BorderLayout.WEST);
		
		slipTFPanel.setLayout(new GridLayout(3,0,1,1));
 
		slipTFPanel.add(isPoweredSlipTF);
		slipTFPanel.add(isLeasedTF);
		slipTFPanel.add(isOccupiedTF);
		
		slipPanel.add(slipTFPanel, BorderLayout.CENTER);	
		
		//leasePanel
		tabbedPane.addTab("Leases", leasePanel);
		leasePanel.setLayout(new BorderLayout());
		
		leaseLBPanel.setLayout(new GridLayout(4,0,1,1));
		leaseLBPanel.add(vinLB);
		leaseLBPanel.add(slipIDLB);
		leaseLBPanel.add(leaseStartDateLB);
		leaseLBPanel.add(leaseEndDateLB);
		
		leasePanel.add(leaseLBPanel, BorderLayout.WEST);
		
		leaseTFPanel.setLayout(new GridLayout(4,0,1,1));
		leaseTFPanel.add(vinTF);
		leaseTFPanel.add(slipIDTF);
		leaseTFPanel.add(leaseStartDateTF);
		leaseStartDateTF.setValue(new Date());
		leaseTFPanel.add(leaseEndDateTF);
		leaseEndDateTF.setValue(new Date());
				
		leasePanel.add(leaseTFPanel, BorderLayout.CENTER);	
				
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
		
		createButton.addActionListener(listener);
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
			
		// Create button is pressed
			if(event.getSource() == createButton){
				int selection = tabbedPane.getSelectedIndex();
				Customer cust = new Customer();
				Boat boat = new Boat();
				Slip slip = new Slip();
				Lease lease = new Lease();
				// For each tab, create the respective object, populate it, and update the database
				switch(selection){
				case 0:
					//Customer cust = new Customer();
					cust.setAllCustomerInfo(fnameTF.getText(), lnameTF.getText(), paymentTF.getText(), phoneTF.getText(), streetTF.getText(), cityTF.getText(),stateTF.getText(),zipcodeTF.getText());;
					
					// Populate object
					db.addCustomer(cust.getFirstName(),cust.getLastName(), cust.getPaymentInfo(), cust.getPhoneNumber(),cust.getStreetAddress(),cust.getCity(), cust.getState(), cust.getZip());
					customerIDTF.setText(String.valueOf(db.GetCustomerID()));;
					break;
					
				case 1:
					//Boat boat = new Boat();
					boat.setAllBoatInfo(makeTF.getText(),modelTF.getText(),colorTF.getText(),isPoweredBoatTF.getText());
					boat.setCustomerID(customerIDTF.getText());
//					boat.setMake(makeTF.getText());
//					boat.setModel(modelTF.getText());
//					boat.setColor(modelTF.getText());
//					boat.setIsPowered(isPoweredBoatTF.getText());
					boolean isPoweredBoatOrNot;					
					
					String temp = isPoweredBoatTF.getText().trim();
					 
					 if(temp.equalsIgnoreCase("yes") || temp.equalsIgnoreCase("y"))
					 {isPoweredBoatOrNot = true;
					 db.addBoat(Long.valueOf(boat.getCustomerID()), boat.getMake(), boat.getModel(), boat.getColor(), isPoweredBoatOrNot);
					 }
					 else if(temp.equalsIgnoreCase("no") || temp.equalsIgnoreCase("n"))
					 {isPoweredBoatOrNot = false;
					 db.addBoat(Long.valueOf(boat.getCustomerID()), boat.getMake(), boat.getModel(), boat.getColor(), isPoweredBoatOrNot);
					 }
					 else
					 { JOptionPane.showMessageDialog(null, "Please enter 'yes' or 'no' to indentify whether it is a powered boat.");}	
					 //db.addBoat(boat);

					 vinTF.setText(String.valueOf(db.GetBoatVin()));;
					break;
					
				case 2:
					//Slip slip = new Slip();					
					 slip.setAllSlipInfo(isPoweredSlipTF.getText(),isLeasedTF.getText(),isOccupiedTF.getText());
					
					 boolean isPoweredSlipOrNot, isLeasedOrNot, isOccupiedOrNot;
					 String tempPowered = isPoweredSlipTF.getText().trim(),
					 tempLeased = isLeasedTF.getText().trim(),
					 tempOccupied = isOccupiedTF.getText().trim();
					
					 if(tempPowered.equalsIgnoreCase("yes") || tempPowered.equalsIgnoreCase("y"))
				  	 {
						 isPoweredSlipOrNot = true;
						 if(tempLeased.equalsIgnoreCase("yes") || tempLeased.equalsIgnoreCase("y"))
						  	 {
							 isLeasedOrNot = true;
							 	if(tempOccupied.equalsIgnoreCase("yes") || tempOccupied.equalsIgnoreCase("y"))
							  	 	{isOccupiedOrNot = true;
							  	 	db.addSlip(isPoweredSlipOrNot, isLeasedOrNot, isOccupiedOrNot);}
							 	else if(tempOccupied.equalsIgnoreCase("no") || tempOccupied.equalsIgnoreCase("n"))
								  	 {isOccupiedOrNot = false;
								  	db.addSlip(isPoweredSlipOrNot, isLeasedOrNot, isOccupiedOrNot);}
								else
								  	 {JOptionPane.showMessageDialog(null, "Please enter 'yes' or 'no' to indentify whether it is a occupied slip.");}
							 }
						 else if(tempLeased.equalsIgnoreCase("no") || tempLeased.equalsIgnoreCase("n"))
						  	 {isLeasedOrNot = false;
						  	if(tempOccupied.equalsIgnoreCase("yes") || tempOccupied.equalsIgnoreCase("y"))
					  	 	{isOccupiedOrNot = true;
					  	 	db.addSlip(isPoweredSlipOrNot, isLeasedOrNot, isOccupiedOrNot);}
					 	else if(tempOccupied.equalsIgnoreCase("no") || tempOccupied.equalsIgnoreCase("n"))
						  	 {isOccupiedOrNot = false;
						  	db.addSlip(isPoweredSlipOrNot, isLeasedOrNot, isOccupiedOrNot);}
						else
						  	 {JOptionPane.showMessageDialog(null, "Please enter 'yes' or 'no' to indentify whether it is a occupied slip.");}
						  	 }
						 else
						  	 {JOptionPane.showMessageDialog(null, "Please enter 'yes' or 'no' to indentify whether it is a leased slip.");}
					 }
					 	 
				  	 else if(tempPowered.equalsIgnoreCase("no") || tempPowered.equalsIgnoreCase("n"))
				  	 {
				  		 isPoweredSlipOrNot = false;
				  		if(tempLeased.equalsIgnoreCase("yes") || tempLeased.equalsIgnoreCase("y"))
					  	 {
						 isLeasedOrNot = true;
						 	if(tempOccupied.equalsIgnoreCase("yes") || tempOccupied.equalsIgnoreCase("y"))
						  	 	{isOccupiedOrNot = true;
						  	 	db.addSlip(isPoweredSlipOrNot, isLeasedOrNot, isOccupiedOrNot);}
						 	else if(tempOccupied.equalsIgnoreCase("no") || tempOccupied.equalsIgnoreCase("n"))
							  	 {isOccupiedOrNot = false;
							  	db.addSlip(isPoweredSlipOrNot, isLeasedOrNot, isOccupiedOrNot);}
							else
							  	 {JOptionPane.showMessageDialog(null, "Please enter 'yes' or 'no' to indentify whether it is a occupied slip.");}
						 }
					 else if(tempLeased.equalsIgnoreCase("no") || tempLeased.equalsIgnoreCase("n"))
					  	 {isLeasedOrNot = false;
					  	if(tempOccupied.equalsIgnoreCase("yes") || tempOccupied.equalsIgnoreCase("y"))
				  	 	{isOccupiedOrNot = true;
				  	 	db.addSlip(isPoweredSlipOrNot, isLeasedOrNot, isOccupiedOrNot);}
				 	else if(tempOccupied.equalsIgnoreCase("no") || tempOccupied.equalsIgnoreCase("n"))
					  	 {isOccupiedOrNot = false;
					  	db.addSlip(isPoweredSlipOrNot, isLeasedOrNot, isOccupiedOrNot);}
					else
					  	 {JOptionPane.showMessageDialog(null, "Please enter 'yes' or 'no' to indentify whether it is a occupied slip.");}
					  	 }
					 else
					  	 {JOptionPane.showMessageDialog(null, "Please enter 'yes' or 'no' to indentify whether it is a leased slip.");}
				 }
				 
					 slipIDTF.setText(String.valueOf(db.GetSLipID()));
					 break;
					
				case 3:
					//Lease lease = new Lease();
					 lease.setAllLeaseInfo(leaseStartDateTF.getText(),leaseEndDateTF.getText());
					 lease.setCustomerID(customerIDTF.getText());
					 lease.setVin(vinTF.getText());
					 lease.setSlipID(slipIDTF.getText());
//					 lease.setLeaseStartDate(leaseStartDateTF.getText());
//					 lease.setLeaseEndDate(leaseEndDateTF.getText());
					 
					 String lsd = leaseStartDateTF.getText(),
							led = leaseEndDateTF.getText();
						
						SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
					Date startDate = null, endDate = null;
					try {
						startDate = format.parse(lsd);
						endDate = format.parse(led);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
				        java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
				        
				        
				        db.addLease(Long.valueOf(lease.getCustomerID()), Long.valueOf(lease.getVin()), Long.valueOf(lease.getSlipID()), sqlStartDate, sqlEndDate);								
				}
			}
			
			//press find button
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
						System.out.println(String.valueOf(customerResults[i].toString()));
						customerResults[i].setCustomerID(customerResults[i].getCustomerID());
						customerIDTF.setText(customerResults[i].getCustomerID());
						fnameTF.setText(customerResults[i].getFirstName());
						lnameTF.setText(customerResults[i].getLastName());
						paymentTF.setText(customerResults[i].getPaymentInfo());
						phoneTF.setText(customerResults[i].getPhoneNumber());
						streetTF.setText(customerResults[i].getStreetAddress()); 
						cityTF.setText(customerResults[i].getCity()); 
						stateTF.setText(customerResults[i].getState());
						zipcodeTF.setText(customerResults[i].getZip());
						
					}
					break;
				case 1:
					Boat[] boatResults = db.findBoats(searchField.getText());
					for(int i = 0; i < boatResults.length; i++){
						System.out.println(boatResults[i].toString());
						
						vinTF.setText(boatResults[i].getVin());
						customerIDTF.setText(boatResults[i].getCustomerID());
						makeTF.setText(boatResults[i].getMake());
						modelTF.setText(boatResults[i].getModel()); //wrong info when testing
						colorTF.setText(boatResults[i].getColor()); //wrong info when testing
						isPoweredBoatTF.setText(boatResults[i].getIsPowered());
						
					}
					break;
				case 2:
					Slip[] slipResults = db.findSlips(searchField.getText());
					for(int i = 0; i < slipResults.length; i++){
						System.out.println(slipResults[i].toString());
						
						//output is wrong???
						slipIDTF.setText(slipResults[i].getSlipID());
						isPoweredSlipTF.setText(slipResults[i].getIsPowered());
						isLeasedTF.setText(slipResults[i].getIsLeased());
						isOccupiedTF.setText(slipResults[i].getIsOccupied());
						
						
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
			
			if(event.getSource() == deleteButton){
				int selection = tabbedPane.getSelectedIndex();
				// For each tab, create the respective object, populate it, and update the database
				switch(selection){
				case 0:
					Customer cust = new Customer();
					// Populate object
					//db.deleteCustomer(Long.valueOf(cust.getCustomerID()));
					//System.out.println(cust.getCustomerID());
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
