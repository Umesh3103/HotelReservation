package com.bl.learning;

public class HotelDetails {
	String name;
	int weekDayRates;

	// Constructor
	public HotelDetails(String name, int weekDayRates) {
		super();
		this.name = name;
		this.weekDayRates = weekDayRates;
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
