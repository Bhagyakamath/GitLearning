package com.example.controller;

import com.example.service.*;
import com.example.entity.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/owner/places")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @PostMapping("/add")
    public PgPlace addPlace(@RequestBody PgPlace place) {
        return ownerService.addPlace(place);
    }

    @GetMapping("/{ownerId}")
    public List<PgPlace> getOwnerPlaces(@PathVariable Long ownerId) {
        return ownerService.getOwnerPlaces(ownerId);
    }

    @PutMapping("/status/{id}")
    public PgPlace changeStatus(@PathVariable Long id) {
        return ownerService.changeStatus(id);
    }

    @PutMapping("/edit")
    public PgPlace editPlace(@RequestBody PgPlace place) {
        return ownerService.editPlace(place);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePlace(@PathVariable Long id) {
        ownerService.deletePlace(id);
    }
}
