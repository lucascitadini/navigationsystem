package com.citadini.navigationsystem.services.exceptions;

public class CommunicationNotEstablishedException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private int errorCode;
	private float latitude;
	private float longitude;
	private String mobileStationid;

	public CommunicationNotEstablishedException(String mobileStationId, float latitude, float longitude) {
		super("Communication not established with any base station!");
		errorCode = 99;
		this.latitude = latitude;
		this.longitude = longitude;
		this.mobileStationid = mobileStationId;
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
}
