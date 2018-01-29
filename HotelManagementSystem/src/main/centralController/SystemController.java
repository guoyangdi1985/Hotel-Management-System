package centralController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.Timestamp;

import cAssignedTo.CAssignedTo;
import cAssignedTo.CAssignedToController;
import checkin.CheckIn;
import checkin.CheckInController;
import customer.Customer;
import customer.CustomerController;
import hotel.Hotel;
import hotel.HotelController;
import room.Room;
import room.RoomController;
import rsAssignedTo.RSAssignedTo;
import rsAssignedTo.RSAssignedToController;
import staff.Staff;
import staff.StaffController;

/**
 * The workhorse class to carry out operations with utility methods
 */
public class SystemController {
    private HotelController hotelController;
    private StaffController staffController;
    private CustomerController customerController;
    private RoomController roomController;
    private CheckInController checkInController;
    private CAssignedToController cateringController;
    private RSAssignedToController roomServiceController;
    
    /**
     * Default empty constructor
     */
    public SystemController() {
        hotelController = new HotelController();
        staffController = new StaffController();
        customerController = new CustomerController();
        roomController = new RoomController();
        checkInController = new CheckInController();
        cateringController = new CAssignedToController();
        roomServiceController = new RSAssignedToController();
    }
    
    /* **************************  Get List methods   **************************** */
    
    /**
     * Returns a list of all customers
     * @return 
     * @throws SQLException
     */
    public List<Customer> getCustomerList() throws SQLException {
        return customerController.getAll();
    }
    
    /**
     * Returns a list of all rooms
     * @return 
     * @throws SQLException
     */
    public List<Room> getRoomList() throws SQLException {
        return roomController.getAll();
    }
    
    /**
     * Returns a list of all rooms with the given room type
     * @return 
     * @throws SQLException is thrown if the SQL database throws an exception
     */
    public List<Room> getRoomType(String roomType) throws SQLException {
        return roomController.getRoomType(roomType);
    }
    
    /**
     * Returns a list of all staff
     * @return 
     * @throws SQLException
     */
    public List<Staff> getStaffList() throws SQLException {
        return staffController.getAll();
    }
    
    /**
     * Returns a list of staff who have the given job
     * @param staffType
     * @return 
     * @throws SQLException
     */
    public List<Staff> getStaffList(String staffType) throws SQLException {
        return staffController.getAllByStaffType(staffType);
    }
    
    /**
     * Returns a list of all checkins
     * @return 
     * @throws SQLException
     */
    public List<CheckIn> getCheckInList() throws SQLException {
        return checkInController.getAll();
    }
    
    /**
     * Returns a list of all hotels
     * @return 
     * @throws SQLException
     */
    public List<Hotel> getHotelList() throws SQLException {
        return hotelController.getAll();
    }
    
    /**
     * Returns a list of all catering assignments
     * @return 
     * @throws SQLException
     */
    public List<CAssignedTo> getCateringList() throws SQLException {
        return cateringController.getAll();
    }
    
    /**
     * Returns a list of all room service assignments
     * @return 
     * @throws SQLException
     */
    public List<RSAssignedTo> getRoomServiceList() throws SQLException {
        return roomServiceController.getAll();
    }
    
    /* **************************  Get Specific Object methods   **************************** */
    
    /**
     * Returns a specific customer
     * @param customerID
     * @return
     * @throws SQLException
     */
    public Customer getCustomer(int customerID) throws SQLException {
        return customerController.getSpecificCustomer(customerID);
    }
    
    /**
     * Returns a specific room
     * @param roomID
     * @return
     * @throws SQLException
     */
    public Room getRoom(int roomID) throws SQLException {
        return roomController.getSpecificRoom(roomID);
    }
    
    /**
     * Returns a specific hotel
     * @param hotelID
     * @return
     * @throws SQLException
     */
    public Hotel getHotel(int hotelID) throws SQLException {
        return hotelController.getSpecificHotel(hotelID);
    }

    /**
     * Returns a specific staff member based on their ID.
     * @param staffID
     * @return
     * @throws SQLException
     */
    public Staff getStaff(int staffID) throws SQLException {
        return staffController.getSpecificStaff(staffID);
    }
    
    /**
     * Get a specific check in from the room ID
     * @param roomID to find check in for
     * @return CheckIn object found
     * @throws SQLException
     */
    public CheckIn getSpecificCheckIn(int roomID) throws SQLException {
    	return checkInController.getSpecificCheckIn(roomID);
    }
    
    /**
     * Get a list of checkins for a particular customer
     * @param customerID the customer of interest
     * @return the list of checkins for the particular customer
     * @throws SQLException
     */
    public List<CheckIn> getCustomerCheckIns(int customerID) throws SQLException {
    	return checkInController.getCustomerCheckIns(customerID);
    }
    
    /* **************************  Add Object methods   **************************** */
    
    /**
     * Add a customer into the system
     * @param lineReader used to get user input
     * @throws SQLException
     */
    public void addCustomer(Scanner lineReader) throws SQLException {
        System.out.println("Enter a value for each of the following fields:");
        Customer c = new Customer();
        
        System.out.print("Name: ");
        String input = lineReader.nextLine();
        if(input.length() > 0) {
            c.setName(input);
        }
        
        System.out.print("SSN: ");
        input = lineReader.nextLine();
        if(input.length() > 0) {
            c.setSsn(input);
        }
        
        System.out.print("Gender: ");
        input = lineReader.nextLine();
        if(input.length() > 0) {
            c.setGender(input.charAt(0));
        }
        
        System.out.print("Address: ");
        input = lineReader.nextLine();
        if(input.length() > 0) {
            c.setAddress(input);
        }
        
        System.out.print("Email: ");
        input = lineReader.nextLine();
        if(input.length() > 0) {
            c.setEmail(input);
        }
        
        customerController.add(null, c);
        System.out.println("Customer updated successfully.");
    }
    
