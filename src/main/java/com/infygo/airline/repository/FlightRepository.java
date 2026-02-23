package com.infygo.airline.repository;

import com.infygo.airline.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("SELECT f FROM Flight f WHERE f.source = :source AND f.destination = :destination AND f.journeyDate = :journeyDate")
    public List<Flight> findBySourceAndDestinationAndJourneyDate(String source, String destination, LocalDate journeyDate);

    @Query("SELECT f FROM Flight f WHERE f.journeyDate = :journeyDate")
    public List<Flight> findFlightsByDate(LocalDate journeyDate);
}
