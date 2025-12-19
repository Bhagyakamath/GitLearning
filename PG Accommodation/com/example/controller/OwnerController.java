package com.example.controller;

import com.example.entity.Owner;
import com.example.entity.PgPlace;
import com.example.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;
    
    @PostMapping("/register")
    public Owner registerOwner(@RequestBody Owner owner) {
        return ownerService.registerOwner(owner);
    }

    
    @PostMapping("/addplace")
    public PgPlace addPlace(@RequestBody PgPlace place) {
        return ownerService.addPlace(place);
    }

   
    @PutMapping("/editplace")
    public PgPlace editPlace(@RequestBody PgPlace place) {
        return ownerService.editPlace(place);
    }

    
    @PutMapping("/place/status/{pgId}")
    public PgPlace changeStatus(@PathVariable Long pgId) {
        return ownerService.changeStatus(pgId);
    }

    
    @GetMapping("/places/{ownerId}")
    public List<PgPlace> getOwnerPlaces(@PathVariable Long ownerId) {
        return ownerService.getOwnerPlaces(ownerId);
    }

   
    @GetMapping("/place/visitors/{pgId}")
    public int getVisitorCount(@PathVariable Long pgId) {
        return ownerService.getVisitorCount(pgId);
    }
}
