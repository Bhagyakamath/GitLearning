package com.example.seviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.City;
import com.example.entity.Locality;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.CityRepository;
import com.example.repository.LocalityRepository;
import com.example.service.LocalityService;

@Service
public class LocalityServiceImpl implements LocalityService {

    @Autowired
    private LocalityRepository localityRepository;

    @Autowired
    private CityRepository cityRepository;

    @Override
    public Locality addLocality(Locality locality) {

        // Ensure city exists
        Long cityId = locality.getCity().getCityId();
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException("City not found"));

        locality.setCity(city);
        return localityRepository.save(locality);
    }

    @Override
    public List<Locality> getLocalitiesByCity(Long cityId) {
    	cityRepository.findById(cityId)
        .orElseThrow(() -> new ResourceNotFoundException("City not found"));
        return localityRepository.findAll()
                .stream()
                .filter(l -> l.getCity().getCityId().equals(cityId))
                .toList();
    }

    @Override
    public String deleteLocality(Long id) {
    	localityRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Locality not found"));
        localityRepository.deleteById(id);
        return "Locality deleted with id:"+id;
    }

	@Override
	public List<Locality> getAllLocalities() {
		return localityRepository.findAll();
	}
}

