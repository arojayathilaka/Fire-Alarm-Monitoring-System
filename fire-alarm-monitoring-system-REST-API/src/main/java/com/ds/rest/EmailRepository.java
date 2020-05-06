// email repository class
// talks to the DB
package com.ds.rest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmailRepository {
	
	Connection connection = null;
	
	public EmailRepository() {
		
		String url = "jdbc:mysql://localhost:3306/restdb?useSSL=false";
		String username = "root";
		String password = "root";
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); // load the driver
			connection = DriverManager.getConnection(url, username, password); // get connection object
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	// get boolean value from the DB
	public Email getStatus() {
		String sql = "select * from email";
		Email email = new Email();
		
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			// assign returned values to the created email object
			if(rs.next()) {
				email.setSendEmail(rs.getBoolean(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return email;
	}
	
	
	
	// update value
	public Email updateEmail(Email email) {
		
		String sql = "update email set sendEmail=?";
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			// set values from the passed email object to update
			st.setBoolean(1, email.isSendEmail());
				
			st.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return email;
	}
}
