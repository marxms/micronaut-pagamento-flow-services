package micronaut.orch.pagamento.representation;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.micronaut.core.annotation.Introspected;
import lombok.*;

@Getter
@Setter
@Builder
@Data
@Introspected
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class CoordenadasGeograficasRepresentation {

    private String numeroLatitude;

    private String numeroLongitude;

}
