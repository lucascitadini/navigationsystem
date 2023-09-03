package com.citadini.navigationsystem.config;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommunicationNotEstablished {
	
	private LocalDateTime timestamp;
	@JsonProperty(value = "mobileId")
	private String mobileStationid;
	@JsonProperty(value = "x")
	private float latitude;
	@JsonProperty(value = "y")
	private float longitude;
	@JsonProperty(value = "error_code")
	private int errorCode;
	@JsonProperty(value = "error_description")
	private String description;

	public CommunicationNotEstablished() {

	}

	public CommunicationNotEstablished(LocalDateTime timestamp, int errorCode, float latitude, float longitude,
			String mobileStationid, String description) {
		super();
		this.timestamp = timestamp;
		this.errorCode = errorCode;
		this.latitude = latitude;
		this.longitude = longitude;
		this.mobileStationid = mobileStationid;
		this.setDescription(description);
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
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

	public String getMobileStationid() {
		return mobileStationid;
	}

	public void setMobileStationid(String mobileStationid) {
		this.mobileStationid = mobileStationid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
