package com.bl.learning;

public class HotelDetails {
	String name;
	int weekDayRates;
	int weekendsRates;
	int rating;
	int rewardeeWeekDayRates;
	int rewardeeWeekendsRates;

	// Constructor
	public HotelDetails(String name, int weekDayRates, int weekendsRates, int rating, int rewardeeWeekDayRates,
			int rewardeeWeekendsRates) {
		super();
		this.name = name;
		this.weekDayRates = weekDayRates;
		this.weekendsRates = weekendsRates;
		this.rating = rating;
		this.rewardeeWeekDayRates = rewardeeWeekDayRates;
		this.rewardeeWeekendsRates = rewardeeWeekendsRates;
	}

	public int getRewardeeWeekDayRates() {
		return rewardeeWeekDayRates;
	}

	public void setRewardeeWeekDayRates(int rewardeeWeekDayRates) {
		this.rewardeeWeekDayRates = rewardeeWeekDayRates;
	}

	public int getRewardeeWeekendsRates() {
		return rewardeeWeekendsRates;
	}

	public void setRewardeeWeekendsRates(int rewardeeWeekendsRates) {
		this.rewardeeWeekendsRates = rewardeeWeekendsRates;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getWeekendsRates() {
		return weekendsRates;
	}

	public void setWeekendsRates(int weekendsRates) {
		this.weekendsRates = weekendsRates;
	}

	// getting hotel name
	public String getName() {
		return name;
	}

	// setting hotel name
	public void setName(String name) {
		this.name = name;
	}

	// getting week day rates
	public int getWeekDayRates() {
		return weekDayRates;
	}

	// setting week day rates
	public void setWeekDayRates(int rates) {
		this.weekDayRates = rates;
	}
}
