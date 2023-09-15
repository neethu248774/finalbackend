package com.example.flight_routes;

import java.time.LocalDate;
import java.util.List;

import com.example.flight_routes.dto.GetDetailedItineraryResponse;

public interface AirlineRouteService {
	public List<AirlineRoute> getAllAirlineRoutes();
	public List<AirlineRoute> getAllRoutes();
//	List<AirlineRoute>getBestRecommendations(AirlineRoute request);
	public List<GetDetailedItineraryResponse> getDetailedItinerary(List<Integer> routeIds);
	}
