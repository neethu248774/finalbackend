package com.ust.air_lines;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;



@Service
public class AirlineService {
   

    @Autowired
    private AirlineRepository ar;

 

 


    public String readJsonFromFileSystem(String filePath) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(filePath));
        String jsonContent= new String(bytes, StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper(); // Assuming you have Jackson ObjectMapper
        Airline[] airline=objectMapper.readValue(jsonContent,Airline[].class);
        List<Airline> airlines=new ArrayList();
        for(Airline am:airline) {
        	airlines.add(am);
        }

        ar.saveAll(airlines);

        return  new String(bytes, StandardCharsets.UTF_8);
//        ObjectMapper objectMapper = new ObjectMapper();
    }


    public List<Airline> getAllAirlines() {
        return ar.findAll();
    }
    public Airline getAirlineByCode(String code) {
        return ar.findByCode(code); // Assuming you have a method findByAirlineCode in your AirlineRepository
    }
}
