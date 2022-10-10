package com.team4.dispatch.repository;

import com.team4.dispatch.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {
    Station findByStationID(int stationID);
}
