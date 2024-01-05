package com.example.webtechProjekt.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@Table(name="marker")
public class Marker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="lat")
    private double lat;
    @Column(name="lng")
    private double lng;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    public Marker(double lat, double lng, Trip trip) {
        this.lat = lat;
        this.lng = lng;
        this.trip = trip;
    }
}
