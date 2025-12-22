package com.example.service;

import java.util.List;

import com.example.entity.PgPlace;
import com.example.entity.Tenant;

public interface TenantService {

    Tenant registerTenant(Tenant tenant);
    
    List<PgPlace> getPlacesByCity(Long id) throws Exception;

    List<Tenant> getAllTenants();

    Tenant getTenantById(Long id);

    void deleteTenant(Long id);
}

