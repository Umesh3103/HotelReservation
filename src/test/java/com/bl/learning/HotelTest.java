package com.bl.learning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class HotelTest {

	HotelDetails hotel1 = new HotelDetails("LakeWood", 110, 90, 3);
	HotelDetails hotel2 = new HotelDetails("BridgeWood", 150, 50, 4);
	HotelDetails hotel3 = new HotelDetails("RidgeWood", 220, 150, 5);
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
		List result = hotelManagement.cheapestHotelBasedOnRates(hotels, "10/09/2020", "11/09/2020");
		List<String> expected = new ArrayList<>();
		expected.add("LakeWood");
		Assert.assertEquals(expected, result);
	}

	// finding cheapest hotel for weekdays and weekends also
	@Test
	public void GivenHotels_WhenCheapestInWeekdaysAndWeekendsRate_ShouldReturnHotel() {
		HotelManagement hotelManagement = new HotelManagement();
		List result = hotelManagement.cheapestHotelBasedOnRates(hotels, "11/09/2020", "12/09/2020");
		List<String> expected = new ArrayList<>();
		expected.add("LakeWood");
		expected.add("BridgeWood");
		Assert.assertEquals(expected, result);
	}

	// cheapest best rated hotel
	@Test
	public void GivenHotelsWithRatings_WhenFindingCheapest_ShouldReturnHotel() {
		HotelManagement hotelManagement = new HotelManagement();
		String result = hotelManagement.cheapestBestRatedHotel(hotels, "11/09/2020", "12/09/2020");
		Assert.assertEquals("BridgeWood", result);
	}
	
	// finding best rated hotel
	@Test
	public void GivenHotelWithRating_WhenFindingBestRatedHotel_ShouldReturnHotel(){
		HotelManagement hotelManagement = new HotelManagement();
		String result = hotelManagement.BestRatedHotel(hotels, "11/09/2020", "12/09/2020");
		Assert.assertEquals("RidgeWood", result);
		
	}
}
