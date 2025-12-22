package com.example.seviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.City;
import com.example.entity.Locality;
import com.example.entity.PgPlace;
import com.example.entity.Tenant;
import com.example.exception.InvalidAgeException;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.CityRepository;
import com.example.repository.LocalityRepository;
import com.example.repository.PgPlaceRepository;
import com.example.repository.TenantRepository;
import com.example.service.TenantService;

@Service
public class TenantServiceImpl implements TenantService
{

    @Autowired
    
    private TenantRepository tenantRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private LocalityRepository localityRepository;
    
    @Autowired
    private PgPlaceRepository pgRepo;

    @Override
    public Tenant registerTenant(Tenant tenant) {

        // Age validation
        if (tenant.getAge() < 18) {
            throw new InvalidAgeException("Tenant must be 18 years or older");
        }

        // Validate city
        City city = cityRepository.findById(tenant.getCity().getCityId())
                .orElseThrow(() -> new ResourceNotFoundException("City not found"));

        // Validate locality
        Locality locality = localityRepository.findById(tenant.getLocality().getLocalityId())
                .orElseThrow(() -> new ResourceNotFoundException("Locality not found"));

        tenant.setCity(city);
        tenant.setLocality(locality);

        return tenantRepository.save(tenant);
    }

    @Override
    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }

    @Override
    public Tenant getTenantById(Long id) {
        return tenantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tenant not found"));
    }

    @Override
    public void deleteTenant(Long id) {
        tenantRepository.deleteById(id);
    }

	@Override
	public List<PgPlace> getPlacesByCity(Long id) throws Exception{
		City city = cityRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("City not found with id: " + id));
		return pgRepo.findByCityCityIdAndAvailabilityStatus(city.getCityId(), "AVAILABLE");
	}
}
