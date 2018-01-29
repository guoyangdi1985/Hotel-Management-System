package staff;

/**
 * Staff works in the hotel
 */
public class Staff {
	private int hotelID;
	private int staffID;
	private String ssn;
	private String name;
	private String address;
	private String gender;
	private String phone;
	private String staffType;

	/**
	 * Default empty constructor
	 */
	public Staff() {
	}

	/**
	 * Initialize the staff class
	 * @param staffID unique ID of staff
	 * @param ssn of the staff member
	 * @param name of the staff member
	 * @param gender of the staff
	 * @param phone of the staff
	 * @param address of the staff member
	 * @param staffType the role of the staff member
	 * @param hotelID is the Hotel the staff works at
	 */
	public Staff(int staffID, String ssn, String name, String gender, String phone, String address, String staffType,
			int hotelID) {
		setStaffID(staffID);
		setSsn(ssn);
		setName(name);
		setPhone(phone);
		setAddress(address);
		setHotelID(hotelID);
		setGender(gender);
		setStaffType(staffType);
	}

	/**
	 * return the staff ID
	 * @return current staff ID
	 */
	public int getStaffID() {
		return staffID;
	}

	/**
	 * set the staff ID
	 * @param staffID is the unique ID of the staff
	 */
	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}

	/**
	 * return the ssn of the staff member
	 * @return ssn of staff
	 */
	public String getSsn() {
		return ssn;
	}

	/**
	 * set the ssn of the staff member
	 * @param ssn to set the staff member to have
	 */
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	/**
	 * return the name of the staff member
	 * @return name of the staff
	 */
	public String getName() {
		return name;
	}

	/**
	 * set the name of the staff member
	 * @param name the staff member uses
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the address of the staff member
	 * @return address of staff
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Set the address of the staff member
	 * @param address of the staff
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Get the gender of the staff member
	 * @return the gender of the staff
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Set the gender of the staff member
	 * @param gender to set the staff to
	 */
	public void setGender(String gender) {
		if (gender == null || gender.length() == 0) {
			this.gender = gender;
		} else if (gender.charAt(0) == 'm') {
			this.gender = "M";
		} else if (gender.charAt(0) == 'f') {
			this.gender = "F";
		} else {
			this.gender = gender;
		}
	}

	/**
	 * return the phone number of the staff
	 * @return phone number of the staff
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * set the phone number of the staff member
	 * @param phone number of the staff memeber
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Get the phone number of the staff member
	 * @return phone number of the staff member
	 */
	public String getStaffType() {
		return staffType;
	}

	/**
	 * Set the staff type of the staff member
	 * @param staffType new staff type of the staff member
	 */
	public void setStaffType(String staffType) {
		if (staffType.equalsIgnoreCase("manager")) {
			this.staffType = "Manager";
		} else if (staffType.equalsIgnoreCase("front desk representative")) {
			this.staffType = "Front Desk Representative";
		} else if (staffType.equalsIgnoreCase("catering staff")) {
			this.staffType = "Catering Staff";
		} else if (staffType.equalsIgnoreCase("room service staff")) {
			this.staffType = "Room Service Staff";
		} else {
			this.staffType = staffType;
		}
	}

	/**
	 * get the hotel ID of that the staff member works at
	 * @return hotel ID of his/her hotel
	 */
	public int getHotelID() {
		return hotelID;
	}

	/**
	 * Set the hotel ID that the staff member works at
	 * @param hotelID new hotelID that the staff works at
	 */
	public void setHotelID(int hotelID) {
		this.hotelID = hotelID;
	}

	/**
	 * Convert the staff object to a string
	 * @return the String representation of the staff member
	 */
	public String toString() {
		String ret = "";
		ret = "HotelID: " + hotelID;
		ret += "\nStaffID: " + staffID;
		ret += "\nStaff Type: " + staffType;
		ret += "\nSSN: " + ssn;
		ret += "\nName: " + name;
		ret += "\nGender: " + gender;
		ret += "\nAddress: " + address;
		ret += "\nPhone: " + phone;
		return ret;
	}
}
