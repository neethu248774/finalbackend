package com.example.airport_db;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/airport")
public class AirportController {
	@Autowired
private AirportService jds;
	 @Autowired
	    private AirportRepository ar;

    @GetMapping("/fetch-json")
    public ResponseEntity<String> fetchJSONData() throws IOException {
    	return ResponseEntity.ok(jds.readJsonFromFileSystem("src/main/resources/json/airport.json"));
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Airport>> getAllAirports() {
        List<Airport> airlines = jds.getAllAirports();
        return  ResponseEntity.ok(airlines);
    }
    @GetMapping("get/{code}")
    public ResponseEntity<Airport> getAirportByCode(@PathVariable String code) {
        Airport airport = jds.getAirportByCode(code);
        if (airport != null) {
            return new ResponseEntity<>(airport, HttpStatus.OK);
        } else {
            throw new AirportNotFoundException("Airport not found with code: " + code);
        }
    }
    @PostMapping("/autocomplete")
    public ResponseEntity<Optional<List<AirportResult>>> autocompleteAirports(@RequestBody Map<String, String> requestBody) {
        String searchString = requestBody.get("search_string");

        // Call the repository method to get airport suggestions
        Optional<List<AirportResult>> airportSuggestions = ar.getAirportSuggestionsWithDetails(searchString);

        return ResponseEntity.ok(airportSuggestions);
    }


    
    @ExceptionHandler(AirportNotFoundException.class)
    public ResponseEntity<String> handleAirportNotFoundException(AirportNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }	
    }

