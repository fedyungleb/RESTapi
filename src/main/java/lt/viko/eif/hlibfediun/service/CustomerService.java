package lt.viko.eif.hlibfediun.service;

import lt.viko.eif.hlibfediun.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class CustomerService implements CrudService<Customer> {

    private final List<Customer> embeddedData = new ArrayList<>();

    public CustomerService(List<Customer> customersEmbeddedData) {
        embeddedData.addAll(customersEmbeddedData);
    }

    /**
     * Creates a new instance of the customer in the embedded data list.
     *
     * @param customer object
     * @return created customer.
     */
    @Override
    public Customer create(Customer customer) {
        if (null == customer.getId() || customer.getId().toString().isEmpty()) customer.setId(UUID.randomUUID());
        embeddedData.add(customer);
        return customer;
    }

    /**
     * Get customer by id
     *
     * @param id String UUID
     * @return customer object
     */
    @Override
    public Customer getById(String id) {
        return embeddedData.stream()
                .filter(e -> e.getId().toString().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Customer was not found with id = " + id));
    }

    /**
     * Gets all customers from the embedded data list.
     *
     * @return customer list.
     */
    @Override
    public List<Customer> getAll() {
        if (embeddedData.isEmpty()) throw new IllegalStateException("There is no customer");
        return embeddedData;
    }

    /**
     * Deletes customer by id from the embedded data list.
     *
     * @param id String UUID.
     */
    @Override
    public void delete(String id) {
        Customer customer = getById(id);
        embeddedData.remove(customer);
    }

    /**
     * Updates customer by id in the embedded data list.
     *
     * @param customer object.
     */
    @Override
    public Customer update(Customer customer) {
        if (null == customer.getId()) throw new NoSuchElementException("Id was not provided");
        String id = customer.getId().toString();

        Customer updateCustomer = getById(id);

        embeddedData.remove(updateCustomer);
        embeddedData.add(customer);

        return customer;
    }

    public List<Customer> getEmbeddedData() {
        return embeddedData;
    }
}
