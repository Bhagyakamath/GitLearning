package com.example.seviceimpl;

import com.example.entity.*;
import com.example.exception.PgNotAvailableException;
import com.example.exception.ResourceNotFoundException;
import com.example.service.*;
import com.example.repository.*;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PgServiceImpl implements PgService {

    @Autowired
    private PgPlaceRepository pgRepo;
    
    @Autowired
    private CityRepository cityRepository;
    
    @Autowired
    private LocalityRepository locRepo;

    @Override
    public List<PgPlace> getPgByCity(Long cityId) {
    	City city = cityRepository.findById(cityId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("City not found with id: " + cityId));
    	List<PgPlace> pgList = pgRepo.findByCity(city);

    	pgList.forEach(pg -> {
    	    if (!"AVAILABLE".equalsIgnoreCase(pg.getAvailabilityStatus())) {
    	        pg.setOwner(null); 
    	    }
    	});
        return pgRepo.findByCityCityIdAndAvailabilityStatus(cityId, "AVAILABLE");
    }

    @Override
    public List<PgPlace> getPgByLocality(String locality) {
    	Locality loc = locRepo.findByLocalityName(locality)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Locality not found with id: " + locality));
        return pgRepo.findByLocality(loc);
    }

    @Override
    public PgPlace getPgDetails(Long id) {
        PgPlace pg = pgRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PG not found"));
        pg.setVisitorCount(pg.getVisitorCount() + 1);
        return pgRepo.save(pg);
    }

    @Override
    public Owner getOwnerDetails(Long pgId) {
        PgPlace pg = getPgDetails(pgId);
        if (!pg.getAvailabilityStatus().equalsIgnoreCase("AVAILABLE")) {
            throw new PgNotAvailableException("PG is occupied");
        }
        return pg.getOwner();
    }
    
    
}