    /**
     * Add a hotel into the system
     * @param lineReader used to get user input
     * @throws SQLException
     */
    public void addHotel(Scanner lineReader) throws SQLException {
        System.out.println("Enter a value for each of the following fields:");
        Hotel h = new Hotel();
        
        System.out.print("Hotel name: ");
        String input = lineReader.nextLine();
        if(input.length() > 0) {
            h.setName(input);
        }
        
        System.out.print("Address: ");
        input = lineReader.nextLine();
        if(input.length() > 0) {
            h.setAddress(input);
        }
        
        System.out.print("Phone (___-___-____): ");
        input = lineReader.nextLine();
        if(input.length() > 0) {
            h.setPhone(input);
        }
        
        hotelController.add(null, h);
        System.out.println("Hotel added successfully.");
    }

    /**
     * Add a room into the system.
     * @param lineReader the scanner object used to read room data from a user.
     * @throws SQLException
     */
    public void addRoom( Scanner lineReader ) throws SQLException {
    	System.out.println("Enter a value for each of the following fields:");
    	Room r = new Room();
    	
    	System.out.print( "HotelID: " );
    	String input = lineReader.nextLine();
    	if( input.length() > 0 ) {
            int hotelID;
            try {
                hotelID = Integer.parseInt( input );
            } catch (NumberFormatException e) {
                throw new SQLException("HotelID must be an integer.");
            }
            r.setHotelID(hotelID);
    	}
    	
    	System.out.print( "Room Number: " );
    	input = lineReader.nextLine();
    	if( input.length() > 0 ) {
    		int roomNumber;
    		try {
    			roomNumber = Integer.parseInt( input );
    		} catch (NumberFormatException e) {
                throw new SQLException("Room number must be an integer.");
            }
    		r.setRoomNumber( roomNumber );
    	}
    	
    	System.out.print( "Room Type: " );
    	input = lineReader.nextLine();
    	if( input.length() > 0 ) {
    		r.setRoomType( input );
    	}
    	
    	System.out.print( "Rate: " );
    	input = lineReader.nextLine();
    	if( input.length() > 0 ) {
    		double rate;
    		try {
    			rate = Double.parseDouble( input );
    		} catch ( NumberFormatException e ) {
    			throw new SQLException("Rate must be a real number.");
    		}
    		r.setRate( rate );
    	}
    	
    	System.out.print( "Max Occupancy: " );
    	input = lineReader.nextLine();
    	if( input.length() > 0 ) {
    		int maxOccupancy;
    		try {
    			maxOccupancy = Integer.parseInt( input );
    		} catch (NumberFormatException e) {
    			throw new SQLException("Max Occupancy must be an integer.");
    		}
    		r.setMaxOccupancy( maxOccupancy );
    	}
    	
    	roomController.add( null, r );
    	System.out.println("Room added successfully.");
    }

    /**
     * Add a staff member into the system.
     * @param lineReader the scanner object used to read staff data from a user.
     * @throws SQLException
     */
    public void addStaff(Scanner lineReader) throws SQLException {
        System.out.println("Enter a value for each of the following fields:");
        Staff s = new Staff();
        
        System.out.print("HotelID: ");
        String input = lineReader.nextLine();
        if(input.length() > 0) {
            int hotelID;
            try {
                hotelID = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                throw new SQLException("HotelID must be an integer.");
            }
            s.setHotelID(hotelID);
        }
        
        System.out.print("Staff Type: ");
        input = lineReader.nextLine();
        if(input.length() > 0) {
            s.setStaffType(input);
        }
        
        System.out.print("Name: ");
        input = lineReader.nextLine();
        if(input.length() > 0) {
            s.setName(input);
        }
        
        System.out.print("SSN: ");
        input = lineReader.nextLine();
        if(input.length() > 0) {
            s.setSsn(input);
        }
        
        System.out.print("Gender: ");
        input = lineReader.nextLine();
        if(input.length() > 0) {
            s.setGender(input);
        }
        
        System.out.print("Address: ");
        input = lineReader.nextLine();
        if(input.length() > 0) {
            s.setAddress(input);
        }
        
        System.out.print("Phone: ");
        input = lineReader.nextLine();
        if(input.length() > 0) {
            s.setPhone(input);
        }
        
        staffController.add(null, s);
        System.out.println("Staff member updated successfully.");
    }
    
