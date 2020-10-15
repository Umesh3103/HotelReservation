package com.bl.learning;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class HotelManagement {
	public static Map<String, Integer> rewardCostMap = new HashMap<>();
	public static Map<String, Integer> regularCostMap = new HashMap<>();
	public static Map<String, Integer> ratingMap = new HashMap<>();

	// finding cheapest hotel for a given date range
	public static List cheapestHotelBasedOnRates(List<HotelDetails> hotels, String date1, String date2) {
		String[] date1List = date1.split("/");
		int day1 = Integer.parseInt(date1List[0]);
		String[] date2List = date2.split("/");
		int day2 = Integer.parseInt(date2List[0]);
		int min = Integer.MAX_VALUE;
		List<String> cheapestHotel = new ArrayList<>();
		for (HotelDetails hotel : hotels) {
			int totalCost = 0;
			for (int days = day1; days <= day2; days++) {
				String day = dateToDay(Integer.toString(days) + "/" + date1List[1] + "/" + date1List[2]);
				if (day.toLowerCase().contains("sat") || day.toLowerCase().contains("sun")) {
					totalCost += hotel.getWeekendsRates();
				} else {
					totalCost += hotel.getWeekDayRates();
				}
			}
			regularCostMap.put(hotel.getName(), totalCost);
			if (totalCost <= min) {
				min = totalCost;
				cheapestHotel.add(hotel.name);
			}
		}
		return cheapestHotel;
	}

	// finding cheapest best rated hotel
	public static String cheapestBestRatedHotel(List<HotelDetails> hotels, String date1, String date2) {
		String[] date1List = date1.split("/");
		int day1 = Integer.parseInt(date1List[0]);
		String[] date2List = date2.split("/");
		int day2 = Integer.parseInt(date2List[0]);
		int min = Integer.MAX_VALUE;
		int rating = 0;
		List<String> cheapestHotel = new ArrayList<>();
		for (HotelDetails hotel : hotels) {
			int totalCost = 0;
			for (int days = day1; days <= day2; days++) {
				String day = dateToDay(Integer.toString(days) + "/" + date1List[1] + "/" + date1List[2]);
				if (day.toLowerCase().contains("sat") || day.toLowerCase().contains("sun")) {
					totalCost += hotel.getWeekendsRates();
				} else {
					totalCost += hotel.getWeekDayRates();
				}
			}
			if (totalCost <= min && rating < hotel.getRating()) {
				rating = hotel.getRating();
				cheapestHotel.clear();
				min = totalCost;
				cheapestHotel.add(hotel.getName());
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
		String day = sdf.format(myDate);
		return day;
	}

	// finding best rated hotel
	public String BestRatedHotel(List<HotelDetails> hotels, String date1, String date2) {
		String[] date1List = date1.split("/");
		int day1 = Integer.parseInt(date1List[0]);
		String[] date2List = date2.split("/");
		int day2 = Integer.parseInt(date2List[0]);
		int rating = 0;
		List<String> cheapestHotel = new ArrayList<>();
		for (HotelDetails hotel : hotels) {
			int totalCost = 0;
			for (int days = day1; days <= day2; days++) {
				String day = dateToDay(Integer.toString(days) + "/" + date1List[1] + "/" + date1List[2]);
				if (day.toLowerCase().contains("sat") || day.toLowerCase().contains("sun")) {
					totalCost += hotel.getWeekendsRates();
				} else {
					totalCost += hotel.getWeekDayRates();
				}
			}
			if (rating < hotel.getRating()) {
				rating = hotel.getRating();
				cheapestHotel.clear();
				cheapestHotel.add(hotel.getName());
			}
		}
		return cheapestHotel.get(0);
	}

	// finding best rated cheapest hotel for reward customer
	public String bestRatedCheapestHotel(List<HotelDetails> hotels, String customerType, String date1, String date2) {
		String[] date1List = date1.split("/");
		int day1 = Integer.parseInt(date1List[0]);
		String[] date2List = date2.split("/");
		int day2 = Integer.parseInt(date2List[0]);
		int min = Integer.MAX_VALUE;
		int rating = 0;
		List<String> cheapestHotel = new ArrayList<>();
		for (HotelDetails hotel : hotels) {
			int totalCost = 0;
			for (int days = day1; days <= day2; days++) {
				String day = dateToDay(Integer.toString(days) + "/" + date1List[1] + "/" + date1List[2]);
				if (customerType.toLowerCase().equals("regular")) {
					if (day.toLowerCase().contains("sat") || day.toLowerCase().contains("sun")) {
						totalCost += hotel.getWeekendsRates();
					} else {
						totalCost += hotel.getWeekDayRates();
					}
				} else if (customerType.toLowerCase().equals("reward")) {
					if (day.toLowerCase().contains("sat") || day.toLowerCase().contains("sun")) {
						totalCost += hotel.getRewardeeWeekendsRates();
					} else {
						totalCost += hotel.getRewardeeWeekDayRates();
					}
				} else {
					throw new IllegalArgumentException("Enter valid customer type");
				}
			}
			if (totalCost <= min && rating < hotel.getRating()) {
				rating = hotel.getRating();
				cheapestHotel.clear();
				min = totalCost;
				cheapestHotel.add(hotel.getName());
			}
		}
		return cheapestHotel.get(0);
	}

	// finding cheapest best rated hotel using java streams
	public List bestRatedCheapestRewardHotelStream(List<HotelDetails> hotels, String date1, String date2) {
		String[] date1List = date1.split("/");
		int day1 = Integer.parseInt(date1List[0]);
		String[] date2List = date2.split("/");
		int day2 = Integer.parseInt(date2List[0]);

		List<String> cheapestHotel = new ArrayList<>();
		for (HotelDetails hotel : hotels) {
			int totalCost = 0;
			for (int days = day1; days <= day2; days++) {
				String day = dateToDay(Integer.toString(days) + "/" + date1List[1] + "/" + date1List[2]);
				if (day.toLowerCase().contains("sat") || day.toLowerCase().contains("sun")) {
					totalCost += hotel.getRewardeeWeekendsRates();
				} else {
					totalCost += hotel.getRewardeeWeekDayRates();
				}
			}
			rewardCostMap.put(hotel.getName(), totalCost);
			ratingMap.put(hotel.getName(), hotel.getRating());
		}
		String result = rewardCostMap.entrySet().stream().min(Map.Entry.comparingByValue()).get().getKey();
		String bestRatingHotel = ratingMap.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();

		if (bestRatingHotel.equals(result)) {
			cheapestHotel.add(result);
		} else {
			cheapestHotel.add(result);
			cheapestHotel.add(bestRatingHotel);
		}
		return cheapestHotel;
	}
}
