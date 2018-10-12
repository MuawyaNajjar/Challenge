package com.beam.junit;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.beam.config.ApplicationConfiguration;
import com.beam.controller.HotelController;
import com.beam.model.HotelRequest;
import com.beam.sevices.HotelServices;
import com.fasterxml.jackson.databind.ObjectMapper;

@ContextConfiguration(classes = ApplicationConfiguration.class)
public class HotelControllerTest {

	@InjectMocks
	private HotelController hotelController;

	@Mock
	HotelServices hotelServices;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);

	}

	public void testAvailableHotel() throws Exception {
		HotelRequest hotelRequest = new HotelRequest();
		hotelRequest.setCity("");
		hotelRequest.setFromDate(new Date());
		hotelRequest.setNumberOfAdults(1);

		when(hotelServices.getAllavailableHotel(hotelRequest));

		mockMvc.perform(
				post("/available-hotel").contentType(MediaType.APPLICATION_JSON).content(asJsonString(hotelRequest)))
				.andExpect(status().isOk());

		mockMvc.perform(post("/http://localhost:8081/simgateway/rest/test/bestHotel")
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(hotelRequest)))
				.andExpect(status().isOk());

		mockMvc.perform(post("/http://localhost:8081/simgateway/rest/test/crazyHotel")
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(hotelRequest)))
				.andExpect(status().isOk());

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
