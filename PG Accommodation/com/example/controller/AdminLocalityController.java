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

import com.example.entity.City;
import com.example.entity.Locality;
import com.example.service.LocalityService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/admin/locality")
@Tag(
	    name = "Admin - Locality Management",
	    description = "APIs for Admin to manage localities"
	)
public class AdminLocalityController {

    @Autowired
    private LocalityService localityService;
    
    @Operation(
            summary = "Add a new locality",
            description = "Creates a new locality and associates it with a city"
        )
        @ApiResponse(
            responseCode = "200",
            description = "Locality added successfully",
            content = @Content(schema = @Schema(implementation = Locality.class))
        )
    @PostMapping("/add")
    public Locality addLocality(@RequestBody Locality locality) {
        return localityService.addLocality(locality);
    }
    
    @Operation(
            summary = "Get all localities",
            description = "Fetches all localities available in the system"
        )
        @ApiResponse(
            responseCode = "200",
            description = "List of localities",
            content = @Content(schema = @Schema(implementation = Locality.class))
        )
    @GetMapping("/all")
    public List<Locality> getAllLocalities() {
        return localityService.getAllLocalities();
    }
    
    @Operation(
            summary = "Get localities by city ID",
            description = "Fetches all localities belonging to a specific city"
        )
        @ApiResponse(
            responseCode = "200",
            description = "Localities fetched successfully"
        )
        @ApiResponse(
            responseCode = "404",
            description = "City not found"
        )
    @GetMapping("/city/{cityId}")
    public List<Locality> getLocalitiesByCity(@PathVariable Long cityId) {
        return localityService.getLocalitiesByCity(cityId);
    }
    
    @Operation(
            summary = "Delete locality by ID",
            description = "Deletes a locality using its ID"
        )
        @ApiResponse(
            responseCode = "200",
            description = "Locality deleted successfully"
        )
        @ApiResponse(
            responseCode = "404",
            description = "Locality not found"
        )
    @DeleteMapping("/delete/{id}")
    public String deleteLocality(@PathVariable Long id) {
        return localityService.deleteLocality(id);
    }
}

