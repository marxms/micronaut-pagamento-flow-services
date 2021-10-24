package micronaut.orch.pagamento.camel.processor;

import micronaut.orch.pagamento.representation.ContaRepresentation;
import micronaut.orch.pagamento.representation.PagamentoRepresentation;
import micronaut.orch.pagamento.representation.SolicitarPagamentoRepresentation;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.http.HttpMethods;

import java.time.LocalDateTime;

import static micronaut.orch.pagamento.util.CamelUtils.removeAllCamelHttpHeaders;

public class EfetivarPagamentoProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		SolicitarPagamentoRepresentation solicitacaoPagamento = exchange.getUnitOfWork().getOriginalInMessage().getBody(SolicitarPagamentoRepresentation.class);
		ContaRepresentation contaRepresentation = exchange.getIn().getBody(ContaRepresentation.class);
		var efetivarPagamentoRepresentation = PagamentoRepresentation.builder()
				.numeroConta(contaRepresentation.getNumeroConta())
				.valorPagamento(solicitacaoPagamento.getValorPagamento())
				.dtPagamento(LocalDateTime.now())
				.build();
		removeAllCamelHttpHeaders(exchange.getMessage());
		exchange.getMessage().setBody(efetivarPagamentoRepresentation, PagamentoRepresentation.class);
		exchange.getMessage().setHeader(Exchange.HTTP_METHOD, HttpMethods.POST);
	}

}
