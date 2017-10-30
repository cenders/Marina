/**
 * This class performs the functions necessary to set get and information from the Boat object.
 * @author Collin Enders
 *
 */

public class Boat {
	private String vin,
				   make,
				   model,
				   color,
				   leaseStartDate,
				   leaseEndDate;
	public Boat(){
		setVin("");
		setMake("");
		setModel("");
		setColor("");
		setLeaseStartDate("");
		setLeaseEndDate("");
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
	 * @return the leaseStartDate
	 */
	public String getLeaseStartDate() {
		return leaseStartDate;
	}
	/**
	 * @param leaseStartDate the leaseStartDate to set
	 */
	public void setLeaseStartDate(String leaseStartDate) {
		this.leaseStartDate = leaseStartDate;
	}
	/**
	 * @return the leaseEndDate
	 */
	public String getLeaseEndDate() {
		return leaseEndDate;
	}
	/**
	 * @param leaseEndDate the leaseEndDate to set
	 */
	public void setLeaseEndDate(String leaseEndDate) {
		this.leaseEndDate = leaseEndDate;
	}
}
