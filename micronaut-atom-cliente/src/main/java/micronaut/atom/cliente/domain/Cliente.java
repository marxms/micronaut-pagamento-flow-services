package micronaut.atom.cliente.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.micronaut.core.annotation.Introspected;
import lombok.*;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@Introspected
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class Cliente implements Serializable {

    @BsonRepresentation(BsonType.OBJECT_ID)
    private String id;

    private String nomeCompleto;

    private String numeroDocumento;

    private String nacionalidade;

    private List<Endereco> enderecos;

}
