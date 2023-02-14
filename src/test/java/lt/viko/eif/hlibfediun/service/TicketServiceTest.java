package lt.viko.eif.hlibfediun.service;

import lt.viko.eif.hlibfediun.model.Airport;
import lt.viko.eif.hlibfediun.model.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class TicketServiceTest {

    private TicketService service;

    private final List<Ticket> TEST_TICKETS = getTestData();

    @BeforeEach
    public void setUp() {
        service = new TicketService(TEST_TICKETS);
    }

    @Test
    @DisplayName("test create when ticket was created")
    public void testCreate200() {
        Ticket ticketToCreate = new Ticket(UUID.fromString("b62b72a0-7021-4161-886b-1a7fab81d763"),
                250.0, 110, LocalDateTime.now(),
                null, null);
        Ticket createdTicket = service.create(ticketToCreate);

        List<Ticket> expectedTickets = new ArrayList<>(TEST_TICKETS);
        expectedTickets.add(ticketToCreate);

        Assertions.assertEquals(ticketToCreate, createdTicket);
        Assertions.assertEquals(expectedTickets, service.getEmbeddedData());
    }

    @Test
    @DisplayName("test get all when tickets are found")
    public void testGetAll200() {
        List<Ticket> tickets = service.getAll();
        Assertions.assertEquals(TEST_TICKETS, tickets);
    }

    @Test
    @DisplayName("test get all when tickets list is empty")
    public void testGetAll204() {
        service = new TicketService(List.of());
        Assertions.assertThrows(IllegalStateException.class, () -> service.getAll());
    }

    @Test
    @DisplayName("test get by id when tickets is found")
    public void testGetById200() {
        Ticket actualTicket = service.getById("b62b72a0-7021-4161-886b-1a7fab81d761");
        Assertions.assertEquals(TEST_TICKETS.get(0), actualTicket);
    }

    @Test
    @DisplayName("test get by id when tickets is not found")
    public void testGetById404() {
        Assertions.assertThrows(NoSuchElementException.class, () -> service.getById("123"));
    }

    @Test
    @DisplayName("test delete by id when tickets was deleted")
    public void testDelete204() {
        service.delete("b62b72a0-7021-4161-886b-1a7fab81d761");

        List<Ticket> expectedTicket = new ArrayList<>(TEST_TICKETS);
        expectedTicket.remove(0);

        Assertions.assertEquals(expectedTicket, service.getEmbeddedData());
    }

    @Test
    @DisplayName("test update by id when tickets was updated")
    public void testUpdate200() {
        Ticket ticketToUpdate = TEST_TICKETS.get(0);
        ticketToUpdate.setDuration(111);

        Ticket actualTicket = service.update(ticketToUpdate);
        Assertions.assertEquals(ticketToUpdate, actualTicket);
    }

    private List<Ticket> getTestData() {
        List<Airport> testAirports = List.of(
                new Airport(UUID.fromString("b62b72a0-7021-4161-886b-1a7fab81d760"), "Airport I", "USA", "Street 56"),
                new Airport(UUID.fromString("b62b72a0-7021-4161-886b-1a7fab81d759"), "Airport II", "Canada", "Street 12")
        );

        return List.of(
                new Ticket(UUID.fromString("b62b72a0-7021-4161-886b-1a7fab81d761"), 200.0, 120,
                        LocalDateTime.of(2022, Month.APRIL, 30, 1, 30),
                        testAirports.get(0), testAirports.get(1)),
                new Ticket(UUID.fromString("b62b72a0-7021-4161-886b-1a7fab81d762"), 300.0, 130,
                        LocalDateTime.of(2022, Month.APRIL, 30, 1, 30),
                        testAirports.get(1), testAirports.get(0))
        );
    }
}
