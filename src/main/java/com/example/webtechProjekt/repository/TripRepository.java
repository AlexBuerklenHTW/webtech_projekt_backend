package com.example.webtechProjekt.repository;

import com.example.webtechProjekt.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TripRepository extends JpaRepository<Trip,Integer> {}
