package com.example.webtechProjekt.service.TripService;

import com.example.webtechProjekt.model.Trip;

import java.util.List;

public interface TripService {
    List<Trip> findAll();

    Trip findById(int theId);

    Trip save(Trip theTrip);

    void deleteById(int theId);
}
