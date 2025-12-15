package com.example.service;

import java.util.List;

import com.example.entity.Locality;

public interface LocalityService {

    Locality addLocality(Locality locality);

    List<Locality> getLocalitiesByCity(Long cityId);

    void deleteLocality(Long id);
}

