package com.example.webtechProjekt.model;

import jakarta.persistence.*;

@Entity
@Table(name="trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;

    @Column(name="totalDistance")
    private double totalDistance;

    public Trip(){}

    public Trip(String name, double totalDistance) {
        this.totalDistance = totalDistance;
        this.name = name;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", totalDistance=" + totalDistance +
                '}';
    }
}
