package com.example.webtechProjekt.controller;

import com.example.webtechProjekt.model.Trip;
import com.example.webtechProjekt.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class TripController {
    private final TripService tripService;

    @Autowired
    public TripController(TripService theTripService){
        tripService = theTripService;
    }

    @GetMapping("/trips")
    public List<Trip> findAll(){
        return tripService.findAll();
    }

    @PostMapping("/trips")
    public Trip addTrip(@RequestBody Trip theTrip){

        theTrip.setId(0);

        return tripService.save(theTrip);
    }

    @GetMapping("/trips/{tripId}")
    public Trip getTrips(@PathVariable int tripId){

        Trip theTrip = tripService.findById(tripId);

        if(theTrip == null){
            throw new RuntimeException("Trip id: " + tripId + " not found");
        }

        return theTrip;
    }

    @PutMapping("/trips")
    public Trip updateTrip(@RequestBody Trip theTrip){

        return tripService.save(theTrip);
    }

    @DeleteMapping("/trips/{tripId}")
    public String deleteTrip(@PathVariable int tripId){

        Trip tempTrip = tripService.findById(tripId);

        if(tempTrip == null){
            throw new RuntimeException("Trip id: " + tripId + " not found");
        }

        tripService.deleteById(tripId);

        return "Deleted tripId: " + tripId;
    }
}
