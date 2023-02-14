package lt.viko.eif.hlibfediun.model;

import lombok.*;

import java.util.List;
import java.util.UUID;

/**
 * Customer model class.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Customer {
    private UUID id;
    private String firstName;
    private String lastName;
    private List<Ticket> tickets;
}
