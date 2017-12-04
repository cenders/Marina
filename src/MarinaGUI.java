import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JFormattedTextField;

public class MarinaGUI extends JFrame {
	private JLabel outOfLabel = new JLabel("0 of 0");
	
	private JButton previousButton = new JButton("<");
	private JButton nextButton = new JButton(">");
	private JButton createButton = new JButton("Create");
	private JButton deleteButton = new JButton("Delete");
	private JButton findButton = new JButton("Find");
	private JButton clearButton = new JButton("Clear"); 
	private JButton editButton = new JButton("Edit");
	private JButton updateButton = new JButton("Update");
	private JButton executeSearchButton = new JButton("Find");
	private JButton slipStatusButton = new JButton("Slip Status");

	private JPanel contentPanel = new JPanel();
	private JPanel tablePanel = new JPanel();
	private JPanel buttonPanel = new JPanel(new GridLayout(9, 1, 1, 5)); 
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

	// customerPanel
	private JLabel customerIDLB = new JLabel("Customer ID", SwingConstants.RIGHT);
	private JLabel fnameLB = new JLabel("First Name", SwingConstants.RIGHT);
	private JLabel lnameLB = new JLabel("Last Name", SwingConstants.RIGHT);
	private JLabel paymentLB = new JLabel("Payment Info", SwingConstants.RIGHT);
	private JLabel phoneLB = new JLabel("Phone Number", SwingConstants.RIGHT);
	private JLabel streetLB = new JLabel("Street", SwingConstants.RIGHT);
	private JLabel cityLB = new JLabel("City", SwingConstants.RIGHT);
	private JLabel stateLB = new JLabel("State", SwingConstants.RIGHT);
	private JLabel zipcodeLB = new JLabel("Zip Code", SwingConstants.RIGHT);

	private JTextField customerIDTF = new JTextField(25);
	private JTextField fnameTF = new JTextField(25);
	private JTextField lnameTF = new JTextField(25);
	private JTextField paymentTF = new JTextField(25);
	private JTextField phoneTF = new JTextField(25);
	private JTextField streetTF = new JTextField(25);
	private JTextField cityTF = new JTextField(25);
	private JTextField stateTF = new JTextField(25);
	private JTextField zipcodeTF = new JTextField(25);

	private JPanel customerLBPanel = new JPanel();
	private JPanel customerTFPanel = new JPanel();

	// boatPanel
	private JLabel ownerLB = new JLabel("Owner", SwingConstants.RIGHT);
	private JLabel vinLB = new JLabel("Vin", SwingConstants.RIGHT);
	private JLabel makeLB = new JLabel("Make", SwingConstants.RIGHT);
	private JLabel modelLB = new JLabel("Model", SwingConstants.RIGHT);
	private JLabel colorLB = new JLabel("Color", SwingConstants.RIGHT);
	private JLabel isPoweredBoatLB = new JLabel("Is Powered Boat", SwingConstants.RIGHT);

	private JTextField ownerTF = new JTextField(25);
	private JTextField vinTF = new JTextField(25);
	private JTextField makeTF = new JTextField(25);
	private JTextField modelTF = new JTextField(25);
	private JTextField colorTF = new JTextField(25);
	private JTextField isPoweredBoatTF = new JTextField(25);

	private JPanel boatLBPanel = new JPanel();
	private JPanel boatTFPanel = new JPanel();

	// slipPanel
	private JLabel slipIDLB = new JLabel("Slip ID", SwingConstants.RIGHT);
	private JLabel isPoweredLB = new JLabel("Is Powered Slip?", SwingConstants.RIGHT);
	private JLabel isLeasedLB = new JLabel("Is Leased?", SwingConstants.RIGHT);
	private JLabel isOccupiedLB = new JLabel("Is Occupied?", SwingConstants.RIGHT);

	private JTextField slipIDTF = new JTextField(25);
	private JTextField isPoweredSlipTF = new JTextField(25);
	private JTextField isLeasedTF = new JTextField(25);
	private JTextField isOccupiedTF = new JTextField(25);

	private JPanel slipLBPanel = new JPanel();
	private JPanel slipTFPanel = new JPanel();

