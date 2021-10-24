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
public class EnderecoRepresentation {

    private String id;

    private String UF;

    private String cidade;

    private String bairro;

    private Long numero;

    private String complemento;

    private String tipoEndereco;

    private CoordenadasGeograficasRepresentation coordenadasGeograficas;


}
