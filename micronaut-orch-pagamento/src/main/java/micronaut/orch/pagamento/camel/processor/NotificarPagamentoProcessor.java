package micronaut.orch.pagamento.camel.processor;

import micronaut.orch.pagamento.representation.SolicitacaoPagamentoResponseRepresentation;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class NotificarPagamentoProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		SolicitacaoPagamentoResponseRepresentation response = new SolicitacaoPagamentoResponseRepresentation();
    	response.setCode(200);
    	response.setMessage("Pagamento enviado");
    	exchange.getMessage().setBody(response, SolicitacaoPagamentoResponseRepresentation.class);
	}
}
