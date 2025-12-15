package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Locality;
import com.example.service.LocalityService;

@RestController
@RequestMapping("/admin/locality")
public class AdminLocalityController {

    @Autowired
    private LocalityService localityService;

    @PostMapping("/add")
    public Locality addLocality(@RequestBody Locality locality) {
        return localityService.addLocality(locality);
    }

    @GetMapping("/city/{cityId}")
    public List<Locality> getLocalitiesByCity(@PathVariable Long cityId) {
        return localityService.getLocalitiesByCity(cityId);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteLocality(@PathVariable Long id) {
        localityService.deleteLocality(id);
    }
}

