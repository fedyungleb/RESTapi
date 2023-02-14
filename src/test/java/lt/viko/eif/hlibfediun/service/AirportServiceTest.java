package lt.viko.eif.hlibfediun.service;

import lt.viko.eif.hlibfediun.model.Airport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class AirportServiceTest {

    private AirportService service;

    private final List<Airport> TEST_AIRPORTS = List.of(
            new Airport(UUID.fromString("b62b72a0-7021-4161-886b-1a7fab81d764"), "Airport I", "USA", "Street 56"),
            new Airport(UUID.fromString("617faca6-24a9-11ed-861d-0242ac120002"), "Airport II", "Canada", "Street 12")
    );

    @BeforeEach
    public void setUp() {
        service = new AirportService(TEST_AIRPORTS);
    }

    @Test
    @DisplayName("test create when airport was created")
    public void testCreate200() {
        Airport airportToCreate = new Airport(UUID.fromString("6797e95e-24aa-11ed-861d-0242ac120002"),
                "Airport III", "New Zealand", "Street 9");
        Airport createdAirport = service.create(airportToCreate);

        List<Airport> expectedAirports = new ArrayList<>(TEST_AIRPORTS);
        expectedAirports.add(airportToCreate);

        Assertions.assertEquals(airportToCreate, createdAirport);
        Assertions.assertEquals(expectedAirports, service.getEmbeddedData());
    }

    @Test
    @DisplayName("test get all when airports are found")
    public void testGetAll200() {
        List<Airport> airports = service.getAll();
        Assertions.assertEquals(TEST_AIRPORTS, airports);
    }

    @Test
    @DisplayName("test get all when airports list is empty")
    public void testGetAll204() {
        service = new AirportService(List.of());
        Assertions.assertThrows(IllegalStateException.class, () -> service.getAll());
    }

    @Test
    @DisplayName("test get by id when airport is found")
    public void testGetById200() {
        Airport actualAirport = service.getById("b62b72a0-7021-4161-886b-1a7fab81d764");
        Assertions.assertEquals(TEST_AIRPORTS.get(0), actualAirport);
    }

    @Test
    @DisplayName("test get by id when airport is not found")
    public void testGetById404() {
        Assertions.assertThrows(NoSuchElementException.class, () -> service.getById("123"));
    }

    @Test
    @DisplayName("test delete by id when airport was deleted")
    public void testDelete204() {
        service.delete("b62b72a0-7021-4161-886b-1a7fab81d764");

        List<Airport> expectedAirports = new ArrayList<>(TEST_AIRPORTS);
        expectedAirports.remove(0);

        Assertions.assertEquals(expectedAirports, service.getEmbeddedData());
    }

    @Test
    @DisplayName("test update by id when airport was updated")
    public void testUpdate200() {
        Airport airportToUpdate = new Airport(UUID.fromString("b62b72a0-7021-4161-886b-1a7fab81d764"),
                "UPDATED Airport I", "USA", "Street 56");
        Airport actualAirport = service.update(airportToUpdate);
        Assertions.assertEquals(airportToUpdate, actualAirport);
    }
}
