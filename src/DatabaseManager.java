import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * This class manages all database related functions of the program
 */
public class DatabaseManager {
	static final String DATABASE_URL = "jdbc:ucanaccess://../Database/Marina.accdb";
	Connection connection = null;
	Statement statement = null;
	Statement selectAllCustomers = null;
	ResultSet resultSet = null;
	PreparedStatement selectCustomers = null;
	PreparedStatement selectBoats = null;
	PreparedStatement selectSlips = null;
	PreparedStatement selectLeases = null;

	PreparedStatement insertNewCustomer = null;
	PreparedStatement insertNewBoat = null;
	PreparedStatement insertNewSlip = null;
	PreparedStatement insertNewLease = null;

	PreparedStatement updateCustomerRecord = null;
	PreparedStatement updateBoatRecord = null;
	PreparedStatement updateSlipRecord = null;
	PreparedStatement updateLeaseRecord = null;

	PreparedStatement deleteCustomerRecord = null;
	PreparedStatement deleteBoatRecord = null;
	PreparedStatement deleteSlipRecord = null;
	PreparedStatement deleteLeaseRecord = null;
	PreparedStatement updateSlipAfterDeleteLease = null;
	PreparedStatement updateSlipAfterCreateLease = null;
	
	PreparedStatement getDayCount = null;
	
	PreparedStatement getSlipStatus = null; 

	long customer_id;
	long boat_vin;
	long slip_id;
	long lease_id;
	