    /**
     * Add a checkin into the system
     * @param lineReader used to get user input
     * @throws SQLException
     */
    public void addCheckIn(Scanner lineReader) throws SQLException {
        System.out.println("Enter a value for each of the following fields:");
        CheckIn ci = new CheckIn();
        
        System.out.print("RoomID: ");
        String input = lineReader.nextLine();
        if(input.length() > 0) {
            int roomID;
            try {
                roomID = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                throw new SQLException("roomID must be an integer.");
            }
            ci.setRoomID(roomID);
        }
        Room r = getRoom(ci.getRoomID());
        if(r == null) {
            throw new SQLException("This roomID is invalid.");
        }
        
        System.out.print("CustomerID: ");
        input = lineReader.nextLine();
        if(input.length() > 0) {
            int customerID;
            try {
                customerID = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                throw new SQLException("customerID must be an integer.");
            }
            ci.setCustomerID(customerID);
        }
        Customer c = getCustomer(ci.getCustomerID());
        if(c == null) {
            throw new SQLException("This customerID is invalid.");
        }
        
        System.out.println("Check-in date & time (yyyy-mm-dd hh:mm:ss): ");
        input = lineReader.nextLine();
        if (input.length() > 0) {
        	Timestamp checkInDateTime;
        	try {
        		checkInDateTime = Timestamp.valueOf(input);
        	} catch (Exception e) {
        		throw new SQLException("Date & time not in proper format.");
        	}
        	ci.setCheckInDateTime(checkInDateTime);
        }
        
        System.out.println("Check-out date & time (yyyy-mm-dd hh:mm:ss): ");
        input = lineReader.nextLine();
        if (input.length() > 0) {
        	Timestamp checkOutDateTime;
        	try {
        		checkOutDateTime = Timestamp.valueOf(input);
        	} catch (Exception e) {
        		throw new SQLException("Date & time not in proper format.");
        	}
        	ci.setCheckOutDateTime(checkOutDateTime);
        }
        
        System.out.print("Number in party (1-4): ");
        input = lineReader.nextLine();
        if(input.length() > 0) {
            int numberInParty;
            try {
                numberInParty = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                throw new SQLException("The number in party must be an integer.");
            }
            ci.setNumberInParty(numberInParty);
        }
        if(ci.getNumberInParty() > r.getMaxOccupancy()) {
            throw new SQLException("Number in party cannot be greater than max room occupancy.");
        }
        
        //If the room is a presidential suite, automatically require room service and catering.
        if(r.getRoomType().equalsIgnoreCase("Presidential Suite")) {
            ci.setHasCatering("T");
            ci.setHasRoomService("T");
        } else {
            System.out.print("There will be dedicated catering staff (T/F): ");
            input = lineReader.nextLine();
            if (input.length() > 0) {
            	ci.setHasCatering(input);
            }
            
            System.out.print("There will be dedicated room service staff (T/F): ");
            input = lineReader.nextLine();
            if (input.length() > 0) {
            	ci.setHasRoomService(input);
            }
        }
        
        
        // NOTE: The restaurant bill, laundry bill, and phone bill all start at 0 automatically
        //   so no need for user prompt
        ci.setRestaurantBill(0f);
        ci.setLaundryBill(0f);
        ci.setPhoneBill(0f);
        
        System.out.print("Payment method ('cash'/'check'/'Credit Card (xxxx-xxxx-xxxx-xxxx)'): ");
        input = lineReader.nextLine();
        if (input.length() > 0) {
        	ci.setPaymentMethod(input);
        }
        
        checkInController.add(null, ci);
        System.out.println("Check-in entry added successfully.");
    }

    
    /* **************************  Update Object methods   **************************** */
    
    /**
     * Used by edit room rate to only update a rooms rate.
     * @param r
     * @throws SQLException
     */
    public void editRoomRate(Room r) throws SQLException {
        roomController.update(null, r);
    }
    
    /**
     * Gives the user a choice of fields to update, then attempts
     * to make the changes they specify.
     * @param lineReader used for user input
     * @param c the Customer to update
     * @throws SQLException
     */
    public void updateCustomerInformation(Scanner lineReader, Customer c) throws SQLException {
        System.out.println("For the following fields, enter a new value to update the given field, "
                + "\nor leave it blank to keep the original value");
        
        System.out.print("Old Name: " + c.getName() + ", New Name: ");
        String input = lineReader.nextLine();
        if(input.length() > 0) {
            c.setName(input);
        }
        
        System.out.print("Old SSN: " + c.getSsn() + ", New SSN: ");
        input = lineReader.nextLine();
        if(input.length() > 0) {
            c.setSsn(input);
        }
        
        System.out.print("Old Gender: " + c.getGender() + ", New Gender: ");
        input = lineReader.nextLine();
        if(input.length() > 0) {
            c.setGender(input.charAt(0));
        }
        
        System.out.print("Old Address: " + c.getAddress() + ", New Address: ");
        input = lineReader.nextLine();
        if(input.length() > 0) {
            c.setAddress(input);
        }
        
        System.out.print("Old Email: " + c.getEmail() + ", New Email: ");
        input = lineReader.nextLine();
        if(input.length() > 0) {
            c.setEmail(input);
        }
        
        customerController.update(null, c);
        System.out.println("Customer updated successfully.\n");
    }
    
    /**
     * Gives the user a choice of fields to update, then attempts
     * to make the changes they specify.
     * @param lineReader used for user input
     * @param r the Room to be updated
     * @throws SQLException
     */
    public void updateRoomInformation( Scanner lineReader, Room r ) throws SQLException {
        System.out.println("For the following fields, enter a new value to update the given field, "
                + "\nor leave it blank to keep the original value");
        
        System.out.print("Old HotelID: " + r.getHotelID() + ", New HotelID: ");
        String input = lineReader.nextLine();
        if( input.length() > 0 ) {
            int hotelID;
            try {
                hotelID = Integer.parseInt( input );
            } catch ( NumberFormatException e ) {
                throw new SQLException( "HotelID must be an integer.\n" );
            }
            r.setHotelID( hotelID );
        }
        
        System.out.print("Old room number: " + r.getRoomNumber() + ", New room number: ");
        input = lineReader.nextLine();
        if( input.length() > 0 ) {
            int roomNumber;
            try {
            	roomNumber = Integer.parseInt( input );
            } catch ( NumberFormatException e ) {
                throw new SQLException( "Room number must be an integer.\n" );
            }
            r.setRoomNumber( roomNumber );
        }
        
        System.out.print("Old room type: " + r.getRoomType() + ", New room type: ");
        input = lineReader.nextLine();
        if( input.length() > 0 ) {
            r.setRoomType( input );
        }
        
        System.out.print("Old rate: " + r.getRate() + ", New rate: ");
        input = lineReader.nextLine();
        if( input.length() > 0 ) {
            double rate;
            try {
            	rate = Double.parseDouble( input );
            } catch ( NumberFormatException e ) {
                throw new SQLException( "Rate must be a real number.\n" );
            }
            r.setRate( rate );
        }
        
        System.out.print("Old max occupancy: " + r.getMaxOccupancy() + ", New max occupancy: ");
        input = lineReader.nextLine();
        if( input.length() > 0 ) {
            int maxOccupancy;
            try {
            	maxOccupancy = Integer.parseInt( input );
            } catch ( NumberFormatException e ) {
                throw new SQLException( "Max occupancy must be an integer.\n" );
            }
            r.setMaxOccupancy( maxOccupancy );
        }
        
        roomController.update( null, r );
        System.out.println( "Room updated successfully.\n" );
    }

