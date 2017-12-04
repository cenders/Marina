/**
 * This class performs the functions necessary to set get and information from the Boat object.
 * @author Collin Enders
 *
 */

public class Boat {
	private String vin,
				   customerID,
				   make,
				   model,
				   color,
				   isPowered;
	
	/**
	 * Default constructor
	 */
	public Boat(){
		setVin("");
		setCustomerID("");
		setMake("");
		setModel("");
		setColor("");
		setIsPowered("");
	}
	
	/**
	 * Sets information in the boat object
	 * @param make The make of the boat
	 * @param model The model of the boat
	 * @param color The color of the boat
	 * @param isPowered Whether the boat is powered or not
	 */
	public Boat(String vin, String customerID, String make, String model, String color, String isPowered){
		this.vin = vin;
		this.customerID = customerID;
		this.make = make;
		this.model = model;
		this.color = color;
		this.isPowered = isPowered;
	}
	
	/**
	 * @return the vin
	 */
	public String getVin() {
		return vin;
	}
	/**
	 * @param vin the vin to set
	 */
	public void setVin(String vin) {
		this.vin = vin;
	}
	
	/** 
	 * @return the customer ID
	 */
	public String getCustomerID(){
		return customerID;
	}
	/**
	 * @param customerID the customerID to set
	 */
	public void setCustomerID(String customerID){
		this.customerID = customerID;
	}
	/**
	 * @return the make
	 */
	public String getMake() {
		return make;
	}
	/**
	 * @param make the make to set
	 */
	public void setMake(String make) {
		this.make = make;
	}
	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}
	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}
	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * @return the isPowered
	 */
	public String getIsPowered() {
		return isPowered;
	}

	/**
	 * @param isPowered the isPowered to set
	 */
	public void setIsPowered(String isPowered) {
		this.isPowered = isPowered;
	}
	
	@Override
	public String toString(){
		return "VIN: " + getVin() + " Customer ID: " + getCustomerID() + " Make: " + getMake() + " Model: " + getModel() + " Color: " + getColor() + " Is Powered: " + getIsPowered();
	}
}
