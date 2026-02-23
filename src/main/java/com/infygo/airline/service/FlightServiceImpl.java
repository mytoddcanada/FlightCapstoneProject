package com.infygo.airline.service;

import com.infygo.airline.model.Flight;
import com.infygo.airline.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public List<Flight> searchFlight(String source, String destination, LocalDate journeyDate) {
        List<Flight> modifiedFlights = flightRepository.findBySourceAndDestinationAndJourneyDate(source, destination, journeyDate);
        modifiedFlights.forEach(flight -> {
            Month month = flight.getJourneyDate().getMonth();
            if (month == Month.DECEMBER || month == Month.JANUARY) {
                // Apply 10% discount for winter months
                flight.setFare(flight.getFare() * 1.20);
            }
        });

        return modifiedFlights;
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public List<Flight> getFlightsByDate(LocalDate journeyDate) {
        return flightRepository.findFlightsByDate(journeyDate);
    }
}
