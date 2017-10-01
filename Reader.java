// Kevin Chen
// This program checks for the presence of a sqlite3 file and
// prints a list of user names and a list of comments they have made.
// db.sqlite3 from my socialpy project is used as an example.

import java.sql.*;
import java.io.*;

public class Reader {
	public static void main( String[] args ) {
		Connection c = null;
		Statement stmt = null;
		String fname = null;
		
		// User types in path of the file or use the default
		if (args.length == 0) {
			fname = "./db.sqlite3";
		} else {
			fname = args[0];
		}
		// System.out.printf("File name: %s\n", fname);
		
		File f = new File(fname);
		if(f.exists()) {
			try {
				// Open the database
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:" + fname);
				c.setAutoCommit(false);
				// System.out.println("Opened database successfully");
				stmt = c.createStatement();
				
				// Check for and read the auth_user table.
				try {
					ResultSet rs = stmt.executeQuery( "SELECT * FROM auth_user ORDER BY id;" );
		
					System.out.println("Users:");
					while ( rs.next() ) {
						String  name = rs.getString("username");
						
						System.out.println(name);
					}
					
					rs.close();
				} catch( Exception e ) {
					System.err.println( e.getClass().getName() + ": " + e.getMessage() );
					System.exit(0);
				}
					
				
				// Provide a line between users and comments
				System.out.println();
				
				// Check for and read the account_comment table.
				try {
					ResultSet rs = stmt.executeQuery( "SELECT * FROM account_comment ORDER BY created;" );
					
					System.out.println("Comments:");
					while ( rs.next() ) {
						String comment = rs.getString("body");
						String author = rs.getString("name");
						
						System.out.println( "\"" + comment + "\"" + " by: " + author);
					}
					
					rs.close();
				} catch ( Exception e ) {
					System.err.println( e.getClass().getName() + ": " + e.getMessage() );
					System.exit(0);
				}
				// Close databases when done for security reasons. 
				stmt.close();
				
				c.close();
			} catch ( Exception e ) {
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
			// System.out.println("Operation done successfully");
		}
		else {
			System.out.println(fname + " does not exist.");
		}
	}
}
