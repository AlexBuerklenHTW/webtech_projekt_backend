package com.example.webtechProjekt.controller;

import com.example.webtechProjekt.model.Dummy;
import com.example.webtechProjekt.service.DummyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class DummyController {
    private DummyService dummyService;

    @Autowired
    public DummyController(DummyService theDummyService){
        dummyService = theDummyService;
    }

    @GetMapping("/dummys")
    public List<Dummy> findAll(){
        return dummyService.findAll();
    }

    @PostMapping("/dummys")
    public Dummy addDummy(@RequestBody Dummy theDummy){

        theDummy.setId(0);

        return dummyService.save(theDummy);
    }

    @GetMapping("/dummys/{dummyId}")
    public Dummy getDummy(@PathVariable int dummyId){

        Dummy theDummy = dummyService.findById(dummyId);

        if(theDummy == null){
            throw new RuntimeException("Dummy id: " + dummyId + " not found");
        }

        return theDummy;
    }

    @PutMapping("/dummys")
    public Dummy updateDummy(@RequestBody Dummy theDummy){

        return dummyService.save(theDummy);
    }

    @DeleteMapping("/dummys/{dummyId}")
    public String deleteDummy(@PathVariable int dummyId){

        Dummy tempDummy = dummyService.deleteById(dummyId);

        if(tempDummy == null){
            throw new RuntimeException("Dummy id: " + dummyId + " not found");
        }
        return "Deleted dummyId: " + dummyId;
    }
}
