package com.example.controller;

import com.example.entity.Owner;
import com.example.entity.PgPlace;
import com.example.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;


@RestController
@RequestMapping("/owner")
@Tag(
	    name = "Owner APIs",
	    description = "APIs for PG owners to manage profile and PG places"
	)
public class OwnerController {

    @Autowired
    private OwnerService ownerService;
    
    @Operation(
            summary = "Register owner",
            description = "Registers a new PG owner"
        )
        @ApiResponse(
            responseCode = "200",
            description = "Owner registered successfully",
            content = @Content(schema = @Schema(implementation = Owner.class))
        )
    @PostMapping("/register")
    public Owner registerOwner(@RequestBody Owner owner) {
        return ownerService.registerOwner(owner);
    }

    @Operation(
            summary = "Add PG place",
            description = "Owner adds a new PG place"
        )
        @ApiResponse(
            responseCode = "200",
            description = "PG place added successfully",
            content = @Content(schema = @Schema(implementation = PgPlace.class))
        )
    @PostMapping("/addplace")
    public PgPlace addPlace(@RequestBody PgPlace place) {
        return ownerService.addPlace(place);
    }

    @Operation(
            summary = "Edit PG place",
            description = "Owner edits PG place details"
        )
        @ApiResponse(
            responseCode = "200",
            description = "PG place updated successfully",
            content = @Content(schema = @Schema(implementation = PgPlace.class))
        )
    @PutMapping("/editplace")
    public PgPlace editPlace(@RequestBody PgPlace place) {
        return ownerService.editPlace(place);
    }

    @Operation(
            summary = "Change PG availability status",
            description = "Updates PG place status (AVAILABLE / OCCUPIED)"
        )
        @ApiResponse(
            responseCode = "200",
            description = "Status updated successfully",
            content = @Content(schema = @Schema(implementation = PgPlace.class))
        )
    @PutMapping("/place/status/{pgId}")
    public PgPlace changeStatus(@PathVariable Long pgId,  @RequestBody Map<String, String> body) {
        return ownerService.changeStatus(pgId, body.get("status"));
    }

    @Operation(
            summary = "Get owner's PG places",
            description = "Fetches all PG places owned by an owner"
        )
        @ApiResponse(
            responseCode = "200",
            description = "PG places fetched successfully"
        )
    @GetMapping("/places/{ownerId}")
    public List<PgPlace> getOwnerPlaces(@PathVariable Long ownerId) {
        return ownerService.getOwnerPlaces(ownerId);
    }

    @Operation(
            summary = "Get visitor count",
            description = "Returns number of visitors for a PG place"
        )
        @ApiResponse(
            responseCode = "200",
            description = "Visitor count fetched successfully"
        )
    @GetMapping("/place/visitors/{pgId}")
    public int getVisitorCount(@PathVariable Long pgId) {
        return ownerService.getVisitorCount(pgId);
    }
    
    @Operation(
            summary = "Delete PG place",
            description = "Deletes a PG place by ID"
        )
        @ApiResponse(
            responseCode = "200",
            description = "PG place deleted successfully"
        )
        @ApiResponse(
            responseCode = "404",
            description = "PG place not found"
        )
    @DeleteMapping("/delete/{id}")
    public String deletePlace(@PathVariable Long id) {
    	return ownerService.deletePlace(id);
    }
}
