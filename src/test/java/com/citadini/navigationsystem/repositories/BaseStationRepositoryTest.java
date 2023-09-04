package com.citadini.navigationsystem.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.citadini.navigationsystem.domain.BaseStation;

@DataJpaTest
public class BaseStationRepositoryTest {
	@Autowired
	private BaseStationRepository repository;
	
	@Test
	public void testFindAll() {
		List<BaseStation> items = repository.findAll();
		assertEquals(6, items.size());
	}
	
	@Test
	public void testFindById() {
		String id = "5048eac5-fa64-45bd-af55-8cd6cd9fee6d";
		Optional<BaseStation> optionalbBaseStation = repository.findById(id);
		assertEquals(id, optionalbBaseStation.get().getId());
	}
	
	
}
