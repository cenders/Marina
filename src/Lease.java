import java.sql.Timestamp;

public class Lease {
	private String leaseID,
				   customerID,
				   vin,
				   slipID;
	Timestamp leaseEndDate;
	Timestamp leaseStartDate;
	
	/**
	 * Default constructor
	 */
	public Lease(){
		setLeaseID("");
		setCustomerID("");
		setVin("");
		setSlipID("");
		setLeaseStartDate(leaseStartDate);
		setLeaseEndDate(leaseEndDate);
	}
	
	/**
	 * Sets data in the slip object
	 * @param lsd The start date of the lease
	 * @param led The end date of the lease
	 */
	public Lease(String leaseID, String customerID, String vin, String slipID, Timestamp lsd, Timestamp led){
		setLeaseID(leaseID);
		setCustomerID(customerID);
		setVin(vin);
		setSlipID(slipID);
		setLeaseStartDate(lsd);
		setLeaseEndDate(led);
	 }

	/**
	 * @return the leaseID
	 */
	public String getLeaseID() {
		return leaseID;
	}

	/**
	 * @param leaseID the leaseID to set
	 */
	public void setLeaseID(String leaseID) {
		this.leaseID = leaseID;
	}

	/**
	 * @return the customerID
	 */
	public String getCustomerID() {
		return customerID;
	}

	/**
	 * @param customerID the customerID to set
	 */
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
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
	 * @return the slipID
	 */
	public String getSlipID() {
		return slipID;
	}

	/**
	 * @param slipID the slipID to set
	 */
	public void setSlipID(String slipID) {
		this.slipID = slipID;
	}

	/**
	 * @return the leaseStartDate
	 */
	public Timestamp getLeaseStartDate() {
		return leaseStartDate;
	}

	/**
	 * @param lsd the leaseStartDate to set
	 */
	public void setLeaseStartDate(Timestamp lsd) {
		this.leaseStartDate = lsd;
	}

	/**
	 * @return the leaseEndDate
	 */
	public Timestamp getLeaseEndDate() {
		return leaseEndDate;
	}

	/**
	 * @param led the leaseEndDate to set
	 */
	public void setLeaseEndDate(Timestamp led) {
		this.leaseEndDate = led;
	}

}