    /**
     * Gives the user a choice of fields to update, then attempts
     * to make the changes they specify.
     * @param lineReader used for user input
     * @param s the Staff to update
     * @throws SQLException
     */
    public void updateStaffInformation(Scanner lineReader, Staff s) throws SQLException {
        System.out.println("For the following fields, enter a new value to update the given field, "
                + "\nor leave it blank to keep the original value");
        
        System.out.print("Old HotelID: " + s.getHotelID() + ", New HotelID: ");
        String input = lineReader.nextLine();
        if(input.length() > 0) {
            int hotelID;
            try {
                hotelID = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                throw new SQLException("HotelID must be an integer.");
            }
            s.setHotelID(hotelID);
        }
        
        System.out.print("Old Staff Type: " + s.getStaffType() + ", New Staff Type: ");
        input = lineReader.nextLine();
        if(input.length() > 0) {
            s.setStaffType(input);
        }
        
        System.out.print("Old Name: " + s.getName() + ", New Name");
        input = lineReader.nextLine();
        if(input.length() > 0) {
            s.setName(input);
        }
        
        System.out.print("Old SSN: " + s.getSsn() + ", New SSN: ");
        input = lineReader.nextLine();
        if(input.length() > 0) {
            s.setSsn(input);
        }
        
        System.out.print("Old Gender: " + s.getGender() + ", New Gender: ");
        input = lineReader.nextLine();
        if(input.length() > 0) {
            s.setGender(input);
        }
        
        System.out.print("Old Address: " + s.getAddress() + ", New Address: ");
        input = lineReader.nextLine();
        if(input.length() > 0) {
            s.setAddress(input);
        }
        
        System.out.print("Old Phone: " + s.getPhone() + ", New Phone: ");
        input = lineReader.nextLine();
        if(input.length() > 0) {
            s.setPhone(input);
        }
        
        staffController.update(null, s);
        System.out.println("Staff member updated successfully.\n");
    }
    
