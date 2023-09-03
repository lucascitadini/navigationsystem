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

import com.citadini.navigationsystem.dto.BaseStationCommunicationsReportsDto;
import com.citadini.navigationsystem.dto.BaseStationDto;
import com.citadini.navigationsystem.domain.BaseStation;
import com.citadini.navigationsystem.services.BaseStationService;

@RestController
@RequestMapping(value = "/base-station")
public class BaseStationController {
	@Autowired
	private BaseStationService service;

	@PostMapping()
	public ResponseEntity<Void> insert(@Valid @RequestBody BaseStationDto baseStationDto) {
		String id = service.insert(baseStationDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable String id, @Valid @RequestBody BaseStationDto baseStationDto) {
		service.update(baseStationDto, id);
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<BaseStation> findById(@PathVariable String id) {
		BaseStation baseStation = service.findById(id);
		if (baseStation == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(baseStation);
	}

	@GetMapping()
	public ResponseEntity<List<BaseStation>> findAll() {
		List<BaseStation> list = service.findAll();
		return ResponseEntity.ok(list);
	}

	@GetMapping(value = "/communications/{id}")
	public ResponseEntity<BaseStationCommunicationsReportsDto> getCommunicationsById(@PathVariable String id) {
		BaseStationCommunicationsReportsDto baseStationCommunicationsReportsDto = service
				.findBaseStationCommunicationsReportsById(id);
		if (baseStationCommunicationsReportsDto == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(baseStationCommunicationsReportsDto);
	}
}
