CREATE TABLE Hotel (
  hotelID INT,
  name VARCHAR2(30) NOT NULL,
  address VARCHAR2(50) NOT NULL UNIQUE,
  phone VARCHAR2(16) NOT NULL UNIQUE,
  PRIMARY KEY (hotelID)
);

CREATE TABLE Staff (
	staffID INT,
	ssn CHAR(11),
	name VARCHAR2(30) NOT NULL,
	gender VARCHAR2(1) NOT NULL,
	phone VARCHAR2(16) NOT NULL,
	address VARCHAR2(50) NOT NULL,
	staffType VARCHAR2(25) NOT NULL,
    hotelID INT,
	PRIMARY KEY (staffID),
    FOREIGN KEY (hotelID) REFERENCES hotel(hotelID) ON DELETE CASCADE,
    CONSTRAINT checkStaffType CHECK (staffType IN ('Manager', 'Front Desk Representative', 'Catering Staff', 'Room Service Staff')),
	CONSTRAINT checkStaffGender CHECK (gender IN ('M','F'))
);

CREATE TABLE Customer (
	customerID INT,
	name VARCHAR2(30) NOT NULL,  
  ssn CHAR(11),
	gender CHAR(1) NOT NULL,
	address VARCHAR2(50) NOT NULL,
	email VARCHAR2(40) NOT NULL,
	PRIMARY KEY (customerID),
	CONSTRAINT checkCustomerGender CHECK (gender IN('M', 'F'))
);

CREATE TABLE Room (
	hotelID INT,
	roomNumber INT NOT NULL,
	roomType VARCHAR2(20),
	rate FLOAT NOT NULL,
	maxOccupancy INT NOT NULL,
	roomID INT UNIQUE NOT NULL,
	PRIMARY KEY (hotelID, roomNumber, roomID),
	FOREIGN KEY (hotelID) REFERENCES hotel(hotelID) ON DELETE CASCADE,
	CONSTRAINT checkRoomType CHECK (roomType IN ('Economy', 'Deluxe', 'Executive Suite', 'Presidential Suite')),
	CONSTRAINT occupancy CHECK (maxOccupancy BETWEEN 1 AND 4),
	CONSTRAINT minRate CHECK (rate > 0)
);

CREATE TABLE CheckIn (
  customerID INT,
  roomID INT, 
  checkOutDateTime DATE NOT NULL,
  checkInDateTime DATE NOT NULL,
  numberInParty INT NOT NULL,
  hasCatering CHAR(1) NOT NULL,
  hasRoomService CHAR(1) NOT NULL,
  restaurantBill FLOAT DEFAULT 0.0,
  laundryBill FLOAT DEFAULT 0.0,
  phoneBill FLOAT DEFAULT 0.0,
  paymentMethod VARCHAR2(50) NOT NULL, 
  PRIMARY KEY (roomID),
  FOREIGN KEY (customerID) REFERENCES customer(customerID) ON DELETE CASCADE,
  FOREIGN KEY (roomID) REFERENCES room(roomID) ON DELETE CASCADE,
  CONSTRAINT checkHasCatering CHECK (hasCatering IN ('T','F')),
  CONSTRAINT checkHasRoomService CHECK (hasRoomService IN ('T','F')),
  CONSTRAINT checkNumberInParty CHECK (numberInParty BETWEEN 1 AND 4),
  CONSTRAINT minBill CHECK (restaurantBill >= 0 AND laundryBill>=0 AND phoneBill>=0),
  CONSTRAINT checkTime CHECK (checkOutDateTime > checkInDateTime)
);

CREATE TABLE RSAssignedTo (
  staffID INT,
  roomID INT,
  customerID INT,
  hotelID INT,
  PRIMARY KEY(roomID),
  FOREIGN KEY (customerID) REFERENCES Customer(customerID) ON DELETE CASCADE,
  FOREIGN KEY (staffID) REFERENCES staff(staffID) ON DELETE CASCADE,
  FOREIGN KEY (roomID) REFERENCES Room(roomID) ON DELETE CASCADE,
  FOREIGN KEY (hotelID) REFERENCES Hotel(hotelID) ON DELETE CASCADE
);

CREATE TABLE CAssignedTo (
  staffID INT,
  roomID INT,
  customerID INT,
  hotelID INT,
  PRIMARY KEY(roomID),
  FOREIGN KEY (customerID) REFERENCES Customer(customerID) ON DELETE CASCADE,
  FOREIGN KEY (staffID) REFERENCES staff(staffID) ON DELETE CASCADE,
  FOREIGN KEY (roomID) REFERENCES Room(roomID) ON DELETE CASCADE,
  FOREIGN KEY (hotelID) REFERENCES Hotel(hotelID) ON DELETE CASCADE

);

CREATE SEQUENCE hotel_seq MINVALUE 1 START WITH 1;
CREATE SEQUENCE staff_seq MINVALUE 1 START WITH 1;
CREATE SEQUENCE customer_seq MINVALUE 1 START WITH 1;
CREATE SEQUENCE room_seq MINVALUE 1 START WITH 1;