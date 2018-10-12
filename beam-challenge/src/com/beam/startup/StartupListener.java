package com.beam.startup;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupListener {

	private Map<String, Integer> hotelRate = new HashMap<>();

	@EventListener
	public void startupListener(ContextRefreshedEvent event) {
		this.setHotelRate(rate());
	}

	private Map<String, Integer> rate() {
		Map<String, Integer> stars = new HashMap<>();

		stars.put("*", 1);
		stars.put("**", 2);
		stars.put("***", 3);
		stars.put("****", 4);
		stars.put("*****", 5);

		return stars;
	}

	public Map<String, Integer> getHotelRate() {
		return hotelRate;
	}

	public void setHotelRate(Map<String, Integer> hotelRate) {
		this.hotelRate = hotelRate;
	}

}