    /**
     * Gives the user a choice of fields to update, then attempts
     * to make the changes they specify.
     * @param lineReader used for user input
     * @param ci the check-in entry to be updated
     * @throws SQLException
     */
    public void updateCheckInInformation(Scanner lineReader, CheckIn ci) throws SQLException {
        System.out.println("For the following fields, enter a new value to update the given field, "
                + "\nor leave it blank to keep the original value");
        
        System.out.print("Old roomID: " + ci.getRoomID() + ", New roomID: ");
        String input = lineReader.nextLine();
        if(input.length() > 0) {
            int roomID;
            try {
                roomID = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                throw new SQLException("roomID must be an integer.\n");
            }
            ci.setRoomID(roomID);
        }

        Room r = getRoom(ci.getRoomID());
        if(r == null) {
            throw new SQLException("This roomID is invalid.");
        }
        
        System.out.print("Old customerID: " + ci.getCustomerID() + ", New customerID: ");
        input = lineReader.nextLine();
        if(input.length() > 0) {
            int customerID;
            try {
                customerID = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                throw new SQLException("customerID must be an integer.\n");
            }
            ci.setCustomerID(customerID);
        }
        
        Customer c = getCustomer(ci.getCustomerID());
        if(c == null) {
            throw new SQLException("This customerID is invalid.");
        }
        
        System.out.print("Old check-in date/time: " + ci.getCheckInDateTime() + ", New check-in date/time: ");
        input = lineReader.nextLine();
        if (input.length() > 0) {
        	Timestamp checkInDateTime;
        	try {
        		checkInDateTime = Timestamp.valueOf(input);
        	} catch (Exception e) {
        		throw new SQLException("Date & time not in proper format.\n");
        	}
        	ci.setCheckInDateTime(checkInDateTime);
        }
        
        System.out.print("Old check-out date/time: " + ci.getCheckOutDateTime() + ", New check-out date/time: ");
        input = lineReader.nextLine();
        if (input.length() > 0) {
        	Timestamp checkOutDateTime;
        	try {
        		checkOutDateTime = Timestamp.valueOf(input);
        	} catch (Exception e) {
        		throw new SQLException("Date & time not in proper format.\n");
        	}
        	ci.setCheckOutDateTime(checkOutDateTime);
        }
        
        System.out.print("Old number in party: " + ci.getNumberInParty() + ", New number in party (1-4): ");
        input = lineReader.nextLine();
        if(input.length() > 0) {
            int numberInParty;
            try {
                numberInParty = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                throw new SQLException("The number in party must be an integer.\n");
            }
            ci.setNumberInParty(numberInParty);
        }
        if(ci.getNumberInParty() > r.getMaxOccupancy()) {
            throw new SQLException("Number in party cannot be greater than max room occupancy.");
        }
        
        //If the room is a presidential suite, automatically require room service and catering.
        if(r.getRoomType().equalsIgnoreCase("Presidential Suite")) {
            ci.setHasCatering("T");
            ci.setHasRoomService("T");
        } else {
            System.out.print("There previously was dedicated catering staff: " + ci.getHasCatering() + ", There is now dedicated catering staff (T/F): ");
            input = lineReader.nextLine();
            if (input.length() > 0) {
            	ci.setHasCatering(input);
            }
            
            System.out.print("There previously was dedicated room service staff: " + ci.getHasRoomService() + ", There is now dedicated room service staff (T/F): ");
            input = lineReader.nextLine();
            if (input.length() > 0) {
            	ci.setHasRoomService(input);
            }
        }

        System.out.print("Old phone bill: " + ci.getPhoneBill() + ", New phone bill: ");
        input = lineReader.nextLine();
        if(input.length() > 0) {
            float phoneBill;
            try {
                phoneBill = Float.parseFloat(input);
            } catch (NumberFormatException e) {
                throw new SQLException("Phone bill must be a floating point number.\n");
            }
            ci.setPhoneBill(phoneBill);
        }
        
        System.out.print("Old restaurant bill: " + ci.getRestaurantBill() + ", New restaurant bill: ");
        input = lineReader.nextLine();
        if(input.length() > 0) {
            float restaurantBill;
            try {
                restaurantBill = Float.parseFloat(input);
            } catch (NumberFormatException e) {
                throw new SQLException("Restaurant bill must be a floating point number.\n");
            }
            ci.setRestaurantBill(restaurantBill);
        }
        
        System.out.print("Old laundry bill: " + ci.getLaundryBill() + ", New laundry bill: ");
        input = lineReader.nextLine();
        if(input.length() > 0) {
            float laundryBill;
            try {
                laundryBill = Float.parseFloat(input);
            } catch (NumberFormatException e) {
                throw new SQLException("Laundry bill must be a floating point number.\n");
            }
            ci.setLaundryBill(laundryBill);
        }
        
        System.out.println("Old payment method: " + ci.getPaymentMethod());
        System.out.print("New payment method ('cash'/'check'/'Credit Card (xxxx-xxxx-xxxx-xxxx)'): ");
        input = lineReader.nextLine();
        if (input.length() > 0) {
        	ci.setPaymentMethod(input);
        }
        
        checkInController.update(null, ci);
        System.out.println("Check-in entry updated successfully.\n");
    }
    
    /**
     * Adds to either restaurant, laundry, or phone bill
     * @param lineReader used for user input
     * @param ci the check-in entry to be updated
     * @throws SQLException
     */
    public void addToBill(Scanner lineReader, CheckIn ci) throws SQLException {
        System.out.println("For the following bills, enter an amount to add, "
                + "\nor leave it blank to keep the original value");
        
        System.out.print("Old phone bill: " + ci.getPhoneBill() + ", Amount to add: ");
        String input = lineReader.nextLine();
        if(input.length() > 0) {
            float phoneBill;
            try {
                phoneBill = Float.parseFloat(input);
            } catch (NumberFormatException e) {
                throw new SQLException("Phone bill must be a floating point number.");
            }
            phoneBill += ci.getPhoneBill();
            ci.setPhoneBill(phoneBill);
        }
        
        System.out.print("Old restaurant bill: " + ci.getRestaurantBill() + ", Amount to add: ");
        input = lineReader.nextLine();
        if(input.length() > 0) {
            float restaurantBill;
            try {
                restaurantBill = Float.parseFloat(input);
            } catch (NumberFormatException e) {
                throw new SQLException("Restaurant bill must be a floating point number.");
            }
            restaurantBill += ci.getRestaurantBill();
            ci.setRestaurantBill(restaurantBill);
        }
        
        System.out.print("Old laundry bill: " + ci.getLaundryBill() + ", Amount to add: ");
        input = lineReader.nextLine();
        if(input.length() > 0) {
            float laundryBill;
            try {
                laundryBill = Float.parseFloat(input);
            } catch (NumberFormatException e) {
                throw new SQLException("Laundry bill must be a floating point number.");
            }
            laundryBill += ci.getLaundryBill();
            ci.setLaundryBill(laundryBill);
        }
        
        checkInController.update(null, ci);
        System.out.println("Bills modified successfully.\n");
    }
    
    /**
     * Gives the user a choice of fields to update, then attempts
     * to make the changes they specify.
     * @param lineReader used for user input
     * @param h the hotel to be updated
     * @throws SQLException
     */
    public void updateHotelInformation(Scanner lineReader, Hotel h) throws SQLException {
        System.out.println("For the following fields, enter a new value to update the given field, "
                + "\nor leave it blank to keep the original value");
        
        System.out.print("Old Hotel Name: " + h.getName() + ", New Hotel Name: ");
        String input = lineReader.nextLine();
        if(input.length() > 0) {
            h.setName(input);
        }
        
        System.out.print("Old Hotel Address: " + h.getAddress() + ", New Hotel Address: ");
        input = lineReader.nextLine();
        if(input.length() > 0) {
            h.setAddress(input);
        }
        
        System.out.print("Old Hotel Phone: " + h.getPhone() + ", New Hotel Phone: ");
        input = lineReader.nextLine();
        if(input.length() > 0) {
            h.setPhone(input);
        }
        
        hotelController.update(null, h);
        System.out.println("Hotel updated successfully.\n");
    }
    

