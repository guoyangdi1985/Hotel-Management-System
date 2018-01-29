package customer;

/**
 * This class is the representation of a tuple for the Customer table.
 */

public class Customer {
    private int customerID;
    private String name;
    private Character gender;
    private String address;
    private String email;
    private String ssn;
    
    /**
     * Default empty constructor
     */
    public Customer() {}

    /**
     * Constructor for all fields.
     * @param customerID unique ID of the customer
     * @param name of the customer
     * @param gender of the customer
     * @param address of the customer
     * @param email of the customer
     * @param ssn of the customer
     */
    public Customer(int customerID, String name, Character gender, String address, String email, String ssn) {
        setCustomerID(customerID);
        setName(name);
        setGender(gender);
        setAddress(address);
        setEmail(email);
        setSsn(ssn);
    }

    /**
     * @return the customerID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Set the customer ID
     * @param customerID the customerID to set
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * Get the name of the customer
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the customer
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the gender of the customer
     * @return gender of the customer ('M' or 'F')
     */
    public Character getGender() {
		return gender;
	}

    /**
     * Set the gender of the customer
     * @param gender M for male, F for female
     */
	public void setGender(Character gender) {
		if(gender == 'm') {
		    this.gender = 'M';
		} else if(gender == 'f') {
		    this.gender = 'F';
		} else {
		    this.gender = gender;
		}
	}

	/**
	 * Get the email of the customer
	 * @return email of customer
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set the email of the customer
	 * @param email of the customer
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get the address of the customer
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set the address of the customer
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get the SSN of the customer
     * @return the SSN of the customer
     */
    public String getSsn() {
        return ssn;
    }

    /**
     * Set the SSN of the customer
     * @param ssn - new SSN of customer
     */
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
    
    /**
     * Convert the customer to a string
     * @return the String representation of the customer
     */
    public String toString() {
        String ret = "";
        ret = "CustomerID: " + customerID;
        ret += "\nSSN: " + ssn;
        ret += "\nName: " + name;
        ret += "\nGender: " + gender;
        ret += "\nAddress: " + address;
        ret += "\nEmail: " + email;
        return ret;
    }
}
