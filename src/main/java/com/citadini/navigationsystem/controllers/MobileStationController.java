package com.citadini.navigationsystem.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.citadini.navigationsystem.domain.MobileStation;
import com.citadini.navigationsystem.dto.MobileStationDto;
import com.citadini.navigationsystem.services.MobileStationService;

@RestController
@RequestMapping(value = "/mobile-station")
public class MobileStationController {
	@Autowired
	private MobileStationService service;
	
	@PostMapping()
	public ResponseEntity<Void> insert(@Valid @RequestBody MobileStationDto mobileStationDto) {
		String id = service.insert(mobileStationDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> update(@PathVariable String id, @Valid @RequestBody MobileStationDto mobileStationDto) {
		service.update(mobileStationDto, id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<MobileStation> findById(@PathVariable String id) {
		MobileStation mobileStation = service.findById(id);
		if (mobileStation == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(mobileStation);
	}
	
	@GetMapping()
	public ResponseEntity<List<MobileStation>> findAll() {
		List<MobileStation> list = service.findAll();
		return ResponseEntity.ok(list);
	}
}
