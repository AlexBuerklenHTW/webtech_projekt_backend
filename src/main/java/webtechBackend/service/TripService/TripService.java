package webtechBackend.service.TripService;

import webtechBackend.model.Trip;

import java.util.List;

public interface TripService {
    List<Trip> findAll();

    Trip findById(int theId);

    Trip save(Trip theTrip);

    void deleteById(int theId);
}
