package com.example.webtechProjekt.service;

import com.example.webtechProjekt.model.Dummy;

import java.util.List;

public interface DummyService {
    List<Dummy> findAll();

    Dummy findById(int theId);

    Dummy save(Dummy theDummy);

    Dummy deleteById(int theId);
}
