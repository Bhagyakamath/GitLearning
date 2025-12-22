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

import com.example.entity.Tenant;
import com.example.service.TenantService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;


@RestController
@RequestMapping("/admin/tenant")
@Tag(
	    name = "Admin - Tenant Management",
	    description = "APIs for Admin to manage tenants"
	)
public class AdminTenantController {

    @Autowired
    private TenantService tenantService;
    
    @Operation(
            summary = "Get all tenants",
            description = "Fetches all tenants registered in the system"
        )
        @ApiResponse(
            responseCode = "200",
            description = "Tenants fetched successfully",
            content = @Content(schema = @Schema(implementation = Tenant.class))
        )
    @GetMapping("/all")
    public List<Tenant> getAllTenants() {
        return tenantService.getAllTenants();
    }
    
    @Operation(
            summary = "Get tenant by ID",
            description = "Fetches tenant details using tenant ID"
        )
        @ApiResponse(
            responseCode = "200",
            description = "Tenant fetched successfully",
            content = @Content(schema = @Schema(implementation = Tenant.class))
        )
        @ApiResponse(
            responseCode = "404",
            description = "Tenant not found"
        )
    @GetMapping("/{id}")
    public Tenant getTenantById(@PathVariable Long id) {
        return tenantService.getTenantById(id);
    }
    
    @Operation(
            summary = "Delete tenant",
            description = "Deletes a tenant using tenant ID"
        )
        @ApiResponse(
            responseCode = "200",
            description = "Tenant deleted successfully"
        )
        @ApiResponse(
            responseCode = "404",
            description = "Tenant not found"
        )
    @DeleteMapping("/delete/{id}")
    public void deleteTenant(@PathVariable Long id) {
        tenantService.deleteTenant(id);
    }
}

