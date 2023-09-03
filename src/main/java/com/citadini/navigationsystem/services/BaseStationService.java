package com.citadini.navigationsystem.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citadini.navigationsystem.domain.BaseStation;
import com.citadini.navigationsystem.dto.BaseStationCommunicationsReportsDto;
import com.citadini.navigationsystem.dto.BaseStationDto;
import com.citadini.navigationsystem.dto.CommunicationReportDto;
import com.citadini.navigationsystem.repositories.BaseStationRepository;
import com.citadini.navigationsystem.services.exceptions.LimitQuantityExceededException;

@Service
public class BaseStationService {

	@Autowired
	private BaseStationRepository repository;

	public String insert(BaseStationDto baseStationDto) {
		if (repository.count() >= 99) {
			throw new LimitQuantityExceededException("Exceeded limit amount of base station");
		}
		BaseStation baseStation = fromDto(baseStationDto);
		baseStation.setId(UUID.randomUUID().toString());
		repository.save(baseStation);
		return baseStation.getId();
	}

	public void update(BaseStationDto baseStationDto, String id) {
		if (repository.existsById(id)) {
			BaseStation baseStation = fromDto(baseStationDto);
			baseStation.setId(id);
			repository.save(baseStation);
		}
	}

	private BaseStation fromDto(BaseStationDto baseStationDto) {
		return new BaseStation(baseStationDto.getName(), baseStationDto.getX(), baseStationDto.getY(),
				baseStationDto.getDetectionRadiusInMeters());
	}

	public List<BaseStation> findAll() {
		return repository.findAll();
	}

	public BaseStation findById(String id) {
		return repository.findById(id).orElse(null);
	}

	public BaseStationCommunicationsReportsDto findBaseStationCommunicationsReportsById(String id) {
		BaseStation baseStation = findById(id);
		if (baseStation != null) {
			BaseStationCommunicationsReportsDto baseStationCommunicationsReportsDto = new BaseStationCommunicationsReportsDto();
			baseStationCommunicationsReportsDto.setBaseStationId(id);
			baseStationCommunicationsReportsDto.setReports(baseStation.getComunications().stream()
					.map(baseStationCommunication -> new CommunicationReportDto(
							baseStationCommunication.getId().getMobileStation().getId(),
							baseStationCommunication.getDistance(), baseStationCommunication.getTimestamp()))
					.collect(Collectors.toList()));
			return baseStationCommunicationsReportsDto;
		}
		return null;
	}
}
