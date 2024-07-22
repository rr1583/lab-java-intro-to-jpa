package com.renereyes.sql.repositories;

import com.renereyes.sql.entities.FlightBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightBookingRepository extends JpaRepository<FlightBooking, Integer> {
}
