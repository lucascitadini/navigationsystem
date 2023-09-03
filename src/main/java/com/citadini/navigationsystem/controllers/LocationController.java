package com.citadini.navigationsystem.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citadini.navigationsystem.dto.MobileLocationDto;
import com.citadini.navigationsystem.services.LocationService;

@RestController
@RequestMapping(value = "/locations")
public class LocationController {
	@Autowired
	private LocationService service;

	@PutMapping(value = "/{uuid}")
	public ResponseEntity<Void> communicatesMobileLocation(@PathVariable String uuid,
			@Valid @RequestBody MobileLocationDto mobileLocationDto) {
		mobileLocationDto.setMobileStationId(uuid);
		service.communicatesMobileLocation(mobileLocationDto);
		return ResponseEntity.ok().build();
	}
}
