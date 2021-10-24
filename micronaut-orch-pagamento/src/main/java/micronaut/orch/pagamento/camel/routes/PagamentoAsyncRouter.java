package micronaut.orch.pagamento.camel.routes;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import micronaut.orch.pagamento.camel.processor.NotificarPagamentoProcessor;
import micronaut.orch.pagamento.representation.SolicitarPagamentoRepresentation;
import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

import javax.inject.Singleton;

import static micronaut.orch.pagamento.common.constants.RouterConstants.*;


@Slf4j
@Singleton
@AllArgsConstructor
public class PagamentoAsyncRouter extends RouteBuilder {

	public void configure() {
/****************************************************************************************************************
* INTERNAL ROUTES DEFINITIONS 																			        *
****************************************************************************************************************/

		from(fromPagamentoQueue())
				.routeId(ID_ROTA_PROCESSAR_PAGAMENTO_ASYNC)
                .to(PROCESSAR_PAGAMENTO_CORE_FLOW)
		.end();

        from(SOLICITAR_PAGAMENTO_ASYNC_FLOW)
				.routeId(ID_ROTA_SOLICITAR_PAGAMENTO_ASYNC)
				.marshal().json(JsonLibrary.Jackson, SolicitarPagamentoRepresentation.class)
				.to(ExchangePattern.InOnly, toParceiroSolicitaPagamento())
				.process(new NotificarPagamentoProcessor())
		.end();

	}

	private String fromPagamentoQueue() {
		StringBuffer sb = new StringBuffer();
		sb.append(RABBIT_COMPONENT);
		sb.append(EXCHANGE_PAGAMENTO);
		sb.append("?" + QUEUE);
		sb.append("=" + QUEUE_SOLICITAR_PAGAMENTO);
		sb.append("&" + AUTO_DELETE_FALSE);
		return sb.toString();
	}


	private String toParceiroSolicitaPagamento() {
		StringBuffer sb = new StringBuffer();
		sb.append(RABBIT_COMPONENT);
		sb.append(EXCHANGE_PAGAMENTO);
		sb.append("?" + QUEUE);
		sb.append("=" + QUEUE_SOLICITAR_PAGAMENTO);
		sb.append("&" + AUTO_DELETE_FALSE);
		return sb.toString();
	}

}