	/**
	 * Creates a connection to the database
	 */
	public DatabaseManager(){
		try{
			// Establish connection to database
			connection = DriverManager.getConnection(DATABASE_URL);
			System.out.println("Database connection established.");

			// Create statement for querying database
			statement = connection.createStatement();
			System.out.println("Established statement");
		}
		catch(SQLException sqlex){
			JOptionPane.showMessageDialog(null, sqlex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Adds a customer to the database
	 * @param customer Customer object to insert
	 * @return result SQL return code
	 */
	public int addCustomer(Customer customer)
	{
		int result = 0;

		try
		{
			insertNewCustomer = connection.prepareStatement("INSERT INTO CUSTOMER (first_name, last_name, payment_info, phone_number,street_address, city, state, zip)"
															+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

			insertNewCustomer.setString(1,customer.getFirstName());
			insertNewCustomer.setString(2,customer.getLastName());
			insertNewCustomer.setString(3,customer.getPaymentInfo());
			insertNewCustomer.setString(4,customer.getPhoneNumber());
			insertNewCustomer.setString(5,customer.getStreetAddress());
			insertNewCustomer.setString(6,customer.getCity());
			insertNewCustomer.setString(7,customer.getState());
			insertNewCustomer.setString(8,customer.getZip());

			result = insertNewCustomer.executeUpdate();
			JOptionPane.showMessageDialog(null, "Successfully Added New Customer.");
			//get newly inserted record id
	        if (result == 0) {
	            throw new SQLException("Creating new customer failed, no rows affected.");
	        }
	        try (ResultSet generatedKeys = insertNewCustomer.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	               customer_id = generatedKeys.getLong(1);
	               //System.out.println(customer_id);
	            }
	            else {
	                throw new SQLException("Creating user failed, no ID obtained.");
	            }
	        }

		}
		catch (SQLException sqlex)
		{
			JOptionPane.showMessageDialog(null,sqlex.getMessage(),"Database Insert Failed",JOptionPane.ERROR_MESSAGE);
			result = 0;
		}

		return result;
	}

	/**
	 * Add a boat to the database
	 * @param customer_id The customer's ID #
	 * @param make The make of the boat
	 * @param model The model of the boat
	 * @param color The color of the boat
	 * @param isPowered Whether the boat is powered
	 * @return result SQL return code
	 */
	public int addBoat(long customer_id, String make, String model, String color, boolean isPowered)
	{
		int result = 0;

		try
		{
			insertNewBoat = connection.prepareStatement
					//("UPDATE Boat SET make = ? WHERE customer_id = ?");
					//("UPDATE Employee SET FirstName = '" + fname + "' WHERE EmployeeID = ' " + employeeID + "'");
					//("UPDATE Boat SET make = ?, model = ?, color = ?, is_powered_boat = ? WHERE customer_id = ?");
					("INSERT INTO BOAT (customer_id, make, model, color, is_powered_boat)"
														+ " VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);


			insertNewBoat.setLong(1,customer_id);
			insertNewBoat.setString(2,make);
			insertNewBoat.setString(3,model);
			insertNewBoat.setString(4,color);
			insertNewBoat.setBoolean(5,isPowered);

			result = insertNewBoat.executeUpdate();
			JOptionPane.showMessageDialog(null, "Successfully Added New Boat.");

			//get newly inserted record id
	        if (result == 0) {
	            throw new SQLException("Creating new boat failed, no rows affected.");
	        }
	        try (ResultSet generatedKeys = insertNewBoat.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	               boat_vin = generatedKeys.getLong(1);
	               //System.out.println(boat_vin);
	            }
	            else {
	                throw new SQLException("Creating user failed, no ID obtained.");
	            }
	        }
		}

		catch (SQLException sqlex)
		{
			JOptionPane.showMessageDialog(null,sqlex.getMessage(),"Database Insert Failed",JOptionPane.ERROR_MESSAGE);
			result = 0;
		}

		return result;
	}


	/**
	 * Add a slip to the database
	 * @param isPoweredSlip Whether the slip is powered
	 * @param isLeased Whether the slip is leased
	 * @param isOccupied Whether the slip is occupied
	 * @return result SQL return code
	 */
	 public int addSlip(boolean isPoweredSlip, boolean isLeased, boolean isOccupied)
		 {
		 	int result = 0;

		 	try
		 	{
		 		insertNewSlip = connection.prepareStatement("INSERT INTO SLIP (is_powered_slip, is_leased, is_occupied)"
		 														+ "VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

		 		insertNewSlip.setBoolean(1,isPoweredSlip);
		 		insertNewSlip.setBoolean(2,isLeased);
		 		insertNewSlip.setBoolean(3,isOccupied);

		 		result = insertNewSlip.executeUpdate();
				JOptionPane.showMessageDialog(null, "Successfully Added New Slip.");
		 		//System.out.println(result);

		 		//get newly inserted record id
		 				if (result == 0) {
		 						throw new SQLException("Creating new slip failed, no rows affected.");
		 				}
		 				try (ResultSet generatedKeys = insertNewSlip.getGeneratedKeys()) {
		 						if (generatedKeys.next()) {
		 							 slip_id = generatedKeys.getLong(1);
		 							 //System.out.println(slip_id);
		 						}
		 						else {
		 								throw new SQLException("Creating slip failed, no ID obtained.");
		 						}
		 				}
		 	}


		 	catch (SQLException sqlex)
		 	{
		 		JOptionPane.showMessageDialog(null,sqlex.getMessage(),"Database Insert Failed",JOptionPane.ERROR_MESSAGE);
		 		result = 0;
		 	}

		 	return result;
		 }

	//add new lease
		 public int addLease(Long customer_id, Long vin, Long slip_id, Date leaseStartDate, Date leaseEndDate)
			 {
			 	int result = 0;

			 	try
			 	{
			 		insertNewLease = connection.prepareStatement("INSERT INTO LEASE (customer_id, vin, slip_id, lease_start_date, lease_end_date)"
			 														+ "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

			 		insertNewLease.setLong(1,customer_id);
			 		insertNewLease.setLong(2,vin);
			 		insertNewLease.setLong(3,slip_id);
			 		insertNewLease.setDate(4, leaseStartDate);
			 		insertNewLease.setDate(5,leaseEndDate);

			 		result = insertNewLease.executeUpdate();
					JOptionPane.showMessageDialog(null, "Successfully Added New Lease.");
			 		//System.out.println(result);

			 		//get newly inserted record id
			 				if (result == 0) {
			 						throw new SQLException("Creating new lease failed, no rows affected.");
			 				}
			 				try (ResultSet generatedKeys = insertNewLease.getGeneratedKeys()) {
			 						if (generatedKeys.next()) {
			 							 lease_id = generatedKeys.getLong(1);
			 							 //System.out.println("Lease ID is " + lease_id);
			 						}
			 						else {
			 								throw new SQLException("Creating lease failed, no ID obtained.");
			 						}
			 				}
			 	}
			 	catch (SQLException sqlex)
			 	{
			 		JOptionPane.showMessageDialog(null,sqlex.getMessage(),"Database Insert Failed",JOptionPane.ERROR_MESSAGE);
			 		result = 0;
			 	}
			 	return result;
			 }

	/**
	* Find the customers matching a term
	* @param term The term to search for
	* @return Customer[] An array of Customer objects
	*/
	public Customer[] findCustomers(String term){
		try{
			// Create search query to return all data associated with a string/substring
			// Example: %tree% will return objects for "trees", "street", and "stree"
			selectCustomers = connection.prepareStatement("SELECT * FROM Customer WHERE "
							+ "customer_id LIKE ? OR "
							+ "first_name LIKE ? OR "
							+ "last_name LIKE ? OR "
							+ "payment_info LIKE ? OR "
							+ "phone_number LIKE ? OR "
							+ "street_address LIKE ? OR "
							+ "city LIKE ? OR "
							+ "state LIKE ? OR "
							+ "zip LIKE ?",
							ResultSet.TYPE_SCROLL_INSENSITIVE,
						    ResultSet.CONCUR_READ_ONLY);
			// Set the term values
			selectCustomers.setString(1, "%" + term + "%");
			selectCustomers.setString(2, "%" + term + "%");
			selectCustomers.setString(3, "%" + term + "%");
			selectCustomers.setString(4, "%" + term + "%");
			selectCustomers.setString(5, "%" + term + "%");
			selectCustomers.setString(6, "%" + term + "%");
			selectCustomers.setString(7, "%" + term + "%");
			selectCustomers.setString(8, "%" + term + "%");
			selectCustomers.setString(9, "%" + term + "%");
			resultSet = selectCustomers.executeQuery();

			// Calculate the number of rows in the ResultSet
			int rowCount = getRowCount(resultSet);

			// Create array of Customer objects
			Customer[] results = new Customer[rowCount];

			// Iterate through the ResultSet and create the full Customer object
			for(int i = 0; i < rowCount; i++){
				// Get next result
				resultSet.next();

				// Instantiate new Customer object
				results[i] = new Customer();

				// Add values to Customer object
				results[i].setCustomerID(Integer.toString(resultSet.getInt(1)));
				results[i].setFirstName(resultSet.getString(2));
				results[i].setLastName(resultSet.getString(3));
				results[i].setPaymentInfo(resultSet.getString(4));
				results[i].setPhoneNumber(resultSet.getString(5));
				results[i].setStreetAddress(resultSet.getString(6));
				results[i].setCity(resultSet.getString(7));
				results[i].setState(resultSet.getString(8));
				results[i].setZip(resultSet.getString(9));
			}
			return results;
		}
		catch(SQLException sqlex){
			JOptionPane.showMessageDialog(null, sqlex.getMessage(), "Database Search Failed", JOptionPane.ERROR_MESSAGE);
			System.out.println(sqlex.getLocalizedMessage());
			return new Customer[0];
		}
	}

	/**
	 * Find the boats matching a term
	 * @param term The term to search for
	 * @return Boat[] An array of Boat objects
	 */
	public Boat[] findBoats(String term){
		try{
			// Create search query to return all data associated with a string/substring
			// Example: %tree% will return objects for "trees", "street", and "stree"
			selectBoats = connection.prepareStatement("SELECT * FROM Boat WHERE "
							+ "vin LIKE ? OR "
							+ "customer_id LIKE ? OR "
							+ "make LIKE ? OR "
							+ "model LIKE ? OR "
							+ "color LIKE ? OR "
							+ "is_powered_boat LIKE ?",
							ResultSet.TYPE_SCROLL_INSENSITIVE,
						    ResultSet.CONCUR_READ_ONLY);
			// Set the term values
			selectBoats.setString(1, "%" + term + "%");
			selectBoats.setString(2, "%" + term + "%");
			selectBoats.setString(3, "%" + term + "%");
			selectBoats.setString(4, "%" + term + "%");
			selectBoats.setString(5, "%" + term + "%");
			selectBoats.setString(6, "%" + term + "%");
			resultSet = selectBoats.executeQuery();

			// Calculate the number of rows in the ResultSet
			int rowCount = getRowCount(resultSet);

			// Create array of Boat objects
			Boat[] results = new Boat[rowCount];

			// Iterate through the ResultSet and create the full Boat object
			for(int i = 0; i < rowCount; i++){
				// Get next result
				resultSet.next();

				// Instantiate new Boat object
				results[i] = new Boat();
				//System.out.println(resultSet.getString(3));

				// Add values to Boat object
				results[i].setVin(Integer.toString(resultSet.getInt(1)));
				results[i].setMake(resultSet.getString(2));
				results[i].setModel(resultSet.getString(3));
				results[i].setColor(resultSet.getString(4));
				results[i].setCustomerID(resultSet.getString(5));
				results[i].setIsPowered(resultSet.getString(6));
			}
			return results;
		}
		catch(SQLException sqlex){
			JOptionPane.showMessageDialog(null, sqlex.getMessage(), "Database Search Failed", JOptionPane.ERROR_MESSAGE);
			System.out.println(sqlex.getLocalizedMessage());
			return new Boat[0];
		}
	}

	/**
	 * Find the slips matching a term
	 * @param term The term to search for
	 * @return Slip[] An array of Slip objects
	 */
	public Slip[] findSlips(String term){
		try{
			// Create search query to return all data associated with a string/substring
			// Example: %tree% will return objects for "trees", "street", and "stree"
			selectSlips = connection.prepareStatement("SELECT * FROM Slip WHERE "
							+ "slip_id LIKE ? OR "
							+ "is_powered_slip LIKE ? OR "
							+ "is_leased LIKE ? OR "
							+ "is_occupied LIKE ?",
							ResultSet.TYPE_SCROLL_INSENSITIVE,
						    ResultSet.CONCUR_READ_ONLY);
			// Set the term values
			selectSlips.setString(1, "%" + term + "%");
			selectSlips.setString(2, "%" + term + "%");
			selectSlips.setString(3, "%" + term + "%");
			selectSlips.setString(4, "%" + term + "%");

			resultSet = selectSlips.executeQuery();

			// Calculate the number of rows in the ResultSet
			int rowCount = getRowCount(resultSet);

			// Create array of Slip objects
			Slip[] results = new Slip[rowCount];

			// Iterate through the ResultSet and create the full Slip object
			for(int i = 0; i < rowCount; i++){
				// Get next result
				resultSet.next();

				// Instantiate new Slip object
				results[i] = new Slip();

				// Add values to Slip object
				results[i].setSlipID(Integer.toString(resultSet.getInt(1)));
				results[i].setIsLeased(resultSet.getString(2));
				results[i].setIsOccupied(resultSet.getString(3));
				results[i].setIsPowered(resultSet.getString(4));
			}
			return results;
		}
		catch(SQLException sqlex){
			JOptionPane.showMessageDialog(null, sqlex.getMessage(), "Database Search Failed", JOptionPane.ERROR_MESSAGE);
			System.out.println(sqlex.getLocalizedMessage());
			return new Slip[0];
		}
	}
	
	/**
	 * Find the leases matching a term
	 * @param term The term to search for
	 * @return Lease[] An array of Lease objects
	 */
	public Lease[] findLeases(String term){
		try{
			// Create search query to return all data associated with a string/substring
			// Example: %tree% will return objects for "trees", "street", and "stree"
			selectLeases = connection.prepareStatement("SELECT * FROM Lease WHERE "
							+ "lease_id LIKE ? OR "
							+ "customer_id LIKE ? OR "
							+ "vin LIKE ? OR "
							+ "slip_id LIKE ? OR "
							+ "lease_start_date LIKE ? OR "
							+ "lease_end_date LIKE ?",
							ResultSet.TYPE_SCROLL_INSENSITIVE,
						    ResultSet.CONCUR_READ_ONLY);
			// Set the term values
			selectLeases.setString(1, "%" + term + "%");
			selectLeases.setString(2, "%" + term + "%");
			selectLeases.setString(3, "%" + term + "%");
			selectLeases.setString(4, "%" + term + "%");
			selectLeases.setString(5, "%" + term + "%");
			selectLeases.setString(6, "%" + term + "%");
			resultSet = selectLeases.executeQuery();

			// Calculate the number of rows in the ResultSet
			int rowCount = getRowCount(resultSet);

			// Create array of Lease objects
			Lease[] results = new Lease[rowCount];

			// Iterate through the ResultSet and create the full Lease object
			for(int i = 0; i < rowCount; i++){
				// Get next result
				resultSet.next();

				// Instantiate new Lease object
				results[i] = new Lease();

				// Add values to Lease object
				results[i].setLeaseID(Integer.toString(resultSet.getInt(1)));
				results[i].setSlipID(Integer.toString(resultSet.getInt(2)));
				results[i].setVin(Integer.toString(resultSet.getInt(3)));
				results[i].setCustomerID(Integer.toString(resultSet.getInt(4)));
				results[i].setLeaseStartDate(resultSet.getString(5));
				results[i].setLeaseEndDate(resultSet.getString(6));
				
//				System.out.println("Lease ID is " + resultSet.getInt(1));
//				System.out.println("Slip ID is " + resultSet.getInt(2));
//				System.out.println("Vin is " + resultSet.getInt(3));
//				System.out.println("Customer ID is " + resultSet.getInt(4));
				
				lease_id = Long.valueOf(resultSet.getInt(1));
				slip_id = Long.valueOf(resultSet.getInt(2));
				boat_vin = Long.valueOf(resultSet.getInt(3));
			}
			return results;
		}
		catch(SQLException sqlex){
			JOptionPane.showMessageDialog(null, sqlex.getMessage(), "Database Search Failed", JOptionPane.ERROR_MESSAGE);
			System.out.println(sqlex.getLocalizedMessage());
			return new Lease[0];
		}
	}

	/**
	 * Update the customer information
	 * @param customer The populated customer object that needs to be updated in the database
	 */
	public void updateCustomer(Customer customer){
		try {
			updateCustomerRecord = connection.prepareStatement("UPDATE Customer SET first_name = ?, last_name = ?, payment_info = ?, "
					+ "phone_number = ?, street_address = ?, city = ?, state = ?, zip =? WHERE customer_id = ?");

			updateCustomerRecord.setString(1,customer.getFirstName());
			updateCustomerRecord.setString(2,customer.getLastName());
			updateCustomerRecord.setString(3,customer.getPaymentInfo());
			updateCustomerRecord.setString(4,customer.getPhoneNumber());
			updateCustomerRecord.setString(5,customer.getStreetAddress());
			updateCustomerRecord.setString(6,customer.getCity());
			updateCustomerRecord.setString(7,customer.getState());
			updateCustomerRecord.setString(8,customer.getZip());
			updateCustomerRecord.setLong(9,Long.valueOf(customer.getCustomerID()));
			updateCustomerRecord.executeUpdate();
			JOptionPane.showMessageDialog(null, "Successfully Updated Customer Record.");
			//System.out.println("Update Complete");
		} catch (SQLException sqlex) {
			JOptionPane.showMessageDialog(null, sqlex.getMessage(), "Update Customer Information Failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Update information in a boat
	 * @param customerID The customer's ID
	 * @param make The make of the boat
	 * @param model The model of the boat
	 * @param color The color of the boat
	 * @param isPoweredBoat Whether the boat is powered
	 * @param vin The VIN # of the boat
	 */
	public void updateBoat(Long customerID, String make, String model, String color, Boolean isPoweredBoat, Long vin){
		try {
			updateBoatRecord = connection.prepareStatement("UPDATE Boat SET customer_id = ?, make = ?, model = ?, "
					+ "color = ?, is_powered_boat = ? WHERE vin = ?");

			updateBoatRecord.setLong(1,customerID);
			updateBoatRecord.setString(2,make);
			updateBoatRecord.setString(3,model);
			updateBoatRecord.setString(4,color);
			updateBoatRecord.setBoolean(5,isPoweredBoat);
			updateBoatRecord.setLong(6,vin);

			updateBoatRecord.executeUpdate();
			JOptionPane.showMessageDialog(null, "Successfully Updated Boat Record.");

		} catch (SQLException sqlex) {
			JOptionPane.showMessageDialog(null, sqlex.getMessage(), "Update Boat Information Failed", JOptionPane.ERROR_MESSAGE);		}
	}
	
	/**
	 * Update information in a slip
	 * @param isPoweredSlip Whether the slip is for a powerboat
	 * @param isLeased Whether the slip is leased
	 * @param isOccupied Whether the slip is currently occupied
	 * @param slipID The ID of the slip
	 */
	public void updateSlip(Boolean isPoweredSlip, Boolean isLeased, Boolean isOccupied, Long slipID){
		try {
			updateSlipRecord = connection.prepareStatement("UPDATE Slip SET is_powered_slip = ?, is_leased = ?, is_occupied = ? WHERE slip_id = ?");

			updateSlipRecord.setBoolean(1, isPoweredSlip); //this column is not updating????
			updateSlipRecord.setBoolean(2, isLeased);
			updateSlipRecord.setBoolean(3, isOccupied);
			updateSlipRecord.setLong(4, slipID);

			updateSlipRecord.executeUpdate();
			JOptionPane.showMessageDialog(null, "Successfully Updated Slip Record.");

			}
		catch (SQLException sqlex) {
			JOptionPane.showMessageDialog(null, sqlex.getMessage(), "Update Boat Information Failed", JOptionPane.ERROR_MESSAGE);
			}
		}


	/**
	 * Update information in a lease
	 * @param customerID The Customer's ID #
	 * @param vin The boat's VIN #
	 * @param slipID The slip's ID
	 * @param leaseStartDate The start date of the lease
	 * @param leaseEndDate The end date of the lease
	 * @param leaseID The ID of the lease
	 */
	public void updateLease(Long customerID, Long vin, Long slipID, Date leaseStartDate, Date leaseEndDate, Long leaseID){
		try {
			updateLeaseRecord = connection.prepareStatement("UPDATE Lease SET customer_id = ?, vin = ?, slip_id = ?, lease_start_date = ?, lease_end_date = ? WHERE lease_id = ?");

			updateLeaseRecord.setLong(1, customerID);
			updateLeaseRecord.setLong(2, vin);
			updateLeaseRecord.setLong(3, slipID);
			updateLeaseRecord.setDate(4, leaseStartDate);
			updateLeaseRecord.setDate(5, leaseEndDate);
			updateLeaseRecord.setLong(6, leaseID);

			updateLeaseRecord.executeUpdate();
			JOptionPane.showMessageDialog(null, "Successfully Updated Lease Record.");

			}
		catch (SQLException sqlex) {
			JOptionPane.showMessageDialog(null, sqlex.getMessage(), "Update Boat Information Failed", JOptionPane.ERROR_MESSAGE);
			}
		}

	/**
	 * Delete a customer from the database
	 * @param customerID The Customer ID of the customer to be deleted
	 */
	public void deleteCustomer(Long customerID)
	{
		try {
			deleteCustomerRecord = connection.prepareStatement("DELETE FROM Customer WHERE customer_id = ?");
			deleteCustomerRecord.setLong(1,customerID);
			deleteCustomerRecord.executeUpdate();
			JOptionPane.showMessageDialog(null, "Successfully Deleted Customer.");

			//System.out.println("Delete Complete");
		} catch (SQLException sqlex) {
			JOptionPane.showMessageDialog(null, sqlex.getMessage(), "Delete Customer Failed", JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Delete a boat from the database
	 * @param vin The VIN # of the boat to be deleted
	 */
	public void deleteBoat(Long vin)
	{
		try {
			deleteBoatRecord = connection.prepareStatement("DELETE FROM Boat WHERE vin = ?");
			deleteBoatRecord.setLong(1,vin);
			deleteBoatRecord.executeUpdate();
			JOptionPane.showMessageDialog(null, "Successfully Deleted Boat.");
			//System.out.println("Delete Boat Complete");
		} catch (SQLException sqlex) {
			JOptionPane.showMessageDialog(null, sqlex.getMessage(), "Delete Customer Failed", JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Delete a slip from the database
	 * @param slipID The ID of the slip to be deleted
	 */
	public void deleteSlip(Long slipID)
	{
		try {
			deleteSlipRecord = connection.prepareStatement("DELETE FROM Slip WHERE slip_id = ?");
			deleteSlipRecord.setLong(1,slipID);
			//System.out.println(slipID);
			deleteSlipRecord.executeUpdate();
			JOptionPane.showMessageDialog(null, "Successfully Deleted Slip.");
			//System.out.println("Delete SLip Complete");
		} catch (SQLException sqlex) {
			JOptionPane.showMessageDialog(null, sqlex.getMessage(), "Delete SLip Failed", JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Delete a lease from the database
	 * @param leaseID The ID of the lease to be deleted
	 */
	public void deleteLease(Long leaseID)
	{
		try {
			deleteLeaseRecord = connection.prepareStatement("DELETE FROM Lease WHERE lease_id = ?");
			deleteLeaseRecord.setLong(1,leaseID);
			//System.out.println(leaseID);
			deleteLeaseRecord.executeUpdate();			
			
			JOptionPane.showMessageDialog(null, "Successfully Deleted Lease.");
			//System.out.println("Delete Lease Complete");
		} catch (SQLException sqlex) {
			JOptionPane.showMessageDialog(null, sqlex.getMessage(), "Delete Lease Failed", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	//restore slip status to be not leased/occupied
	public void restoreSlipStatus(Long slipID){
		try {
			updateSlipAfterDeleteLease = connection.prepareStatement("UPDATE Slip SET is_leased = ?, is_occupied = ? WHERE slip_id = ?");

			updateSlipAfterDeleteLease.setBoolean(1, false);
			updateSlipAfterDeleteLease.setBoolean(2, false);
			updateSlipAfterDeleteLease.setLong(3, slipID);

			updateSlipAfterDeleteLease.executeUpdate();
			JOptionPane.showMessageDialog(null, "Successfully Restore Slip Status.");

			}
		catch (SQLException sqlex) {
			JOptionPane.showMessageDialog(null, sqlex.getMessage(), "Restore Slip Status Failed", JOptionPane.ERROR_MESSAGE);
			}
		}
	
	//renew slip status to be leased and occupied 
	public void renewSlipStatus(Long slipID){
		try {
			updateSlipAfterCreateLease = connection.prepareStatement("UPDATE Slip SET is_leased = ?, is_occupied = ? WHERE slip_id = ?");

			updateSlipAfterCreateLease.setBoolean(1, true);
			updateSlipAfterCreateLease.setBoolean(2, true);
			updateSlipAfterCreateLease.setLong(3, slipID);

			updateSlipAfterCreateLease.executeUpdate();
			JOptionPane.showMessageDialog(null, "Successfully Updated Slip Status.");

			}
		catch (SQLException sqlex) {
			JOptionPane.showMessageDialog(null, sqlex.getMessage(), "Update Slip Status Failed", JOptionPane.ERROR_MESSAGE);
			}
		}
	
	/**
	 * Calculate the amount of days in a lease
	 * @param slipID The ID of the slip
	 * @return count The amount of days
	 */
	public int getDayCount(Long leaseID){
		int count = 0;
		try {
			getDayCount = connection.prepareStatement("SELECT DateDiff('d', [lease_start_date], [lease_end_date]) AS Days FROM Lease WHERE lease_id = ?;",
					ResultSet.TYPE_SCROLL_INSENSITIVE,
				    ResultSet.CONCUR_READ_ONLY);
			getDayCount.setLong(1, leaseID);
			
			resultSet = getDayCount.executeQuery();
			if(resultSet == null){
				//System.out.println("RS is null");
				return 0;
			}
			resultSet.next();
			count = resultSet.getInt(1);
		} catch (SQLException sqlex) {
			JOptionPane.showMessageDialog(null, sqlex.getMessage(), "Couldn't get day count", JOptionPane.ERROR_MESSAGE);
		}
		return count;
	}

	/**
	 * Calculate how many rows are in a ResultSet
	 * @param rs ResultSet to calculate rows in
	 * @return size The amount of rows
	 */
	public int getRowCount(ResultSet rs){
		int size = 0;
		try {
			rs.last();
			size = resultSet.getRow();
			rs.beforeFirst();
		}
		catch (SQLException sqlex) {
			sqlex.printStackTrace();
			return 0;
		}
		return size;
	}

	public void slipStatus()
	{
		try {
			getSlipStatus = connection.prepareStatement("SELECT * FROM Slip");

			 ResultSet rs = getSlipStatus.executeQuery();
			
			 // It creates and displays the table
		    JTable table = new JTable(buildTableModel(rs));

		    // Closes the Connection
		    JOptionPane.showMessageDialog(null, new JScrollPane(table), "Slip Status", JOptionPane.INFORMATION_MESSAGE);

		} catch (SQLException sqlex) {
			JOptionPane.showMessageDialog(null, sqlex.getMessage(), "Obtained Slip Status Failed", JOptionPane.ERROR_MESSAGE);
		}

	}


	public static DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	   
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);

	}


	public long GetCustomerID()
	{ return customer_id;}

	public long GetBoatVin()
	{ return boat_vin;}

	public long GetSlipID()
	{ return slip_id;}

	public long GetLeaseID()
	{ return lease_id;}

}
