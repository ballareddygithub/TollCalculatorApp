package com.ex.toll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ex.toll.service.TollCalculatorService;

@RequestMapping("/v1")
@RestController
public class TollCalculatorController {

	@Autowired
	private TollCalculatorService tollCalculatorService;
	
	@GetMapping("/calCostDist/{soure}/{destination}")
	public ResponseEntity<Object> calculateCostDistance(@PathVariable String soure, @PathVariable String destination) {
		return ResponseEntity.ok(tollCalculatorService.costOfTrip(soure, destination));		
	}
}
