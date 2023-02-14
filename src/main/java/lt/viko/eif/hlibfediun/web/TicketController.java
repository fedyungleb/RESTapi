package lt.viko.eif.hlibfediun.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lt.viko.eif.hlibfediun.model.Ticket;
import lt.viko.eif.hlibfediun.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService service;

    @Autowired
    public TicketController(TicketService service) {
        this.service = service;
    }

    @Operation(summary = "Create a ticket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket is created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Ticket.class))})})
    @PostMapping
    public ResponseEntity<Ticket> create(@RequestBody Ticket ticket) {
        Ticket createdTicket = service.create(ticket);
        return ResponseEntity.ok(createdTicket);
    }

    @Operation(summary = "Get a ticket by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the ticket", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Ticket.class))}),
            @ApiResponse(responseCode = "404", description = "Ticket not found", content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getById(@PathVariable String id) {
        Ticket ticket = service.getById(id);
        return ResponseEntity.ok(ticket);
    }

    @Operation(summary = "Get all tickets")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all tickets", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Ticket.class))}),
            @ApiResponse(responseCode = "204", description = "There is no tickets", content = @Content)})
    @GetMapping
    public ResponseEntity<List<Ticket>> getAll() {
        List<Ticket> ticketList = service.getAll();
        return ResponseEntity.ok(ticketList);
    }

    @Operation(summary = "Deletes a ticket by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ticket is deleted", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Ticket.class))}),
            @ApiResponse(responseCode = "404", description = "Ticket not found", content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.status(204).build();
    }

    @Operation(summary = "Updates a ticket by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket is updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Ticket.class))}),
            @ApiResponse(responseCode = "404", description = "Ticket not found", content = @Content)})
    @PutMapping
    public ResponseEntity<Ticket> update(@RequestBody Ticket ticket) {
        Ticket updatedTicket = service.update(ticket);
        return ResponseEntity.ok(updatedTicket);
    }
}
