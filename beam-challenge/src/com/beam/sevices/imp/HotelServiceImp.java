package com.beam.sevices.imp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.beam.model.AvailableHotelResponse;
import com.beam.model.BestHotelResponse;
import com.beam.model.CrazyHotelsResponse;
import com.beam.model.HotelRequest;
import com.beam.model.Provider;
import com.beam.sevices.HotelServices;
import com.beam.startup.StartupListener;
import com.beam.util.AppConstant;

/**
 * 
 * HotelServiceImp Service Class
 * 
 * @author Mu'awya Najjar
 * @since 11 October. 2018
 * @version 1.0.0
 * 
 */

@Service
public class HotelServiceImp implements HotelServices {

	final String BEST_HOTEL_API_URL = "bestHotel";
	final String CRAZY_HOTEL_API_URL = "crazyHotel";
	final String BASE_URL = "http://localhost:8081/simgateway/rest/test/";

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	StartupListener startupListener;

	/***
	 * @author Mu'awyaNajjar
	 * @param hotelRequest
	 * @return BestHotel
	 */

	@Override
	public List<BestHotelResponse> getAllBestHotel(HotelRequest hotelRequest) {
		ResponseEntity<BestHotelResponse[]> response = null;

		// filter BestHotel by on fromDate and toDate based on service

		try {
			response = restTemplate.postForEntity(BASE_URL + BEST_HOTEL_API_URL, hotelRequest,
					BestHotelResponse[].class);
			// Arrays.asList(response.getBody()).stream().filter(obj ->
			// obj.fromDate.equal(hotelRequest.getFromDate())); if we need to filter list after get all hotel
			                                                                 
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Arrays.asList(response.getBody());
	}

	/***
	 * @author Mu'awyaNajjar
	 * @param hotelRequest
	 * @return CrazyHotels
	 * @throws JsonProcessingException
	 */

	@Override
	public List<CrazyHotelsResponse> getAllCrazyHotel(HotelRequest hotelRequest) {
		ResponseEntity<CrazyHotelsResponse[]> response = null;
		try {
			response = restTemplate.postForEntity(BASE_URL + CRAZY_HOTEL_API_URL, hotelRequest,
					CrazyHotelsResponse[].class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Arrays.asList(response.getBody());
	}

	/***
	 * @author Mu'awyaNajjar
	 * @param HotelRequest
	 * @return All AvailableHotel(BestHotel Or CrazyHotel and Sorted By Rate)
	 */
	@Override
	public List<AvailableHotelResponse> getAllavailableHotel(HotelRequest hotelRequest) {
		List<AvailableHotelResponse> availableHotel = new ArrayList<AvailableHotelResponse>();
		List<BestHotelResponse> bestHotel = this.getAllBestHotel(hotelRequest);
		List<CrazyHotelsResponse> crazyHotel = this.getAllCrazyHotel(hotelRequest);

		for (BestHotelResponse best : bestHotel) {
			AvailableHotelResponse available = new AvailableHotelResponse();
			List<String> amenities = new ArrayList<>();
			Provider provider = new Provider();
			provider.setName(AppConstant.BEST_HOTEL);
			available.setFare(best.getHotelFare());
			available.setHotelName(best.getHotel());
			amenities.add(best.getRoomAmenities());
			available.setAmenities(amenities);
			available.setProvider(provider);
			available.setRate(best.getHotelRate());
			availableHotel.add(available);
		}

		for (CrazyHotelsResponse crazy : crazyHotel) {
			AvailableHotelResponse available = new AvailableHotelResponse();
			Provider provider = new Provider();
			provider.setName(AppConstant.CRAZY_HOTEL);
			available.setFare(crazy.getPrice());
			available.setHotelName(crazy.getHotelName());
			available.setAmenities(crazy.getAmenities());
			available.setProvider(provider);
			available.setRate(startupListener.getHotelRate().get(crazy.getRate()));
			availableHotel.add(available);
		}

		Collections.sort(availableHotel, Collections.reverseOrder());
		return availableHotel;
	}

	/*
	 * public List<AvailableHotelResponse> getAvailableHotelByDate(HotelRequest
	 * request) {
	 * 
	 * SimpleDateFormat format = new SimpleDateFormat("mm-dd-yyyy"); Date
	 * requestDate = getDateWithoutTime(request.getFromDate());
	 * format.format(requestDate);
	 * 
	 * List<AvailableHotelResponse> filterList = getAllavailableHotel().stream()
	 * .filter(obj ->
	 * obj.getFrom().equals(requestDate)).collect(Collectors.toList());
	 * 
	 * return filterList;
	 * 
	 * }
	 * 
	 */

	/***
	 * @author Mu'awyaNajjar
	 * @param Date
	 * @return Date without time
	 * 
	 *         I used it to filter the best hotel or crazy to compare date
	 *         without time
	 */
	private static Date getDateWithoutTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		date = calendar.getTime();
		return date;
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
