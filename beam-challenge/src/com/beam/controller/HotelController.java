package com.beam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beam.model.AvailableHotelResponse;
import com.beam.model.HotelRequest;
import com.beam.sevices.HotelServices;

/**
* @author  Mu'awya Najjar
* @version 1.0.0
* @since 11 October.2018
*/

@RestController
@RequestMapping(value = "/hotel", produces = MediaType.APPLICATION_JSON_VALUE)
public class HotelController {

	@Autowired
	HotelServices hotelServices;
	
	/***
	 * @author Mu'awyaNajjar
	 * @param hotelRequest
	 * @return all available hotel (crazy or best)
	 */


	@PostMapping("/available-hotel")
	public List<AvailableHotelResponse> getAllavailableHotel(@RequestBody HotelRequest hotelRequest) {
		List<AvailableHotelResponse> getAvailable = null;
		try {
			getAvailable = hotelServices.getAllavailableHotel(hotelRequest);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return getAvailable;
	}

}
