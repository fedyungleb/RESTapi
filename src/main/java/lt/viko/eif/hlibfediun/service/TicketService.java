package lt.viko.eif.hlibfediun.service;

import lt.viko.eif.hlibfediun.model.Ticket;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class TicketService implements CrudService<Ticket> {

    private final List<Ticket> embeddedData = new ArrayList<>();

    public TicketService(List<Ticket> ticketsEmbeddedData) {
        embeddedData.addAll(ticketsEmbeddedData);
    }

    /**
     * Creates a new instance of the ticket in the embedded data list.
     *
     * @param ticket object
     * @return created ticket.
     */
    @Override
    public Ticket create(Ticket ticket) {
        if (null == ticket.getId() || ticket.getId().toString().isEmpty()) ticket.setId(UUID.randomUUID());
        embeddedData.add(ticket);
        return ticket;
    }

    /**
     * Get ticket by id
     *
     * @param id String UUID
     * @return ticket object
     */
    @Override
    public Ticket getById(String id) {
        return embeddedData.stream()
                .filter(e -> e.getId().toString().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Ticket was not found with id = " + id));
    }

    /**
     * Gets all tickets from the embedded data list.
     *
     * @return ticket list.
     */
    @Override
    public List<Ticket> getAll() {
        if (embeddedData.isEmpty()) throw new IllegalStateException("There is no tickets");
        return embeddedData;
    }

    /**
     * Deletes ticket by id from the embedded data list.
     *
     * @param id String UUID.
     */
    @Override
    public void delete(String id) {
        Ticket ticket = getById(id);
        embeddedData.remove(ticket);
    }

    /**
     * Updates ticket by id in the embedded data list.
     *
     * @param ticket object.
     */
    @Override
    public Ticket update(Ticket ticket) {
        if (null == ticket.getId()) throw new NoSuchElementException("Id was not provided");

        String id = ticket.getId().toString();

        Ticket updateTicket = getById(id);

        embeddedData.remove(updateTicket);
        embeddedData.add(ticket);

        return ticket;
    }

    public List<Ticket> getEmbeddedData() {
        return embeddedData;
    }
}