	// leasePanel
	private JLabel occupiedByLB = new JLabel("Occupied By", SwingConstants.RIGHT);
	private JLabel locationLB = new JLabel("Location", SwingConstants.RIGHT);
	private JLabel leaseStartDateLB = new JLabel("Lease Start Date", SwingConstants.RIGHT);
	private JLabel leaseEndDateLB = new JLabel("Lease End Date", SwingConstants.RIGHT);
	private JLabel costBreakDownLB= new JLabel("Cost Break Down", SwingConstants.RIGHT);
	int dayCount;
	int totalCost;
	int dailyRate = 250;

	private JTextField occupiedByTF = new JTextField(25);
	private JTextField locationTF = new JTextField(25);
	private JFormattedTextField leaseStartDateTF = new JFormattedTextField(new SimpleDateFormat("yyyy-MM-dd"));
	private JFormattedTextField leaseEndDateTF = new JFormattedTextField(new SimpleDateFormat("yyyy-MM-dd"));
	private JTextField costBreakDownTF = new JTextField(25);

	private JPanel leaseLBPanel = new JPanel();
	private JPanel leaseTFPanel = new JPanel();

	private void buildGUI() {
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

		customerLBPanel.setLayout(new GridLayout(9, 0, 1, 1));
		customerLBPanel.add(customerIDLB);
		customerLBPanel.add(fnameLB);
		customerLBPanel.add(lnameLB);
		customerLBPanel.add(paymentLB);
		customerLBPanel.add(phoneLB);
		customerLBPanel.add(streetLB);
		customerLBPanel.add(cityLB);
		customerLBPanel.add(stateLB);
		customerLBPanel.add(zipcodeLB);

		customerPanel.add(customerLBPanel, BorderLayout.WEST);

		customerTFPanel.setLayout(new GridLayout(9, 0, 1, 1));
		customerTFPanel.add(customerIDTF);
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

		// boatPanel
		tabbedPane.addTab("Boats", boatPanel);
		boatPanel.setLayout(new BorderLayout());

		boatLBPanel.setLayout(new GridLayout(6, 0, 1, 1));
		boatLBPanel.add(ownerLB);
		boatLBPanel.add(vinLB);
		boatLBPanel.add(makeLB);
		boatLBPanel.add(modelLB);
		boatLBPanel.add(colorLB);
		boatLBPanel.add(isPoweredBoatLB);

		boatPanel.add(boatLBPanel, BorderLayout.WEST);

		boatTFPanel.setLayout(new GridLayout(6, 0, 1, 1));
		boatTFPanel.add(ownerTF);
		ownerTF.setEnabled(false);
		boatTFPanel.add(vinTF);
		boatTFPanel.add(makeTF);
		boatTFPanel.add(modelTF);
		boatTFPanel.add(colorTF);
		boatTFPanel.add(isPoweredBoatTF);

		boatPanel.add(boatTFPanel, BorderLayout.CENTER);

		// slipPanel
		tabbedPane.addTab("Slips", slipPanel);
		slipPanel.setLayout(new BorderLayout());

		slipLBPanel.setLayout(new GridLayout(4, 0, 1, 1));
		slipLBPanel.add(slipIDLB);
		slipLBPanel.add(isLeasedLB);
		slipLBPanel.add(isOccupiedLB);
		slipLBPanel.add(isPoweredLB);

		slipPanel.add(slipLBPanel, BorderLayout.WEST);

		slipTFPanel.setLayout(new GridLayout(4, 0, 1, 1));
		slipTFPanel.add(slipIDTF);
		slipTFPanel.add(isLeasedTF);
		slipTFPanel.add(isOccupiedTF);
		slipTFPanel.add(isPoweredSlipTF);

		slipPanel.add(slipTFPanel, BorderLayout.CENTER);

		// leasePanel
		tabbedPane.addTab("Leases", leasePanel);
		leasePanel.setLayout(new BorderLayout());

		leaseLBPanel.setLayout(new GridLayout(5, 0, 1, 1));
		leaseLBPanel.add(occupiedByLB);
		leaseLBPanel.add(locationLB);
		leaseLBPanel.add(leaseStartDateLB);
		leaseLBPanel.add(leaseEndDateLB);
		leaseLBPanel.add(costBreakDownLB);

		leasePanel.add(leaseLBPanel, BorderLayout.WEST);

		leaseTFPanel.setLayout(new GridLayout(5, 0, 1, 1));
		leaseTFPanel.add(occupiedByTF);
		occupiedByTF.setEnabled(false);
		
		leaseTFPanel.add(locationTF);
		locationTF.setEnabled(false);
		
		leaseTFPanel.add(leaseStartDateTF);
		leaseStartDateTF.setValue(new Date());
		leaseTFPanel.add(leaseEndDateTF);
		leaseEndDateTF.setValue(new Date());
		
		leaseTFPanel.add(costBreakDownTF);
		costBreakDownTF.setEnabled(false);

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

		buttonPanel.add(previousButton);
		buttonPanel.add(nextButton);
		buttonPanel.add(clearButton); 
		buttonPanel.add(slipStatusButton); 

		//slipStatusButton.setVisible(false);
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
		clearButton.addActionListener(listener); 
		editButton.addActionListener(listener);
		updateButton.addActionListener(listener);
		deleteButton.addActionListener(listener);
		executeSearchButton.addActionListener(listener);
		slipStatusButton.addActionListener(listener); 

		previousButton.addActionListener(listener);
		nextButton.addActionListener(listener);
	}

