import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class DatabaseManager {
	static final String DATABASE_URL = "jdbc:ucanaccess:///Users/bai/Documents/workspace/NewMarina/src/Database/Marina.accdb";
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
	
	long customer_id;
	
	
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
	
	public int addCustomer(String fname, String lname, String payment, String phone, String street, String city, String state, String zip)
	{
		int result = 0;
		
		try
		{
			insertNewCustomer = connection.prepareStatement("INSERT INTO CUSTOMER (first_name, last_name, payment_info, phone_number,street_address, city, state, zip)" 
															+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS); 
		
			insertNewCustomer.setString(1,fname);
			insertNewCustomer.setString(2,lname);
			insertNewCustomer.setString(3,payment);
			insertNewCustomer.setString(4,phone);
			insertNewCustomer.setString(5,street);
			insertNewCustomer.setString(6,city);
			insertNewCustomer.setString(7,state);
			insertNewCustomer.setString(8,zip);
						
			result = insertNewCustomer.executeUpdate();
			
			//get newly inserted record id
	        if (result == 0) {
	            throw new SQLException("Creating new customer failed, no rows affected.");
	        }
	        try (ResultSet generatedKeys = insertNewCustomer.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	               customer_id = generatedKeys.getLong(1); ///////////////////////////////////////
	               System.out.println(customer_id);
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
	
	public int addBoat(String make, String model, String color, boolean isPowered)
	{
		int result = 0;
		
		try
		{
			insertNewBoat = connection.prepareStatement("INSERT INTO BOAT (customer_id, make, model, color, is_powered_boat)" 
															+ "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS); 
		
			insertNewBoat.setLong(1,customer_id);
			insertNewBoat.setString(2,make);
			insertNewBoat.setString(3,model);
			insertNewBoat.setString(4,color);
			insertNewBoat.setBoolean(5,isPowered);
						
			result = insertNewBoat.executeUpdate();
			System.out.println(result); 
		}
		catch (SQLException sqlex)
		{
			JOptionPane.showMessageDialog(null,sqlex.getMessage(),"Database Insert Failed",JOptionPane.ERROR_MESSAGE);
			result = 0;
		}
		
		return result;
	}
	
	
	
	
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
				System.out.println(resultSet.getString(3));
				
				// Add values to Boat object
				results[i].setVin(resultSet.getString(1));
				results[i].setCustomerID(resultSet.getString(2));
				results[i].setMake(resultSet.getString(3));
				results[i].setModel(resultSet.getString(4));
				results[i].setColor(resultSet.getString(5));
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
				results[i].setSlipID(Integer.toString(1));
				results[i].setIsPowered(Integer.toString(2));
				results[i].setIsLeased(Integer.toString(3));
				results[i].setIsOccupied(Integer.toString(4));
			}
			return results;
		}
		catch(SQLException sqlex){
			JOptionPane.showMessageDialog(null, sqlex.getMessage(), "Database Search Failed", JOptionPane.ERROR_MESSAGE);
			System.out.println(sqlex.getLocalizedMessage());
			return new Slip[0];
		}
	}
	
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
				results[i].setCustomerID(Integer.toString(resultSet.getInt(2)));
				results[i].setVin(Integer.toString(resultSet.getInt(3)));
				results[i].setSlipID(Integer.toString(resultSet.getInt(4)));
				results[i].setLeaseStartDate(resultSet.getString(5));
				results[i].setLeaseEndDate(resultSet.getString(6));
			}
			return results;
		}
		catch(SQLException sqlex){
			JOptionPane.showMessageDialog(null, sqlex.getMessage(), "Database Search Failed", JOptionPane.ERROR_MESSAGE);
			System.out.println(sqlex.getLocalizedMessage());
			return new Lease[0];
		}
	}
	
	public void updateCustomer(Customer cust){
		
	}
	
	public void updateBoat(Boat boat){
		
	}
	
	public void updateSlip(Slip slip){
		
	}
	
	public void updateLease(Lease lease){
		
	}
	
	private int getRowCount(ResultSet rs){
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
	
	public long GetCustomerID()
	{ return customer_id;}
}
