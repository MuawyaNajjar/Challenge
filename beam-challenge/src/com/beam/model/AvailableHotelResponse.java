package com.beam.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AvailableHotelResponse implements Comparable {

	private String hotelName;
	private Double fare;
	private Provider provider;
	private List<String> amenities;

	@JsonIgnore
	private Integer rate;

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public Double getFare() {
		return fare;
	}

	public void setFare(Double fare) {
		this.fare = fare;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public List<String> getAmenities() {
		return amenities;
	}

	public void setAmenities(List<String> amenities) {
		this.amenities = amenities;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	@Override
	public int compareTo(Object o) {
		int compareRate = ((AvailableHotelResponse) o).getRate();
		return this.getRate() - compareRate;
	}

}
