package com.citadini.navigationsystem.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MobileLocationDto {
	@JsonIgnore
	private String mobileStationId;
	
	@Max(value = 90, message="Latitude (X) must be less than 90")
	@Min(value = -90, message="Latitude (X) must be greater than -90")
	private float x;

	@Max(value = 180, message="Latitude (X) must be less than 180")
	@Min(value = -180, message="Latitude (X) must be greater than -180")
	private float y;
	
	public MobileLocationDto() { }

	public String getMobileStationId() {
		return mobileStationId;
	}

	public void setMobileStationId(String mobileStationId) {
		this.mobileStationId = mobileStationId;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
