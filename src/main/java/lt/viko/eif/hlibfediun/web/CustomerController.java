package lt.viko.eif.hlibfediun.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lt.viko.eif.hlibfediun.model.Customer;
import lt.viko.eif.hlibfediun.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @Operation(summary = "Create a customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer is created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class))})})
    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        Customer createdCustomer = service.create(customer);
        return ResponseEntity.ok(createdCustomer);
    }

    @Operation(summary = "Get a customer by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the customer", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class))}),
            @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getById(@PathVariable String id) {
        Customer customer = service.getById(id);
        return ResponseEntity.ok(customer);
    }

    @Operation(summary = "Get all customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all customers", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class))}),
            @ApiResponse(responseCode = "204", description = "There is no customers", content = @Content)})
    @GetMapping
    public ResponseEntity<List<Customer>> getAll() {
        List<Customer> customerList = service.getAll();
        return ResponseEntity.ok(customerList);
    }

    @Operation(summary = "Deletes a customer by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Customer is deleted", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class))}),
            @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.status(204).build();
    }

    @Operation(summary = "Updates a customer by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer is updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class))}),
            @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content)})
    @PutMapping
    public ResponseEntity<Customer> update(@RequestBody Customer customer) {
        Customer updatedCustomer = service.update(customer);
        return ResponseEntity.ok(updatedCustomer);
    }
}
