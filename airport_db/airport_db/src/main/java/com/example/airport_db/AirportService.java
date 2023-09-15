package com.example.airport_db;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;







@Service
public class AirportService {
	 @Autowired
	    private AirportRepository ar;




public String readJsonFromFileSystem(String filePath) throws IOException {
    byte[] bytes = Files.readAllBytes(Paths.get(filePath));
    String jsonContent= new String(bytes, StandardCharsets.UTF_8);
    ObjectMapper objectMapper = new ObjectMapper(); // Assuming you have Jackson ObjectMapper
    Airport[] airline=objectMapper.readValue(jsonContent,Airport[].class);
    List<Airport> airlines=new ArrayList();
    for(Airport am:airline) {
    	airlines.add(am);
    }
for(Airport am:airline) {
	try {
    ar.save(am);
	}
	catch (Exception e) {
		// TODO: handle exception
		
		System.out.println(am.getState());
		
	}
}

    return  new String(bytes, StandardCharsets.UTF_8);
//    ObjectMapper objectMapper = new ObjectMapper();
}
public List<Airport> getAllAirports() {
    return ar.findAll();
}
public Airport getAirportByCode(String code) {
    return ar.findByCode(code); // Assuming you have a method findByAirlineCode in your AirlineRepository
}   
}
