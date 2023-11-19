package com.example.webtechProjekt.service;

import com.example.webtechProjekt.model.Marker;

import java.util.List;

public interface MarkerService {

    List<Marker> findAll();

    Marker findById(int theId);

    Marker save(Marker theMarker);

    void deleteById(int theId);
}
