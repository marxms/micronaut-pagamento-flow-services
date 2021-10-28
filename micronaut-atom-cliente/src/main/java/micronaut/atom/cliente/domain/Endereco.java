package micronaut.atom.cliente.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.micronaut.core.annotation.Introspected;
import lombok.*;
import org.bson.types.ObjectId;

@Getter
@Setter
@Builder
@Data
@Introspected
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class Endereco {

    private ObjectId id;

    private String UF;

    private String cidade;

    private String logradouro;

    private String bairro;

    private Long numero;

    private String complemento;

    private TipoEnderecoEnum tipoEndereco;

    private CoordenadasGeograficas coordenadasGeograficas;

}
