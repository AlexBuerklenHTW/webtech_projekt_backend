package com.example.webtechProjekt.controller;

import com.example.webtechProjekt.model.Marker;
import com.example.webtechProjekt.service.MarkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/apiMarker")
public class MarkerController {

    private final MarkerService markerService;

    @GetMapping("/markers")
    public List<Marker> findAll(){
        return markerService.findAll();
    }

    @PostMapping("/markers")
    public Marker addMarker(@RequestBody Marker theMarker){

        //theMarker.setId(0);

        return markerService.save(theMarker);
    }

    @GetMapping("/marker/{markerId}")
    public Marker getMarkers(@PathVariable int markerId){

        Marker theMarker = markerService.findById(markerId);

        if(theMarker == null){
            throw new RuntimeException("Marker id: " + markerId + " not found");
        }

        return theMarker;
    }

    @PutMapping("/markers")
    public Marker updateMarker(@RequestBody Marker theMarker){

        return markerService.save(theMarker);
    }

    @DeleteMapping("/markers/{markersId}")
    public String deleteMarker(@PathVariable int markersId){

        Marker tempMarker = markerService.findById(markersId);

        if(tempMarker == null){
            throw new RuntimeException("Marker id: " + markersId + " not found");
        }

        markerService.deleteById(markersId);

        return "Deleted markerId: " + markersId;
    }
}
