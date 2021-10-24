package micronaut.orch.pagamento.camel.processor;

import micronaut.orch.pagamento.representation.SolicitarPagamentoRepresentation;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.http.HttpMethods;

public class ConsultaContaCorrenteProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		SolicitarPagamentoRepresentation solicitarPagamentoRepresentation = exchange.getIn().getBody(SolicitarPagamentoRepresentation.class);
		exchange.getMessage().setHeader(Exchange.HTTP_METHOD, HttpMethods.GET);
		exchange.getMessage().setHeader(Exchange.HTTP_PATH, solicitarPagamentoRepresentation.getNumeroConta());
	}

}
