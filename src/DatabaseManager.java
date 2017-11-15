import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
	
	public Customer[] findCustomers(String term){
		try{
			// Create search query to return all data associated with a string/substring
			// Example: %tree% will return objects for "trees", "street", and "stree"
			selectCustomers = connection.prepareStatement("SELECT * FROM Customer WHERE "
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
	
	public ResultSet findBoats(){
		try{
			selectBoats = connection.prepareStatement("SELECT * FROM BOAT");
			resultSet = selectBoats.executeQuery();
			return resultSet;
		}
		catch(SQLException sqlex){
			JOptionPane.showMessageDialog(null, sqlex.getMessage(), "Database Search Failed", JOptionPane.ERROR_MESSAGE);
			return resultSet;
		}
	}
	
	public ResultSet findSlips(){
		try{
			selectSlips = connection.prepareStatement("SELECT * FROM SLIP");
			resultSet = selectSlips.executeQuery();
			return resultSet;
		}
		catch(SQLException sqlex){
			JOptionPane.showMessageDialog(null, sqlex.getMessage(), "Database Search Failed", JOptionPane.ERROR_MESSAGE);
			return resultSet;
		}
	}
	
	public ResultSet findLeases(){
		try{
			selectLeases = connection.prepareStatement("SELECT * FROM LEASE");
			resultSet = selectLeases.executeQuery();
			return resultSet;
		}
		catch(SQLException sqlex){
			JOptionPane.showMessageDialog(null, sqlex.getMessage(), "Database Search Failed", JOptionPane.ERROR_MESSAGE);
			return resultSet;
		}
	}
	
	private int getRowCount(ResultSet rs){
		int size = 0;
		try {
			rs.last();
			size = resultSet.getRow();
			rs.beforeFirst();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return size;
	}
}