    /* **************************  Delete Object methods   **************************** */
    

    /**
     * Delete a given customer from the system.
     * @param c The customer to be removed
     * @throws SQLException
     */
    public void deleteCustomer(Customer c) throws SQLException {
        boolean success = customerController.delete(null, c);
        if(success) {
            System.out.println("Customer successfully deleted\n");
        } else {
            //Should never get here, since customer is fetched from the database
            //before entering this method, so they are guaranteed to exist.
            System.out.println("Customer does not exist\n");
        }
    }
    
    /**
     * Delete a given staff member from the system.
     * @param s The staff member to be removed
     * @throws SQLException
     */
    public void deleteStaff(Staff s) throws SQLException {
        boolean success = staffController.delete(null, s);
        if(success) {
            System.out.println("Staff member successfully deleted\n");
        } else {
            //Should never get here, since s is fetched from the database
            //before entering this method, so they are guaranteed to exist.
            System.out.println("Staff member does not exist\n");
        }
    }
    
    /**
     * Delete a given check-in entry from the system.
     * @param ci the checkin to delete
     * @throws SQLException
     */
    public void deleteCheckIn(CheckIn ci) throws SQLException {
        boolean success = checkInController.delete(null, ci);
        if(success) {
            System.out.println("Check-in entry successfully deleted\n");
        } else {
            //Should never get here, since ci is fetched from the database
            //before entering this method, so they are guaranteed to exist.
            System.out.println("Check-in entry does not exist\n");
        }
    }
    
    /**
     * Delete a given hotel from the system.
     * @param h the hotel to delete
     * @throws SQLException
     */
    public void deleteHotel(Hotel h) throws SQLException {
        boolean success = hotelController.delete(null, h);
        if(success) {
            System.out.println("Hotel successfully deleted\n");
        } else {
            //Should never get here, since h is fetched from the database
            //before entering this method, so they are guaranteed to exist.
            System.out.println("Hotel does not exist\n");
        }
    }
     
     /**
     * Delete a room from the system.
     * @param r the room to be deleted.
     * @throws SQLException
     */
    public void deleteRoom( Room r ) throws SQLException {
    	boolean success = roomController.delete( null, r );
    	if ( success ) {
    		System.out.println("Room successfully deleted\n");
    	} else {
            //Should never get here, since r is fetched from the database
            //before entering this method, so they are guaranteed to exist.
            System.out.println("Room does not exist\n");
    	}
    }
    
    /* **************************  Utility methods   **************************** */
    
    /**
     * If s is not null, return its hotelID.
     * If s is null, then provide the user with
     * the list of hotels, and return the hotelID
     * that they pick.
     * @param lineReader used for user input
     * @param s description above
     * @return the chosen hotelID, or -1 if there are no hotels
     *  or if the user chooses to go back without selecting a hotel.
     * @throws SQLException 
     */
    public int findHotelID(Scanner lineReader, Staff s) throws SQLException {
        if(s != null) {
            return s.getHotelID();
        } else {
            List<Hotel> hList = hotelController.getAll();
            if(hList.size() == 0) {
                return -1;
            }
            while(true) {
                System.out.println("HotelID | Hotel Name ");
                System.out.println("---------------------");
                for(Hotel h : hList) {
                    System.out.printf("%-7d | %s\n", h.getHotelID(), h.getName());
                }
                System.out.print("Please enter a hotelID or type \"Return\" to go back: ");
                String input = lineReader.nextLine();
                System.out.println();
                if(input.equalsIgnoreCase("return")) {
                    return -1;
                }
                int hotelID;
                try {
                    hotelID = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid hotelID.");
                    continue;
                }
                boolean foundHotel = false;
                for(Hotel h : hList) {
                    if(h.getHotelID() == hotelID) {
                        foundHotel = true;
                        break;
                    }
                }
                if(foundHotel == true) {
                    return hotelID;
                } else {
                    System.out.println("Please enter a valid hotelID.");
                }
            }
        }
    }
    
    /**
     * Return a customerID chosen from the list of customers.
     * @param lineReader used for user input
     * @return
     * @throws SQLException
     */
    public int chooseCustomer(Scanner lineReader) throws SQLException {
        List<Customer> cList = getCustomerList();
        if(cList.size() == 0) {
            System.out.println("There are no customers to choose from.");
            return -1;
        }
        while(true) {
            System.out.println("CustomerID | Customer Name");
            System.out.println("----------------------------");
            for(Customer c : cList) {
                System.out.printf("%-11s| %s\n", c.getCustomerID(), c.getName());
            }
            System.out.println("Please choose a customerID, \n"
                    + "or type \"Return\" to go back.");
            String input = lineReader.nextLine();
            System.out.println();
            if(input.equalsIgnoreCase("return")) {
                return -1;
            } else {
                int customerID;
                try {
                    customerID = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid customerID.");
                    continue;
                }
                Customer c = getCustomer(customerID);
                if(c == null) {
                    System.out.println("Please enter a valid customerID");
                    continue;
                }
                return c.getCustomerID();
            }
        }
    }

