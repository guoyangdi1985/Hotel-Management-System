package centralController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.sql.Timestamp;
import cAssignedTo.CAssignedTo;
import checkin.CheckIn;
import customer.Customer;
import room.Room;
import rsAssignedTo.RSAssignedTo;
import staff.Staff;
import hotel.Hotel;

/**
 * The user interface for the hotel management system
 */
public class HotelManagementUI {
	private static SystemController controller = new SystemController();
	private static Scanner lineReader;

	public static void main(String args[]) {
		try {
			lineReader = new Scanner(System.in);
			// Get log in class from user.
			while (true) {
				System.out.println("Type \"Customer\" to log in as a customer, \n\"Staff\" to log in as a staff,\n"
						+ "\"Admin\" to log in as an administrator, \nor \"Exit\" to exit.");
				String input = lineReader.nextLine();
				System.out.println();
				if (input.equalsIgnoreCase("admin")) {
					adminActions();
				} else if (input.equalsIgnoreCase("customer")) {
					customerActions();
				} else if (input.equalsIgnoreCase("staff")) {
					staffActions();
				} else if (input.equalsIgnoreCase("exit")) {
					System.out.println("Terminating Hotel Management System");
					System.exit(0);
				} else {
					System.out.println("Please provide valid input");
				}
			}
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	/**
	 * This provides the Administrator the tools necessary to make any changes
	 * to the system he/she desires.
	 * 
	 * @throws SQLException
	 */
	private static void adminActions() throws SQLException {
		// Figure out which action the user wants to do.
		while (true) {
			System.out.println("Please type \"Manage Hotels\" to view/add/edit/delete hotels from the system, \n"
					+ "\"Manage Staff\" to view/add/edit/delete staff from the system, \n"
					+ "\"Manage Rooms\" to view/add/edit/delete rooms from the system, \n"
					+ "\"Manage Customers\" to view/add/edit/delete customers from the system, \n"
					+ "\"Manage Checkins\" to view/add/edit/delete checkins from the system, \n"
					+ "\"View Reports\" to view occupancy reports, \n" + "or \"Return\" to return to the main menu.\"");
			String input = lineReader.nextLine();
			System.out.println();
			if (input.equalsIgnoreCase("return")) {
				break;
			} else if (input.equalsIgnoreCase("Manage Hotels")) {
				manageHotels();
			} else if (input.equalsIgnoreCase("Manage Staff")) {
				manageStaff(null);
			} else if (input.equalsIgnoreCase("Manage Rooms")) {
				manageRooms();
			} else if (input.equalsIgnoreCase("Manage Customers")) {
				manageCustomers(null);
			} else if (input.equalsIgnoreCase("Manage Checkins")) {
				manageCheckins(null);
			} else if (input.equalsIgnoreCase("View Reports")) {
				viewReports(null);
			} else {
				System.out.println("Please enter a valid action.\n");
			}
		}
	}

	/**
	 * This provides a list of staff types to log in as, then sends the user to
	 * the appropriate action method.
	 * 
	 * @throws SQLException
	 */
	private static void staffActions() throws SQLException {
		// Figure out which staff type the user wants to log in as.
		while (true) {
			System.out.println("Type \"Manager\" to log in as a manager, \n\"Front Desk\" to log in as "
					+ "a front desk representative, \n\"Catering\" to log in as catering, \nor "
					+ "\"Room Service\" to log in as room service, \n" + "or \"Return\" to return to the main menu");
			String input = lineReader.nextLine();
			System.out.println();
			if (input.equalsIgnoreCase("manager")) {
				managerActions();
				return;
			} else if (input.equalsIgnoreCase("front desk")) {
				frontDeskActions();
				return;
			} else if (input.equalsIgnoreCase("catering")) {
				cateringActions();
				return;
			} else if (input.equalsIgnoreCase("room service")) {
				roomServiceActions();
				return;
			} else if (input.equalsIgnoreCase("return")) {
				return;
			} else {
				System.out.println("Please provide valid input");
			}
		}
	}

	/**
	 * This provides the user a list of room service staff to log in as, then
	 * provides a list of actions to choose from.
	 * 
	 * @throws SQLException
	 */
	private static void roomServiceActions() throws SQLException {
		// Display the list of room service staff, so the user can
		// pick a staffID to log in as.
		List<Staff> staffList = null;
		staffList = controller.getStaffList("Room Service Staff");
		if (staffList.size() == 0) {
			System.out.println("There are no room service staff available.");
			return;
		}
		while (true) {
			System.out.println("\nstaffID    |       Job Title           |  Staff Name  ");
			System.out.println("--------------------------------------------------------");
			for (Staff s : staffList) {
				System.out.printf("%-11s| %s | %s\n", s.getStaffID(), s.getStaffType(), s.getName());
			}
			System.out
					.println("Please enter a staffID to log in, \n" + "or type \"Return\" to return to the main menu.");
			String input = lineReader.nextLine();
			System.out.println();
			if (input.equalsIgnoreCase("return")) {
				return;
			} else {
				int staffID;
				try {
					staffID = Integer.parseInt(input);
				} catch (NumberFormatException e) {
					System.out.println("Please enter a valid staffID.");
					continue;
				}
				Staff s = controller.getStaff(staffID);
				if (s == null) {
					System.out.println("Please enter a valid staffID");
					continue;
				}
				// Pick a desired action
				while (true) {
					System.out.println(
							"Please type \"View Customers\" to view your list of assigned rooms and customers, \n"
									+ " or \"Return\" to return to the staff menu.\"");
					input = lineReader.nextLine();
					System.out.println();
					if (input.equalsIgnoreCase("return")) {
						break;
					} else if (input.equalsIgnoreCase("View Customers")) {
						viewServiceStaffCustomers(s);
					} else {
						System.out.println("Please enter a valid action.\n");
					}
				}
			}
		}
	}

	/**
	 * This provides the user a list of catering staff to log in as, then
	 * provides a list of actions to choose from.
	 * 
	 * @throws SQLException
	 */
	private static void cateringActions() throws SQLException {
		// Display the list of catering staff, so the user can
		// pick a staffID to log in as.
		List<Staff> staffList = null;
		staffList = controller.getStaffList("Catering Staff");
		if (staffList.size() == 0) {
			System.out.println("There are no catering staff available.");
			return;
		}
		while (true) {
			System.out.println("\nstaffID    |   Job Title    |  Staff Name  ");
			System.out.println("---------------------------------------------");
			for (Staff s : staffList) {
				System.out.printf("%-11s| %s | %s\n", s.getStaffID(), s.getStaffType(), s.getName());
			}
			System.out
					.println("Please enter a staffID to log in, \n" + "or type \"Return\" to return to the main menu.");
			String input = lineReader.nextLine();
			if (input.equalsIgnoreCase("return")) {
				return;
			} else {
				int staffID;
				try {
					staffID = Integer.parseInt(input);
				} catch (NumberFormatException e) {
					System.out.println("Please enter a valid staffID.");
					continue;
				}
				Staff s = controller.getStaff(staffID);
				if (s == null) {
					System.out.println("Please enter a valid staffID");
					continue;
				}
				// Pick a desired action.
				while (true) {
					System.out.println(
							"Please type \"View Customers\" to view your list of assigned rooms and customers, \n"
									+ " or \"Return\" to return to the staff menu.\"");
					input = lineReader.nextLine();
					System.out.println();
					if (input.equalsIgnoreCase("return")) {
						break;
					} else if (input.equalsIgnoreCase("View Customers")) {
						viewServiceStaffCustomers(s);
					} else {
						System.out.println("Please enter a valid action.\n");
					}
				}
			}
		}
	}

	/**
	 * This provides the user a list of front desk reps to log in as, then
	 * provides a list of actions to choose from.
	 * 
	 * @throws SQLException
	 */
	private static void frontDeskActions() throws SQLException {
		// Display the list of front desk rep, so the user can
		// pick a staffID to log in as.
		List<Staff> staffList = null;
		staffList = controller.getStaffList("Front Desk Representative");
		if (staffList.size() == 0) {
			System.out.println("There are no front desk reps available.");
			return;
		}
		while (true) {
			System.out.println("\nstaffID    |       Job Title           |  Staff Name  ");
			System.out.println("--------------------------------------------------------");
			for (Staff s : staffList) {
				System.out.printf("%-11s| %s | %s\n", s.getStaffID(), s.getStaffType(), s.getName());
			}
			System.out
					.println("Please enter a staffID to log in,\n" + " or type \"Return\" to return to the main menu.");
			String input = lineReader.nextLine();
			if (input.equalsIgnoreCase("return")) {
				return;
			} else {
				int staffID;
				try {
					staffID = Integer.parseInt(input);
				} catch (NumberFormatException e) {
					System.out.println("Please enter a valid staffID.");
					continue;
				}
				Staff s = controller.getStaff(staffID);
				if (s == null) {
					System.out.println("Please enter a valid staffID");
					continue;
				}
				// Pick a desired action.
				while (true) {
					System.out.println(
							"Please type \"Manage Customers\" to view/add/edit/delete customers from the system, \n"
									+ "\"Manage Checkins\" to view/add/edit/delete checkins from the system, \n"
									+ "\"View Billing\" to view billing reports, \n"
									+ "\"View Rooms\" to view available rooms for a given room type,\n"
									+ " or \"Return\" to return to the staff menu.\"");
					input = lineReader.nextLine();
					System.out.println();
					if (input.equalsIgnoreCase("return")) {
						break;
					} else if (input.equalsIgnoreCase("Manage Customers")) {
						manageCustomers(s);
					} else if (input.equalsIgnoreCase("Manage Checkins")) {
						manageCheckins(s);
					} else if (input.equalsIgnoreCase("View Billing")) {
						viewBilling(s, lineReader);
					} else if (input.equalsIgnoreCase("View Rooms")) {
						viewRooms(s, lineReader);
					} else {
						System.out.println("Please enter a valid action.\n");
					}
				}
			}
		}
	}

	/**
	 * This provides the user a list of managers to log in as, then provides a
	 * list of actions to choose from.
	 * 
	 * @throws SQLException
	 */
	private static void managerActions() throws SQLException {
		// Display the list of managers, so the user can
		// pick a staffID to log in as.
		List<Staff> staffList = null;
		staffList = controller.getStaffList("Manager");
		if (staffList.size() == 0) {
			System.out.println("There are no managers available.");
			return;
		}
		while (true) {
			System.out.println("\nstaffID    | Job Title |  Staff Name  ");
			System.out.println("----------------------------------------");
			for (Staff s : staffList) {
				System.out.printf("%-11s| %8s  | %s\n", s.getStaffID(), s.getStaffType(), s.getName());
			}
			System.out.println("Please enter a staffID to log in, \n" + "or type \"Return\" to return to the main menu.");
			String input = lineReader.nextLine();
			if (input.equalsIgnoreCase("return")) {
				return;
			} else {
				int staffID;
				try {
					staffID = Integer.parseInt(input);
				} catch (NumberFormatException e) {
					System.out.println("Please enter a valid staffID.");
					continue;
				}
				Staff s = controller.getStaff(staffID);
				if (s == null) {
					System.out.println("Please enter a valid staffID");
					continue;
				}
				// Pick a desired action
				while (true) {
					System.out.println("Please type \"Manage Staff\" to view/add/edit/delete staff from the system, \n"
							+ "\"Manage Customers\" to view/add/edit/delete customers from the system, \n"
							+ "\"Manage Checkins\" to view/add/edit/delete checkins from the system, \n"
							+ "\"Edit Room Rate\" to edit room rates, \n"
							+ "\"View Reports\" to view occupancy reports, \n"
							+ "\"View Service Staff Customers\" to view information on customers assigned to a given Room Service or Catering staff member, \n"
							+ "or \"Return\" to return to the staff menu.\"");
					input = lineReader.nextLine();
					System.out.println();
					if (input.equalsIgnoreCase("return")) {
						break;
					} else if (input.equalsIgnoreCase("Manage Staff")) {
						manageStaff(s);
					} else if (input.equalsIgnoreCase("Manage Customers")) {
						manageCustomers(s);
					} else if (input.equalsIgnoreCase("Manage Checkins")) {
						manageCheckins(s);
					} else if (input.equalsIgnoreCase("Edit Room Rate")) {
						manageRoomRate(s);
					} else if (input.equalsIgnoreCase("View Reports")) {
						viewReports(s);
					} else if (input.equalsIgnoreCase("View Service Staff Customers")) {
					    Staff staff = controller.chooseServiceStaff(lineReader, s);
					    if(staff == null) {
					        continue;
					    }
					    viewServiceStaffCustomers(staff);
					} else {
						System.out.println("Please enter a valid action.\n");
					}
				}
			}
		}
	}

	/**
	 * This lets the user choose a customer to log in as, then provides a set of
	 * actions for them to choose from.
	 * 
	 * @throws SQLException
	 */
	private static void customerActions() throws SQLException {
		// Display the list of customers, so the user can
		// pick a customerID to log in as.
		List<Customer> customerList = null;
		customerList = controller.getCustomerList();
		if (customerList.size() == 0) {
			System.out.println("There are no customers available.");
			return;
		}
		while (true) {
			System.out.println("\nCustomerID | Customer Name");
			System.out.println("----------------------------");
			for (Customer c : customerList) {
				System.out.printf("%-11s| %s\n", c.getCustomerID(), c.getName());
			}
			System.out.println(
					"Please enter a customerID to log in, \n" + "or type \"Return\" to return to the main menu.");
			String input = lineReader.nextLine();
			if (input.equalsIgnoreCase("return")) {
				return;
			} else {
				int customerID;
				try {
					customerID = Integer.parseInt(input);
				} catch (NumberFormatException e) {
					System.out.println("Please enter a valid customerID.");
					continue;
				}
				Customer c = controller.getCustomer(customerID);
				if (c == null) {
					System.out.println("Please enter a valid customerID");
					continue;
				}
				// Pick a desired action.
				while (true) {
					System.out.println("Type \"View info\" to view your information, \n"
							+ "\"Update info\" to update your information, \n"
							+ "\"View Check Ins\" to view your check in information, \n"
							+ "\"View Bill\" to view your billing report, \n"
							+ "or \"Return\" to return to the customer select menu.");
					input = lineReader.nextLine();
					System.out.println();
					if (input.equalsIgnoreCase("return")) {
						break;
					} else if (input.equalsIgnoreCase("view info")) {
						System.out.println(c.toString());
						System.out.println();
					} else if (input.equalsIgnoreCase("update info")) {
						try {
							// c gets changed during updateCustomer, so this
							// refreshes the correct customer information
							c = controller.getCustomer(customerID);
							controller.updateCustomerInformation(lineReader, c);
						} catch (SQLException e) {
							System.out.println("Error updating customer: " + e.getMessage());
						}
					} else if (input.equalsIgnoreCase("view check ins")) {
						List<CheckIn> ciList = controller.getCustomerCheckIns(customerID);
						System.out.println("Room Number | Starting Date and Time | Ending Date and Time | Party Size | "
								+ "Has Catering | Has Room Service | Restaurant Bill | Laundry Bill | "
								+ "Phone Bill | Payment Method");
						for (CheckIn ci : ciList) {
							int roomNumber = controller.getRoom(ci.getRoomID()).getRoomNumber();
							System.out.printf("%-12s| %-23s| %-21s| %-11s| %-13s| %-17s| %-16s| %-13s| %-11s| %s\n",
									roomNumber, ci.getCheckInDateTime(), ci.getCheckOutDateTime(),
									ci.getNumberInParty(), ci.getHasCatering(), ci.getHasRoomService(),
									ci.getRestaurantBill(), ci.getLaundryBill(), ci.getPhoneBill(),
									ci.getPaymentMethod());
						}
					} else if (input.equalsIgnoreCase("view bill")) {
						List<CheckIn> ciList = controller.getCustomerCheckIns(customerID);
						System.out.println("Room ID");
						for (CheckIn ci : ciList) {
							System.out.println(ci.getRoomID());
						}
						System.out.println("Type: \"all rooms\" for total report.");
						input = lineReader.nextLine();

						if (input.equalsIgnoreCase("all rooms")) {
							// total billing report
							printBillingForCustomer(c);
						} else {
							// print billing report for single checkout
							int roomID;
							try {
								roomID = Integer.parseInt(input);
							} catch (NumberFormatException e) {
								System.out.println("Please enter a valid roomID.");
								continue;
							}
							CheckIn ci = controller.getSpecificCheckIn(roomID);
							if (ci == null) {
								System.out.println("Please enter a valid roomID");
								continue;
							}
							printBillingReportForSingleCheckIn(ci);
						}
					} else {
						System.out.println("Please enter a valid action.");
					}
				}
			}
		}
	}

	/**
	 * This methods lets the user view all of the customers, and their assigned
	 * rooms, for a given service staff member. The passed staff object should
	 * be either a room service or catering staff, or this method will produce
	 * no meaningful output.
	 * 
	 * @param s
	 *            - Used to determine the hotelID if necessary.
	 * @throws SQLException
	 */
	private static void viewServiceStaffCustomers(Staff s) throws SQLException {
		int hotelID = controller.findHotelID(lineReader, s);
		if (hotelID == -1) {
			return;
		}

		if(s.getStaffType().equalsIgnoreCase("catering staff")) {
    		List<CAssignedTo> initialCList = controller.getCateringList();
    		List<CAssignedTo> sortedList = new ArrayList<CAssignedTo>();
    		for(CAssignedTo service : initialCList) {
                if(service.getHotelID() == s.getHotelID() && service.getStaffID() == s.getStaffID()) {
                    sortedList.add(service);
                }
            }
    		if(sortedList.size() == 0) {
    		    System.out.println("This service staff has no assigned rooms.\n");
    		    return;
    		}
    		System.out.println("StaffID | RoomID | CustomerID | Customer Name");
    		for(CAssignedTo service : sortedList) {
    		    Customer c = controller.getCustomer(service.getCustomerID());
    		    System.out.printf("%-7d | %-6d | %-10d | %s\n", service.getStaffID(), service.getRoomID(), service.getCustomerID(), c.getName());
    		}
    		System.out.println();
		} else {
		    List<RSAssignedTo> initialRsList = controller.getRoomServiceList();
		    List<RSAssignedTo> sortedList = new ArrayList<RSAssignedTo>();
            for(RSAssignedTo service : initialRsList) {
                if(service.getHotelID() == s.getHotelID() && service.getStaffID() == s.getStaffID()) {
                    sortedList.add(service);
                }
            }
            if(sortedList.size() == 0) {
                System.out.println("This service staff has no assigned rooms.\n");
                return;
            }
            System.out.println("StaffID | RoomID | CustomerID | Customer Name");
            for(RSAssignedTo service : sortedList) {
                Customer c = controller.getCustomer(service.getCustomerID());
                System.out.printf("%8d | %6d | %10d | %s", service.getStaffID(), service.getRoomID(), service.getCustomerID(), c.getName());
            }
            System.out.println();
		}
		
		
	}

	/**
	 * This method gives the user a choice of room type, then prints the list of
	 * available rooms with that room type.
	 * 
	 * @param s - Used to determine the hotelID if necessary.
	 * @param lineReader - Is the Scanner to use the read the user input.
	 * @throws SQLException
	 */
	private static void viewRooms(Staff s, Scanner lineReader) throws SQLException {
		int hotelID = controller.findHotelID(lineReader, s);
		if (hotelID == -1) {
			return;
		}

		// get room type to use
		List<Room> rList = new ArrayList<Room>();
		System.out.println("Type the room type to view availability for (\"Economy\", \"Deluxe\", \"Executive Suite\","
				+ "\n\"Presidential Suite\", \"all types\"), or type \"return\" to back.");
		String input = lineReader.nextLine();
		if (input.equalsIgnoreCase("economy")) {
			rList = controller.getRoomType("Economy");
		} else if (input.equalsIgnoreCase("deluxe")) {
			rList = controller.getRoomType("Deluxe");
		} else if (input.equalsIgnoreCase("executive suite")) {
			rList = controller.getRoomType("Executive Suite");
		} else if (input.equalsIgnoreCase("presidential suite")) {
			rList = controller.getRoomType("Presidential Suite");
		} else if (input.equalsIgnoreCase("all types")) {
			rList = controller.getRoomList();
		}
		
		// filter room for current hotel
		rList = filterRoomByHotelID(rList, hotelID);
		
		// filter rooms for currently rooms available only from this time period or is free
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());	
		List<Room> rAvailableList = new ArrayList<Room>();
		for (Room r : rList) {
			CheckIn ci = controller.getSpecificCheckIn(r.getRoomID());
			// check if room is free or does not collide with the check-in time
			if (ci == null || !datesCollide(ci.getCheckInDateTime(), ci.getCheckOutDateTime(), currentTime, currentTime)) {
				rAvailableList.add(r);
			}
		}

		// validate the is something in the list
		if (rAvailableList.size() == 0) {
			System.out.println("There are no rooms currently available.");
			return;
		}

		// view room availability for given type
		System.out.println("Room ID | Room Number | Room Type | Rate   | Max Occupancy");
		for (Room ra : rAvailableList) {
			System.out.printf("%-7s | %-11s | %-9s | %-6s | %s\n" , ra.getRoomID(), ra.getRoomNumber(), ra.getRoomType(), ra.getRate(), ra.getMaxOccupancy());
		}
	}

	/**
	 * This method gives the user the choice of printing a billing report for a
	 * given check-in, or for a given customer. They first choose whether to view
	 * a report for a check-in or customer, then choose one of them from a list.
	 * 
	 * @param s - Used to determine the hotelID if necessary.
	 * @param lineReader - Is the Scanner to use the read the user input.
	 * @throws SQLException is thrown when the database returns an exception
	 */
	private static void viewBilling(Staff s, Scanner lineReader) throws SQLException {
		int hotelID = controller.findHotelID(lineReader, s);
		if (hotelID == -1) {
			return;
		}
		
		System.out.println("Type \"Customer\" for billing report for a customer, \"Checkin\" for\na billing report for a single check-in or \"return\" to back.");
		String input = lineReader.nextLine();
		
		// billing report for a customer
		if (input.equalsIgnoreCase("customer")) {
			// print customer list
			List<Customer> customerList = null;
			customerList = controller.getCustomerList();
			if (customerList.size() == 0) {
				System.out.println("There are no customers available.");
				return;
			}
			
			System.out.println("\nCustomerID | Customer Name");
			System.out.println("----------------------------");
			for (Customer c : customerList) {
				System.out.printf("%-11s| %s\n", c.getCustomerID(), c.getName());
			}
			System.out.println("Please enter a customerID to log in, \n" + "or type \"Return\" to return to the main menu.");
			
			// get a customer
			input = lineReader.nextLine();
			if (input.equalsIgnoreCase("return")) {
				return;
			} else {
				int customerID;
				try {
					customerID = Integer.parseInt(input);
				} catch (NumberFormatException e) {
					System.out.println("Please enter a valid customerID.");
					return;
				}
				Customer c = controller.getCustomer(customerID);
				if (c == null) {
					System.out.println("Please enter a valid customerID");
					return;
				}
			// total billing report
			printBillingForCustomer(c);
			}
		}
		// billing report for a check-in
		else if (input.equalsIgnoreCase("checkin")) {
			// print check-in list
			List<CheckIn> ciList = controller.getCheckInList();
			ciList = filterCheckInByHotelID(ciList, hotelID);
			System.out.println("Room ID");
			for (CheckIn ci : ciList) {
				System.out.println(ci.getRoomID());
			}
			System.out.println("Type the Room ID to get the billing report for.");
			// get user selected check in
			input = lineReader.nextLine();
			int roomID;
			try {
				roomID = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid roomID.");
				return;
			}
			CheckIn ci = controller.getSpecificCheckIn(roomID);
			if (ci == null) {
				System.out.println("Please enter a valid roomID");
				return;
			}
			// print billing report for check-in
			printBillingReportForSingleCheckIn(ci);
		}
		// return
		else if (input.equalsIgnoreCase("return")) {
			return;
		}		
		// invalid command
		else {
			System.out.println("Invalid command.");
			return;
		}
	}

	/**
	 * This method asks for a room type, and date range, then prints occupancy
	 * reports given that information. It prints a list of rooms, plus the id's
	 * of customers who are checked in, plus the dates that the customer is
	 * occupying the room. It also prints the % of rooms occupied given this
	 * information.
	 * 
	 * @param s
	 *            - Used to determine the hotelID if necessary.
	 * @throws SQLException
	 */
	private static void viewReports(Staff s) throws SQLException {
		int hotelID = controller.findHotelID(lineReader, s);
		if (hotelID == -1) {
			return;
		}
		
		// The full lists
		List<CheckIn> initialCheckInList = controller.getCheckInList();
		List<Room> initialRoomList = controller.getRoomList();
		//List<Hotel> initialHotelList = controller.getHotelList();
		
		// Now get the lists pertaining to the relevant hotelID
		//    ... checkin list
		List<CheckIn> ciList = new ArrayList<CheckIn>();
		int totalOccupancy = 0; // counter for total occupancy included in report
		for (CheckIn checkin : initialCheckInList) {
			int roomID = checkin.getRoomID();
			if (controller.getRoom(roomID).getHotelID() == hotelID) {
				ciList.add(checkin);
				totalOccupancy += checkin.getNumberInParty();
			}
		}
		//    ... room list
		List<Room> rList = new ArrayList<Room>();
		for (Room r : initialRoomList) {
			if (r.getHotelID() == hotelID) {
				rList.add(r);
			}
		}
		int maxOccupancy = 0;
		for (Room r : rList) {
		    maxOccupancy += r.getMaxOccupancy();
		}
		
		// Report the general information about the hotel before continuing
		Hotel h = controller.getHotel(hotelID);
		System.out.println("Selected hotel is " + h.getName() + " with hotel ID " + h.getHotelID());
		System.out.println("It has " + rList.size() + " total rooms, " + ciList.size() + " of which are occupied.");
		double percentOccupied = ((double) ciList.size()) / rList.size() * 100.0;
		System.out.printf("Therefore, the %% of rooms occupied is: %.2f \n", percentOccupied);
		System.out.printf("The total occupancy for the hotel is: %d / %d \n", totalOccupancy, maxOccupancy);
		System.out.println();
		
		// For this hotel we ask the user if they want to get a report for 
		// a specific room type and date range
		System.out.print("Do you wish to continue? (Y/N): ");
		String input = lineReader.nextLine();
		if (!input.equalsIgnoreCase("Y")) {
			return;
		}
		
		while (true) {
			System.out.print("Please enter a room type: ");
			input = lineReader.nextLine();
			
			//Validate room type input, and allow for input without perfect matching.
			if (input == null || input.length() == 0) {
	            System.out.println("Please enter a valid room type.\n");
	            continue;
	        } else if(input.equalsIgnoreCase("economy")) {
	            input = "Economy";
	        } else if(input.equalsIgnoreCase("deluxe")) {
	            input = "Deluxe";
	        } else if(input.equalsIgnoreCase("executive suite")) {
	            input = "Executive Suite";
	        } else if(input.equalsIgnoreCase("Presidential Suite")) {
	            input = "Presidential Suite";
	        } else {
	            System.out.println("Please enter a valid room type.\n");
                continue;
	        }
			
			// Build up lists that filter out the previous lists based on room type
			List<CheckIn> ciListRoomType = new ArrayList<CheckIn>();
			List<Room> rListRoomType = new ArrayList<Room>();
			for (CheckIn ci : ciList) {
				if ((controller.getRoom(ci.getRoomID())).getRoomType().equals(input)) {
					ciListRoomType.add(ci);
				}
			}
			for (Room r : rList) {
				if (r.getRoomType().equals(input)) {
					rListRoomType.add(r);
				}
			}

			// Build up lists that take the previous lists and filter out further based on start and end date
			Timestamp startDateTime = new Timestamp(0); 
			Timestamp endDateTime = new Timestamp(0);
				
			System.out.print("Please enter a start date (yyyy-mm-dd hh:mm:ss): ");
			input = lineReader.nextLine();
			if (input.length() > 0) {
				try {
					startDateTime = Timestamp.valueOf(input);
				} catch (Exception e) {
					throw new SQLException("Date & time not in proper format.");
				}
			}
				
			System.out.print("Please enter an end date (yyyy-mm-dd hh:mm:ss): ");
			input = lineReader.nextLine();
			if (input.length() > 0) {
				try {
					endDateTime = Timestamp.valueOf(input);
				} catch (Exception e) {
					throw new SQLException("Date & time not in proper format.");
				}
			}
				
			// Build up lists that filter out the previous lists based on the given start and end date
			List<CheckIn> ciListRT_DT = new ArrayList<CheckIn>();
			for (CheckIn ci : ciListRoomType) {
				Timestamp currCI_DT = ci.getCheckInDateTime();
				Timestamp currCO_DT = ci.getCheckOutDateTime();
				
				// Which checkins collide with the start and end date?
				if (currCI_DT.compareTo(endDateTime) <= 0 && currCO_DT.compareTo(startDateTime) >= 0) {
					ciListRT_DT.add(ci);
				}
			}
				
			// Report on rooms of that type and date range that have been checked into
			System.out.println();
			System.out.println("The rooms of that type and date range that have been checked into: ");
			System.out.println("(Number of results: " + ciListRT_DT.size() + ")");
			controller.printCheckInList(ciListRT_DT);
			System.out.println();
				
			// Report on rooms of that type that have NOT been checked into for the date range given
			List<Room> availableRooms = new ArrayList<Room>();
			for (Room r : rListRoomType) {
				// test if room is NOT listed in the checkins
				boolean found = false;
				for (CheckIn ci : ciListRT_DT) {
					if (ci.getRoomID() == r.getRoomID()) {
						found = true;
						break;
					}
				}
				if (!found) {
					availableRooms.add(r);
				}
			}
			System.out.println("The rooms of that type that have NOT been checked into for the date range: ");
			if (availableRooms.size() == 0) {
				System.out.println("There are no rooms to show.");
				System.out.println();
			}
			else {
				System.out.println("(Number of results: " + availableRooms.size() + ")");
				for (Room r : availableRooms) {
					System.out.println(r);
					System.out.println();
				}
			}
				
			// Ask user whether to continue
			System.out.print("Wish to view another report based on room type and date range for this hotel? (Y/N) :");
			input = lineReader.nextLine();
			if (!input.equalsIgnoreCase("Y")) {
				return;
			}
		}
	}

	/**
	 * This method lets the user View/Add/Edit/Delete any and all check in
	 * information.
	 * 
	 * @param s
	 *            - Used to determine the hotelID if necessary.
	 * @throws SQLException
	 */
	private static void manageCheckins(Staff s) throws SQLException {
		int hotelID = controller.findHotelID(lineReader, s);
		if (hotelID == -1) {
			return;
		}

		// Build a list of checkins using the hotelID
		List<CheckIn> initialCheckInList; // Do not use anywhere else
		List<CheckIn> ciList; // Use me!

		// Choose which action to take.
		while (true) {
			// Rebuild the list each loop, as the checkin information
			// may change as it is added, edited, and/or deleted from
			// the database
			initialCheckInList = controller.getCheckInList();
			ciList = new ArrayList<CheckIn>();
			for (CheckIn checkin : initialCheckInList) {

				// Hotel ID not an attribute of CheckIn relation, so must find
				// it using the roomID
				int roomID = checkin.getRoomID();
				if (controller.getRoom(roomID).getHotelID() == hotelID) {
					ciList.add(checkin);
				}
			}

			System.out.println("Type \"View Checkins\" to view check-in information, \n"
					+ "\"Add CheckIn\" to add a check-in entry, \n" + "\"Edit CheckIn\" to edit a check-in entry, \n"
					+ "\"Delete CheckIn\" to delete a check-in entry, \n" + "\"Update bill\" to update bill for a check-in entry, \n"
					+ "or \"Return\" to go back.");

			String input = lineReader.nextLine();
            System.out.println();

			// View check-in information
			if (input.equalsIgnoreCase("view checkins")) {
				int roomID = controller.chooseCheckIn(lineReader, ciList);
				if (roomID == -1) {
					continue;
				} else {
					CheckIn checkin = controller.getSpecificCheckIn(roomID);
					System.out.println(checkin.toString());
					System.out.println();
				}
			}

			// Add a check-in entry
			else if (input.equalsIgnoreCase("add checkin")) {
				try {
					controller.addCheckIn(lineReader);
				} catch (SQLException e) {
					System.out.println("Error adding check-in: " + e.getMessage());
				}
			}

			// Edit a check-in entry
			else if (input.equalsIgnoreCase("edit checkin")) {
				int roomID = controller.chooseCheckIn(lineReader, ciList);
				if (roomID == -1) {
					continue;
				} else {
					try {
						CheckIn checkin = controller.getSpecificCheckIn(roomID);
						controller.updateCheckInInformation(lineReader, checkin);
					} catch (SQLException e) {
						System.out.println("Error updating check-in: " + e.getMessage());
					}
				}
			}

			// Delete a check-in entry from the system
			else if (input.equalsIgnoreCase("delete checkin")) {
				int roomID = controller.chooseCheckIn(lineReader, ciList);
				if (roomID == -1) {
					continue;
				} else {
					CheckIn checkin = controller.getSpecificCheckIn(roomID);
					controller.deleteCheckIn(checkin);
				}
			}
			
			// Update a bill for a check-in entry
			else if (input.equalsIgnoreCase("update bill")) {
				int roomID = controller.chooseCheckIn(lineReader, ciList);
				if (roomID == -1) {
					continue;
				} else {
					CheckIn checkin = controller.getSpecificCheckIn(roomID);
					controller.addToBill(lineReader, checkin);
				}
			}

			// Return
			else if (input.equalsIgnoreCase("return")) {
				return;
			}

			// Invalid action was entered
			else {
				System.out.println("Please enter a valid action.");
			}
		}
	}

	/**
	 * This method lets the user update the room rate
	 * 
	 * @param s
	 *            - Used to determine the hotelID if necessary.
	 * @throws SQLException
	 */
	private static void manageRoomRate(Staff s) throws SQLException {
		int hotelID = controller.findHotelID(lineReader, s);
		if (hotelID == -1) {
			return;
		} else {
			List<Room> rList = controller.getRoomList();
			rList = filterRoomByHotelID(rList, hotelID);
			int roomID = controller.chooseRoom(lineReader, rList);
			if (roomID == -1) {
				return;
			} else {
				Room updatedRoom = controller.getRoom(roomID);
				double oldRate = updatedRoom.getRate();

				System.out.println("Old room rate is " + oldRate + ". Enter new rate for room., \n"
						+ "or type \"Return\" to go back.");
				String input = lineReader.nextLine();
	            System.out.println();

	            if (input.equalsIgnoreCase("return")) {
					return;
				} else {
					double newRate;
					try {
						newRate = Double.parseDouble(input);
					} catch (NumberFormatException e) {
						System.out.println("Invalid room rate.");
						return;
					}
					updatedRoom.setRate(newRate);
					controller.editRoomRate(updatedRoom);
					return;
				}
			}
		}
	}

	/**
	 * This method lets the user View/Add/Edit/Delete any and all customer
	 * information.
	 * 
	 * @param s
	 *            - Used to determine the hotelID if necessary.
	 * @throws SQLException
	 */
	private static void manageCustomers(Staff s) throws SQLException {
		int hotelID = controller.findHotelID(lineReader, s);
		if (hotelID == -1) {
			return;
		}

		// Chose which action to take.
		while (true) {
			System.out.println("Type \"View Customers\" to view customer information, \n"
					+ "\"Add Customer\" to add a customer, \n" + "\"Edit Customer\" to edit a customers information, \n"
					+ "\"Delete Customer\" to delete a customer, \n" + "or \"Return\" to go back.");
			String input = lineReader.nextLine();
            System.out.println();

            // View customer information
			if (input.equalsIgnoreCase("view customers")) {
				int customerID = controller.chooseCustomer(lineReader);
				if (customerID == -1) {
					continue;
				} else {
					Customer c = controller.getCustomer(customerID);
					System.out.println(c.toString());
					System.out.println();
				}
				// Add a customer to the system
			} else if (input.equalsIgnoreCase("add customer")) {
				try {
					controller.addCustomer(lineReader);
				} catch (SQLException e) {
					System.out.println("Error adding customer: " + e.getMessage());
				}
				// Edit a customer in the system
			} else if (input.equalsIgnoreCase("edit customer")) {
				int customerID = controller.chooseCustomer(lineReader);
				if (customerID == -1) {
					continue;
				} else {
					try {
						Customer c = controller.getCustomer(customerID);
						controller.updateCustomerInformation(lineReader, c);
					} catch (SQLException e) {
						System.out.println("Error updating customer: " + e.getMessage());
					}
				}
				// Delete a customer from the system
			} else if (input.equalsIgnoreCase("delete customer")) {
				int customerID = controller.chooseCustomer(lineReader);
				if (customerID == -1) {
					continue;
				} else {
					Customer c = controller.getCustomer(customerID);
					controller.deleteCustomer(c);
				}
			} else if (input.equalsIgnoreCase("return")) {
				return;
			} else {
				System.out.println("Please enter a valid action.");
			}
		}
	}

	/**
	 * This method lets the user View/Add/Edit/Delete any and all staff
	 * information.
	 * 
	 * @param s
	 *            - Used to determine the hotelID if necessary.
	 * @throws SQLException
	 */
	private static void manageStaff(Staff s) throws SQLException {
		int hotelID = controller.findHotelID(lineReader, s);
		if (hotelID == -1) {
			return;
		}

		// Build a list of staff using the hotelID
		List<Staff> initialStaffList;// Do not use anywhere else
		List<Staff> sList; // Use me!

		// Chose which action to take.
		while (true) {
			// Rebuild the list each loop, as the staff members information
			// may change as they are added, edited, and/or deleted from the
			// database
			initialStaffList = controller.getStaffList();
			sList = new ArrayList<Staff>();
			for (Staff staff : initialStaffList) {
				if (staff.getHotelID() == hotelID) {
					sList.add(staff);
				}
			}

			System.out.println(
					"Type \"View Staff\" to view staff information, \n" + "\"Add Staff\" to add a staff member, \n"
							+ "\"Edit Staff\" to edit a staff members information, \n"
							+ "\"Delete Staff\" to delete a staff member, \n" + "or \"Return\" to go back.");
			String input = lineReader.nextLine();
            System.out.println();

            // View staff information
			if (input.equalsIgnoreCase("view staff")) {
				int staffID = controller.chooseStaff(lineReader, sList);
				if (staffID == -1) {
					continue;
				} else {
					Staff staff = controller.getStaff(staffID);
					System.out.println();
					System.out.println(staff.toString());
					System.out.println();
				}
				// Add a staff member
			} else if (input.equalsIgnoreCase("add staff")) {
				try {
					controller.addStaff(lineReader);
				} catch (SQLException e) {
					System.out.println("Error adding staff: " + e.getMessage());
				}
				// Edit a staff members information
			} else if (input.equalsIgnoreCase("edit staff")) {
				int staffID = controller.chooseStaff(lineReader, sList);
				if (staffID == -1) {
					continue;
				} else {
					try {
						Staff staff = controller.getStaff(staffID);
						controller.updateStaffInformation(lineReader, staff);
					} catch (SQLException e) {
						System.out.println("Error updating staff: " + e.getMessage());
					}
				}
				// Delete a staff member front the system
			} else if (input.equalsIgnoreCase("delete staff")) {
				int staffID = controller.chooseStaff(lineReader, sList);
				if (staffID == -1) {
					continue;
				} else {
					Staff staff = controller.getStaff(staffID);
					controller.deleteStaff(staff);
				}
			} else if (input.equalsIgnoreCase("return")) {
				return;
			} else {
				System.out.println("Please enter a valid action.");
			}
		}

	}

	/**
	 * This method lets the user View/Add/Edit/Delete any and all room
	 * information. This can only be called by the Admin, so it doesn't require
	 * a staff object to determine hotelID, as it allows changing all data in
	 * the system.
	 * 
	 * @throws SQLException
	 */
	private static void manageRooms() throws SQLException {
		int hotelID = controller.findHotelID(lineReader, null);
		if (hotelID == -1) {
			return;
		} else {
			// Build a list of rooms using the hotelID
			List<Room> initialRoomList; // Do not use anywhere else
			List<Room> rList; // Use me!
			
			// Chose which action to take.
			while ( true ) {
				// Rebuild the list each loop, as the rooms information
				// may change as they are added, edited, and/or deleted from the
				// database
				initialRoomList = controller.getRoomList();
				rList = new ArrayList<Room>();
				for (Room room : initialRoomList) {
					if (room.getHotelID() == hotelID) {
						rList.add(room);
					}
				}
				System.out.println(
						"Type \"View Room\" to view Room information, \n" + "\"Add Room\" to add a room, \n"
								+ "\"Edit Room\" to edit a room information, \n"
								+ "\"Delete Room\" to delete a room, " + "or \"Return\" to go back.");
				String input = lineReader.nextLine();
	            System.out.println();
	            
				/* View Room information */
				if ( input.equalsIgnoreCase( "view room" ) ) {
					int roomID = controller.chooseRoom( lineReader, rList );
					if ( roomID == -1 ) {
						continue;
					} else {
						Room room = controller.getRoom( roomID );
						System.out.println();
						System.out.println( room.toString() );
						System.out.println();
					}
				/* Add a Room */
				} else if ( input.equalsIgnoreCase( "add room" ) ) {
					try {
						controller.addRoom( lineReader );
					} catch ( SQLException e ) {
						System.out.println("Error adding room: " + e.getMessage());
					}
				/* Edit a Room information */
				} else if ( input.equalsIgnoreCase( "edit room" ) ) {
					int roomID = controller.chooseRoom( lineReader, rList );
					if ( roomID == -1 ) {
						continue;
					} else {
						try {
							Room room = controller.getRoom( roomID );
							controller.updateRoomInformation( lineReader, room );
						} catch ( SQLException e ) {
							System.out.println("Error updating room: " + e.getMessage());
						}
					}
				/* Delete a room from the system */
				} else if ( input.equalsIgnoreCase( "delete room" ) ) {
					int roomID = controller.chooseRoom( lineReader, rList );
					if ( roomID == -1 ) {
						continue;
					} else {
						try {
							Room room = controller.getRoom( roomID );
							controller.deleteRoom( room );
						}  catch ( SQLException e ) {
							System.out.println("Error deleting room: " + e.getMessage());
						}
					}
				/* Return to the previous menu */	
				} else if ( input.equalsIgnoreCase( "return" ) ) {
					return;
				} else {
					System.out.println("Please enter a valid action.");
				}
			} // end while
		} // end the 1st else
	} // end manageRooms()

	/**
	 * This method lets the user View/Add/Edit/Delete any and all hotel
	 * information. This can only be called by the Admin, so it doesn't require
	 * a staff object to determine hotelID, as it allows changing all data in
	 * the system.
	 * 
	 * @throws SQLException
	 */
	private static void manageHotels() throws SQLException {
		
		// Chose which action to take.
		while (true) {
			
			// Get the hotel list each time since it is repeatedly updated
			List<Hotel> hList = controller.getHotelList();
			
			System.out.println("Type \"View Hotels\" to view hotel information, \n"
					+ "\"Add Hotel\" to add a hotel, \n" + "\"Edit Hotel\" to edit a hotel's information, \n"
					+ "\"Delete Hotel\" to delete a hotel, \n" +  "or \"Return\" to go back.");
			String input = lineReader.nextLine();
            System.out.println();
			// View hotel information
			if (input.equalsIgnoreCase("view hotels")) {
				int hotelID = controller.chooseHotel(lineReader, hList);
				if (hotelID == -1) {
					continue;
				} else {
					Hotel h = controller.getHotel(hotelID);
					System.out.println(h.toString());
					System.out.println();
				}
				
			// Add a hotel to the system
			} else if (input.equalsIgnoreCase("add hotel")) {
				try {
					controller.addHotel(lineReader);
				} catch (SQLException e) {
					System.out.println("Error adding hotel: " + e.getMessage());
				}
				
			// Edit a hotel in the system
			} else if (input.equalsIgnoreCase("edit hotel")) {
				int hotelID = controller.chooseHotel(lineReader, hList);
				if (hotelID == -1) {
					continue;
				} else {
					try {
						Hotel h = controller.getHotel(hotelID);
						controller.updateHotelInformation(lineReader, h);
					} catch (SQLException e) {
						System.out.println("Error updating hotel: " + e.getMessage());
					}
				}
				
			// Delete a hotel from the system
			} else if (input.equalsIgnoreCase("delete hotel")) {
				int hotelID = controller.chooseHotel(lineReader, hList);
				if (hotelID == -1) {
					continue;
				} else {
					Hotel h = controller.getHotel(hotelID);
					controller.deleteHotel(h);
				}
				
			// Return
			} else if (input.equalsIgnoreCase("return")) {
				return;
				
		    // Invalid action entered
			} else {
				System.out.println("Please enter a valid action.");
			}
		}
	}

	/**
	 * Print the billing for a single check in
	 * @param ci - the check-in to print the report for
	 * @throws SQLException throws exception if the SQL database throws an exception
	 */
	private static void printBillingReportForSingleCheckIn(CheckIn ci) throws SQLException {
		double roomRate = controller.getRoom(ci.getRoomID()).getRate();
		final double ROOM_SERVICE_RATE = 50.0;
		final double CATERING_RATE = 50.0;

		double thisRSRate = 0;
		if (ci.getHasRoomService().equalsIgnoreCase("T")) {
			thisRSRate = ROOM_SERVICE_RATE;
		}
		double thisCateringRate = 0;
		if (ci.getHasCatering().equalsIgnoreCase("T")) {
			thisCateringRate = CATERING_RATE;
		}

		System.out.println("(Room Rate + Room Service Rate + Catering Rate) * Days Checked Out + Restaurant Bill + Laundry Bill + Phone Bill = Total");

		long duration = ci.getCheckOutDateTime().getTime() - ci.getCheckInDateTime().getTime();
		long daysCheckedOut = (long) (Math.ceil((TimeUnit.MILLISECONDS.toHours(duration) - 1)/24 + 1));
		daysCheckedOut = Math.max(daysCheckedOut, 1);
		double total = ((roomRate + thisRSRate + thisCateringRate) * daysCheckedOut + ci.getRestaurantBill() + ci.getLaundryBill() + ci.getPhoneBill());
		System.out.printf("(%-9s + %-17s + %-13s) * %-16s + %-15s + %-12s + %-10s = %-5s\n", roomRate, thisRSRate, thisCateringRate, daysCheckedOut, ci.getRestaurantBill(),
				ci.getLaundryBill(), ci.getPhoneBill(), total);
	}

	/**
	 * Print the billing for a customer
	 * @param c - Customer to get billing report for
	 * @throws SQLException is thrown when the database returns an exception
	 */
	private static void printBillingForCustomer(Customer c) throws SQLException {
		// get all check-ins from customer
		List<CheckIn> ciList = controller.getCustomerCheckIns(c.getCustomerID());
		
		// calculate total bill
		final double ROOM_SERVICE_RATE = 50.0;
		final double CATERING_RATE = 50.0;
		double grandTotal = 0;
		
		System.out.println("Room ID | (Room Rate + Room Service Rate + Catering Rate) * Days Checked Out + Restaurant Bill + Laundry Bill + Phone Bill = Total");
		for (CheckIn ci : ciList) {
			double roomRate = controller.getRoom(ci.getRoomID()).getRate();
			double thisRSRate = 0;
			if (ci.getHasRoomService().equalsIgnoreCase("T")) {
				thisRSRate = ROOM_SERVICE_RATE;
			}
			double thisCateringRate = 0;
			if (ci.getHasCatering().equalsIgnoreCase("T")) {
				thisCateringRate = CATERING_RATE;
			}

			long duration = ci.getCheckOutDateTime().getTime() - ci.getCheckInDateTime().getTime();
			long daysCheckedOut = (long) (Math.ceil((TimeUnit.MILLISECONDS.toHours(duration) - 1)/24 + 1));
			daysCheckedOut = Math.max(daysCheckedOut, 1);
			double total = ((roomRate + thisRSRate + thisCateringRate) * daysCheckedOut + ci.getRestaurantBill() + ci.getLaundryBill() + ci.getPhoneBill());
			System.out.printf("%-7s | (%-9s + %-17s + %-13s) * %-16s + %-15s + %-12s + %-10s = %-5s\n", ci.getRoomID(), roomRate, thisRSRate, thisCateringRate, daysCheckedOut, ci.getRestaurantBill(),
					ci.getLaundryBill(), ci.getPhoneBill(), total);
			grandTotal += total;
		}
		System.out.println("Grand Total: " + grandTotal);
	}

	/**
	 * This prints the stack trace for the given exception, then terminates the
	 * program.
	 * 
	 * @param e - exception to print
	 */
	private static void errorMessage(Exception e) {
		e.printStackTrace();
		System.exit(1);
	}
	
	/**
	 * Filter CheckIn list to only contain rooms with a certain hotel id
	 * @param ciList original list to filter
	 * @param hotel id to filter by
	 * @return a filtered list
	 * @throws SQLException thrown if the SQL database throws an exception
	 */
	private static List<CheckIn> filterCheckInByHotelID(List<CheckIn> ciList, int hotelID) throws SQLException {
		List<CheckIn> ciFiltered = new ArrayList<CheckIn>();
		for (CheckIn checkin : ciList) {
			// Hotel ID not an attribute of CheckIn relation, so must find it using the roomID
			int roomID = checkin.getRoomID();
			int foundHotelID = controller.getRoom(roomID).getHotelID();
			// make sure hotel is at least 1
			if (foundHotelID <= 0)
				continue;
			// filter
			if (foundHotelID == hotelID) {
				ciFiltered.add(checkin);
			}
		}
		return ciFiltered;
	}
	
	/**
	 * Filter Rooms list to only contain rooms with a certain hotel id
	 * @param ciList original list to filter
	 * @param hotel id to filter by
	 * @return a filtered list
	 * @throws SQLException thrown if the SQL database throws an exception
	 */
	private static List<Room> filterRoomByHotelID(List<Room> rList, int hotelID) throws SQLException {
		List<Room> rFiltered = new ArrayList<Room>();
		for (Room room : rList) {
			if (room.getHotelID() == hotelID) {
				rFiltered.add(room);
			}
		}
		return rFiltered;
	}

	/**
	 * Helper method to determine if two datetime intervals overlap at all
	 * @param dateStart1 the beginning of datetime interval #1
	 * @param dateEnd1 the end of datetime interval #1
	 * @param dateStart2 the beginning of datetime interval #2
	 * @param dateEnd2 the end of datetime interval #2
	 * @return whether or not there is a collision in the two datetime intervals
	 */
	private static boolean datesCollide(Timestamp dateStart1, Timestamp dateEnd1, Timestamp dateStart2, Timestamp dateEnd2) {
		return (dateStart1.compareTo(dateEnd2) <= 0 && dateEnd1.compareTo(dateStart2) >= 0);
	}
}
