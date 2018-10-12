package com.beam.model;

public class BestHotelResponse {

	private String hotel;
	private Integer hotelRate;
	private Double hotelFare;
	private String roomAmenities;

	public String getHotel() {
		return hotel;
	}

	public void setHotel(String hotel) {
		this.hotel = hotel;
	}

	public Integer getHotelRate() {
		return hotelRate;
	}

	public void setHotelRate(Integer hotelRate) {
		this.hotelRate = hotelRate;
	}

	public Double getHotelFare() {
		return hotelFare;
	}

	public void setHotelFare(Double hotelFare) {
		this.hotelFare = hotelFare;
	}

	public String getRoomAmenities() {
		return roomAmenities;
	}

	public void setRoomAmenities(String roomAmenities) {
		this.roomAmenities = roomAmenities;
	}

}
