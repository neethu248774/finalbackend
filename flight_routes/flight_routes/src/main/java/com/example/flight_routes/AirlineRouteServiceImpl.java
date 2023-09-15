package com.example.flight_routes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.flight_routes.config.AirportConfiguration;
import com.example.flight_routes.dto.Airline;
import com.example.flight_routes.dto.Airport;
import com.example.flight_routes.dto.GetDetailedItineraryResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
	
@Service



public class AirlineRouteServiceImpl implements AirlineRouteService {

 

	@Autowired
	AirlineRouteRepo airlineRouteRepo;
	
	@Autowired
	RestTemplate airportConfig;

 

	public List<AirlineRoute> getAllAirlineRoutes() {
		List<AirlineRoute> dataList = new ArrayList();
		ObjectMapper objectMapper = new ObjectMapper();
	try {
			// Specify the path to your JSON file
			File jsonFile = new File("src/main/resources/json/flightsDB.routes_v2.json");
			// Read JSON data into a List of MyJsonObject
			dataList = objectMapper.readValue(jsonFile, new TypeReference<List<AirlineRoute>>() {
			});

		// Now you can work with the 'dataList' which contains a list of JSON objects.
			for (AirlineRoute obj : dataList) {
				airlineRouteRepo.save(obj);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataList;

 

}

 

	public List<AirlineRoute> getAllRoutes() {
		return airlineRouteRepo.findAll();
	}

 

	
//	public List<AirlineRoute> getBestRecommendations(AirlineRoute request) {
//		// TODO Auto-generated method stub
//		return null;
//	}

 

	


//	public List<AirlineRoute> getBestRecommendations(AirlineRoute request) {
//		String iataFrom =request.getIataFrom();
//		String iataTo=request.getIataTo();
//		boolean classBusiness=request.isClassBusiness();
//		boolean classEconomy=request.isClassEconomy();
//		boolean classFirst=request.isClassFirst();
//		List<AirlineRoute>recommendations=airlineRouteRepo.findBestRoutes(iataFrom, iataTo, classBusiness, classEconomy, classFirst,);
//		return recommendations;
//	}

 @Override
		public List<GetDetailedItineraryResponse> getDetailedItinerary(List<Integer> routeId) {
			List<GetDetailedItineraryResponse> itineraryList = new ArrayList<>();
			for (int id : routeId) {

	 

				AirlineRoute getRoute = airlineRouteRepo.findById(id).get();
				String airlineIata = getRoute.getAirLineIata();
				String airportToIata = getRoute.getIataTo();
				String airportFromIata = getRoute.getIataFrom();
				Airline getAirline = airportConfig
						.getForObject("http://localhost:8000/api/airline/get/" + airlineIata, Airline.class);
				Airport getAirportTo = airportConfig
						.getForObject("http://localhost:8100/api/airport/get/" + airportToIata, Airport.class);
				Airport getAirportFrom = airportConfig
						.getForObject("http://localhost:8100/api/airport/get/" + airportFromIata, Airport.class);
				GetDetailedItineraryResponse itineraryResponse = new GetDetailedItineraryResponse(id, getAirportFrom,
						getAirportTo, getAirline);
				itineraryList.add(itineraryResponse);
			}

	 

			return itineraryList;
		}

}

 