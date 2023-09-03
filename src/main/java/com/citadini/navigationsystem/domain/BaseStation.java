package com.citadini.navigationsystem.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class BaseStation implements Serializable {
	private static final long serialVersionUID = 1;
	
	@Id
	private String id;
	
	@Column(length = 128)
	private String name;
	
	private float latitude;
	
	private float longitude;
	
	private float detectionRadiusInMeters;
	
	@OneToMany(mappedBy="id.baseStation")
	private List<BaseStationCommunication> comunications = new ArrayList<BaseStationCommunication>();
	
	public BaseStation() { }

	public BaseStation(String id, String name, float latitude, float longitude, float detectionRadiusInMeters) {
		this.id = id;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.detectionRadiusInMeters = detectionRadiusInMeters;
	}
	
	public BaseStation(String name, float latitude, float longitude, float detectionRadiusInMeters) {
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.detectionRadiusInMeters = detectionRadiusInMeters;
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

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getDetectionRadiusInMeters() {
		return detectionRadiusInMeters;
	}

	public void setDetectionRadiusInMeters(float detectionRadiusInMeters) {
		this.detectionRadiusInMeters = detectionRadiusInMeters;
	}

	public List<BaseStationCommunication> getComunications() {
		return comunications;
	}

	public void setComunications(List<BaseStationCommunication> comunications) {
		this.comunications = comunications;
	}

}
