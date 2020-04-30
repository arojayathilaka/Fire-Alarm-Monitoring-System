// repository class
// talks to the DB
package com.ds.rest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SensorRepository {
	
	Connection connection = null;
	
	public SensorRepository() {
		
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
	
	// get details of all the sensors from the DB
	public List<Sensor> getSensors() {
		List<Sensor> sensors = new ArrayList<>();
		String sql = "select * from sensor";
		
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			// for each result, create a sensor object and assign the returned values and add the object to sensors
			while(rs.next()) {
				Sensor sensor = new Sensor();
				sensor.setId(rs.getInt(1));
				sensor.setActive(rs.getBoolean(2));
				sensor.setLocation(rs.getString(3));
				sensor.setSmokeLvl(rs.getInt(4));
				sensor.setCO2Lvl(rs.getInt(5));
				
				sensors.add(sensor);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sensors;
	}
	
	// get details of a particular sensor from the DB
	public Sensor getSensor(int id) {
		String sql = "select * from sensor where id="+id;
		Sensor sensor = new Sensor();;
		
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			// assign returned values to the created sensor object
			if(rs.next()) {
				sensor.setId(rs.getInt(1));
				sensor.setActive(rs.getBoolean(2));
				sensor.setLocation(rs.getString(3));
				sensor.setSmokeLvl(rs.getInt(4));
				sensor.setCO2Lvl(rs.getInt(5));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sensor;
	}
	
	// add a sensor to the DB
	public Sensor addSensor(Sensor sensor) {
		String sql = "insert into sensor values (?, ?, ?, ?, ?)";
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			// set values from the passed sensor object to insert it to the database
			st.setInt(1, sensor.getId());
			st.setBoolean(2, sensor.isActive());
			st.setString(3, sensor.getLocation());
			st.setInt(4, sensor.getSmokeLvl());
			st.setInt(5, sensor.getCO2Lvl());
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return sensor;
	}
	
	// update details of a sensor
	public Sensor updateSensor(Sensor sensor) {
		String sql;
		
		if (sensor.getCO2Lvl() == 0) { 
			// this will be executed when the admin tries to update the location of a sensor 		
			sql = "update sensor set location=? where id=?";
			try {
				PreparedStatement st = connection.prepareStatement(sql);
				// set id and location from the passed sensor object to update its location
				st.setString(1, sensor.getLocation());
				st.setInt(2, sensor.getId());
				
				st.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			// this will be executed when the sensor app tries to update sensor details
			sql = "update sensor set isActive=?, location=?, smokeLvl=?, CO2Lvl=? where id=?";
			try {
				PreparedStatement st = connection.prepareStatement(sql);
				// set values from the passed sensor object to update its details
				st.setBoolean(1, sensor.isActive());
				st.setString(2, sensor.getLocation());
				st.setInt(3, sensor.getSmokeLvl());
				st.setInt(4, sensor.getCO2Lvl());
				st.setInt(5, sensor.getId());
				
				st.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return sensor;
	}
}
