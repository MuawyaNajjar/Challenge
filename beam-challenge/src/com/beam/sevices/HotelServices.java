package com.beam.sevices;

import java.util.List;

import com.beam.model.AvailableHotelResponse;
import com.beam.model.BestHotelResponse;
import com.beam.model.CrazyHotelsResponse;
import com.beam.model.HotelRequest;

public interface HotelServices {

	List<BestHotelResponse> getAllBestHotel(HotelRequest hotelRequest);

	List<CrazyHotelsResponse> getAllCrazyHotel(HotelRequest hotelRequest);

	List<AvailableHotelResponse> getAllavailableHotel(HotelRequest hotelRequest);

}
