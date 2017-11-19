
public class Slip {
	private String slipID,
				   isPowered,
				   isLeased,
				   isOccupied;
	public Slip(){
		setSlipID("");
		setIsPowered("");
		setIsLeased("");
		setIsOccupied("");
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
