package micronaut.atom.conta.representation;

import io.micronaut.core.annotation.Introspected;
import lombok.*;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Introspected
public class Pagamento implements Serializable {

	@BsonRepresentation(BsonType.OBJECT_ID)
	private String id;

	private BigDecimal valorPagamento;

	private LocalDateTime dtPagamento;

	private String numeroConta;

}
