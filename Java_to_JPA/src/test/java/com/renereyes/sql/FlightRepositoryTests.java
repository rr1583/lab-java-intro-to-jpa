package com.renereyes.sql.repositories;

import com.renereyes.sql.entities.Flight;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class FlightRepositoryTests {
    @Autowired
    private FlightRepository flightRepository;

    @Test
    public void testCreateFlight() {
        Flight flight = new Flight("AA123", "Boeing 737", 150, 1000);
        flightRepository.save(flight);
        assertThat(flight.getFlightId()).isNotNull();
    }

    @Test
    public void testFindFlightByNumber() {
        Flight flight = new Flight("UA456", "Airbus A320", 180, 1200);
        flightRepository.save(flight);

        Flight foundFlight = flightRepository.findByFlightNumber("UA456");
        assertThat(foundFlight).isNotNull();
        assertThat(foundFlight.getFlightNumber()).isEqualTo("UA456");
    }

    @Test
    public void testFindAircraftContainingBoeing() {
        Flight flight = new Flight("DL789", "Boeing 747", 200, 1500);
        flightRepository.save(flight);

        List<Flight> flights = flightRepository.findByAircraftContaining("Boeing");
        assertThat(flights).isNotEmpty();
        assertThat(flights.get(0).getAircraft()).contains("Boeing");
    }

    @Test
    public void testFindFlightsGreaterThan500Miles() {
        Flight flight = new Flight("SW101", "Boeing 737", 150, 600);
        flightRepository.save(flight);

        List<Flight> flights = flightRepository.findByFlightMileageGreaterThan(500);
        assertThat(flights).isNotEmpty();
        assertThat(flights.get(0).getFlightMileage()).isGreaterThan(500);
    }
}
