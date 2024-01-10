package com.example.webtechProjekt.MarkerIntegrationTest;

import com.example.webtechProjekt.controller.MarkerController;
import com.example.webtechProjekt.controller.TripController;
import com.example.webtechProjekt.model.Marker;
import com.example.webtechProjekt.model.Trip;
import com.example.webtechProjekt.repository.MarkerRepository;
import com.example.webtechProjekt.repository.TripRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class MarkerControllerIntegrationTest {
    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    private static RestTemplate restTemplate;

    @Autowired
    private MarkerRepository markerRepository;

    @Autowired
    private TripController tripController;
    @Autowired
    private MarkerController markerController;

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
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/apiMarker");
    }

    //Test is not necessary, because endpoint is not used, but it shows, that markers can be added
    @Test
    @Sql(statements = "INSERT INTO trip (id, name, total_distance) VALUES (1,'my_first_trip',1000)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO marker (id, lat, lng, trip_id) VALUES (1, 13.52523, 52.49494, 1)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM trip WHERE id=1", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Sql(statements = "DELETE FROM marker WHERE id=1", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testAddMarker(){
        List<Marker> markerList = markerController.findAll();
        double lat = 0.0;
        double lng = 0.0;
        for(Marker markerFromDb : markerList){
            lng = markerFromDb.getLng();
            lat = markerFromDb.getLat();
        }
        Marker marker = new Marker(lat,lng,tripController.getTrips(1));
        Marker response = restTemplate.postForObject(baseUrl + "/markers?tripId=" + tripController.getTrips(1).getId(), marker, Marker.class);
        assertEquals(13.52523, response.getLat());
        assertEquals(52.49494, response.getLng());
    }

    @Test
    @Sql(statements = "INSERT INTO marker (id, lat, lng) VALUES (2,13.52523,52.49494)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testDeleteMarker(){
        int size = markerRepository.findAll().size();
        assertEquals(1, size);
        restTemplate.delete(baseUrl + "/markers/{id}", 2);
        assertEquals(0, markerRepository.findAll().size());
    }

}
