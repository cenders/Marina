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
	
	public void findAllCustomers(){
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Customer");
			while(rs.next())
				System.out.println(rs.getInt(1) + ' ' + rs.getString(2) + ' ' + rs.getString(3));
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public DefaultTableModel findCustomers(String term){
		try{
			System.out.println("Searching customers");
			if(term == null){
				selectCustomers = connection.prepareStatement("SELECT * FROM CUSTOMER");
				System.out.println("Wildcard");
			}
			else{
				selectCustomers = connection.prepareStatement("SELECT * FROM CUSTOMER WHERE customer_id LIKE %"
															  + term + "% OR first_name LIKE %"
															  + term + "% OR last_name LIKE %"
															  + term + "% OR payment_info_name LIKE %"
															  + term + "% OR phone_number LIKE %"
															  + term + "% OR street_address LIKE %"
															  + term + "% OR city LIKE %"
															  + term + "% OR state LIKE %"
															  + term + "% OR zip LIKE %"
															  + term + "%");
				System.out.println("LIKE");
			}
			resultSet = selectCustomers.executeQuery();
			resultSet.next();
			System.out.println("Query executed");
			
			int rowCount = getRowCount(resultSet);
			String[] customerColumnNames = {"Customer ID", "First Name", "Last Name", "Payment Info", "Phone Number", "Street Address", "City", "State", "ZIP Code"};
			Object[] tableData = new Object[9];		
			DefaultTableModel model = new DefaultTableModel(customerColumnNames, 0);
			
			System.out.println("Row Count:" + rowCount);
			
			for(int i = 0; i < rowCount; i++){
				tableData[0] = resultSet.getInt(1);
				tableData[1] = resultSet.getString(2);
				tableData[2] = resultSet.getString(3);
				tableData[3] = resultSet.getString(4);
				tableData[4] = resultSet.getString(5);
				tableData[5] = resultSet.getString(6);
				tableData[6] = resultSet.getString(7);
				tableData[7] = resultSet.getString(8);
				tableData[8] = resultSet.getString(9);
				
				//model.addRow(tableData);
			}
			
			return model;
		}
		catch(SQLException sqlex){
			JOptionPane.showMessageDialog(null, sqlex.getMessage(), "Database Search Failed", JOptionPane.ERROR_MESSAGE);
			return new DefaultTableModel();
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
		int i = 0;
		
		try {
			while(rs.next()){
				System.out.println("Count: " + i);
				if(rs.isAfterLast() == true)
					System.out.println("After Last");
				i++;
			}
			rs.beforeFirst();
			System.out.println("i: " + i);
			System.out.println("Cursor at first");
		} 
		catch (SQLException sqlex) {
			System.out.println(sqlex);
		}
		
		return i;
	}
	
	/*
	private int getRowCount(String table){
		try{
			selectCount = connection.prepareStatement("SELECT COUNT() FROM " + table);
			resultSet = selectCount.executeQuery();
			return resultSet.getInt(columnIndex);
		}
		catch(SQLException sqlex){
			JOptionPane.showMessageDialog(null, sqlex.getMessage(), "Database Search Failed", JOptionPane.ERROR_MESSAGE);
			return resultSet;
		}
	}
	*/
}
