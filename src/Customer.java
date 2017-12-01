/**
 * This class performs the functions necessary to set get and information from the Customer object.
 * @author Collin Enders
 *
 */

public class Customer {
	private String customerID,
				   firstName,
				   lastName,
				   paymentInfo,
				   phoneNumber,
				   streetAddress,
				   city,
				   state,
				   zip;
	
	public Customer(){
		setCustomerID("");
		setFirstName("");
		setLastName("");
		setPaymentInfo("");
		setPhoneNumber("");
		setStreetAddress("");
		setCity("");
		setState("");
		setZip("");
	}
	
	/**
	 * 
	 * @param firstName the firstName to set
	 * @param lastName the lastName to set	
	 * @param paymentInfo the paymentInfo to set
	 * @param phoneNumber the phoneNumber to set
	 * @param streetAddress the streetAddress to set
	 * @param city the city to set
	 * @param state the state to set
	 * @param zip the zip to set
	 */
	public Customer(String customerID, String firstName, String lastName, String paymentInfo, String phoneNumber, String streetAddress, String city, String state, String zip){
		this.customerID = customerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.paymentInfo = paymentInfo;
		this.phoneNumber = phoneNumber;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zip = zip;
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the paymentInfo
	 */
	public String getPaymentInfo() {
		return paymentInfo;
	}

	/**
	 * @param paymentInfo the paymentInfo to set
	 */
	public void setPaymentInfo(String paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the streetAddress
	 */
	public String getStreetAddress() {
		return streetAddress;
	}

	/**
	 * @param streetAddress the streetAddress to set
	 */
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	@Override
	public String toString(){
		return getFirstName() + " " + getLastName() + " " + getPaymentInfo() + " " + getPhoneNumber() + " " + getStreetAddress() + " " + getCity() + " " + getState() + " "  + getZip();
	}
}
