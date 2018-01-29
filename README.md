# HotelManagementSystem

This is the project my teammates and I created for DB Management Class.

To run, first install Eclipse for Java EE developers

http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/keplersr2

At this point there are probably errors in the project.  To fix these, go to help -> eclipse marketplace and download the oracle database tool package.  Then go to update build path -> library -> add library -> connectivity driver definitions and add the oracle 10g driver.  This should allow you to connect to the database, and resolve any errors relating to that.

To run the major SQL scripts (drop, create, loadData) will still require a terminal, as this functionality has not been added to the program yet.