import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import java.awt.*;
import java.awt.event.*;
import java.sql.Timestamp;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

	private JPanel contentPanel = new JPanel();
	private JPanel tablePanel = new JPanel();
	private JPanel buttonPanel = new JPanel(new GridLayout(8, 1, 1, 5)); 
	private JPanel searchPanel = new JPanel();
	private JPanel customerPanel = new JPanel();
	private JPanel boatPanel = new JPanel();
	private JPanel slipPanel = new JPanel();
	private JPanel leasePanel = new JPanel();

	private JDialog searchDialog = new JDialog(this);

	private JTabbedPane tabbedPane = new JTabbedPane();

	private JTextField searchField = new JTextField(30);

	ChoiceListener listener = new ChoiceListener();

	// Customer Panel
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

	// Boat Panel
	private JLabel boatVINLB = new JLabel("VIN #", SwingConstants.RIGHT);
	private JLabel boatCustomerIDLB = new JLabel("Customer ID", SwingConstants.RIGHT);
	private JLabel makeLB = new JLabel("Make", SwingConstants.RIGHT);
	private JLabel modelLB = new JLabel("Model", SwingConstants.RIGHT);
	private JLabel colorLB = new JLabel("Color", SwingConstants.RIGHT);
	private JLabel isPoweredBoatLB = new JLabel("Is Powered Boat", SwingConstants.RIGHT);

	private JTextField boatVINTF = new JTextField(25);
	private JTextField boatCustomerIDTF = new JTextField(25);
	private JTextField makeTF = new JTextField(25);
	private JTextField modelTF = new JTextField(25);
	private JTextField colorTF = new JTextField(25);
	private JTextField isPoweredBoatTF = new JTextField(25);

	private JPanel boatLBPanel = new JPanel();
	private JPanel boatTFPanel = new JPanel();

	// Slip Panel
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

	// Lease Panel
	private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	
	private JLabel leaseIDLB = new JLabel("Lease ID", SwingConstants.RIGHT);
	private JLabel leaseCustomerIDLB = new JLabel("Customer ID", SwingConstants.RIGHT);
	private JLabel leaseVINLB = new JLabel("VIN", SwingConstants.RIGHT);
	private JLabel leaseSlipIDLB = new JLabel("Slip ID", SwingConstants.RIGHT);
	private JLabel leaseStartDateLB = new JLabel("Lease Start Date", SwingConstants.RIGHT);
	private JLabel leaseEndDateLB = new JLabel("Lease End Date", SwingConstants.RIGHT);

	private JTextField leaseIDTF = new JTextField(25);
	private JTextField leaseCustomerIDTF = new JTextField(25);
	private JTextField leaseVINTF = new JTextField(25);
	private JTextField leaseSlipIDTF = new JTextField(25);
	private JFormattedTextField leaseStartDateTF = new JFormattedTextField(dateFormat);
	private JFormattedTextField leaseEndDateTF = new JFormattedTextField(dateFormat);

	private JPanel leaseLBPanel = new JPanel();
	private JPanel leaseTFPanel = new JPanel();

	private void buildGUI() {

		// Search panel
		searchPanel.setLayout(new BorderLayout());
		searchPanel.add(searchField, BorderLayout.CENTER);
		searchPanel.add(executeSearchButton, BorderLayout.EAST);

		// Main customer panel
		customerPanel.setLayout(new BorderLayout());
		
		// Customer label panel
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

		// Customer text field panel
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
		
		customerPanel.add(customerLBPanel, BorderLayout.WEST);
		customerPanel.add(customerTFPanel, BorderLayout.CENTER);

		// Add Customers tab to tabbed panel
		tabbedPane.addTab("Customers", customerPanel);

		// Main boat panel
		boatPanel.setLayout(new BorderLayout());

		// Boat label panel
		boatLBPanel.setLayout(new GridLayout(6, 0, 1, 1));
		boatLBPanel.add(boatVINLB);
		boatLBPanel.add(boatCustomerIDLB);
		boatLBPanel.add(makeLB);
		boatLBPanel.add(modelLB);
		boatLBPanel.add(colorLB);
		boatLBPanel.add(isPoweredBoatLB);

		// Boat text field panel
		boatTFPanel.setLayout(new GridLayout(6, 0, 1, 1));
		boatTFPanel.add(boatVINTF);
		boatTFPanel.add(boatCustomerIDTF);
		boatTFPanel.add(makeTF);
		boatTFPanel.add(modelTF);
		boatTFPanel.add(colorTF);
		boatTFPanel.add(isPoweredBoatTF);
		
		boatPanel.add(boatLBPanel, BorderLayout.WEST);
		boatPanel.add(boatTFPanel, BorderLayout.CENTER);
		
		// Add Boats tab to panel
		tabbedPane.addTab("Boats", boatPanel);

		// Slip panel
		slipPanel.setLayout(new BorderLayout());

		// Slip label panel
		slipLBPanel.setLayout(new GridLayout(4, 0, 1, 1));
		slipLBPanel.add(slipIDLB);
		slipLBPanel.add(isPoweredLB);
		slipLBPanel.add(isLeasedLB);
		slipLBPanel.add(isOccupiedLB);

		// Slip text field panel
		slipTFPanel.setLayout(new GridLayout(4, 0, 1, 1));
		slipTFPanel.add(slipIDTF);
		slipTFPanel.add(isPoweredSlipTF);
		slipTFPanel.add(isLeasedTF);
		slipTFPanel.add(isOccupiedTF);

		slipPanel.add(slipLBPanel, BorderLayout.WEST);
		slipPanel.add(slipTFPanel, BorderLayout.CENTER);
		
		// Add Slips tab to panel
		tabbedPane.addTab("Slips", slipPanel);

		// leasePanel
		leasePanel.setLayout(new BorderLayout());

		// Lease label panel
		leaseLBPanel.setLayout(new GridLayout(6, 0, 1, 1));
		leaseLBPanel.add(leaseIDLB);
		leaseLBPanel.add(leaseCustomerIDLB);
		leaseLBPanel.add(leaseVINLB);
		leaseLBPanel.add(leaseSlipIDLB);
		leaseLBPanel.add(leaseStartDateLB);
		leaseLBPanel.add(leaseEndDateLB);

		// Lease text field panel
		leaseTFPanel.setLayout(new GridLayout(6, 0, 1, 1));
		leaseTFPanel.add(leaseIDTF);
		leaseTFPanel.add(leaseCustomerIDTF);
		leaseTFPanel.add(leaseVINTF);
		leaseTFPanel.add(leaseSlipIDTF);
		leaseTFPanel.add(leaseStartDateTF);
		leaseStartDateTF.setValue(new Date());
		leaseTFPanel.add(leaseEndDateTF);
		leaseEndDateTF.setValue(new Date());

		leasePanel.add(leaseLBPanel, BorderLayout.WEST);
		leasePanel.add(leaseTFPanel, BorderLayout.CENTER);
		
		// Add leases tab to panel
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
		buttonPanel.add(clearButton); 
		buttonPanel.add(editButton);
		buttonPanel.add(updateButton);

		buttonPanel.add(previousButton);
		buttonPanel.add(nextButton);

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
					customerIDTF.setText(String.valueOf(db.GetCustomerID()));;
					break;

				case 1:
					String temp = isPoweredBoatTF.getText().trim();

					 if(temp.equalsIgnoreCase("yes") || temp.equalsIgnoreCase("y")){
						 boat = new Boat(null, boatCustomerIDTF.getText(), makeTF.getText(), modelTF.getText(), colorTF.getText(), "Yes");
					 }
					 else if(temp.equalsIgnoreCase("no") || temp.equalsIgnoreCase("n")){
						 boat = new Boat(null, boatCustomerIDTF.getText(), makeTF.getText(), modelTF.getText(), colorTF.getText(), "No");
					 }
					 else{ 
						 JOptionPane.showMessageDialog(null, "Please enter 'yes' or 'no' to indentify whether it is a powered boat.");
					 }
					 db.addBoat(boat);
					 boatVINTF.setText(String.valueOf(db.GetBoatVin()));;
					break;

				case 3:					 
					Timestamp lsd = null;
					Timestamp led = null;
					try {
						lsd = new Timestamp(dateFormat.parse(leaseStartDateTF.getText()).getTime());
						led = new Timestamp(dateFormat.parse(leaseEndDateTF.getText()).getTime());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					lease = new Lease(null, leaseCustomerIDTF.getText(), leaseVINTF.getText(), leaseSlipIDTF.getText(), lsd, led);
				    db.addLease(lease);
				    leaseIDTF.setText(String.valueOf(db.GetLeaseID()));
				    
				    int days = db.getDayCount(Long.parseLong(leaseSlipIDTF.getText()));
				    JOptionPane.showMessageDialog(null, "Lease Added!", "Amount due for this lease is $" + (days * 50) + ".", JOptionPane.OK_OPTION);
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
				System.out.println("Find Button");
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
					
					customerIDTF.setEditable(false);
					fnameTF.setEditable(false);
					lnameTF.setEditable(false);
					paymentTF.setEditable(false);
					phoneTF.setEditable(false);
					streetTF.setEditable(false);
					cityTF.setEditable(false);
					stateTF.setEditable(false);
					zipcodeTF.setEditable(false);
					
					break;

				case 1:
					boatResults = db.findBoats(searchField.getText());
					
					if(boatResults.length == 0){
						break;
					}
					
					for(int i = 0; i < boatResults.length; i++){
						System.out.println(boatResults[i].toString());
					}

					boatVINTF.setText(boatResults[0].getVin());
					boatCustomerIDTF.setText(boatResults[0].getCustomerID());
					makeTF.setText(boatResults[0].getMake());
					modelTF.setText(boatResults[0].getModel());
					colorTF.setText(boatResults[0].getColor());
					isPoweredBoatTF.setText(boatResults[0].getIsPowered());
					
					boatVINTF.setEditable(false);
					boatCustomerIDTF.setEditable(false);
					makeTF.setEditable(false);
					modelTF.setEditable(false);
					colorTF.setEditable(false);
					isPoweredBoatTF.setEditable(false);
					
					break;

				case 2:
					slipResults = db.findSlips(searchField.getText());
					
					if(slipResults.length == 0){
						break;
					}
					
					for(int i = 0; i < slipResults.length; i++){
						System.out.println(slipResults[i].toString());
					}
		
					slipIDTF.setText(slipResults[0].getSlipID());
					isPoweredSlipTF.setText(slipResults[0].getIsPowered());
					isLeasedTF.setText(slipResults[0].getIsLeased());
					isOccupiedTF.setText(slipResults[0].getIsOccupied());
					
					slipIDTF.setEditable(false);
					isPoweredSlipTF.setEditable(false);
					isLeasedTF.setEditable(false);
					isOccupiedTF.setEditable(false);
					
					break;

				case 3:
					leaseResults = db.findLeases(searchField.getText());
					
					if(leaseResults.length == 0){
						break;
					}
					
					for(int i = 0; i < leaseResults.length; i++){
						System.out.println(leaseResults[i].toString());
					}
					
					leaseIDTF.setText(leaseResults[0].getLeaseID());
					leaseCustomerIDTF.setText(leaseResults[0].getCustomerID());
					leaseVINTF.setText(leaseResults[0].getVin());
					leaseSlipIDTF.setText(leaseResults[0].getSlipID());							
					leaseStartDateTF.setText(leaseResults[0].getLeaseStartDate().toString());
					leaseEndDateTF.setText(leaseResults[0].getLeaseEndDate().toString());
					
					leaseIDTF.setEditable(false);
					leaseCustomerIDTF.setEditable(false);
					leaseVINTF.setEditable(false);
					leaseSlipIDTF.setEditable(false);
					leaseStartDateTF.setEditable(false);
					leaseEndDateTF.setEditable(false);
					
					break;
				}
			}

			// Edit button is pressed
			if(event.getSource() == editButton){
				updateButton.setVisible(true);
				int selection = tabbedPane.getSelectedIndex();
				// Do JTextField.setEditable(true) for each field
				switch(selection){
				case 0:
					fnameTF.setEditable(true);
					lnameTF.setEditable(true);
					paymentTF.setEditable(true);
					phoneTF.setEditable(true);
					streetTF.setEditable(true);
					cityTF.setEditable(true);
					stateTF.setEditable(true);
					zipcodeTF.setEditable(true);
					break;
					
				case 1:
					boatVINTF.setEditable(true);
					boatCustomerIDTF.setEditable(true);
					makeTF.setEditable(true);
					modelTF.setEditable(true);
					colorTF.setEditable(true);
					isPoweredBoatTF.setEditable(true);
					break;
					
				case 2:
					isPoweredSlipTF.setEditable(true);
					isLeasedTF.setEditable(true);
					isOccupiedTF.setEditable(true);
					break;
					
				case 3:
					leaseCustomerIDTF.setEditable(true);
					leaseVINTF.setEditable(true);
					leaseSlipIDTF.setEditable(true);
					leaseStartDateTF.setEditable(true);
					leaseEndDateTF.setEditable(true);
				}
			}

			// Update button is pressed
			if(event.getSource() == updateButton){
				updateButton.setVisible(false);
				int selection = tabbedPane.getSelectedIndex();
				// For each tab, create the respective object, populate it, and update the database
				switch(selection){
					case 0:
						System.out.println("Customer ID: " + customerIDTF.getText());
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
								db.updateBoat(Long.valueOf(customerIDTF.getText()), makeTF.getText(), modelTF.getText(), colorTF.getText(), status, Long.valueOf(boatVINTF.getText()));
							}
							else if(poweredBoatStatus.equalsIgnoreCase("no") || poweredBoatStatus.equalsIgnoreCase("n"))
							{
								status = false;
								db.updateBoat(Long.valueOf(customerIDTF.getText()), makeTF.getText(), modelTF.getText(), colorTF.getText(), status, Long.valueOf(boatVINTF.getText()));

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
							System.out.println(Long.valueOf(lease.getLeaseID())); //return primary key

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

						    
							db.updateLease(Long.valueOf(customerIDTF.getText()), Long.valueOf(leaseVINTF.getText()), Long.valueOf(slipIDTF.getText()),
									sqlStartDate, sqlEndDate, Long.valueOf(lease.getLeaseID()));
							
						}
					}

					// Delete button is pressed
					if(event.getSource() == deleteButton){
						int selection = tabbedPane.getSelectedIndex();
						switch(selection){
						case 0:
							db.deleteCustomer(Long.valueOf(customerIDTF.getText()));
							System.out.println("Customer Delete button");
							break;

						case 1:
							db.deleteBoat(Long.valueOf(boatVINTF.getText()));
							System.out.println("Boat Delete Button");
							break;

						case 2:
							db.deleteSlip(Long.valueOf(slipIDTF.getText()));
							System.out.println("Slip Delete button");

							break;
						case 3:
							db.deleteLease(db.GetLeaseID());
							System.out.println("Lease Delete button");
						}
					}
					
					// Clear button is pressed
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
							
							fnameTF.setEditable(true);
							lnameTF.setEditable(true);
							paymentTF.setEditable(true);
							phoneTF.setEditable(true);
							streetTF.setEditable(true);
							cityTF.setEditable(true);
							stateTF.setEditable(true);
							zipcodeTF.setEditable(true);
							break;
							
						case 1:
							boatVINTF.setText("");
							boatCustomerIDTF.setText("");
							makeTF.setText("");
							modelTF.setText("");
							colorTF.setText("");
							isPoweredBoatTF.setText("");

							boatVINTF.setEditable(true);
							boatCustomerIDTF.setEditable(true);
							makeTF.setEditable(true);
							modelTF.setEditable(true);
							colorTF.setEditable(true);
							isPoweredBoatTF.setEditable(true);
							break;
							
						case 2:
							slipIDTF.setText("");
							isPoweredSlipTF.setText("");
							isLeasedTF.setText("");
							isOccupiedTF.setText("");
							
							isPoweredSlipTF.setEditable(true);
							isLeasedTF.setEditable(true);
							isOccupiedTF.setEditable(true);
							break;
							
						case 3:
							leaseIDTF.setText("");
							leaseCustomerIDTF.setText("");
							leaseVINTF.setText("");
							leaseSlipIDTF.setText("");
							leaseStartDateTF.setValue(new Date());
							leaseEndDateTF.setValue(new Date());
							
							leaseCustomerIDTF.setEditable(true);
							leaseVINTF.setEditable(true);
							slipIDTF.setEditable(true);
							leaseStartDateTF.setEditable(true);
							leaseEndDateTF.setEditable(true);
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
						if(customerArrowIterator == 1){
							break;
						}
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
						if(boatArrowIterator == 1){
							break;
						}
						if(boatArrowIterator != 1){
							boatArrowIterator--;
						}

						// Set text fields with data
						customerIDTF.setText(boatResults[boatArrowIterator - 1].getCustomerID());
						boatVINTF.setText(boatResults[boatArrowIterator - 1].getVin());
						makeTF.setText(boatResults[boatArrowIterator - 1].getMake());
						modelTF.setText(boatResults[boatArrowIterator - 1].getModel());
						colorTF.setText(boatResults[boatArrowIterator - 1].getColor());
						isPoweredBoatTF.setText(boatResults[boatArrowIterator - 1].getIsPowered());
						break;
					case 2:
						// Prevent user from going out of range
						if(slipArrowIterator == 1){
							break;
						}
						if(slipArrowIterator != 1){
							slipArrowIterator--;
						}

						// Set text fields with data
						slipIDTF.setText(slipResults[slipArrowIterator - 1].getSlipID());
						isPoweredSlipTF.setText(slipResults[slipArrowIterator - 1].getIsPowered());
						isLeasedTF.setText(slipResults[slipArrowIterator - 1].getIsLeased());
						isOccupiedTF.setText(slipResults[slipArrowIterator - 1].getIsOccupied());
						break;
					case 3:
						// Prevent user from going out of range
						if(leaseArrowIterator == 1){
							break;
						}
						if(leaseArrowIterator != 1){
							leaseArrowIterator--;
						}

						// Set text fields with data
						customerIDTF.setText(leaseResults[leaseArrowIterator - 1].getCustomerID());
						leaseVINTF.setText(leaseResults[leaseArrowIterator - 1].getVin());
						slipIDTF.setText(leaseResults[leaseArrowIterator - 1].getSlipID());
						leaseStartDateTF.setText(leaseResults[leaseArrowIterator - 1].getLeaseStartDate().toString());
						leaseEndDateTF.setText(leaseResults[leaseArrowIterator - 1].getLeaseEndDate().toString());
						break;
				}
			}
			
			
			//Right arrow button is pressed
			if(event.getSource() == nextButton){
				// Get selected panel index
				int selection = tabbedPane.getSelectedIndex();
				System.out.println("Tab # " + selection);

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
						System.out.println("Out of range.");
						break;
					}

					// Increment iterator
					boatArrowIterator++;

					System.out.println("Iterator: " + boatArrowIterator);
					System.out.println("Results Length: " + boatResults.length);
					System.out.println(boatArrowIterator + " out of " + boatResults.length);

					// Set text fields with data
					customerIDTF.setText(boatResults[boatArrowIterator - 1].getCustomerID());
					boatVINTF.setText(boatResults[boatArrowIterator - 1].getVin());
					makeTF.setText(boatResults[boatArrowIterator - 1].getMake());
					modelTF.setText(boatResults[boatArrowIterator - 1].getModel());
					colorTF.setText(boatResults[boatArrowIterator - 1].getColor());
					isPoweredBoatTF.setText(boatResults[boatArrowIterator - 1].getIsPowered());
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
					isPoweredSlipTF.setText(slipResults[slipArrowIterator - 1].getIsPowered());
					isLeasedTF.setText(slipResults[slipArrowIterator - 1].getIsLeased());
					isOccupiedTF.setText(slipResults[slipArrowIterator - 1].getIsOccupied());
					break;
				case 3:
					// Prevent user from going out of range
					if(leaseArrowIterator == leaseResults.length || leaseResults.length == 0){
						break;
					}

					// Increment iterator
					leaseArrowIterator++;

					// Set text fields with data
					customerIDTF.setText(leaseResults[leaseArrowIterator - 1].getCustomerID());
					leaseVINTF.setText(leaseResults[leaseArrowIterator - 1].getVin());
					slipIDTF.setText(leaseResults[leaseArrowIterator - 1].getSlipID());
					leaseStartDateTF.setText(leaseResults[leaseArrowIterator - 1].getLeaseStartDate().toString()); 
					leaseEndDateTF.setText(leaseResults[leaseArrowIterator - 1].getLeaseEndDate().toString());
					break;
				}
			}
		}
	}

	public static void main(String[] args) {
		MarinaGUI gui = new MarinaGUI();
	}

}
