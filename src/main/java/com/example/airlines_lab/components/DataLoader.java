package com.example.airlines_lab.components;

import com.example.airlines_lab.models.Flight;
import com.example.airlines_lab.models.Passenger;
import com.example.airlines_lab.repositories.FlightRepository;
import com.example.airlines_lab.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

@Component
public class DataLoader implements ApplicationRunner {
    @Autowired
    FlightRepository flightRepository;
    @Autowired
    PassengerRepository passengerRepository;

    public DataLoader(){
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {

//        Ethiopian Airlines
        Flight ethiopianAirlines = new Flight("Mexico", 200, LocalDate.of(2023, Month.JANUARY, 2), LocalTime.of(7, 30));
        flightRepository.save(ethiopianAirlines);

        Passenger michaelKaiser = new Passenger("Michael Kaiser", "michaelkaiser@gmail.com");
        michaelKaiser.addFlight(ethiopianAirlines);
        passengerRepository.save(michaelKaiser);

        Passenger yoichiIsagi = new Passenger("Yoichi Isagi", "yoichiisagi@gmail.com");
        yoichiIsagi.addFlight(ethiopianAirlines);
        passengerRepository.save(yoichiIsagi);
//      Qatar Airways
        Flight qatarAirways = new Flight("India", 300, LocalDate.of(2023, Month.APRIL, 3), LocalTime.of(8, 30));
        flightRepository.save(qatarAirways);

        Passenger georgeClooney = new Passenger("George Clooney", "georgeC@gmail.com");
        georgeClooney.addFlight(qatarAirways);
        passengerRepository.save(georgeClooney);

        Passenger benAffleck = new Passenger("Benjamin Affleck", "BAffleck@gmail.com");
        benAffleck.addFlight(qatarAirways);
        passengerRepository.save(benAffleck);
//        British Airways
        Flight britishAirways = new Flight("Colombia", 250, LocalDate.of(2023, Month.AUGUST, 23), LocalTime.of(12, 45));
        flightRepository.save(britishAirways);

        Passenger goku = new Passenger("Son Goku", "kamehameha@gmail.com");
        goku.addFlight(britishAirways);
        passengerRepository.save(goku);

        Passenger vegeta = new Passenger("Prince Vegeta", "princeofallSaiyans@gmail.com");
        vegeta.addFlight(britishAirways);
        passengerRepository.save(vegeta);


    }



}
