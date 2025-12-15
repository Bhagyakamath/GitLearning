package com.example.service;

import com.example.entity.*;
import java.util.List;

public interface OwnerService {

    PgPlace addPlace(PgPlace place);

    List<PgPlace> getOwnerPlaces(Long ownerId);

    PgPlace changeStatus(Long pgId);

    PgPlace editPlace(PgPlace place);

    void deletePlace(Long id);
}

