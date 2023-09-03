package com.citadini.navigationsystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citadini.navigationsystem.domain.BaseStationCommunication;
import com.citadini.navigationsystem.repositories.BaseStationCommunicationsRepository;

@Service
public class BaseStationCommunicationsService {
	@Autowired
	private BaseStationCommunicationsRepository repository;
	
	public void saveAll(List<BaseStationCommunication> baseStationCommunications) {
		repository.saveAll(baseStationCommunications);
	}
}
