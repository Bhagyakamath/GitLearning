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
import com.example.service.CityService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/admin/city")
@Tag(name = "Admin City Management", description = "APIs for managing cities by admin")
public class AdminCityController {

    @Autowired
    private CityService cityService;

    @PostMapping("/add")
    @Operation(summary = "Add a new city", description = "Allows the admin to add a new city to the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "City added successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid city data")
    })
    public City addCity(@RequestBody City city) {
        return cityService.addCity(city);
    }
    
    @Operation(summary = "Get all cities", description = "Returns a list of all cities in the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of cities retrieved successfully")
    })
    @GetMapping("/all")
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }
    
    @Operation(summary = "Get city by ID", description = "Returns the details of a city for the given ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "City found successfully"),
        @ApiResponse(responseCode = "404", description = "City not found with the given ID")
    })
    @GetMapping("/{id}")
    public City getCityById(@PathVariable Long id) {
        return cityService.getCityById(id);
    }
    
    @Operation(summary = "Delete a city", description = "Deletes a city from the system for the given ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "City deleted successfully"),
        @ApiResponse(responseCode = "404", description = "City not found with the given ID")
    })
    @DeleteMapping("/delete/{id}")
    public String deleteCity(@PathVariable Long id) {
        return cityService.deleteCity(id);
    }
}

