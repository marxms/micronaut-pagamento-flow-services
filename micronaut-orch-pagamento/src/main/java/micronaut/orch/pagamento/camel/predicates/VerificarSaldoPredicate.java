package micronaut.orch.pagamento.camel.predicates;

import micronaut.orch.pagamento.representation.ContaRepresentation;
import micronaut.orch.pagamento.representation.SolicitarPagamentoRepresentation;
import org.apache.camel.Exchange;
import org.apache.camel.Predicate;

import java.math.BigDecimal;

public class VerificarSaldoPredicate implements Predicate{

	@Override
	public boolean matches(Exchange exchange) {
		SolicitarPagamentoRepresentation solicitacaoPagamento = exchange.getProperty("initialPayload", SolicitarPagamentoRepresentation.class);
		BigDecimal saldoContaCorrente = exchange.getIn().getBody(ContaRepresentation.class).getSaldo();
		BigDecimal valorPagamento = solicitacaoPagamento.getValorPagamento();
		if(saldoContaCorrente.subtract(valorPagamento).compareTo(BigDecimal.ZERO) >= 0) {
			return true;
		}
		return false;
	}

}
