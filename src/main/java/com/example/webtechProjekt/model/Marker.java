package com.example.webtechProjekt.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
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
    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;
}
