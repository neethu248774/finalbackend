package com.example.airport_db;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AirportRepository extends JpaRepository<Airport, Long>{
	Airport findByCode(String code);
	@Query(value = "SELECT new com.example.airport_db.AirportResult(a.code, a.name, a.state, a.country) FROM Airport a WHERE a.name LIKE :searchString% OR a.code LIKE :searchString%")
	Optional<List<AirportResult>> getAirportSuggestionsWithDetails(@Param("searchString") String searchString);


	
}
