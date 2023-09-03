package com.citadini.navigationsystem.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citadini.navigationsystem.domain.MobileStation;
import com.citadini.navigationsystem.dto.MobileStationDto;
import com.citadini.navigationsystem.repositories.MobileStationRepository;
import com.citadini.navigationsystem.services.exceptions.LimitQuantityExceededException;

@Service
public class MobileStationService {
	@Autowired
	private MobileStationRepository repository;
	
	public String insert(MobileStationDto mobileStationDto) {
		if (repository.count() >= 99) {
			throw new LimitQuantityExceededException("Exceeded limit amount of mobile station");
		}
		MobileStation mobileStation = fromDto(mobileStationDto);
		mobileStation.setId(UUID.randomUUID().toString());
		save(mobileStation);
		return mobileStation.getId();
	}
	
	public void update(MobileStationDto mobileStationDto, String id) {
		if (repository.existsById(id)) {
			MobileStation mobileStation = fromDto(mobileStationDto);
			mobileStation.setId(id);
			save(mobileStation);
		}
	}
	
	public void save(MobileStation mobileStation) {
		repository.save(mobileStation);
	}
	
	private MobileStation fromDto(MobileStationDto objDto) {
		return new MobileStation(objDto.getName(), objDto.getX(), objDto.getY());
	}

	public List<MobileStation> findAll() {
		return repository.findAll();
	}
	
	public MobileStation findById(String id) {
		return repository.findById(id).orElse(null);
	}
}