    /**
     * Return a staff member chosen from the list of service staff.
     * @param lineReader used for user input
     * @return
     * @throws SQLException
     */
    public Staff chooseServiceStaff(Scanner lineReader, Staff s) throws SQLException {
        int hotelID = s.getHotelID();

        // Build a list of staff using the hotelID
        List<Staff> initialStaffList;// Do not use anywhere else
        List<Staff> sList; // Use me!
        
        initialStaffList = getStaffList();
        sList = new ArrayList<Staff>();
        for (Staff staff : initialStaffList) {
            if (staff.getHotelID() == hotelID && (staff.getStaffType().equalsIgnoreCase("room service staff")
                    || staff.getStaffType().equalsIgnoreCase("catering staff"))) {
                sList.add(staff);
            }
        }
        if(sList.size() == 0) {
            System.out.println("There are no service staff to choose from.\n");
            return null;
        }
        
        while(true) {
            System.out.println("staffID |       Job Title           |  Staff Name  ");
            System.out.println("--------------------------------------------------------");
            for(Staff staff : sList) {
                System.out.printf("%-7d | %-26s | %s\n", staff.getStaffID(), staff.getStaffType(), staff.getName());
            }
            
            System.out.println("Please choose a staffID");
            String input = lineReader.nextLine();
            System.out.println();
            int staffID;
            try {
                staffID = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid staffID.");
                continue;
            }
            Staff retStaff = getStaff(staffID);
            if(s == null) {
                System.out.println("Please enter a valid staffID");
                continue;
            }
            return retStaff;
        }
    }
    
    /**
     * Return a staffID chosen from the list of staff members.
     * @param lineReader used for user input
     * @param sList the list of staff members
     * @return the staffID chosen
     * @throws SQLException
     */
    public int chooseStaff(Scanner lineReader, List<Staff> sList) throws SQLException {
        while(true) {
            System.out.println("Please enter \"Manager\" to select a manager, \n"
                    + "\"Front Desk\" to select a front desk rep, \n"
                    + "\"Room Service Staff\" to select a room service staff, \n"
                    + "\"Catering Staff\" to select a catering staff, \n"
                    + "or \"Return\" to go back.");
            String input = lineReader.nextLine();
            if(input.equalsIgnoreCase("manager")) {
                List<Staff> managerList = new ArrayList<Staff>();
                for(Staff s : sList) {
                    if(s.getStaffType().equalsIgnoreCase(input)) {
                        managerList.add(s);
                    }
                }
                if(managerList.size() == 0) {
                    System.out.println("There are no managers to choose from.");
                    return -1;
                }
                
                System.out.println("staffID    | Job Title |  Staff Name  ");
                System.out.println("----------------------------------------");
                for(Staff s : managerList) {
                    System.out.printf("%-11s| %8s  | %s\n", s.getStaffID(), s.getStaffType(), s.getName());
                }
                System.out.println("Please choose a staffID, \n"
                        + "or type \"Return\" to go back.");
                input = lineReader.nextLine();
                System.out.println();
                if(input.equalsIgnoreCase("return")) {
                    return -1;
                } else {
                    int staffID;
                    try {
                        staffID = Integer.parseInt(input);
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid staffID.");
                        continue;
                    }
                    Staff s = getStaff(staffID);
                    if(s == null) {
                        System.out.println("Please enter a valid staffID");
                        continue;
                    }
                    return staffID;
                }
                
            } else if(input.equalsIgnoreCase("front desk")) {
                input = "Front Desk Representative";
                List<Staff> frontDeskList = new ArrayList<Staff>();
                for(Staff s : sList) {
                    if(s.getStaffType().equalsIgnoreCase(input)) {
                        frontDeskList.add(s);
                    }
                }
                if(frontDeskList.size() == 0) {
                    System.out.println("There are no front desk reps to choose from.");
                    return -1;
                }
                
                System.out.println("staffID    |       Job Title           |  Staff Name  ");
                System.out.println("--------------------------------------------------------");
                for(Staff s : frontDeskList) {
                    System.out.printf("%-11s| %s | %s\n", s.getStaffID(), s.getStaffType(), s.getName());
                }
                System.out.println("Please choose a staffID, \n"
                        + "or type \"Return\" to go back.");
                input = lineReader.nextLine();
                System.out.println();
                if(input.equalsIgnoreCase("return")) {
                    return -1;
                } else {
                    int staffID;
                    try {
                        staffID = Integer.parseInt(input);
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid staffID.");
                        continue;
                    }
                    Staff s = getStaff(staffID);
                    if(s == null) {
                        System.out.println("Please enter a valid staffID");
                        continue;
                    }
                    return staffID;
                }
            } else if(input.equalsIgnoreCase("room service staff")) {
                List<Staff> rsList = new ArrayList<Staff>();
                for(Staff s : sList) {
                    if(s.getStaffType().equalsIgnoreCase(input)) {
                        rsList.add(s);
                    }
                }
                if(rsList.size() == 0) {
                    System.out.println("There are no room service staff to choose from.");
                    return -1;
                }
                
                System.out.println("staffID    |       Job Title           |  Staff Name  ");
                System.out.println("--------------------------------------------------------");
                for(Staff s : rsList) {
                    System.out.printf("%-11s| %s | %s\n", s.getStaffID(), s.getStaffType(), s.getName());
                }
                System.out.println("Please choose a staffID, \n"
                        + "or type \"Return\" to go back.");
                input = lineReader.nextLine();
                System.out.println();
                if(input.equalsIgnoreCase("return")) {
                    return -1;
                } else {
                    int staffID;
                    try {
                        staffID = Integer.parseInt(input);
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid staffID.");
                        continue;
                    }
                    Staff s = getStaff(staffID);
                    if(s == null) {
                        System.out.println("Please enter a valid staffID");
                        continue;
                    }
                    return staffID;
                }
            } else if(input.equalsIgnoreCase("catering staff")) {
                List<Staff> cateringList = new ArrayList<Staff>();
                for(Staff s : sList) {
                    if(s.getStaffType().equalsIgnoreCase(input)) {
                        cateringList.add(s);
                    }
                }
                if(cateringList.size() == 0) {
                    System.out.println("There are no catering staff to choose from.");
                    return -1;
                }
                
                System.out.println("staffID    |   Job Title    |  Staff Name  ");
                System.out.println("---------------------------------------------");
                for(Staff s : cateringList) {
                    System.out.printf("%-11s| %s | %s\n", s.getStaffID(), s.getStaffType(), s.getName());
                }
                System.out.println("Please choose a staffID, \n"
                        + "or type \"Return\" to go back.");
                input = lineReader.nextLine();
                System.out.println();
                if(input.equalsIgnoreCase("return")) {
                    return -1;
                } else {
                    int staffID;
                    try {
                        staffID = Integer.parseInt(input);
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid staffID.");
                        continue;
                    }
                    Staff s = getStaff(staffID);
                    if(s == null) {
                        System.out.println("Please enter a valid staffID");
                        continue;
                    }
                    return staffID;
                }
            } else if(input.equalsIgnoreCase("return")){
                return -1;
            } else {
                System.out.println("Please enter valid input.");
            }
                    
        }
    }
    
