package com.bl.learning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class HotelTest {

	HotelDetails hotel1 = new HotelDetails("LakeWood", 110);
	HotelDetails hotel2 = new HotelDetails("BridgeWood", 150);
	HotelDetails hotel3 = new HotelDetails("RidgeWood", 220);
	List<HotelDetails> hotels = new ArrayList<HotelDetails>();

	// adding hotels
	@Before
	public void addingHotels() {
		hotels.add(hotel1);
		hotels.add(hotel2);
		hotels.add(hotel3);
	}

	// finding cheapest hotel for weekday rates
	@Test
	public void GivenHotels_WhenCheapestInRate_ShouldReturnHotel() {
		HotelManagement hotelManagement = new HotelManagement();
		String result = hotelManagement.cheapestHotelBasedOnRates(hotels, "10/09/2020", "11/09/2020");
		Assert.assertEquals("LakeWood", result);
	}
}
