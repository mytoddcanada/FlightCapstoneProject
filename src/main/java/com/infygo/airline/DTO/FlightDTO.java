package com.infygo.airline.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FlightDTO {

    private Long flightId;

    private String airlines;

    private String source;

    private String destination;

    private Double fare;

    private String journeyDate;

    private Integer  seatCount;
}
