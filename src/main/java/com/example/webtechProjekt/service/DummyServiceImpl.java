package com.example.webtechProjekt.service;

import com.example.webtechProjekt.model.Dummy;
import com.example.webtechProjekt.repository.DummyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DummyServiceImpl implements DummyService{
    private DummyRepository dummyRepository;

    @Autowired
    public DummyServiceImpl(DummyRepository theDummyRepository){
        dummyRepository = theDummyRepository;
    }
    @Override
    public List<Dummy> findAll(){return dummyRepository.findAll();}
    @Override
    public Dummy findById(int theId){

        Optional<Dummy> result = dummyRepository.findById(theId);

        Dummy theDummy;

        if(result.isPresent()){
            theDummy = result.get();
        }
        else {
            throw new RuntimeException("Dummy id: " + theId + " not found");
        }
        return theDummy;
    }
    @Override
    public Dummy save(Dummy theDummy){return dummyRepository.save(theDummy);}
    @Override
    public Dummy deleteById(int theId){dummyRepository.deleteById(theId);
        return null;
    }


}
