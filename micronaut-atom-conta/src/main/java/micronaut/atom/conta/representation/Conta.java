package micronaut.atom.conta.representation;

import io.micronaut.core.annotation.Introspected;
import lombok.*;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@Data
@Introspected
@NoArgsConstructor
@AllArgsConstructor
public class Conta implements Serializable {
	
	@BsonRepresentation(BsonType.OBJECT_ID)
	private String id;

	private String numeroConta;
	
	private String digito;
	
	private String numeroDocumentoTitular;
	
	private BigDecimal saldo;
	
	private BigDecimal saldoEspecial;
	
	private String statusAtivo;
	
}
