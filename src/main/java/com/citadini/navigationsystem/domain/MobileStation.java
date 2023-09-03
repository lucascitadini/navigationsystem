package com.citadini.navigationsystem.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MobileStation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String name;

	private float lastKnownLatitude;

	private float lastKnownLongitude;

	public MobileStation() {
	}

	public MobileStation(String id, String name, float lastKnownLatitude, float lastKnownLongitude) {
		super();
		this.id = id;
		this.name = name;
		this.lastKnownLatitude = lastKnownLatitude;
		this.lastKnownLongitude = lastKnownLongitude;
	}

	public MobileStation(String name, float lastKnownLatitude, float lastKnownLongitude) {
		this.name = name;
		this.lastKnownLatitude = lastKnownLatitude;
		this.lastKnownLongitude = lastKnownLongitude;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getLastKnownLatitude() {
		return lastKnownLatitude;
	}

	public void setLastKnownLatitude(float lastKnownLatitude) {
		this.lastKnownLatitude = lastKnownLatitude;
	}

	public float getLastKnownLongitude() {
		return lastKnownLongitude;
	}

	public void setLastKnownLongitude(float lastKnownLongitude) {
		this.lastKnownLongitude = lastKnownLongitude;
	}

}
