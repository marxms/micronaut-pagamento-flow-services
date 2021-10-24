package micronaut.orch.pagamento.common.exception;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonAutoDetect
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ErrorResponseRepresentation {

    private String message;

    private int status;

}
