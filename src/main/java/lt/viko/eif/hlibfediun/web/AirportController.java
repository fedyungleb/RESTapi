package lt.viko.eif.hlibfediun.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lt.viko.eif.hlibfediun.model.Airport;
import lt.viko.eif.hlibfediun.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airports")
public class AirportController {

    private final AirportService service;

    @Autowired
    public AirportController(AirportService service) {
        this.service = service;
    }

    @Operation(summary = "Create a airport")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Airport is created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Airport.class))})})
    @PostMapping
    public ResponseEntity<Airport> create(@RequestBody Airport airport) {
        Airport createdAirport = service.create(airport);
        return ResponseEntity.ok(createdAirport);
    }

    @Operation(summary = "Get a airport by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the airport", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Airport.class))}),
            @ApiResponse(responseCode = "404", description = "Airport not found", content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<Airport> getById(@PathVariable String id) {
        Airport airport = service.getById(id);
        return ResponseEntity.ok(airport);
    }

    @Operation(summary = "Get all airports")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all airports", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Airport.class))}),
            @ApiResponse(responseCode = "204", description = "There is no airports", content = @Content)})
    @GetMapping
    public ResponseEntity<List<Airport>> getAll() {
        List<Airport> airportList = service.getAll();
        return ResponseEntity.ok(airportList);
    }

    @Operation(summary = "Deletes an airport by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Airport is deleted", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Airport.class))}),
            @ApiResponse(responseCode = "404", description = "Airport not found", content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.status(204).build();
    }

    @Operation(summary = "Updates a airport by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Airport is updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Airport.class))}),
            @ApiResponse(responseCode = "404", description = "Airport not found", content = @Content)})
    @PutMapping
    public ResponseEntity<Airport> update(@RequestBody Airport airport) {
        Airport updatedAirport  = service.update(airport);
        return ResponseEntity.ok(updatedAirport);
    }
}
