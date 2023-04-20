package com.example.airlines_lab.services;

import com.example.airlines_lab.models.Flight;
import com.example.airlines_lab.models.Passenger;
import com.example.airlines_lab.models.PassengerDTO;
import com.example.airlines_lab.repositories.FlightRepository;
import com.example.airlines_lab.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PassengerService {

    @Autowired
    PassengerRepository passengerRepository;
    @Autowired
    FlightRepository flightRepository;

    public Passenger updatePassenger(PassengerDTO passengerDTO, Long id){
        Passenger passengerToUpdate = passengerRepository.findById(id).get();
        passengerToUpdate.setName(passengerDTO.getName());
        passengerToUpdate.setEmailAddress(passengerDTO.getEmailAddress());
        passengerToUpdate.setFlights(new ArrayList<Flight>());
        for (Long passengerId : passengerDTO.getFlightIds()){
            Flight flight = flightRepository.findById(passengerId).get();
            passengerToUpdate.addFlight(flight);
        }
        passengerRepository.save(passengerToUpdate);
        return passengerToUpdate;
    }

    public void savePassenger(PassengerDTO passengerDTO){
        Passenger passenger = new Passenger(passengerDTO.getName(), passengerDTO.getEmailAddress());
        for (Long flightId : passengerDTO.getFlightIds()){
            Flight flight = flightRepository.findById(flightId).get();
            passenger.addFlight(flight);
        }
        passengerRepository.save(passenger);
    }

    public Passenger findPassenger(Long id){
        return passengerRepository.findById(id).get();
    }

    public List<Passenger> findAllPassengers(){
        return passengerRepository.findAll();
    }

    public void deletePassenger(Long id){
        passengerRepository.deleteById(id);
    }


}
