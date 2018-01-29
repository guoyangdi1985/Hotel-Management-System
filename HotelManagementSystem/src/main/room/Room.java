package room;

/**
 * This class is the representation of a tuple for the Room table.
 */
public class Room {

	private int hotelID;
	private int roomNumber;
	private String roomType;
	private double rate;
	private int maxOccupancy;
	private int roomID;

	/**
	 * Default empty constructor
	 */
	public Room() {}
	
	/**
	 * @param hotelID
	 * @param roomNumber
	 * @param roomType
	 * @param rate
	 * @param maxOccupancy
	 * @param roomID
	 */
	public Room (int hotelID, int roomNumber, String roomType, double rate, int maxOccupancy, int roomID) {
		setHotelID(hotelID);
		setRoomNumber(roomNumber);
		setRoomType(roomType);
		setRate(rate);
		setMaxOccupancy(maxOccupancy);
		setRoomID(roomID);
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

	/**
	 * @return the roomNumber
	 */
	public int getRoomNumber() {
		return roomNumber;
	}

	/**
	 * @param roomNumber the roomNumber to set
	 */
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	/**
	 * @return the roomType
	 */
	public String getRoomType() {
		return roomType;
	}

	/**
	 * @param roomType the roomType to set
	 */
	public void setRoomType(String roomType) {
	    if (roomType == null || roomType.length() == 0) {
	        this.roomType = roomType;
	    } else if(roomType.equalsIgnoreCase("economy")) {
		    this.roomType = "Economy";
		} else if(roomType.equalsIgnoreCase("deluxe")) {
            this.roomType = "Deluxe";
        } else if(roomType.equalsIgnoreCase("executive suite")) {
            this.roomType = "Executive Suite";
        } else if(roomType.equalsIgnoreCase("Presidential Suite")) {
            this.roomType = "Presidential Suite";
        } else {
            this.roomType = roomType;
        }
	}

	/**
	 * @return the rate
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}

	/**
	 * @return the maxOccupancy
	 */
	public int getMaxOccupancy() {
		return maxOccupancy;
	}

	/**
	 * @param maxOccupancy the maxOccupancy to set
	 */
	public void setMaxOccupancy(int maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}

	/**
	 * @return the roomID
	 */
	public int getRoomID() {
		return roomID;
	}

	/**
	 * @param roomID the roomID to set
	 */
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	
	@Override
	public String toString() {
        String ret = "";
        ret = "HotelID: " + hotelID;
        ret += "\nRoom Number: " + roomNumber;
        ret += "\nRoom Type: " + roomType;
        ret += "\nRate: " + rate;
        ret += "\nMax Occupancy: " + maxOccupancy;
        ret += "\nRoomID: " + roomID;
        return ret;
	}
}
