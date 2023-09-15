package com.example.flight_routes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class GetDetailedItineraryResponse {
	public int id;
	public Airport flyingFrom;
	public Airport flyingTo;
	public Airline airline;
	
	
	public GetDetailedItineraryResponse(int id, Airport flyingFrom, Airport flyingTo, Airline airline) {
		super();
		this.id = id;
		this.flyingFrom = flyingFrom;
		this.flyingTo = flyingTo;
		this.airline = airline;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Airport getFlyingFrom() {
		return flyingFrom;
	}
	public void setFlyingFrom(Airport flyingFrom) {
		this.flyingFrom = flyingFrom;
	}
	public Airport getFlyingTo() {
		return flyingTo;
	}
	public void setFlyingTo(Airport flyingTo) {
		this.flyingTo = flyingTo;
	}
	public Airline getAirline() {
		return airline;
	}
	public void setAirline(Airline airline) {
		this.airline = airline;
	}
	
}