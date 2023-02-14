package lt.viko.eif.hlibfediun.service;

import lt.viko.eif.hlibfediun.model.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class AirportService implements CrudService<Airport> {

    private final List<Airport> embeddedData = new ArrayList<>();

    @Autowired
    public AirportService(List<Airport> airportsEmbeddedData) {
        this.embeddedData.addAll(airportsEmbeddedData);
    }

    /**
     * Creates a new instance of the airport in the embedded data list.
     *
     * @param airport object
     * @return created airport.
     */
    @Override
    public Airport create(Airport airport) {
        if (null == airport.getId() || airport.getId().toString().isEmpty()) airport.setId(UUID.randomUUID());
        embeddedData.add(airport);
        return airport;
    }

    /**
     * Get airport by id
     *
     * @param id String UUID
     * @return airport object
     */
    @Override
    public Airport getById(String id) {
        return embeddedData.stream()
                .filter(e -> e.getId().toString().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Airport was not found with id = " + id));
    }

    /**
     * Gets all airports from the embedded data list.
     *
     * @return airport list.
     */
    @Override
    public List<Airport> getAll() {
        if (embeddedData.isEmpty()) throw new IllegalStateException("There is no airports");
        return embeddedData;
    }

    /**
     * Deletes airport by id from the embedded data list.
     *
     * @param id String UUID.
     */
    @Override
    public void delete(String id) {
        Airport airport = getById(id);
        embeddedData.remove(airport);
    }

    /**
     * Updates airport by id in the embedded data list.
     *
     * @param airport object.
     */
    @Override
    public Airport update(Airport airport) {
        if (null == airport.getId()) throw new NoSuchElementException("Id was not provided");
        String id = airport.getId().toString();

        Airport updateAirport = getById(id);

        embeddedData.remove(updateAirport);
        embeddedData.add(airport);

        return airport;
    }

    public List<Airport> getEmbeddedData() {
        return embeddedData;
    }
}
