package http.dto;

import lombok.Builder;
import lombok.Value;

import java.util.Objects;

// value как data только еще private final прикручивает
@Value
@Builder
public class FlightDto {

    Long id;
    String description;
}
