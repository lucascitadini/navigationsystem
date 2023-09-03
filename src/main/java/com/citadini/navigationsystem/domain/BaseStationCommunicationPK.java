package com.citadini.navigationsystem.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class BaseStationCommunicationPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "base_station_id")
	private BaseStation baseStation;
	
	@ManyToOne
	@JoinColumn(name = "mobile_station_id")
	private MobileStation mobileStation;
	
	public BaseStationCommunicationPK() {
		
	}
	
	public BaseStationCommunicationPK(BaseStation baseStation, MobileStation mobileStation) {
		super();
		this.baseStation = baseStation;
		this.mobileStation = mobileStation;
	}
	
	public BaseStation getBaseStation() {
		return baseStation;
	}
	
	public void setBaseStation(BaseStation baseStation) {
		this.baseStation = baseStation;
	}
	
	public MobileStation getMobileStation() {
		return mobileStation;
	}
	
	public void setMobileStation(MobileStation mobileStation) {
		this.mobileStation = mobileStation;
	}
}
