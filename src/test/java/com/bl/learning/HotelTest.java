package com.bl.learning;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class HotelTest {
	
	@Before
	public void addHotel(){
		HotelDetails hotel1= new HotelDetails("LakeWood","110$","90$");
		HotelDetails hotel2= new HotelDetails("BridgeWood","160$","60$");
		HotelDetails hotel3= new HotelDetails("RidgeWood","220$","140$");
		List<HotelDetails> hotels= new ArrayList<HotelDetails>();
		hotels.add(hotel1);
		hotels.add(hotel2);
		hotels.add(hotel3);
	}
}
