 package com.example.flight_routes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.flight_routes.dto.GetDetailedItineraryResponse;


@RestController
@RequestMapping("/api/routes")

 

public class AirlineRouteController {

 

	@Autowired
	AirlineRouteService airlineRouteService;

	@Autowired
	AirlineRouteRepo ar;

	@GetMapping("fetch-save")
	public List<AirlineRoute> fetchAllAirlineRoute() {
		return airlineRouteService.getAllAirlineRoutes();
	}



	@GetMapping("/getAll")
	public ResponseEntity<List<AirlineRoute>> getAllAirlines() {
		List<AirlineRoute>airlines=airlineRouteService.getAllRoutes();
		return ResponseEntity.ok(airlines);
	}

 

	@PostMapping("/best")

 

	public List<AirlineRoute> getRoute(@RequestBody AirlineRoute model){
		List<AirlineRoute> data = new ArrayList<>();
	List<Boolean> category = Arrays.asList(false, false, false);
		if (model.getClassType().equalsIgnoreCase("Business")) {
			category.set(0, true);
		} else if (model.getClassType().equalsIgnoreCase("Economy")) {
			category.set(1, true);
		} else if (model.getClassType().equalsIgnoreCase("first")) {
			category.set(2, true);
		}
		else return null;
		List<String> dayStatus = Arrays.asList("no", "no", "no", "no", "no", "no", "no");
		int day=model.getDate().getDayOfWeek().getValue();

		if(day==0) {
			dayStatus.set(6,"yes");
		}else {
		dayStatus.set(day-1, "yes");
		}
//		
//		for(AirlineRoute r: ar.findAll()) {
//			if ((r.getIataFrom().equalsIgnoreCase(model.getIataFrom())) && 
//				    (r.getIataTo().equalsIgnoreCase(model.getIataTo())) &&
//				    (
//				        (r.getDay1().equals(dayStatus.get(0)) && dayStatus.get(0).equals("yes")) ||
//				        (r.getDay2().equals(dayStatus.get(1)) && dayStatus.get(1).equals("yes")) ||
//				        (r.getDay3().equals(dayStatus.get(2)) && dayStatus.get(2).equals("yes")) ||
//				        (r.getDay4().equals(dayStatus.get(3)) && dayStatus.get(3).equals("yes")) ||
//				        (r.getDay5().equals(dayStatus.get(4)) && dayStatus.get(4).equals("yes")) ||
//				        (r.getDay6().equals(dayStatus.get(5)) && dayStatus.get(5).equals("yes")) ||
//				        (r.getDay7().equals(dayStatus.get(6)) && dayStatus.get(6).equals("yes"))
//				    ) &&
//				    (
//				        (r.isClassBusiness() == category.get(0)) &&
//				        (r.isClassEconomy() == category.get(1)) &&
//				        (r.isClassFirst() == category.get(2))
//				    )
//				) {
//				    data.add(r);
//				}
//
//			}
//		

 

		return ar.findBestRoutes(model.getIataFrom(), model.getIataTo(),category.get(0) , category.get(1), category.get(2), dayStatus.get(0), dayStatus.get(1), dayStatus.get(2), dayStatus.get(3), dayStatus.get(4), dayStatus.get(5), dayStatus.get(6));
}
	
	
	@PostMapping("/multicity-best")
	public ResponseEntity<List<AirlineRoute>> getMultiCityRoutes(@RequestBody List<AirlineRoute> routes) {
	    List<AirlineRoute> data = new ArrayList<>();

	    // Iterate through the list of routes and find the best routes for each
	    for (AirlineRoute route : routes) {
	        List<AirlineRoute> bestRoutes = findBestRoutesForRoute(route);
	        data.addAll(bestRoutes);
	    }

	    return ResponseEntity.ok(data);
	}

	private List<AirlineRoute> findBestRoutesForRoute(AirlineRoute route) {
	    List<Boolean> category = Arrays.asList(false, false, false);
	    if (route.getClassType().equalsIgnoreCase("Business")) {
	        category.set(0, true);
	    } else if (route.getClassType().equalsIgnoreCase("Economy")) {
	        category.set(1, true);
	    } else if (route.getClassType().equalsIgnoreCase("first")) {
	        category.set(2, true);
	    }

	    List<String> dayStatus = Arrays.asList("no", "no", "no", "no", "no", "no", "no");
	    int day = route.getDate().getDayOfWeek().getValue();

	    if (day == 0) {
	        dayStatus.set(6, "yes");
	    } else {
	        dayStatus.set(day - 1, "yes");
	    }

	    return ar.findBestRoutes(route.getIataFrom(), route.getIataTo(), category.get(0), category.get(1), category.get(2), dayStatus.get(0), dayStatus.get(1), dayStatus.get(2), dayStatus.get(3), dayStatus.get(4), dayStatus.get(5), dayStatus.get(6));
	}

	@PostMapping("/getdetaileditinerary")
	public List<GetDetailedItineraryResponse> fetchDetailedItinerary(@RequestBody List<Integer> routeIds){
		return airlineRouteService.getDetailedItinerary(routeIds);
	}
	
}






