package webtechBackend.TripIntegrationTest;

import webtechBackend.model.Trip;
import webtechBackend.repository.TripRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class TripControllerIntegrationTest {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    private static RestTemplate restTemplate;

    @Autowired
    private TripRepository tripRepository;
    @BeforeAll
    public static void init(){
        restTemplate = new RestTemplate();
    }

    @AfterEach
    void clearDatabase(@Autowired JdbcTemplate jdbcTemplate) {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "marker");
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "trip");
    }
    @BeforeEach
    public void setup(){
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/apiTrip");
    }
    @Test
    public void testAddTrip(){
        Trip trip = new Trip("my_fifth_trip", 100.0);
        Trip response = restTemplate.postForObject(baseUrl + "/trips", trip, Trip.class);
        assertEquals("my_fifth_trip", response.getName());
    }

    @Test
    @Sql(statements = "INSERT INTO trip (id, name, total_distance) VALUES (1,'my_first_trip',1000)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testDeleteTrip(){
        int size = tripRepository.findAll().size();
        assertEquals(1, size);
        restTemplate.delete(baseUrl + "/trips/{id}", 1);
        assertEquals(0, tripRepository.findAll().size());
    }

    @Test
    @Sql(statements = "INSERT INTO trip (id, name, total_distance) VALUES (2,'my_second_trip',2000)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM trip WHERE id=2", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testGetTrips(){
        List<Trip> trips = restTemplate.getForObject(baseUrl + "/trips", List.class);
        assertEquals(1, trips.size());

    }

    @Test
    @Sql(statements = "INSERT INTO trip (id, name, total_distance) VALUES (3,'my_third_trip',3000)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM trip WHERE id=3", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testFindTripById(){
        Trip trip = restTemplate.getForObject(baseUrl + "/trips/{id}", Trip.class, 3);
        assertAll(  () -> assertNotNull(trip),
                    () -> assertEquals(3, trip.getId()));
    }

    @Test
    @Sql(statements = "INSERT INTO trip (id, name, total_distance) VALUES (4,'my_fourth_trip',4000)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM trip WHERE id=4", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testChangeTotalDistance(){
        double totalDistance = 4444;
        Trip response = restTemplate.postForObject(baseUrl + "/trips/{tripId}", totalDistance, Trip.class, 4);
        assertEquals(4444, response.getTotalDistance());
    }

    @Test
    @Sql(statements = "INSERT INTO trip (id, name, total_distance) VALUES (6,'my_sixth_trip',6000)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM trip WHERE id=6", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testChangeTripName(){
        String tripName = "my_new_sixth_trip";
        Trip response = restTemplate.postForObject(baseUrl + "/tripsName/{tripId}", tripName, Trip.class, 6);
        assertEquals("my_new_sixth_trip", response.getName());
    }
}
