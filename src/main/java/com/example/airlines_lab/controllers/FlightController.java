package com.example.airlines_lab.controllers;

import com.example.airlines_lab.models.Flight;
import com.example.airlines_lab.repositories.FlightRepository;
import com.example.airlines_lab.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("flights")
public class FlightController {
    @Autowired
    FlightService flightService;
    @Autowired
    FlightRepository flightRepository;

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights (){
        return new ResponseEntity(flightService.findAllFlights(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        Optional<Flight> flight = flightRepository.findById(id);
        return flight.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Flight> postFlight(@RequestBody Flight flight){
        flightService.saveFlight(flight);
        return new ResponseEntity(flightService.findAllFlights(), HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteFlight(@PathVariable Long id){
        flightService.removePassengersFromFlights(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Flight> updateFlight(@RequestBody Flight flight, @PathVariable Long id){
        flightService.updateFlight(flight, id);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

}
