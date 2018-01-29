package hotel;

/**
 * This class is the representation of a tuple for the Hotel table.
 */
public class Hotel {
    private int hotelID;
    private String name;
    private String address;
    private String phone;
    
    /**
     * Default empty constructor
     */
    public Hotel() {}

    /**
     * Constructor for all fields.
     * @param hotelID
     * @param name
     * @param address
     * @param phone
     */
    public Hotel(int hotelID, String name, String address, String phone) {
        setHotelID(hotelID);
        setName(name);
        setAddress(address);
        setPhone(phone);
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    /**
     * @return the string representation of the hotel
     */
    public String toString() {
        String ret = "";
        ret = "HotelID: " + hotelID;
        ret += "\nName: " + name;
        ret += "\nAddress: " + address;
        ret += "\nPhone: " + phone;
        return ret;
    }
    
}
