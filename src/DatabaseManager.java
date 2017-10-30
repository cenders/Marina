import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

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
	
	public ResultSet findCustomers(){
		try{
			selectCustomers = connection.prepareStatement("SELECT * FROM CUSTOMER");
			resultSet = selectCustomers.executeQuery();
			return resultSet;
		}
		catch(SQLException sqlex){
			JOptionPane.showMessageDialog(null, sqlex.getMessage(), "Database Search Failed", JOptionPane.ERROR_MESSAGE);
			return resultSet;
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
}
