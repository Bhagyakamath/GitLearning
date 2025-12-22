package com.example.entity;

import java.util.List;

import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.UniqueConstraint;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityId;
    
    @Column(unique=true)
    private String cityName;

    // One city can have many localities
    @OneToMany(mappedBy = "city")
    @JsonManagedReference
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private List<Locality> localities;

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public List<Locality> getLocalities() {
		return localities;
	}

	public void setLocalities(List<Locality> localities) {
		this.localities = localities;
	}
    
    
}
