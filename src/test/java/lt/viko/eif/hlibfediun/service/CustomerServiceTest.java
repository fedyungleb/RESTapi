package lt.viko.eif.hlibfediun.service;

import lt.viko.eif.hlibfediun.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class CustomerServiceTest {

    private CustomerService service;

    private final List<Customer> TEST_CUSTOMERS = List.of(
            new Customer(UUID.fromString("b62b72a0-7021-4161-886b-1a7fab81d764"), "Name1", "Surname1", List.of()),
            new Customer(UUID.fromString("617faca6-24a9-11ed-861d-0242ac120002"), "Name2", "Surname2", List.of())
    );

    @BeforeEach
    public void setUp() {
        service = new CustomerService(TEST_CUSTOMERS);
    }

    @Test
    @DisplayName("test create when customer was created")
    public void testCreate200() {
        Customer customerToCreate = new Customer(UUID.fromString("6797e95e-24aa-11ed-861d-0242ac120002"),
                "Name3", "Surname3", List.of());
        Customer createdCustomer = service.create(customerToCreate);

        List<Customer> expectedCustomers = new ArrayList<>(TEST_CUSTOMERS);
        expectedCustomers.add(customerToCreate);

        Assertions.assertEquals(customerToCreate, createdCustomer);
        Assertions.assertEquals(expectedCustomers, service.getEmbeddedData());
    }

    @Test
    @DisplayName("test get all when customers are found")
    public void testGetAll200() {
        List<Customer> customers = service.getAll();
        Assertions.assertEquals(TEST_CUSTOMERS, customers);
    }

    @Test
    @DisplayName("test get all when customers list is empty")
    public void testGetAll204() {
        service = new CustomerService(List.of());
        Assertions.assertThrows(IllegalStateException.class, () -> service.getAll());
    }

    @Test
    @DisplayName("test get by id when customer is found")
    public void testGetById200() {
        Customer actualCustomer = service.getById("b62b72a0-7021-4161-886b-1a7fab81d764");
        Assertions.assertEquals(TEST_CUSTOMERS.get(0), actualCustomer);
    }

    @Test
    @DisplayName("test get by id when customer is not found")
    public void testGetById404() {
        Assertions.assertThrows(NoSuchElementException.class, () -> service.getById("123"));
    }

    @Test
    @DisplayName("test delete by id when customer was deleted")
    public void testDelete204() {
        service.delete("b62b72a0-7021-4161-886b-1a7fab81d764");

        List<Customer> expectedCustomer = new ArrayList<>(TEST_CUSTOMERS);
        expectedCustomer.remove(0);

        Assertions.assertEquals(expectedCustomer, service.getEmbeddedData());
    }

    @Test
    @DisplayName("test update by id when customer was updated")
    public void testUpdate200() {
        Customer customerToUpdate = new Customer(UUID.fromString("b62b72a0-7021-4161-886b-1a7fab81d764"),
                " UPDATED Name1", "Surname1", List.of());
        Customer actualCustomer = service.update(customerToUpdate);
        Assertions.assertEquals(customerToUpdate, actualCustomer);
    }
}
