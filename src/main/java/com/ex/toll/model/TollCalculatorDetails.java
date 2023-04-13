package com.ex.toll.model;

import org.springframework.stereotype.Component;

@Component
public class TollCalculatorDetails {

	private String cost;
	private String distance;
	
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	
	
}
