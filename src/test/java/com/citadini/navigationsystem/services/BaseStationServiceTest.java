package com.citadini.navigationsystem.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.citadini.navigationsystem.domain.BaseStation;
import com.citadini.navigationsystem.domain.BaseStationCommunication;
import com.citadini.navigationsystem.domain.MobileStation;
import com.citadini.navigationsystem.dto.BaseStationCommunicationsReportsDto;
import com.citadini.navigationsystem.repositories.BaseStationRepository;

@ExtendWith(MockitoExtension.class)
public class BaseStationServiceTest {
	@InjectMocks
	private BaseStationService baseStationService;

	@Mock
	private BaseStationRepository repository;

	@Test
	public void findBaseStationCommunicationsReportsById_ok() {
		String uuid = UUID.randomUUID().toString();

		MobileStation mobileStation = new MobileStation(UUID.randomUUID().toString(), 0, 0);

		BaseStation baseStation = new BaseStation(UUID.randomUUID().toString(), -27.570596f, -48.8006111f, 30000);
		baseStation.setComunications(Arrays.asList(new BaseStationCommunication(mobileStation, baseStation, 20f)));
		when(repository.findById(uuid)).thenReturn(Optional.of(baseStation));

		BaseStationCommunicationsReportsDto baseStationCommunicationsReportsDto = baseStationService.findBaseStationCommunicationsReportsById(uuid);
		assertEquals(uuid, baseStationCommunicationsReportsDto.getBaseStationId());
	}
	
	@Test
	public void findBaseStationCommunicationsReportsById_null() {
		String uuid = UUID.randomUUID().toString();

		when(repository.findById(uuid)).thenReturn(Optional.empty());

		BaseStationCommunicationsReportsDto baseStationCommunicationsReportsDto = baseStationService.findBaseStationCommunicationsReportsById(uuid);

		assertNull(baseStationCommunicationsReportsDto);
	}
}
