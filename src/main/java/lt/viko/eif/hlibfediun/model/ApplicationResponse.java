package lt.viko.eif.hlibfediun.model;

import lombok.*;

/**
 * Application response model class.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ApplicationResponse {
    private String message;
    private int statusCode;
}
