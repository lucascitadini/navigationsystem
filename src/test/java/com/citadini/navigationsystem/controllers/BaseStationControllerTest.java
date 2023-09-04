package com.citadini.navigationsystem.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.citadini.navigationsystem.dto.BaseStationCommunicationsReportsDto;
import com.citadini.navigationsystem.dto.BaseStationDto;
import com.citadini.navigationsystem.dto.CommunicationReportDto;
import com.citadini.navigationsystem.services.BaseStationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(BaseStationController.class)
public class BaseStationControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BaseStationService baseStationService;

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Test
	public void insertBastStation_isCreated() throws Exception {
		BaseStationDto baseStationDto = new BaseStationDto("Rio de Janeiro Station", 22.9131567f, -43.7763169f, 40000);
		String uuid = UUID.randomUUID().toString();
		when(baseStationService.insert(baseStationDto)).thenReturn(uuid);

		ObjectMapper mapper = new ObjectMapper();
		String requestJson = mapper.writeValueAsString(baseStationDto);
		
		RequestBuilder request = MockMvcRequestBuilders.post("/base-station").contentType(APPLICATION_JSON_UTF8)
				.content(requestJson);

		mockMvc.perform(request).andExpect(status().isCreated()).andReturn();
	}

	@Test
	public void insertBastStation_isBadRequest() throws Exception {
		BaseStationDto baseStationDto = new BaseStationDto("Rio de Janeiro Station", 91.9131567f, -43.7763169f, 40000);
		String uuid = UUID.randomUUID().toString();
		when(baseStationService.insert(baseStationDto)).thenReturn(uuid);

		ObjectMapper mapper = new ObjectMapper();
		String requestJson = mapper.writeValueAsString(baseStationDto);
		RequestBuilder request = MockMvcRequestBuilders.post("/base-station").contentType(APPLICATION_JSON_UTF8)
				.content(requestJson);

		mockMvc.perform(request).andExpect(status().isBadRequest()).andReturn();
	}

	@Test
	public void updateBastStation_isOk() throws Exception {
		BaseStationDto baseStationDto = new BaseStationDto("Rio de Janeiro Station", 22.9131567f, -43.7763169f, 40000);
		String uuid = UUID.randomUUID().toString();

		ObjectMapper mapper = new ObjectMapper();
		String requestJson = mapper.writeValueAsString(baseStationDto);
		RequestBuilder request = MockMvcRequestBuilders.put(String.format("/base-station/%s", uuid))
				.contentType(APPLICATION_JSON_UTF8).content(requestJson);

		mockMvc.perform(request).andExpect(status().isOk()).andReturn();
	}

	@Test
	public void updateBastStation_isBadRequest() throws Exception {
		BaseStationDto baseStationDto = new BaseStationDto("Rio de Janeiro Station", 91.9131567f, -43.7763169f, 40000);
		String uuid = UUID.randomUUID().toString();

		ObjectMapper mapper = new ObjectMapper();
		String requestJson = mapper.writeValueAsString(baseStationDto);
		RequestBuilder request = MockMvcRequestBuilders.put(String.format("/base-station/%s", uuid))
				.contentType(APPLICATION_JSON_UTF8).content(requestJson);

		mockMvc.perform(request).andExpect(status().isBadRequest()).andReturn();
	}

	@Test
	public void getCommunicationsById_isOk() throws Exception {
		String uuid = UUID.randomUUID().toString();
		BaseStationCommunicationsReportsDto baseStationCommunicationsReportsDto = new BaseStationCommunicationsReportsDto();
		baseStationCommunicationsReportsDto.setBaseStationId(uuid);
		baseStationCommunicationsReportsDto.setReports(
				Arrays.asList(new CommunicationReportDto(UUID.randomUUID().toString(), 2000, LocalDateTime.now())));
		when(baseStationService.findBaseStationCommunicationsReportsById(uuid))
				.thenReturn(baseStationCommunicationsReportsDto);

		ObjectMapper mapper = new ObjectMapper();
		mapper.findAndRegisterModules();
		String responseJson = mapper.writeValueAsString(baseStationCommunicationsReportsDto);
		RequestBuilder request = MockMvcRequestBuilders.get(String.format("/base-station/communications/%s", uuid))
				.accept(APPLICATION_JSON_UTF8);
		
		mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().json(responseJson))
				.andReturn();
	}
	
	@Test
	public void getCommunicationsById_isNoContent() throws Exception {
		String uuid = UUID.randomUUID().toString();
		when(baseStationService.findBaseStationCommunicationsReportsById(uuid))
				.thenReturn(null);
		RequestBuilder request = MockMvcRequestBuilders.get(String.format("/base-station/communications/%s", uuid))
				.accept(APPLICATION_JSON_UTF8);
		
		mockMvc.perform(request).andExpect(status().isNoContent())
				.andReturn();
	}

}
