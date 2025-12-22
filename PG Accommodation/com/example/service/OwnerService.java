package com.example.service;

import com.example.entity.*;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

public interface OwnerService {

    PgPlace addPlace(PgPlace place);

    List<PgPlace> getOwnerPlaces(Long ownerId);

    PgPlace changeStatus(Long pgId, String status);

    PgPlace editPlace(PgPlace place);

    String deletePlace(Long id);
    int getVisitorCount(Long pgId);
    Owner registerOwner(Owner owner);
    
    public List<Owner> getAllOwners();
    public Owner getOwnerById( Long id);
    public String deleteOwner( Long id);
}