	public MarinaGUI() {
		super("Marina");
		setLayout(new GridLayout(1, 2, 1, 1));
		buildGUI();
		setSize(768, 512);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	class ChoiceListener implements ActionListener {
		DatabaseManager db = new DatabaseManager();

		Customer[] customerResults = new Customer[0];
		Boat[] boatResults = new Boat[0];
		Slip[] slipResults = new Slip[0];
		Lease[] leaseResults = new Lease[0];

		Customer customer = new Customer();
		Boat boat = new Boat();
		Slip slip = new Slip();
		Lease lease = new Lease();

		int customerArrowIterator = 1;
		int boatArrowIterator = 1;
		int slipArrowIterator = 1;
		int leaseArrowIterator = 1;

		public void actionPerformed(ActionEvent event){
		// Create button is pressed
			if(event.getSource() == createButton){
				int selection = tabbedPane.getSelectedIndex();
				
				// For each tab, create the respective object, populate it, and update the database
				switch(selection){
				case 0:
					// Initialize customer object from form
					Customer customer = new Customer(null, fnameTF.getText(), lnameTF.getText(), paymentTF.getText(), phoneTF.getText(), streetTF.getText(), cityTF.getText(),stateTF.getText(),zipcodeTF.getText());;
					
					// Add customer to database
					db.addCustomer(customer);
					customerIDTF.setText(String.valueOf(db.GetCustomerID()));
					ownerTF.setText("Customer ID: "+ String.valueOf(db.GetCustomerID()));
					break;

				case 1:
					//Boat boat = new Boat();
					boat.setAllBoatInfo(makeTF.getText(),modelTF.getText(),colorTF.getText(),isPoweredBoatTF.getText());
					boat.setCustomerID(customerIDTF.getText());
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

					 vinTF.setText(String.valueOf(db.GetBoatVin()));
					 occupiedByTF.setText("Vin: " + String.valueOf(db.GetBoatVin()));
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

					 slipIDTF.setText(String.valueOf(db.GetSlipID()));
					 locationTF.setText("Slip ID: " + String.valueOf(db.GetSlipID()));
					 break;

				case 3:
					//Lease lease = new Lease();
					 lease.setAllLeaseInfo(leaseStartDateTF.getText(),leaseEndDateTF.getText());
					 lease.setCustomerID(customerIDTF.getText());
					 lease.setVin(vinTF.getText());
					 lease.setSlipID(slipIDTF.getText());

					 String lsd = leaseStartDateTF.getText(),
							led = leaseEndDateTF.getText();

						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
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
						db.renewSlipStatus(Long.valueOf(slipIDTF.getText())); 

				        dayCount = db.getDayCount(db.GetLeaseID());
						totalCost = calculateTotal(dayCount);
						costBreakDownTF.setText("Daily Rental Slip Rate : $250/day. " +
								"Rental Days: " + dayCount + ". " +
								"Total Cost: " + totalCost);
						
				        occupiedByTF.setText("Vin: " + vinTF.getText());
				        locationTF.setText("Slip ID: " + slipIDTF.getText());
						
						
				}
			}

			// Press find button
			if(event.getSource() == findButton){
				searchDialog.setTitle("Search");
				searchDialog.setLayout(new BorderLayout());
				searchDialog.add(searchField, BorderLayout.WEST);
				searchDialog.add(executeSearchButton, BorderLayout.EAST);
				searchDialog.pack();
				searchDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				searchDialog.setVisible(true);
				//System.out.println("Find Button");
			}

			// Search is executed
			if(event.getSource() == executeSearchButton){
				int selection = tabbedPane.getSelectedIndex();

				switch(selection){
				case 0:
					customerResults = db.findCustomers(searchField.getText());
					
					if(customerResults.length == 0){
						break;
					}
					
					for(int i = 0; i < customerResults.length; i++){
					}
					
					customerIDTF.setText(customerResults[0].getCustomerID());
					fnameTF.setText(customerResults[0].getFirstName());
					lnameTF.setText(customerResults[0].getLastName());
					paymentTF.setText(customerResults[0].getPaymentInfo());
					phoneTF.setText(customerResults[0].getPhoneNumber());
					streetTF.setText(customerResults[0].getStreetAddress());
					cityTF.setText(customerResults[0].getCity());
					stateTF.setText(customerResults[0].getState());
					zipcodeTF.setText(customerResults[0].getZip());
					break;

				case 1:
					boatResults = db.findBoats(searchField.getText());
					
					if(boatResults.length == 0){
						break;
					}
					
					for(int i = 0; i < boatResults.length; i++){
						//System.out.println(boatResults[i].toString());
					}
					ownerTF.setText("Customer ID: " + boatResults[0].getCustomerID());
					vinTF.setText(boatResults[0].getVin());
					customerIDTF.setText(boatResults[0].getCustomerID());
					makeTF.setText(boatResults[0].getMake());
					modelTF.setText(boatResults[0].getModel());
					colorTF.setText(boatResults[0].getColor());
					
					String boatIsPowered = "";
					if((boatResults[0].getIsPowered().toString()).equals("TRUE"))
					{boatIsPowered = "Yes";}
					else
					{boatIsPowered = "No";}
					//isPoweredBoatTF.setText(boatResults[0].getIsPowered());
					isPoweredBoatTF.setText(boatIsPowered);
					break;

				case 2:
					slipResults = db.findSlips(searchField.getText());
					
					if(slipResults.length == 0){
						break;
					}
					
					for(int i = 0; i < slipResults.length; i++){
						//System.out.println(slipResults[i].toString());
					}
		
					slipIDTF.setText(slipResults[0].getSlipID());
					
					String slipIsPowered = "";
					if(slipResults[0].getIsPowered().toString().equals("TRUE"))
					{slipIsPowered = "Yes";}
					else
					{slipIsPowered = "No";}
					
					isPoweredSlipTF.setText(slipIsPowered);
					
					String slipIsLeased = "";
					if(slipResults[0].getIsLeased().toString().equals("TRUE"))
					{slipIsLeased = "Yes";}
					else
					{slipIsLeased = "No";}
					
					isLeasedTF.setText(slipIsLeased);
					
					String slipIsOccupied = "";
					if(slipResults[0].getIsOccupied().toString().equals("TRUE"))
					{slipIsOccupied = "Yes";}
					else
					{slipIsOccupied = "No";}
					
					isOccupiedTF.setText(slipIsOccupied);
					
					
//					isLeasedTF.setText(slipResults[0].getIsLeased());
//					isOccupiedTF.setText(slipResults[0].getIsOccupied());
					break;

				case 3:
					leaseResults = db.findLeases(searchField.getText());
					
					if(leaseResults.length == 0){
						break;
					}
					
					for(int i = 0; i < leaseResults.length; i++){
						//System.out.println(leaseResults[i].toString());
					}
					
					//System.out.println("Days: " + db.getDayCount(Long.valueOf(leaseResults[0].getLeaseID())));
					dayCount = db.getDayCount(Long.valueOf(leaseResults[0].getLeaseID()));
					totalCost = calculateTotal(dayCount);
					costBreakDownTF.setText("Daily Rental Slip Rate : $250/day. " +
											"Rental Days: " + dayCount + ". " +
											"Total Cost: " + totalCost);
					customerIDTF.setText(leaseResults[0].getCustomerID());
					occupiedByTF.setText("Vin: " + leaseResults[0].getVin());
					locationTF.setText("Slip ID: " + leaseResults[0].getSlipID());							
					leaseStartDateTF.setText(leaseResults[0].getLeaseStartDate().substring(0,10));
					leaseEndDateTF.setText(leaseResults[0].getLeaseEndDate().substring(0,10));
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
						//System.out.println("Customer ID: " + customerIDTF.getText());
						Customer customer = new Customer(customerIDTF.getText(), fnameTF.getText(),lnameTF.getText(), phoneTF.getText(), streetTF.getText(), cityTF.getText(), 
								paymentTF.getText(), stateTF.getText(), zipcodeTF.getText());
						db.updateCustomer(customer);
						break;
						case 1:
							Boat boat = new Boat();
							// Populate object
							String poweredBoatStatus = isPoweredBoatTF.getText().trim();
							boolean status = false;
							if(poweredBoatStatus.equalsIgnoreCase("yes") || poweredBoatStatus.equalsIgnoreCase("y"))
							{
								status = true;
								db.updateBoat(Long.valueOf(customerIDTF.getText()), makeTF.getText(), modelTF.getText(), colorTF.getText(), status, Long.valueOf(vinTF.getText()));
							}
							else if(poweredBoatStatus.equalsIgnoreCase("no") || poweredBoatStatus.equalsIgnoreCase("n"))
							{
								status = false;
								db.updateBoat(Long.valueOf(customerIDTF.getText()), makeTF.getText(), modelTF.getText(), colorTF.getText(), status, Long.valueOf(vinTF.getText()));

							}
							else
							{
								JOptionPane.showMessageDialog(null, "Please enter 'yes' or 'no' to indentify whether it is a powered boat.");
							}

							break;

						case 2:
							Slip slip = new Slip();
							// Populate object
							String poweredSlip = isPoweredSlipTF.getText().trim();
							String leased = isLeasedTF.getText().trim();
							String occupied = isOccupiedTF.getText().trim();
							slip.setSlipID(slipIDTF.getText());

							boolean poweredSlipStatus = false,
									leasedStatus = false,
									occupiedStatus = false;

							if(poweredSlip.equalsIgnoreCase("yes") || poweredSlip.equalsIgnoreCase("y"))
							{
								poweredSlipStatus = true;
							}
							else if(poweredSlip.equalsIgnoreCase("no") || poweredSlip.equalsIgnoreCase("n"))
							{
								poweredSlipStatus = false;
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Please enter 'yes' or 'no' to indentify whether it is a powered slip.");
							}


							if(leased.equalsIgnoreCase("yes") || leased.equalsIgnoreCase("y"))
							{
								leasedStatus = true;
							}
							else if(leased.equalsIgnoreCase("no") || leased.equalsIgnoreCase("n"))
							{
								leasedStatus = false;
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Please enter 'yes' or 'no' to indentify whether it is a leased slip.");
							}


							if(occupied.equalsIgnoreCase("yes") || occupied.equalsIgnoreCase("y"))
							{
								occupiedStatus = true;
							}
							else if(occupied.equalsIgnoreCase("no") || occupied.equalsIgnoreCase("n"))
							{
								occupiedStatus = false;
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Please enter 'yes' or 'no' to indentify whether it is a occupied slip.");
							}

							db.updateSlip(poweredSlipStatus, leasedStatus, occupiedStatus, Long.valueOf(slip.getSlipID()));
							break;

						case 3:
							Lease lease = new Lease();
							// Populate object
							lease.setLeaseID(String.valueOf(db.GetLeaseID()));
							//System.out.println(Long.valueOf(lease.getLeaseID()));
							
							 String lsd = leaseStartDateTF.getText(),
									led = leaseEndDateTF.getText();

							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
							Date startDate = null, endDate = null;
							try {
								startDate = format.parse(lsd);
								endDate = format.parse(led);
							} catch (ParseException e) {
								e.printStackTrace();
							}
						        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
						        java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

						    
							db.updateLease(Long.valueOf(customerIDTF.getText()), Long.valueOf(vinTF.getText()), Long.valueOf(slipIDTF.getText()),
									sqlStartDate, sqlEndDate, Long.valueOf(lease.getLeaseID()));
							
						}
					}

					if(event.getSource() == deleteButton){
						int selection = tabbedPane.getSelectedIndex();

						// For each tab, create the respective object, populate it, and update the database
						switch(selection){
						case 0:
							Customer cust = new Customer();
							// Populate object
							cust.setCustomerID(customerIDTF.getText());
							db.deleteCustomer(Long.valueOf(cust.getCustomerID()));
							//System.out.println("Customer Delete button");
							
							customerIDTF.setText("");
							fnameTF.setText("");
							lnameTF.setText("");
							paymentTF.setText("");
							phoneTF.setText("");
							streetTF.setText("");
							cityTF.setText("");
							stateTF.setText("");
							zipcodeTF.setText("");
							break;

						case 1:
							Boat boat = new Boat();
							boat.setVin(vinTF.getText());
							// Populate object
							db.deleteBoat(Long.valueOf(boat.getVin()));
							//System.out.println("Boat Delete Button");
												
							customerIDTF.setText("");
							ownerTF.setText("");
							vinTF.setText("");
							makeTF.setText("");
							modelTF.setText("");
							colorTF.setText("");
							isPoweredBoatTF.setText("");
							break;

						case 2:
							Slip slip = new Slip();
							slip.setSlipID(slipIDTF.getText());
							// Populate object
							db.deleteSlip(Long.valueOf(slip.getSlipID()));
							//System.out.println("slip Delete button");
							slipIDTF.setText("");
							isPoweredSlipTF.setText("");
							isLeasedTF.setText("");
							isOccupiedTF.setText("");

							break;
						case 3:
							Lease lease = new Lease();
							// Populate object
//							System.out.println(db.GetSlipID());
//							System.out.println(db.GetLeaseID());
							if(db.GetLeaseID() == Long.valueOf(0) || db.GetSlipID() == Long.valueOf(0))
							{
							}
							else{
							db.restoreSlipStatus(db.GetSlipID());    //this works but requires search slip
							db.deleteLease(db.GetLeaseID());
							}
							
							occupiedByTF.setText("");
							locationTF.setText("");
							leaseStartDateTF.setValue(new Date());
							leaseEndDateTF.setValue(new Date());
							costBreakDownTF.setText("");
							
						}
					}
					
					//Clear button is pressed
					if(event.getSource() == clearButton){
						int selection = tabbedPane.getSelectedIndex();
						
						// For each tab, create the respective object, populate it, and update the database
						switch(selection){
						case 0:
							// clear all text fields
							customerIDTF.setText("");
							fnameTF.setText("");
							lnameTF.setText("");
							paymentTF.setText("");
							phoneTF.setText("");
							streetTF.setText("");
							cityTF.setText("");
							stateTF.setText("");
							zipcodeTF.setText("");
							break;
							
						case 1:
							customerIDTF.setText("");
							ownerTF.setText("");
							vinTF.setText("");
							makeTF.setText("");
							modelTF.setText("");
							colorTF.setText("");
							isPoweredBoatTF.setText("");
							break;
							
						case 2:
							slipIDTF.setText("");
							isPoweredSlipTF.setText("");
							isLeasedTF.setText("");
							isOccupiedTF.setText("");
							break;
							
						case 3:
							occupiedByTF.setText("");
							locationTF.setText("");
							leaseStartDateTF.setValue(new Date());
							leaseEndDateTF.setValue(new Date());
							costBreakDownTF.setText("");
							break;
					
						}
					}
					

			//Left arrow button is pressed
			if(event.getSource() == previousButton){

				// Get selected panel index
				int selection = tabbedPane.getSelectedIndex();
				switch(selection){
					case 0:
						// Prevent user from going out of range
						if(customerArrowIterator != 1){
							customerArrowIterator--;
						}

						// Set text fields with data
						customerIDTF.setText(customerResults[customerArrowIterator - 1].getCustomerID());
						fnameTF.setText(customerResults[customerArrowIterator - 1].getFirstName());
						lnameTF.setText(customerResults[customerArrowIterator - 1].getLastName());
						paymentTF.setText(customerResults[customerArrowIterator - 1].getPaymentInfo());
						phoneTF.setText(customerResults[customerArrowIterator - 1].getPhoneNumber());
						streetTF.setText(customerResults[customerArrowIterator - 1].getStreetAddress());
						cityTF.setText(customerResults[customerArrowIterator - 1].getCity());
						stateTF.setText(customerResults[customerArrowIterator - 1].getState());
						zipcodeTF.setText(customerResults[customerArrowIterator - 1].getZip());
						break;
					case 1:
						// Prevent user from going out of range
						if(boatArrowIterator != 1){
							boatArrowIterator--;
						}

						// Set text fields with data
						customerIDTF.setText(boatResults[boatArrowIterator - 1].getCustomerID());
						ownerTF.setText("Customer ID: " + boatResults[boatArrowIterator - 1].getCustomerID());
						vinTF.setText(boatResults[boatArrowIterator - 1].getVin());
						makeTF.setText(boatResults[boatArrowIterator - 1].getMake());
						modelTF.setText(boatResults[boatArrowIterator - 1].getModel());
						colorTF.setText(boatResults[boatArrowIterator - 1].getColor());
						
						String boatIsPowered = "";
						if((boatResults[boatArrowIterator - 1].getIsPowered().toString()).equals("TRUE"))
						{boatIsPowered = "Yes";}
						else
						{boatIsPowered = "No";}
						//isPoweredBoatTF.setText(boatResults[0].getIsPowered());
						isPoweredBoatTF.setText(boatIsPowered);
						
						break;
					case 2:
						// Prevent user from going out of range
						if(slipArrowIterator != 1){
							slipArrowIterator--;
						}

						// Set text fields with data
						slipIDTF.setText(slipResults[slipArrowIterator - 1].getSlipID());
						
						String slipIsPowered = "";
						if((slipResults[slipArrowIterator - 1].getIsPowered().toString()).equals("TRUE"))
						{slipIsPowered = "Yes";}
						else
						{slipIsPowered = "No";}
						
						isPoweredSlipTF.setText(slipIsPowered);
						
						String slipIsLeased = "";
						if((slipResults[slipArrowIterator - 1].getIsLeased().toString()).equals("TRUE"))
						{slipIsLeased = "Yes";}
						else
						{slipIsLeased = "No";}
						
						isLeasedTF.setText(slipIsLeased);
						
						String slipIsOccupied = "";
						if((slipResults[slipArrowIterator - 1].getIsOccupied().toString()).equals("TRUE"))
						{slipIsOccupied = "Yes";}
						else
						{slipIsOccupied = "No";}
						
						isOccupiedTF.setText(slipIsOccupied);
						
//						isPoweredSlipTF.setText(slipResults[slipArrowIterator - 1].getIsPowered());
//						isLeasedTF.setText(slipResults[slipArrowIterator - 1].getIsLeased());
//						isOccupiedTF.setText(slipResults[slipArrowIterator - 1].getIsOccupied());
						break;
					case 3:
						// Prevent user from going out of range
						if(leaseArrowIterator != 1){
							leaseArrowIterator--;
						}

						// Set text fields with data
						customerIDTF.setText(leaseResults[leaseArrowIterator - 1].getCustomerID());
						occupiedByTF.setText("Vin: " + leaseResults[leaseArrowIterator - 1].getVin());
						locationTF.setText("Slip ID: "+ leaseResults[leaseArrowIterator - 1].getSlipID());
						leaseStartDateTF.setText(leaseResults[leaseArrowIterator - 1].getLeaseStartDate().substring(0, 10));
						leaseEndDateTF.setText(leaseResults[leaseArrowIterator - 1].getLeaseEndDate().substring(0, 10));
						dayCount = db.getDayCount(Long.valueOf(leaseResults[leaseArrowIterator - 1].getLeaseID()));
						totalCost = calculateTotal(dayCount);
						costBreakDownTF.setText("Daily Rental Slip Rate : $250/day. " +
								"Rental Days: " + dayCount + ". " +
								"Total Cost: " + totalCost);
						break;
				}
			}
			
			
			//Right arrow button is pressed
			if(event.getSource() == nextButton){
				// Get selected panel index
				int selection = tabbedPane.getSelectedIndex();
				//System.out.println("Tab # " + selection);

				switch(selection){
				case 0:
					// Prevent user from going out of range
					if(customerArrowIterator == customerResults.length || customerResults.length == 0){
						break;
					}
					// Increment iterator
					customerArrowIterator++;

					// Set text fields with data
					customerIDTF.setText(customerResults[customerArrowIterator - 1].getCustomerID());
					fnameTF.setText(customerResults[customerArrowIterator - 1].getFirstName());
					lnameTF.setText(customerResults[customerArrowIterator - 1].getLastName());
					paymentTF.setText(customerResults[customerArrowIterator - 1].getPaymentInfo());
					phoneTF.setText(customerResults[customerArrowIterator - 1].getPhoneNumber());
					streetTF.setText(customerResults[customerArrowIterator - 1].getStreetAddress());
					cityTF.setText(customerResults[customerArrowIterator - 1].getCity());
					stateTF.setText(customerResults[customerArrowIterator - 1].getState());
					zipcodeTF.setText(customerResults[customerArrowIterator - 1].getZip());
					break;
				case 1:
					// Prevent user from going out of range
					if(boatArrowIterator == boatResults.length || boatResults.length == 0){
						break;
					}

					// Increment iterator
					boatArrowIterator++;

//					System.out.println("Iterator: " + boatArrowIterator);
//					System.out.println("Results Length: " + boatResults.length);
//					System.out.println(boatArrowIterator + " out of " + boatResults.length);

					// Set text fields with data
					customerIDTF.setText(boatResults[boatArrowIterator - 1].getCustomerID());
					ownerTF.setText("Customer ID: " + boatResults[boatArrowIterator - 1].getCustomerID());
					vinTF.setText(boatResults[boatArrowIterator - 1].getVin());
					makeTF.setText(boatResults[boatArrowIterator - 1].getMake());
					modelTF.setText(boatResults[boatArrowIterator - 1].getModel());
					colorTF.setText(boatResults[boatArrowIterator - 1].getColor());
					
					String boatIsPowered = "";
					if((boatResults[boatArrowIterator - 1].getIsPowered().toString()).equals("TRUE"))
					{boatIsPowered = "Yes";}
					else
					{boatIsPowered = "No";}
					//isPoweredBoatTF.setText(boatResults[0].getIsPowered());
					isPoweredBoatTF.setText(boatIsPowered);
					
					break;
				case 2:
					// Prevent user from going out of range
					if(slipArrowIterator == slipResults.length || slipResults.length == 0){
						break;
					}

					// Increment iterator
					slipArrowIterator++;

					// Set text fields with data
					slipIDTF.setText(slipResults[slipArrowIterator - 1].getSlipID()); 
					
					String slipIsPowered = "";
					if((slipResults[slipArrowIterator - 1].getIsPowered().toString()).equals("TRUE"))
					{slipIsPowered = "Yes";}
					else
					{slipIsPowered = "No";}
					
					isPoweredSlipTF.setText(slipIsPowered);
					
					String slipIsLeased = "";
					if((slipResults[slipArrowIterator - 1].getIsLeased().toString()).equals("TRUE"))
					{slipIsLeased = "Yes";}
					else
					{slipIsLeased = "No";}
					
					isLeasedTF.setText(slipIsLeased);
					
					String slipIsOccupied = "";
					if((slipResults[slipArrowIterator - 1].getIsOccupied().toString()).equals("TRUE"))
					{slipIsOccupied = "Yes";}
					else
					{slipIsOccupied = "No";}
					
					isOccupiedTF.setText(slipIsOccupied);
					
//					isPoweredSlipTF.setText(slipResults[slipArrowIterator - 1].getIsPowered());
//					isLeasedTF.setText(slipResults[slipArrowIterator - 1].getIsLeased());
//					isOccupiedTF.setText(slipResults[slipArrowIterator - 1].getIsOccupied());
					break;
				case 3:
					// Prevent user from going out of range
					if(leaseArrowIterator == leaseResults.length || leaseResults.length == 0){
						//System.out.println("Out of range.");
						break;
					}

					// Increment iterator
					leaseArrowIterator++;

					// Set text fields with data
					customerIDTF.setText(leaseResults[leaseArrowIterator - 1].getCustomerID());					
					occupiedByTF.setText("Vin: " + leaseResults[leaseArrowIterator - 1].getVin());
					locationTF.setText("Slip ID: " + leaseResults[leaseArrowIterator - 1].getSlipID());
					leaseStartDateTF.setText(leaseResults[leaseArrowIterator - 1].getLeaseStartDate().substring(0, 10)); 
					leaseEndDateTF.setText(leaseResults[leaseArrowIterator - 1].getLeaseEndDate().substring(0, 10));
					dayCount = db.getDayCount(Long.valueOf(leaseResults[leaseArrowIterator - 1].getLeaseID()));
					totalCost = calculateTotal(dayCount);
					costBreakDownTF.setText("Daily Rental Slip Rate : $250/day. " +
							"Rental Days: " + dayCount + ". " +
							"Total Cost: " + totalCost);
					break;
				}
			}
			
			// Slip Status button is pressed
			if(event.getSource() == slipStatusButton){
				int selection = tabbedPane.getSelectedIndex();
				// Do JTextField.setEditable(true) for each field
				switch(selection){
				case 0:
					db.slipStatus();
					break;
				case 1:
					db.slipStatus();
					break;
				case 2:
					db.slipStatus();
					break;
				case 3:
					db.slipStatus();

				}
			}

		}
	}
	
	
	//calculate total rental cost for slip
	public int calculateTotal(int dayCount){
		int total = dailyRate * dayCount;	
		return total;
	}

	public static void main(String[] args) {
		MarinaGUI gui = new MarinaGUI();
	}

}
