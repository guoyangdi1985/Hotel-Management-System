INSERT INTO Hotel (hotelID, name, address, phone) VALUES (hotel_seq.nextval,'WolfClub', '10 No Such Dr, Garner, NC 27529', '976-728-1980');

INSERT INTO Customer (customerID, ssn, name, gender, address, email) VALUES (customer_seq.nextval, '111-22-3333', 'Barrel O. Pewdiepie', 'M', '881 Java Lane Graniteville, SC 29829', 'bopewdie@kmail.us');
INSERT INTO Customer (customerID, ssn, name, gender, address, email) VALUES (customer_seq.nextval, '444-55-6666', 'Joy E. Markiplier', 'M', '2697 Stroop Hill Road Atlanta, GA 30342', 'jjmarki@kmail.us');

INSERT INTO Staff (hotelID, staffID, ssn, name, gender, phone, address, staffType) VALUES (1, staff_seq.nextval, '121-23-1234', 'James O. NintendoNerd', 'M', '980-131-1238', '601, Castlevania Ct, Baleigh, AC, 20987', 'Front Desk Representative');
INSERT INTO Staff (hotelID, staffID, ssn, name, gender, phone, address, staffType) VALUES (1, staff_seq.nextval, '331-22-9O99', 'Games M. Grumps', 'M', '980-187-1983', '109, Thehouse Ct, Baleigh, AC, 39882', 'Catering Staff');
INSERT INTO Staff (hotelID, staffID, ssn, name, gender, phone, address, staffType) VALUES (1, staff_seq.nextval, '987-56-4444', 'Vanoss C. Gaming', 'M', '976-728-1980', '1048, ResidentEvil Dr, Baleigh, AC, 33872', 'Manager');

INSERT INTO Room (hotelID, roomNumber, roomType, rate, maxOccupancy, roomID) VALUES (1, 101, 'Economy', 150.0, 2, room_seq.nextval);
INSERT INTO Room (hotelID, roomNumber, roomType, rate, maxOccupancy, roomID) VALUES (1, 201, 'Executive Suite', 250.0, 2, room_seq.nextval);
INSERT INTO Room (hotelID, roomNumber, roomType, rate, maxOccupancy, roomID) VALUES (1, 301, 'Deluxe', 350.0, 2, room_seq.nextval);

INSERT INTO CheckIn(customerID, roomID, checkOutDateTime, checkInDateTime, numberInParty, hasCatering, hasRoomService, restaurantBill, laundryBill, phoneBill, paymentMethod) VALUES
(1, 1, TO_DATE('2016/11/19 12:00:00', 'yyyy/mm/dd hh24:mi:ss'), TO_DATE('2016/11/12 12:00:00', 'yyyy/mm/dd hh24:mi:ss'), 1, 'T', 'F', 65.0, 15.0, 0.0, 'Credit Card (1111-2222-3333-4444)');
INSERT INTO CheckIn(customerID, roomID, checkOutDateTime, checkInDateTime, numberInParty, hasCatering, hasRoomService, restaurantBill, laundryBill, phoneBill, paymentMethod) VALUES
(2, 2, TO_DATE('2016/11/21 12:00:00', 'yyyy/mm/dd hh24:mi:ss'), TO_DATE('2016/11/14 12:00:00', 'yyyy/mm/dd hh24:mi:ss'), 1, 'T', 'F', 40, 25.0, 0.0, 'Credit Card (5555-6666-7777-8888)');

INSERT INTO CAssignedTo(staffID, roomID, customerID, hotelID) VALUES (2, 1, 1, 1);
INSERT INTO CAssignedTo(staffID, roomID, customerID, hotelID) VALUES (2, 2, 2, 1);