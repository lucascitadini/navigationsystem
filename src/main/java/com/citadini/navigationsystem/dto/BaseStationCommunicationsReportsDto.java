package com.citadini.navigationsystem.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseStationCommunicationsReportsDto {
	@JsonProperty(value = "base_station_id")
	private String baseStationId;
	private List<CommunicationReportDto> reports;
	
	public BaseStationCommunicationsReportsDto() {
		
	}
	
	public BaseStationCommunicationsReportsDto(String baseStationId, List<CommunicationReportDto> reports) {
		super();
		this.baseStationId = baseStationId;
		this.reports = reports;
	}

	public List<CommunicationReportDto> getReports() {
		return reports;
	}
	
	public void setReports(List<CommunicationReportDto> reports) {
		this.reports = reports;
	}
	
	public String getBaseStationId() {
		return baseStationId;
	}
	
	public void setBaseStationId(String baseStationId) {
		this.baseStationId = baseStationId;
	}
}
