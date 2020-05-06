// sensor model class
package com.ds.rest;

public class Sensor {
	private int id;
	private boolean isActive;
	private String location;
	private int smokeLvl;
	private int CO2Lvl;
	
	// getters and setters
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getSmokeLvl() {
		return smokeLvl;
	}
	public void setSmokeLvl(int smokeLvl) {
		this.smokeLvl = smokeLvl;
	}
	public int getCO2Lvl() {
		return CO2Lvl;
	}
	public void setCO2Lvl(int cO2Lvl) {
		CO2Lvl = cO2Lvl;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
