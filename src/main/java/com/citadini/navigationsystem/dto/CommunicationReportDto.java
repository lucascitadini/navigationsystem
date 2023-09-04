package com.citadini.navigationsystem.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CommunicationReportDto {
	@JsonProperty(value = "mobile_station_id")
	private String mobileStationId;
	private float distance;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	private LocalDateTime timestamp;
	
	public CommunicationReportDto() {
		
	}
	
	public CommunicationReportDto(String mobileStationId, float distance, LocalDateTime timestamp) {
		super();
		this.mobileStationId = mobileStationId;
		this.distance = distance;
		this.timestamp = timestamp;
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

	public String getMobileStationId() {
		return mobileStationId;
	}

	public void setMobileStationId(String mobileStationId) {
		this.mobileStationId = mobileStationId;
	}
	
}
