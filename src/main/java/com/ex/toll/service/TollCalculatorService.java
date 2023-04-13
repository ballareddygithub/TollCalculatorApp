package com.ex.toll.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.ex.toll.model.CalculateCostDistResponse;
import com.ex.toll.model.Location;
import com.ex.toll.model.Route;
import com.ex.toll.model.TollCalculatorDetails;
import com.ex.toll.model.TollCalculatorRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TollCalculatorService {
	
	public CalculateCostDistResponse costOfTrip(String soure, String destination) {		
		return calculateCostDistance(soure, destination);		
	}
	
	private static CalculateCostDistResponse calculateCostDistance(String soure, String destination) {
		ObjectMapper mapper = new ObjectMapper();
		CalculateCostDistResponse calculateCostDistResponse = null;
		// read JSON file and map/convert to java POJO
		TollCalculatorRequest tollCalculatorRequest = null;
		try {
			File file = ResourceUtils.getFile("classpath:interchanges_modified.json");
			tollCalculatorRequest = (TollCalculatorRequest) mapper.readValue(file, TollCalculatorRequest.class);
		    System.out.println(tollCalculatorRequest);
		    
		    if(tollCalculatorRequest!= null) {
		    	calculateCostDistResponse = calculateDistance(soure, destination, tollCalculatorRequest);
		    	
		    }
		    
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return calculateCostDistResponse;
	}
	
	
	private static CalculateCostDistResponse calculateDistance(String soure, String destination, TollCalculatorRequest tollCalculatorRequest) {

		
		Map<String, Location> locationMap = new HashMap<>();
		int count[] = new int[1];
		count[0] = 1;
		tollCalculatorRequest.getLocations().stream().forEach(r -> {
			
			locationMap.put(String.valueOf(count[0]++), r);
		});
		Location sourceLoc = tollCalculatorRequest.getLocations().stream()
		.filter(loc -> loc.getName().equals(soure))
		.findAny().get();
		
		Location destinationLoc = tollCalculatorRequest.getLocations().stream()
				.filter(loc -> loc.getName().equals(destination))
				.findAny().get();
		int sourePoint = 0;
		if(sourceLoc.getRoutes().size() == 1) {  // source is starting point.
			sourePoint = 1;
		} else {
			sourePoint = sourceLoc.getRoutes().stream().map(r -> r.getToId()).mapToInt(t -> t).sum() / 2;
		}	
		// destination point
		int destinationPoint = destinationLoc.getRoutes().stream().map(r -> r.getToId()).mapToInt(t -> t).sum() / 2;		
		double totalDistance = 0.0;
		for(int i = sourePoint; i < destinationPoint; i++) {
			if(locationMap.get(String.valueOf(i)).getRoutes().size() == 1) {
				totalDistance = totalDistance + locationMap.get(String.valueOf(i)).getRoutes().get(0).getDistance();
			}else {
				int maxVal = locationMap.get(String.valueOf(i)).getRoutes().stream().map(u ->u.getToId()).reduce(Integer::max).get();				
				totalDistance = totalDistance + locationMap.get(String.valueOf(i)).getRoutes().stream().filter(y -> y.getToId() == maxVal).findAny().get().getDistance();
			}			
			
		}	
		// cost = $ 0.25/km
		double totalCost = totalDistance * 0.25;
		CalculateCostDistResponse response = new CalculateCostDistResponse();
		response.setDistance(totalDistance);
		response.setCost(totalCost);
		return response;
	}


	public static void main(String[] args) {
		
		CalculateCostDistResponse calculateCostDistResponse = calculateCostDistance("QEW", "Highway 400");		
		System.out.println("Cost :: " + calculateCostDistResponse.getCost());
		System.out.println("Distance :: " + calculateCostDistResponse.getDistance());
		
		CalculateCostDistResponse calculateCostDistResponse2 = calculateCostDistance("QEW", "Westney Road");		
		System.out.println("Cost :: " + calculateCostDistResponse2.getCost());
		System.out.println("Distance :: " + calculateCostDistResponse2.getDistance());
		
		CalculateCostDistResponse calculateCostDistResponse3 = calculateCostDistance("QEW", "Salem Road");		
		System.out.println("Cost :: " + calculateCostDistResponse3.getCost());
		System.out.println("Distance :: " + calculateCostDistResponse3.getDistance());
		

	}
}
