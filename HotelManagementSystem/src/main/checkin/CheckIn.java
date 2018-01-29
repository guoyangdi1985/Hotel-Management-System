package checkin;

import java.sql.Timestamp;

/**
 * This class is the representation of a tuple for the CheckIn table.
 */
public class CheckIn {
	private int customerID;
	private int roomID;
	private Timestamp checkOutDateTime;
	private Timestamp checkInDateTime;
	private int numberInParty;
	private String hasCatering;
	private String hasRoomService;
	private float restaurantBill;
	private float laundryBill;
	private float phoneBill;
	private String paymentMethod;

	/**
	 * Default empty constructor
	 */
	public CheckIn() {
	}

	/**
	 * Constructor for all fields.
	 * 
	 * @param customerID;
	 * @param roomID;
	 * @param checkOutDateTime;
	 * @param checkInDateTime;
	 * @param numberInParty;
	 * @param hasCatering;
	 * @param hasRoomService;
	 * @param restaurantBill;
	 * @param laundryBill;
	 * @param phoneBill;
	 * @param paymentMethod;
	 */
	public CheckIn(int customerID, int roomID, Timestamp checkOutDateTime, Timestamp checkInDateTime,
			int numberInParty, String hasCatering, String hasRoomService, float restaurantBill, float laundryBill,
			float phoneBill, String paymentMethod) {
		setCustomerID(customerID);
		setRoomID(roomID);
		setCheckInDateTime(checkInDateTime);
		setCheckOutDateTime(checkOutDateTime);
		setNumberInParty(numberInParty);
		setHasCatering(hasCatering);
		setHasRoomService(hasRoomService);
		setRestaurantBill(restaurantBill);
		setLaundryBill(laundryBill);
		setPhoneBill(phoneBill);
		setPaymentMethod(paymentMethod);
	}
	
    /**
     * @return the customerID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * @param customerID the customerID to set
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
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
    
    /**
     * @return the checkOutDateTime (the date and time the guest checks out)
     */
    public Timestamp getCheckOutDateTime() {
        return checkOutDateTime;
    }

    /**
     * @param checkOutDateTime the checkOutDateTime (the date and time the guest checks out) to set
     */
    public void setCheckOutDateTime(Timestamp checkOutDateTime) {
        this.checkOutDateTime = checkOutDateTime;
    }
    
    /**
     * @return the checkInDateTime (the date and time the guest checks in)
     */
    public Timestamp getCheckInDateTime() {
        return checkInDateTime;
    }

    /**
     * @param checkInDateTime the checkInDateTime (the date and time the guest checks in) to set
     */
    public void setCheckInDateTime(Timestamp checkInDateTime) {
        this.checkInDateTime = checkInDateTime;
    }
    
    /**
     * @return the numberInParty i.e. how many people will be staying in the room?
     */
    public int getNumberInParty() {
        return numberInParty;
    }

    /**
     * @param numberInParty the numberInParty to set
     */
    public void setNumberInParty(int numberInParty) {
        this.numberInParty = numberInParty;
    }
    
    /**
     * @return whether or not the customer opted in for catering, as "T" or "F"
     */
    public String getHasCatering() {
        return hasCatering;
    }

    /**
     * @param hasCatering set the catering option to be this, either "T" or "F"
     */
    public void setHasCatering(String hasCatering) {
        if (hasCatering == null || hasCatering.length() == 0) {
            this.hasCatering = hasCatering;
        } else if(hasCatering.equalsIgnoreCase("f")) {
            this.hasCatering = "F";
        } else if(hasCatering.equalsIgnoreCase("t")) {
            this.hasCatering = "T";
        } else {
            this.hasCatering = hasCatering;
        }
        
    }
	
    /**
     * @return whether or not the customer opted in for room service, as "T" or "F"
     */
    public String getHasRoomService() {
        return hasRoomService;
    }

    /**
     * @param hasRoomService set the room service option to be this, either "T" or "F"
     */
    public void setHasRoomService(String hasRoomService) {
        if (hasRoomService == null || hasRoomService.length() == 0) {
            this.hasRoomService = hasRoomService;
        } else if(hasRoomService.equalsIgnoreCase("f")) {
            this.hasRoomService = "F";
        } else if(hasRoomService.equalsIgnoreCase("t")) {
            this.hasRoomService = "T";
        } else {
            this.hasRoomService = hasRoomService;
        }
    }
    
    /**
     * @return the restaurant bill
     */
    public float getRestaurantBill() {
        return restaurantBill;
    }

    /**
     * @param restaurantBill the restaurantBill to set
     */
    public void setRestaurantBill(float restaurantBill) {
        this.restaurantBill = restaurantBill;
    }
    
    /**
     * @return the laundry bill
     */
    public float getLaundryBill() {
        return laundryBill;
    }

    /**
     * @param laundryBill the laundryBill to set
     */
    public void setLaundryBill(float laundryBill) {
        this.laundryBill = laundryBill;
    }
	
    /**
     * @return the phone bill
     */
    public float getPhoneBill() {
        return phoneBill;
    }

    /**
     * @param phoneBill the phoneBill to set
     */
    public void setPhoneBill(float phoneBill) {
        this.phoneBill = phoneBill;
    }
	
    /**
     * @return the payment method
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * @param paymentMethod the paymentMethod to set
     *  the options are "cash", "check", and "Credit Card (xxxx-xxxx-xxxx-xxxx)"
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    /**
     * @return the string representation of the checkin entry
     */
    public String toString() {
        String ret = "";
        ret = "CustomerID: " + customerID;
        ret += "\nRoomID: " + roomID;
        ret += "\nCheck-in Date/Time: " + checkInDateTime;
        ret += "\nCheck-out Date/Time: " + checkOutDateTime;
        ret += "\nNumber in party: " + numberInParty;
        ret += "\nHas catering: " + hasCatering;
        ret += "\nHas room service: " + hasRoomService;
        ret += "\nRestaurant bill: " + restaurantBill;
        ret += "\nLaundry bill: " + laundryBill;
        ret += "\nPhone bill: " + phoneBill;
        ret += "\nPayment method: " + paymentMethod;
        return ret;
    }
    

}