    /**
     * Return a roomID chosen from the list of rooms.
     * @param lineReader used for user input
     * @param rList the list of rooms
     * @return the roomID chosen
     * @throws SQLException
     */
    public int chooseRoom(Scanner lineReader, List<Room> rList) throws SQLException {
        if(rList.size() == 0) {
            System.out.println("There are no rooms to choose from.");
            return -1;
        }
        while(true) {
            System.out.println("RoomID | Room Number");
            System.out.println("----------------------------");
            for(Room r : rList) {
                System.out.printf("%-11s| %s\n", r.getRoomID(), r.getRoomNumber());
            }
            System.out.println("Please choose a roomID, \n"
                    + "or type \"Return\" to go back.");
            String input = lineReader.nextLine();
            System.out.println();
            if(input.equalsIgnoreCase("return")) {
                return -1;
            } else {
                int roomID;
                try {
                    roomID = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid roomID.");
                    continue;
                }
                Room r = getRoom(roomID);
                if(r == null) {
                    System.out.println("Please enter a valid roomID");
                    continue;
                }
                return r.getRoomID();
            }
        }
    }
    
    /**
     * Return a hotelID chosen from the list of hotels.
     * @param lineReader used for user input
     * @param hList the list of hotels
     * @return the hotelID chosen
     * @throws SQLException
     */
    public int chooseHotel(Scanner lineReader, List<Hotel> hList) throws SQLException {
        if(hList.size() == 0) {
            System.out.println("There are no hotels to choose from.");
            return -1;
        }
        while(true) {
            System.out.println("HotelID | Hotel Name");
            System.out.println("-----------------------");
            for(Hotel h : hList) {
                System.out.printf("%-8s| %s\n", h.getHotelID(), h.getName());
            }
            System.out.println("Please choose a hotelID, \n"
                    + "or type \"Return\" to go back.");
            String input = lineReader.nextLine();
            System.out.println();
            if(input.equalsIgnoreCase("return")) {
                return -1;
            } else {
                int hotelID;
                try {
                    hotelID = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a hotelID.");
                    continue;
                }
                Hotel h = getHotel(hotelID);
                if(h == null) {
                    System.out.println("Please enter a valid hotelID");
                    continue;
                }
                return h.getHotelID();
            }
        }
    }
    
    /**
     * Return a roomID (primary key for CheckIn relation) chosen from a list of check-in entries.
     * @param lineReader
     * @param ciList the list of checkin entries
     * @return the roomID chosen from the list of checkin entries
     * @throws SQLException
     */
    public int chooseCheckIn(Scanner lineReader, List<CheckIn> ciList) throws SQLException {
        if(ciList.size() == 0) {
            System.out.println("There are no check-ins to choose from.");
            return -1;
        }
        while(true) {
            System.out.println("\nRoomID | CustomerID | Room Number");
            System.out.println("-----------------------------------");
            for(CheckIn ci : ciList) {
                System.out.printf("%-7s| %-11s| %s\n", ci.getRoomID(), ci.getCustomerID(), getRoom(ci.getRoomID()).getRoomNumber());
            }
            System.out.println("Please choose a roomID, \n"
                    + "or type \"Return\" to go back.");
            String input = lineReader.nextLine();
            System.out.println();
            if(input.equalsIgnoreCase("return")) {
                return -1;
            } else {
                int roomID;
                try {
                    roomID = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid roomID.");
                    continue;
                }
                CheckIn ci = getSpecificCheckIn(roomID);
                if(ci == null) {
                    System.out.println("Please enter a valid roomID");
                    continue;
                }
                return ci.getRoomID();
            }
        }
    }
    
    /**
     * Print a list of check-in entries, a helper method
     * @param ciList the list of checkin entries
     * @throws SQLException
     */
    public void printCheckInList(List<CheckIn> ciList) throws SQLException {
        if(ciList.size() == 0) {
            System.out.println("There is nothing to print; checkin list is empty.");
            return;
        }
        System.out.println("\nRoomID | CustomerID | Room Number | Number in Party | Check-in Date/Time    | Check-out Date/Time");
        System.out.println("------------------------------------------------------------------------------------------------");
        for(CheckIn ci : ciList) {
        	System.out.printf("%-7s| %-11s| %11s | %15s | %19s | %s\n", ci.getRoomID(), ci.getCustomerID(), getRoom(ci.getRoomID()).getRoomNumber(), 
        			ci.getNumberInParty(), ci.getCheckInDateTime(), ci.getCheckOutDateTime());
        }
    }
    
    

    
}
