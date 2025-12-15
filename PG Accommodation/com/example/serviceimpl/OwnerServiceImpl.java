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

    @Override
    public PgPlace addPlace(PgPlace place) {

        if (place.getOwner().getAge() < 18) {
            throw new InvalidAgeException("Owner must be 18+");
        }
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
    public PgPlace changeStatus(Long pgId) {
        PgPlace pg = pgRepo.findById(pgId)
                .orElseThrow(() -> new ResourceNotFoundException("PG not found"));

        pg.setAvailabilityStatus(
                pg.getAvailabilityStatus().equals("AVAILABLE") ? "OCCUPIED" : "AVAILABLE");

        return pgRepo.save(pg);
    }

    @Override
    public PgPlace editPlace(PgPlace place) {
        return pgRepo.save(place);
    }

    @Override
    public void deletePlace(Long id) {
        pgRepo.deleteById(id);
    }
}

