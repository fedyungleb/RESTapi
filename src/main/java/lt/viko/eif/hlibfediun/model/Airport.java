package lt.viko.eif.hlibfediun.model;

import lombok.*;

import java.util.UUID;

/**
 * Airport model class.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Airport {
    private UUID id;
    private String name;
    private String country;
    private String address;
}
