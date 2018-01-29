package dbUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.ibatis.jdbc.ScriptRunner;

/**
 * This file is used to run SQL script files directly.
 * 
 * In order to make this java file work, a jar file named mybatis-3.4.1 is
 * needed, since this jar file supports ScriptRunner class somewhere below.
 * 
 * After adding this jar file into the project, replace the unity ID and the
 * password with yours in DBConnection.java.
 * 
 * Just add a line of code "RunSqlScript.runScript();" at the beginning in the
 * setup() method in each of your test files to set up tables.
 */
public class RunSqlScript {

	/*
	 * This main method below was used to test this class.
	 */
	 public static void main(String[] args) throws ClassNotFoundException,
	 SQLException {
	 RunSqlScript.runScript();
	 }

	public static void runScript() throws SQLException, ClassNotFoundException {
		
		String dropScript = "sql/drop.sql";
		String createScript = "sql/create.sql";
		String loadDataScript = "sql/loadData.sql";

		// Create MySql Connection
		// ------------------------------------------------------------------------------------------
		// Put the database address, user name, and password to make a database
		// connection.
		Connection con = DBConnection.getInstance().getDBConnection();
		// ------------------------------------------------------------------------------------------
		try {
			// Initialize object for ScripRunner
			ScriptRunner scriptRunner = new ScriptRunner(con);
			// Make input file objects for the scripts.
			FileReader dropFileReader = new FileReader(dropScript);
			FileReader createFileReader = new FileReader(createScript);
			FileReader loadDataFileReader = new FileReader(loadDataScript);
			// Give the input files to buffer Readers
			Reader dropBufferReader = new BufferedReader(dropFileReader);
			Reader createBufferReader = new BufferedReader(createFileReader);
			Reader loadDataBufferReader = new BufferedReader(loadDataFileReader);
			// Execute drop tables script.
			System.out.println("Start running " + dropScript + ".");
			scriptRunner.runScript(dropBufferReader);
			// Execute create tables script.
			System.out.println("Start running " + createScript + ".");
			scriptRunner.runScript(createBufferReader);
			// Execute load data script.
			System.out.println("Start running " + loadDataScript + ".");
			scriptRunner.runScript(loadDataBufferReader);

			// Close what should be closed....
			dropFileReader.close();
			createFileReader.close();
			loadDataFileReader.close();

			dropBufferReader.close();
			createBufferReader.close();
			loadDataBufferReader.close();

			scriptRunner.closeConnection();
			con.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}