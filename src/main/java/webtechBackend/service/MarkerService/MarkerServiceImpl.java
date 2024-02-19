package webtechBackend.service.MarkerService;

import webtechBackend.model.Marker;
import webtechBackend.repository.MarkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarkerServiceImpl implements MarkerService {
    private final MarkerRepository markerRepository;

    @Autowired
    public MarkerServiceImpl(MarkerRepository theMarkerRepository){
        markerRepository = theMarkerRepository;
    }
    @Override
    public List<Marker> findAll(){return markerRepository.findAll();}
    @Override
    public Marker findById(int theId){

        Optional<Marker> result = markerRepository.findById(theId);

        Marker theMarker;

        if(result.isPresent()){
            theMarker = result.get();
        }
        else {
            throw new RuntimeException("Marker id: " + theId + " not found");
        }
        return theMarker;
    }
    @Override
    public Marker save(Marker theMarker){return markerRepository.save(theMarker);}

    @Override
    public void deleteById(int theId){markerRepository.deleteById(theId);}
}
