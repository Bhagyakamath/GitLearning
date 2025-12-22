package com.example.controller;

import com.example.service.*;
import com.example.entity.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/pg")
@Tag(
	    name = "PG Search & View APIs",
	    description = "APIs for users to search PG places and view details"
	)
public class PgController {

    @Autowired
    private PgService pgService;
    
    @Operation(
            summary = "Get PGs by city",
            description = "Fetches all AVAILABLE PG places in a city using city ID"
        )
        @ApiResponse(
            responseCode = "200",
            description = "PG places fetched successfully",
            content = @Content(schema = @Schema(implementation = PgPlace.class))
        )
        @ApiResponse(
            responseCode = "404",
            description = "City not found"
        )
    @GetMapping("/{cityId}")
    public List<PgPlace> getPgByCity(@PathVariable Long cityId) {
        return pgService.getPgByCity(cityId);
    }
    
    @Operation(
            summary = "Search PGs by locality",
            description = "Fetches PG places in a given locality (case-insensitive)"
        )
        @ApiResponse(
            responseCode = "200",
            description = "PG places fetched successfully"
        )
        @ApiResponse(
            responseCode = "404",
            description = "Locality not found"
        )
    @GetMapping("/search/{locality}")
    public List<PgPlace> getPgByLocality(@PathVariable String locality) {
        return pgService.getPgByLocality(locality);
    }
    
    @Operation(
            summary = "Get PG details",
            description = "Fetches PG place details and increments visitor count"
        )
        @ApiResponse(
            responseCode = "200",
            description = "PG details fetched successfully",
            content = @Content(schema = @Schema(implementation = PgPlace.class))
        )
        @ApiResponse(
            responseCode = "404",
            description = "PG place not found"
        )
    @GetMapping("/details/{id}")
    public PgPlace getPgDetails(@PathVariable Long id) {
        return pgService.getPgDetails(id);
    }
    
    @Operation(
            summary = "Get PG owner details",
            description = "Returns owner contact details ONLY if PG is AVAILABLE"
        )
        @ApiResponse(
            responseCode = "200",
            description = "Owner details fetched successfully",
            content = @Content(schema = @Schema(implementation = Owner.class))
        )
        @ApiResponse(
            responseCode = "403",
            description = "PG is occupied. Owner details are restricted"
        )
        @ApiResponse(
            responseCode = "404",
            description = "PG place not found"
        )
    @GetMapping("/owner/{id}")
    public Owner getOwnerDetails(@PathVariable Long id) {
        return pgService.getOwnerDetails(id);
    }
}
