package lt.viko.eif.hlibfediun.config;

import lt.viko.eif.hlibfediun.model.Airport;
import lt.viko.eif.hlibfediun.model.Customer;
import lt.viko.eif.hlibfediun.model.Ticket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Test data configuration.
 */
@Configuration
public class ApplicationDataConfig {

    @Bean
    public List<Airport> airportsEmbeddedData() {
        List<Airport> airports = List.of(
                new Airport(UUID.randomUUID(), "Airport I", "USA", "Street 56"),
                new Airport(UUID.randomUUID(), "Airport II", "Canada", "Street 12"),
                new Airport(UUID.randomUUID(), "Airport III", "New Zealand", "Street 9")
        );
        System.out.println("Generated:" + airports);
        return airports;
    }

    @Bean
    public List<Customer> customersEmbeddedData() {
        List<Customer> customers = List.of(
                new Customer(UUID.randomUUID(), "Name1", "Surname1", List.of()),
                new Customer(UUID.randomUUID(), "Name2", "Surname2", List.of()),
                new Customer(UUID.randomUUID(), "Name3", "Surname3", List.of())
        );
        System.out.println("Generated:" + customers);
        return customers;
    }

    @Bean
    public List<Ticket> ticketsEmbeddedData(List<Airport> airportsEmbeddedData) {
        List<Ticket> tickets = List.of(
                new Ticket(UUID.randomUUID(), 200.0, 120, LocalDateTime.now(),
                        airportsEmbeddedData.get(0), airportsEmbeddedData.get(1)),
                new Ticket(UUID.randomUUID(), 300.0, 130, LocalDateTime.now(),
                        airportsEmbeddedData.get(1), airportsEmbeddedData.get(0)),
                new Ticket(UUID.randomUUID(), 250.0, 110, LocalDateTime.now(),
                        airportsEmbeddedData.get(2), airportsEmbeddedData.get(1))
        );
        System.out.println("Generated:" + tickets);
        return tickets;
    }
}
