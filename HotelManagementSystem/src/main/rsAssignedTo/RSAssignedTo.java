package rsAssignedTo;

/**
 * This class is the representation of a tuple for the RSAssignedTo table.
 */
public class RSAssignedTo {
    private int staffID;
    private int roomID;
    private int customerID;
    private int hotelID;
    
	/**
	 * Default empty constructor
	 */
	public RSAssignedTo() {}

	/**
	 * Constructor used to set up an object of RSAssignedTo.
	 * 
	 * @param staffID
	 *            the staff ID
	 * @param roomID
	 *            the room ID
	 * @param customerID
	 *            the customer ID
	 */
	public RSAssignedTo(int staffID, int roomID, int customerID, int hotelID) {
		setStaffID(staffID);
		setRoomID(roomID);
		setCustomerID(customerID);
		setHotelID(hotelID);
	}
	
	/**
     * Return the staff id.
     * 
     * @return the staffID
     */
    public int getStaffID() {
        return staffID;
    }

    /**
     * Set the staff id.
     * 
     * @param staffID
     *            the staffID to set
     */
    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    /**
     * Return the room id.
     * 
     * @return the roomID
     */
    public int getRoomID() {
        return roomID;
    }

    /**
     * Set the room id.
     * 
     * @param roomID
     *            the roomID to set
     */
    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    /**
     * Return the customer id.
     * 
     * @return the customerID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Set the customer id.
     * 
     * @param customerID
     *            the customerID to set
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * @return the hotelID
     */
    public int getHotelID() {
        return hotelID;
    }

    /**
     * @param hotelID the hotelID to set
     */
    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }
}
