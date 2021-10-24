package micronaut.orch.pagamento.representation;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.micronaut.core.annotation.Introspected;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@Data
@Introspected
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class ContaRepresentation implements Serializable {
	
	private String id;

	private String numeroConta;
	
	private String digito;
	
	private String numeroDocumentoTitular;
	
	private BigDecimal saldo;
	
	private BigDecimal saldoEspecial;
	
	private String statusAtivo;
	
}
