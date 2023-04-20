package com.example.airlines_lab.controllers;

import com.example.airlines_lab.models.Passenger;
import com.example.airlines_lab.models.PassengerDTO;
import com.example.airlines_lab.repositories.PassengerRepository;
import com.example.airlines_lab.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("passengers")
public class PassengerController {
    @Autowired
    PassengerService passengerService;
    //    INDEX
    @GetMapping
    public ResponseEntity<List<Passenger>> getAllPassengers(){
        return new ResponseEntity<>(passengerService.findAllPassengers(), HttpStatus.OK);
    }

    //    SHOW
    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Passenger>> getPassenger(@PathVariable Long id){
        Passenger selectedPassenger = passengerService.findPassenger(id);
        return new ResponseEntity(selectedPassenger, HttpStatus.OK);
    }

    //    CREATE (chocolate can be created with or without estates)
    @PostMapping
    public ResponseEntity<List<Passenger>> postPassenger(@RequestBody PassengerDTO passengerDTO){
        passengerService.savePassenger(passengerDTO);
        return new ResponseEntity<>(passengerService.findAllPassengers(), HttpStatus.CREATED);
    }

    //    UPDATE
    @PutMapping(value = "/{id}")
    public ResponseEntity<Passenger> updatePassenger(@RequestBody PassengerDTO passengerDTO, @PathVariable Long id){
        Passenger updatedPassenger = passengerService.updatePassenger(passengerDTO, id);
        return new ResponseEntity<>(updatedPassenger, HttpStatus.OK);
    }

    //    DELETE
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deletePassenger(@PathVariable Long id){
        passengerService.deletePassenger(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }


}
