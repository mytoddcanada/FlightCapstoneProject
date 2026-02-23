package com.infygo.airline.controller;

import com.infygo.airline.DTO.FlightDTO;
import com.infygo.airline.model.Flight;
import com.infygo.airline.service.FlightServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/airline")
public class FlightController {

    private final FlightServiceImpl flightService;

    @Autowired
    public FlightController(FlightServiceImpl flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, welcome to InfyGo Airlines!";
    }

    @PostMapping("/addFlight")
    public Flight addFlight(@RequestBody FlightDTO flightDTO) {
        Flight flight = changeDTOToFlight(flightDTO);
        return flightService.addFlight(flight);
    }

    @GetMapping("/searchFlight")
    public List<Flight> searchFlight(@RequestParam String source, @RequestParam String destination, @RequestParam String journeyDate) {
        LocalDate jd = null;
        if (journeyDate != null && !journeyDate.isBlank()) {
            try {
                jd = LocalDate.parse(journeyDate);
            } catch (DateTimeParseException e) {
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-M-d");
                try {
                    jd = LocalDate.parse(journeyDate, fmt);
                } catch (DateTimeParseException ex) {
                    throw new IllegalArgumentException("Invalid journeyDate format: '" + journeyDate + "'. Expected format: yyyy-MM-dd (e.g. 2026-12-01) or yyyy-M-d (e.g. 2026-12-1).", ex);
                }
            }
        }
        return flightService.searchFlight(source, destination, jd);
    }

    @GetMapping("/getAllFlights")
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/getFlightsByDate")
    public List<Flight> getFlightsByDate(@RequestParam LocalDate journeyDate) {
        return flightService.getFlightsByDate(journeyDate);
    }

    public Flight changeDTOToFlight(FlightDTO flightDTO) {
        Flight flight = new Flight();
        flight.setAirlines(flightDTO.getAirlines());
        flight.setSource(flightDTO.getSource());
        flight.setDestination(flightDTO.getDestination());
        flight.setJourneyDate(LocalDate.parse(flightDTO.getJourneyDate()));
        flight.setFare(flightDTO.getFare());
        flight.setSeatCount(flightDTO.getSeatCount());
        return flight;
    }

}
