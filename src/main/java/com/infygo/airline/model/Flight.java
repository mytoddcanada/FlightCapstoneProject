package com.infygo.airline.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "flights")
@Data
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;

    private String airlines;

    private String source;

    private String destination;

    private Double fare;

    private LocalDate journeyDate;

    private Integer  seatCount;

}
