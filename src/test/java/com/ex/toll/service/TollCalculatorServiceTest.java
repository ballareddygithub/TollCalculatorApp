package com.ex.toll.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.ex.toll.model.CalculateCostDistResponse;

import org.junit.Assert;

public class TollCalculatorServiceTest {
	
	@InjectMocks
	private TollCalculatorService tollCalculatorService;
	
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void testCostOfTrip() {
		
		CalculateCostDistResponse response = tollCalculatorService.costOfTrip("QEW", "Highway 400");
		Assert.assertEquals(17.9795, response.getCost(), 0); 
		Assert.assertEquals(71.918, response.getDistance(), 0); 
		
	}
	
}
