package com.citadini.navigationsystem.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class BaseStationCommunication implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private BaseStationCommunicationPK id;
	
	private float distance;
	
	private LocalDateTime timestamp;
	
	public BaseStationCommunication() { }

	public BaseStationCommunication(MobileStation mobileStation, BaseStation baseStation, float distance) {
		super();
		this.id = new BaseStationCommunicationPK(baseStation, mobileStation);
		this.distance = distance;
		this.timestamp = LocalDateTime.now();
	}

	public BaseStationCommunicationPK getId() {
		return id;
	}

	public void setId(BaseStationCommunicationPK id) {
		this.id = id;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
}
