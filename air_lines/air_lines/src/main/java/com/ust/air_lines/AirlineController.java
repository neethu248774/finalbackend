package com.ust.air_lines;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/airline")
public class AirlineController {

	@Autowired
	private AirlineService jds;

 
   

    @GetMapping("/fetch-save")
    public ResponseEntity<String> fetchJSONData() throws IOException {

    	return ResponseEntity.ok(jds.readJsonFromFileSystem("src/main/resources/json/airlinesdb.json"));
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Airline>> getAllAirlines() {
        List<Airline> airlines = jds.getAllAirlines();
        return  ResponseEntity.ok(airlines);
    }
//    @GetMapping("get/{code}")
//    public ResponseEntity<Airline> getAirlineByCode(@PathVariable String code) {
//        Airline airline = jds.getAirlineByCode(code);
//        if (airline != null) {
//            return new ResponseEntity<>(airline, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
    @GetMapping("get/{code}")
    public ResponseEntity<Airline> getAirlineByCode(@PathVariable String code) {
        Airline airline = jds.getAirlineByCode(code);
        if (airline != null) {
            return new ResponseEntity<>(airline, HttpStatus.OK);
        } else {
            throw new AirlineNotFoundException("Airline not found with code " +code);
        }
    }
    @ExceptionHandler(AirlineNotFoundException.class)
    public ResponseEntity<String> handleAirlineNotFoundException(AirlineNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
