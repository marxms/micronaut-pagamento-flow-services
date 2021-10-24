package micronaut.orch.pagamento.representation;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class SolicitarPagamentoRepresentation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3188265208394685345L;

	private String numeroConta;
	
	private BigDecimal valorPagamento;
	
}
