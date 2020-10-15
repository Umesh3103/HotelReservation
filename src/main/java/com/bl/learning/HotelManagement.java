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
		String[] date2List = date2.split("/");
		int min = Integer.MAX_VALUE;
		List<String> cheapestHotel = new ArrayList<>();
		for (HotelDetails hotel : hotels) {
			int totalCost = 0;
			for (int days = getDayAsInteger(date1List); days <= getDayAsInteger(date2List); days++) {
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
		String[] date2List = date2.split("/");
		int min = Integer.MAX_VALUE;
		int rating = 0;
		List<String> cheapestHotel = new ArrayList<>();
		for (HotelDetails hotel : hotels) {
			int totalCost = 0;
			for (int days = getDayAsInteger(date1List); days <= getDayAsInteger(date2List); days++) {
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
		String[] date2List = date2.split("/");
		int rating = 0;
		List<String> cheapestHotel = new ArrayList<>();
		for (HotelDetails hotel : hotels) {
			int totalCost = 0;
			for (int days = getDayAsInteger(date1List); days <= getDayAsInteger(date2List); days++) {
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
		String[] date2List = date2.split("/");
		int min = Integer.MAX_VALUE;
		int rating = 0;
		List<String> cheapestHotel = new ArrayList<>();
		for (HotelDetails hotel : hotels) {
			int totalCost = 0;
			for (int days = getDayAsInteger(date1List); days <= getDayAsInteger(date2List); days++) {
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
	public String bestRatedCheapestRewardHotelStream(List<HotelDetails> hotels, String date1, String date2) {
		String[] date1List = date1.split("/");
		String[] date2List = date2.split("/");
		int min = Integer.MAX_VALUE;
		List<String> cheapestHotel = new ArrayList<>();
		for (HotelDetails hotel : hotels) {
			int totalCost = 0;
			for (int days = getDayAsInteger(date1List); days <= getDayAsInteger(date2List); days++) {
				String day = dateToDay(Integer.toString(days) + "/" + date1List[1] + "/" + date1List[2]);
				if (day.toLowerCase().contains("sat") || day.toLowerCase().contains("sun")) {
					totalCost += hotel.getRewardeeWeekendsRates();
				} else {
					totalCost += hotel.getRewardeeWeekDayRates();
				}
			}
			if (totalCost <= min) {
				min = totalCost;
				cheapestHotel.add(hotel.getName());
			}
			rewardCostMap.put(hotel.getName(), totalCost);
			ratingMap.put(hotel.getName(), hotel.getRating());
		}

		String result = ratingMap.entrySet().stream().filter(p -> cheapestHotel.contains(p.getKey()))
				.max(Map.Entry.comparingByValue()).get().getKey();

		return result;
	}

	// finding best rated cheapest hotel for regular customer using java streams
	public String bestRatedCheapestRegularHotelStream(List<HotelDetails> hotels, String date1, String date2) {
		String[] date1List = date1.split("/");
		String[] date2List = date2.split("/");
		int min = Integer.MAX_VALUE;
		List<String> cheapestHotel = new ArrayList<>();
		for (HotelDetails hotel : hotels) {
			int totalCost = 0;
			for (int days = getDayAsInteger(date1List); days <= getDayAsInteger(date2List); days++) {
				String day = dateToDay(Integer.toString(days) + "/" + date1List[1] + "/" + date1List[2]);
				if (day.toLowerCase().contains("sat") || day.toLowerCase().contains("sun")) {
					totalCost += hotel.getWeekendsRates();
				} else {
					totalCost += hotel.getWeekDayRates();
				}
			}
			if (totalCost <= min) {
				min = totalCost;
				cheapestHotel.add(hotel.getName());
			}
			regularCostMap.put(hotel.getName(), totalCost);
			ratingMap.put(hotel.getName(), hotel.getRating());
		}

		String result = ratingMap.entrySet().stream().filter(p -> cheapestHotel.contains(p.getKey()))
				.max(Map.Entry.comparingByValue()).get().getKey();

		return result;
	}

	// getting day as integer
	public static int getDayAsInteger(String[] dateList) {
		int day = Integer.parseInt(dateList[0]);
		return day;
	}
}
