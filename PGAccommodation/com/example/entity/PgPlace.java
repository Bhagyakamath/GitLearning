package com.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class PgPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pgId;

    private String registrationNumber;
    private double builtUpArea;
    private double rent;
    private String availabilityStatus; // AVAILABLE / OCCUPIED
    private int visitorCount;

    @ManyToOne
//    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
//    @JoinColumn(name = "locality_id")
    private Locality locality;

    @ManyToOne
//    @JoinColumn(name = "owner_id")
    @JsonBackReference
    private Owner owner;

	public Long getPgId() {
		return pgId;
	}

	public void setPgId(Long pgId) {
		this.pgId = pgId;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public double getBuiltUpArea() {
		return builtUpArea;
	}

	public void setBuiltUpArea(double builtUpArea) {
		this.builtUpArea = builtUpArea;
	}

	public double getRent() {
		return rent;
	}

	public void setRent(double rent) {
		this.rent = rent;
	}

	public String getAvailabilityStatus() {
		return availabilityStatus;
	}

	public void setAvailabilityStatus(String availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}

	public int getVisitorCount() {
		return visitorCount;
	}

	public void setVisitorCount(int visitorCount) {
		this.visitorCount = visitorCount;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Locality getLocality() {
		return locality;
	}

	public void setLocality(Locality locality) {
		this.locality = locality;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
    
}

