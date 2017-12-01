
public class Slip {
	private String slipID,
				   isPowered,
				   isLeased,
				   isOccupied;
	
	/**
	 * Default constructor
	 */
	public Slip(){
		setSlipID("");
		setIsPowered("");
		setIsLeased("");
		setIsOccupied("");
	}
	
	/**
	 * Sets data in the slip object
	 * @param isPowered Whether the boat is powered
	 * @param isLeased Whether the boat is leased
	 * @param isOccupied Whether the slip is occupied
	 */
	public void setAllSlipInfo(String isPowered, String isLeased, String isOccupied){
	 	this.isPowered = isPowered;
	 	this.isLeased = isLeased;
	 	this.isOccupied = isOccupied;
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
	/**
	 * @return the isLeased
	 */
	public String getIsLeased() {
		return isLeased;
	}
	/**
	 * @param isLeased the isLeased to set
	 */
	public void setIsLeased(String isLeased) {
		this.isLeased = isLeased;
	}
	/**
	 * @return the isOccupied
	 */
	public String getIsOccupied() {
		return isOccupied;
	}
	/**
	 * @param isOccupied the isOccupied to set
	 */
	public void setIsOccupied(String isOccupied) {
		this.isOccupied = isOccupied;
	}
}
