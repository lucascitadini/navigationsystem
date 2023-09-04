package com.citadini.navigationsystem.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citadini.navigationsystem.domain.BaseStation;
import com.citadini.navigationsystem.domain.BaseStationCommunication;
import com.citadini.navigationsystem.domain.MobileStation;
import com.citadini.navigationsystem.dto.MobileLocationDto;
import com.citadini.navigationsystem.services.exceptions.CommunicationNotEstablishedException;
import com.citadini.navigationsystem.services.exceptions.ObjectNotFoundExcpetion;

@Service
public class LocationService {

	@Autowired
	private MobileStationService mobileStationService;
	@Autowired
	private BaseStationService baseStationService;
	@Autowired
	private BaseStationCommunicationsService baseStationCommunicationsService;

	public void communicatesMobileLocation(MobileLocationDto mobileLocationDto) {
		MobileStation mobileStation = mobileStationService.findById(mobileLocationDto.getMobileStationId());
		if (mobileStation == null) {
			throw new ObjectNotFoundExcpetion("Mobile location not found!");
		}

		List<BaseStationCommunication> baseStationCommunications = getBaseStationsInTheRadius(mobileLocationDto,
				mobileStation);
		if (baseStationCommunications.isEmpty()) {
			throw new CommunicationNotEstablishedException(mobileLocationDto.getMobileStationId(),
					mobileLocationDto.getX(), mobileLocationDto.getY());
		}
		baseStationCommunicationsService.saveAll(baseStationCommunications);
		mobileStation.setLastKnownLatitude(mobileLocationDto.getX());
		mobileStation.setLastKnownLongitude(mobileLocationDto.getY());
		mobileStationService.save(mobileStation);
	}

	private List<BaseStationCommunication> getBaseStationsInTheRadius(MobileLocationDto mobileLocationDto,
			MobileStation mobileStation) {
		List<BaseStation> baseStations = baseStationService.findAll();
		List<BaseStationCommunication> baseStationCommunications = new ArrayList<BaseStationCommunication>();
		for (BaseStation baseStation : baseStations) {
			float distance = distance(baseStation, mobileLocationDto.getX(), mobileLocationDto.getY());
			if (distance <= baseStation.getDetectionRadiusInMeters()) {
				baseStationCommunications.add(new BaseStationCommunication(mobileStation, baseStation, distance));
			}
		}
		return baseStationCommunications;
	}

	private float distance(BaseStation baseStation, double locationLatitude, double locationLongitude) {

		final int RADIUS_EARTH = 6371; // Radius of the earth in kilometers

		Float latDistance = deg2rad(locationLatitude - baseStation.getLatitude()).floatValue();
		Float lonDistance = deg2rad(locationLongitude - baseStation.getLongitude()).floatValue();
		Double angle = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
				+ Math.cos(deg2rad(baseStation.getLatitude())) * Math.cos(deg2rad(locationLatitude))
						* Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		Double c = 2 * Math.atan2(Math.sqrt(angle), Math.sqrt(1 - angle));
		Double distance = RADIUS_EARTH * c * 1000; // convert to meters
		return distance.floatValue();
	}

	private Double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}
}
