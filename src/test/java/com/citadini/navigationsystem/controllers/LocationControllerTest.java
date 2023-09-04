package com.citadini.navigationsystem.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.citadini.navigationsystem.dto.MobileLocationDto;
import com.citadini.navigationsystem.services.LocationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(LocationController.class)
public class LocationControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private LocationService locationService;
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	@Test
	public void communicatesMobileLocation_isOk() throws Exception {
		String uuid = UUID.randomUUID().toString();
		MobileLocationDto mobileLocationDto = new MobileLocationDto(uuid, 22.9131567f, -43.7763169f);
		
		ObjectMapper mapper = new ObjectMapper();
		String requestJson = mapper.writeValueAsString(mobileLocationDto);
		
		RequestBuilder request = MockMvcRequestBuilders.put(String.format("/locations/%s", uuid))
				.contentType(APPLICATION_JSON_UTF8)
				.content(requestJson);
		
		mockMvc.perform(request).andExpect(status().isOk())
				.andReturn();
	}
}
