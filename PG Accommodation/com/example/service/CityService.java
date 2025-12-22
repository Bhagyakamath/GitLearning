package com.example.service;

import java.util.*;

import com.example.entity.City;


public interface CityService {

    City addCity(City city);

    List<City> getAllCities();

    City getCityById(Long id);

    String deleteCity(Long id);
}

