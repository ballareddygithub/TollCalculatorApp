package com.ex.toll.model;

import java.util.List;

public class Location {

	private String name;
	private Long lat;
	private Long lng;
	private List<Route> routes;
	private String devcomment;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getLat() {
		return lat;
	}
	public void setLat(Long lat) {
		this.lat = lat;
	}
	public Long getLng() {
		return lng;
	}
	public void setLng(Long lng) {
		this.lng = lng;
	}
	public List<Route> getRoutes() {
		return routes;
	}
	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}
	public String getDevcomment() {
		return devcomment;
	}
	public void setDevcomment(String devcomment) {
		this.devcomment = devcomment;
	}	
	
	
}
