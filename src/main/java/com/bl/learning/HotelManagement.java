package com.bl.learning;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class HotelManagement {

	// finding cheapest hotel for a given date range
	public static String cheapestHotelBasedOnRates(List<HotelDetails> hotels, String date1, String date2) {
		String[] date1List = date1.split("/");
		int day1 = Integer.parseInt(date1List[0]);
		String[] date2List = date2.split("/");
		int day2 = Integer.parseInt(date2List[0]);
		int min = Integer.MAX_VALUE;
		int totalCost = 0;
		List<String> cheapestHotel = new ArrayList<>();
		for (int days = day1; days <= day2; days++) {
			for (HotelDetails hotel : hotels) {
				if (hotel.weekDayRates <= min) {
					min = hotel.weekDayRates;
					cheapestHotel.add(hotel.getName());
					totalCost += min;
				}
			}
		}
		return cheapestHotel.get(0);

	}

	// converting date to day
	public static String dateToDay(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", java.util.Locale.ENGLISH);
		Date myDate = null;
		try {
			myDate = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sdf.applyPattern("EEE");
		String day = sdf.format(date);
		return day;
	}
}
