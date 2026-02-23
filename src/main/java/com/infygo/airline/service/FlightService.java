package com.infygo.airline.service;

import com.infygo.airline.model.Flight;

import java.time.LocalDate;
import java.util.List;

public interface FlightService {

    public Flight addFlight(Flight flight);

    public List<Flight> searchFlight(String source, String destination, LocalDate journeyDate);

    public List<Flight> getAllFlights();

    public List<Flight> getFlightsByDate(LocalDate journeyDate);
}
