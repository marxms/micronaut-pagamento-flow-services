package micronaut.orch.pagamento.camel.processor;

import micronaut.orch.pagamento.representation.ContaRepresentation;
import micronaut.orch.pagamento.representation.SolicitarPagamentoRepresentation;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.http.HttpMethods;
import org.apache.camel.support.MessageHelper;

import static micronaut.orch.pagamento.common.constants.RouterConstants.CONTA_PAYLOAD;

public class AtualizarSaldoContaCorrenteProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		MessageHelper.resetStreamCache(exchange.getUnitOfWork().getOriginalInMessage());
		SolicitarPagamentoRepresentation solicitacaoPagamento = exchange.getUnitOfWork().getOriginalInMessage().getBody(SolicitarPagamentoRepresentation.class);
		ContaRepresentation contaRepresentation = exchange.getProperty(CONTA_PAYLOAD, ContaRepresentation.class);
		ContaRepresentation request = ContaRepresentation.builder()
				.saldo(contaRepresentation.getSaldo().subtract(solicitacaoPagamento.getValorPagamento()))
				.build();
		exchange.getMessage().setHeader(Exchange.HTTP_METHOD, HttpMethods.PUT);
		exchange.getMessage().setHeader(Exchange.HTTP_PATH, solicitacaoPagamento.getNumeroConta());
		exchange.getMessage().setBody(request);
	}

}
