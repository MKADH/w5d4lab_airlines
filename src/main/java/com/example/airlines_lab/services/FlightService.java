package com.example.airlines_lab.services;

import com.example.airlines_lab.models.Flight;
import com.example.airlines_lab.models.Passenger;
import com.example.airlines_lab.repositories.FlightRepository;
import com.example.airlines_lab.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    @Autowired
    FlightRepository flightRepository;
    @Autowired
    PassengerRepository passengerRepository;
    //  Find all passengers
    public List<Flight> findAllFlights (){
        return flightRepository.findAll();
    }
    //    Find specific passengers
    public Optional<Flight> findFlights (Long id){
        return flightRepository.findById(id);
    }
    public void saveFlight (Flight flight){
        flightRepository.save(flight);
    }
    public void deleteFlight (Long id){
        flightRepository.deleteById(id);
    }
    public void removePassengersFromFlights (Long id) {
        Flight selectedFlight = flightRepository.getById(id);
        for(Passenger passenger : selectedFlight.getPassengers()){
            passenger.removeFlight(selectedFlight);
            passengerRepository.save(passenger);
        }
        flightRepository.deleteById(id);
    }

    public void updateFlight(Flight flight, Long id){
        Flight updateFlight = flightRepository.findById(id).get();
        updateFlight.setCapacity(flight.getCapacity());
        updateFlight.setDestination(flight.getDestination());
        updateFlight.setDepartureDate(flight.getDepartureDate());
        updateFlight.setDepartureTime(flight.getDepartureTime());
        updateFlight.setPassengers(flight.getPassengers());
        flightRepository.save(updateFlight);
    }


}
