
public class Lease {
	private String leaseID,
				   customerID,
				   vin,
				   slipID,
				   leaseStartDate,
				   leaseEndDate;
	
	/**
	 * Default constructor
	 */
	public Lease(){
		setLeaseID("");
		setCustomerID("");
		setVin("");
		setSlipID("");
		setLeaseStartDate("");
		setLeaseEndDate("");
	}
	
	/**
	 * Sets data in the slip object
	 * @param leaseStartDate The start date of the lease
	 * @param leaseEndDate The end date of the lease
	 */
	public void setAllLeaseInfo(String leaseStartDate, String leaseEndDate){
		this.leaseStartDate = leaseStartDate;
	 	this.leaseEndDate = leaseEndDate;
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
