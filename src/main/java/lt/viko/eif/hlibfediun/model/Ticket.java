package lt.viko.eif.hlibfediun.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Ticket model class.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Ticket {
    private UUID id;
    private double price;
    private int duration;
    private LocalDateTime departureDate;
    private Airport departureAirport;
    private Airport arrivalAirport;
}
