package com.citadini.navigationsystem.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.citadini.navigationsystem.domain.BaseStation;
import com.citadini.navigationsystem.domain.MobileStation;
import com.citadini.navigationsystem.dto.MobileLocationDto;
import com.citadini.navigationsystem.services.exceptions.CommunicationNotEstablishedException;
import com.citadini.navigationsystem.services.exceptions.ObjectNotFoundExcpetion;

@ExtendWith(MockitoExtension.class)
public class LocationServiceTest {
	@InjectMocks
	private LocationService locationService;

	@Mock
	private MobileStationService mobileStationService;

	@Mock
	private BaseStationService baseStationService;

	@Mock
	private BaseStationCommunicationsService baseStationCommunicationsService;

	@Test
	public void communicatesMobileLocation_ObjectNotFoundExcpetion() {
		String uuid = UUID.randomUUID().toString();
		MobileLocationDto mobileLocationDto = new MobileLocationDto(uuid, 22.9131567f, -43.7763169f);

		when(mobileStationService.findById(uuid)).thenReturn(null);

		assertThrows(ObjectNotFoundExcpetion.class,
				() -> locationService.communicatesMobileLocation(mobileLocationDto));
	}

	@Test
	public void communicatesMobileLocation_ok() {
		String uuid = UUID.randomUUID().toString();
		MobileLocationDto mobileLocationDto = new MobileLocationDto(uuid, -27.570596f, -48.8006111f);

		when(mobileStationService.findById(uuid)).thenReturn(new MobileStation(uuid, 0, 0));
		when(baseStationService.findAll()).thenReturn(
				Arrays.asList(new BaseStation(UUID.randomUUID().toString(), -27.570596f, -48.8006111f, 30000)));
		
		assertAll(() -> locationService.communicatesMobileLocation(mobileLocationDto));
	}
	
	@Test
	public void communicatesMobileLocation_CommunicationNotEstablishedException() {
		String uuid = UUID.randomUUID().toString();
		MobileLocationDto mobileLocationDto = new MobileLocationDto(uuid, 22.9131567f, -43.7763169f);

		when(mobileStationService.findById(uuid)).thenReturn(new MobileStation(uuid, 0, 0));
		when(baseStationService.findAll()).thenReturn(
				Arrays.asList(new BaseStation(UUID.randomUUID().toString(), -27.570596f, -48.8006111f, 30000)));
		
		assertThrows(CommunicationNotEstablishedException.class,
				() -> locationService.communicatesMobileLocation(mobileLocationDto));
	}
}
