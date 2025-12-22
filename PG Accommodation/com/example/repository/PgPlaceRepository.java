package com.example.repository;

import com.example.entity.*;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PgPlaceRepository extends JpaRepository<PgPlace, Long> {

    List<PgPlace> findByCityCityIdAndAvailabilityStatus(Long cityId, String status);

    List<PgPlace> findByLocalityLocalityNameIgnoreCase(String locality);
	List<PgPlace> findByLocality(Locality locality);
	List<PgPlace> findByCity(City city);
    
}

