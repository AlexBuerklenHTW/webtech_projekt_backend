package com.example.webtechProjekt.controller;

import com.example.webtechProjekt.model.Trip;
import com.example.webtechProjekt.service.TripService.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/apiTrip")
public class TripController {
    private final TripService tripService;

    @GetMapping("/trips")
    public List<Trip> findAll(){
        return tripService.findAll();
    }

    @PostMapping("/trips")
    public Trip addTrip(@RequestBody Trip theTrip){
        return tripService.save(theTrip);
    }

    @PostMapping("/tripsName/{tripId}")
    public Trip changeTripName(@RequestBody String newTripName, @PathVariable int tripId) throws UnsupportedEncodingException {
        Trip trip = tripService.findById(tripId);
        String decodedName = URLDecoder.decode(newTripName, StandardCharsets.UTF_8);
        trip.setName(decodedName);
        return tripService.save(trip);
    }

    @PostMapping("/trips/{tripId}")
    public Trip addTotalDistance(@RequestBody Double totalDistance, @PathVariable int tripId){
        Trip trip = tripService.findById(tripId);
        trip.setTotalDistance(totalDistance);
        return tripService.save(trip);
    }

    @GetMapping("/trips/{tripId}")
    public Trip getTrips(@PathVariable int tripId){

        Trip theTrip = tripService.findById(tripId);

        if(theTrip == null){
            throw new RuntimeException("Trip id: " + tripId + " not found");
        }
        return theTrip;
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
