package com.citadini.navigationsystem.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class MobileStationDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Filling in the name is mandatory")
	@Size(min = 4, max = 128, message = "The name must be between 4 and 128 characters")
	private String name;

	@Max(value = 90, message="Latitude (X) must be less than 90")
	@Min(value = -90, message="Latitude (X) must be greater than -90")
	private float x;

	@Max(value = 180, message="Latitude (X) must be less than 180")
	@Min(value = -180, message="Latitude (X) must be greater than -180")
	private float y;

	public MobileStationDto() { }
	
	public MobileStationDto(String name, float x, float y) {
		super();
		this.name = name;
		this.x = x;
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
