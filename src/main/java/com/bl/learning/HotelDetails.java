package com.bl.learning;

public class HotelDetails {
	String name;
	String weekDayRates;
	String weekendsRates;
	
	public HotelDetails(String name, String weekDayRates, String weekendsRates) {
		super();
		this.name = name;
		this.weekDayRates = weekDayRates;
		this.weekendsRates=weekendsRates;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWeekDayRates() {
		return weekDayRates;
	}
	public void setWeekDayRates(String rates) {
		this.weekDayRates = rates;
	}
	public String getWeekendsRates() {
		return weekendsRates;
	}
	public void setWeekendsRates(String weekendsRates) {
		this.weekendsRates = weekendsRates;
	}
}
