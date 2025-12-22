package com.example.seviceimpl;

import com.example.entity.*;
import com.example.exception.InvalidAgeException;
import com.example.exception.ResourceNotFoundException;
import com.example.service.*;
import com.example.repository.*;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private PgPlaceRepository pgRepo;

    @Autowired
    private OwnerRepository ownerRepo;
    
    @Autowired
    private LocalityRepository locRepo;
    
    @Autowired
    private CityRepository cityRepo;
    
    @Override
    public Owner registerOwner(Owner owner) {

        if (owner.getAge() < 18) {
            throw new InvalidAgeException("Owner must be 18 years or older");
        }

        return ownerRepo.save(owner);
    }

    @Override
    public PgPlace addPlace(PgPlace place) {
    	Long ownerId = place.getOwner().getOwnerId();
    	Owner owner = ownerRepo.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
    	
    	Locality locality = locRepo.findById(place.getLocality().getLocalityId())
                .orElseThrow(() -> new RuntimeException("Locality not found"));
    	
    	City city = cityRepo.findById(place.getCity().getCityId())
                .orElseThrow(() -> new RuntimeException("City not found"));
        
        if (owner.getAge() < 18) {
            throw new InvalidAgeException("Owner must be 18+ to add PG place");
        }
        
        
        place.setOwner(owner);
        place.setLocality(locality);
        place.setCity(city);
        place.setAvailabilityStatus("AVAILABLE");
        place.setVisitorCount(0);
        return pgRepo.save(place);
    }

    @Override
    public List<PgPlace> getOwnerPlaces(Long ownerId) {
        Owner owner = ownerRepo.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found"));
        return owner.getPgPlaces();
    }

    @Override
    public PgPlace changeStatus(Long pgId, String status) {
        PgPlace pg = pgRepo.findById(pgId)
                .orElseThrow(() -> new ResourceNotFoundException("PG not found"));

        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status must not be null");
        }

        if (!status.equalsIgnoreCase("AVAILABLE") &&
            !status.equalsIgnoreCase("OCCUPIED")) {
            throw new IllegalArgumentException("Invalid status value. Give either available or occupied");
        }
        
        pg.setAvailabilityStatus(status.toUpperCase());

        return pgRepo.save(pg);
    }

    @Override
    public PgPlace editPlace(PgPlace place) {
    	PgPlace existing = pgRepo.findById(place.getPgId())
                .orElseThrow(() -> new RuntimeException("PG Place not found"));
    	
    	existing.setRegistrationNumber(place.getRegistrationNumber());
        existing.setBuiltUpArea(place.getBuiltUpArea());
        existing.setRent(place.getRent());
        existing.setAvailabilityStatus(place.getAvailabilityStatus());
        
        if (place.getCity() != null) {
            City city = cityRepo.findById(place.getCity().getCityId())
                    .orElseThrow(() -> new RuntimeException("City not found"));
            existing.setCity(city);
        }

        if (place.getLocality() != null) {
            Locality locality = locRepo.findById(place.getLocality().getLocalityId())
                    .orElseThrow(() -> new RuntimeException("Locality not found"));
            existing.setLocality(locality);
        }
        
        if (place.getOwner() != null) {
            Owner owner = ownerRepo.findById(place.getOwner().getOwnerId())
                    .orElseThrow(() -> new RuntimeException("Owner not found"));
            existing.setOwner(owner);
        }

        return pgRepo.save(place);
    }

    @Override
    public String deletePlace(Long id) {
    	pgRepo.findById(id)
        .orElseThrow(() -> new RuntimeException("PG Place not found"));
        pgRepo.deleteById(id);
        return "Pg deleted with id: "+id;
    }
    
    @Override
    public int getVisitorCount(Long pgId) {
        PgPlace pg = pgRepo.findById(pgId)
                .orElseThrow(() -> new ResourceNotFoundException("PG not found"));
        return pg.getVisitorCount();
    }

	@Override
	public List<Owner> getAllOwners() {
		return ownerRepo.findAll();
	}

	@Override
	public Owner getOwnerById(Long id) {
		Owner owner = ownerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
		return owner;
	}

	@Override
	public String deleteOwner(Long id) {
		Owner owner = ownerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
		ownerRepo.deleteById(id);
		return "Owner deleted with id:"+id;
		
	}
    


}

