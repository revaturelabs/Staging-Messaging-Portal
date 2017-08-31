package com.revature.smp.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="LOCATION")
public class Location implements Serializable{

	private static final long serialVersionUID = 6033234014682351342L;
	
	@Id
	@Column(name="LOCATION_ID")
	private int locationId;
	
	@Column(name="LOCATION")
	private String location;
	
	public Location() {
		super();
	}
	
	public Location(int locationId, String location) {
		super();
		this.locationId = locationId;
		this.location = location;
	}
	
	public int getLocationId() {
		return locationId;
	}
	
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Override
	public String toString() {
		return "Location [locationId=" + locationId + ", location=" + location + "]";
	}
	
}