package micronaut.orch.pagamento.camel.routes;

import io.micronaut.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import micronaut.orch.pagamento.camel.predicates.VerificarSaldoPredicate;
import micronaut.orch.pagamento.camel.processor.AtualizarSaldoContaCorrenteProcessor;
import micronaut.orch.pagamento.camel.processor.ConsultaContaCorrenteProcessor;
import micronaut.orch.pagamento.camel.processor.EfetivarPagamentoProcessor;
import micronaut.orch.pagamento.common.exception.SaldoIndisponivelException;
import micronaut.orch.pagamento.configuration.EndpointsConfiguration;
import micronaut.orch.pagamento.representation.ContaRepresentation;
import micronaut.orch.pagamento.representation.PagamentoRepresentation;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

import javax.inject.Inject;
import javax.inject.Singleton;

import static micronaut.orch.pagamento.common.constants.RouterConstants.*;


@Slf4j
@Singleton
public class PagamentoRouter extends RouteBuilder {

	private final EndpointsConfiguration endpoints;

	@Inject
	public PagamentoRouter(EndpointsConfiguration endpointsConfiguration) {
		this.endpoints = endpointsConfiguration;
	}

	public void configure() throws Exception{

/****************************************************************************************************************
 * EXCEPTION HANDLING CONFIGURATION 																			*							   *
 ****************************************************************************************************************/

		errorHandler(noErrorHandler());

/****************************************************************************************************************
 * INTERNAL ROUTES DEFINITIONS 																			        *
 ****************************************************************************************************************/

        from(PROCESSAR_PAGAMENTO_CORE_FLOW)
				.routeId(ID_ROTA_DEBITAR_PAGAMENTO_CORE)
				.log(LoggingLevel.INFO, log, "${body}")
				.setProperty(INITIAL_PAYLOAD, simple("${body}"))
				.process(new ConsultaContaCorrenteProcessor())
				.to(endpoints.getContacorrente())
				.unmarshal().json(JsonLibrary.Jackson, ContaRepresentation.class)
				.setProperty(CONTA_PAYLOAD, body())
				.choice()
					.when(new VerificarSaldoPredicate())
						.process(new EfetivarPagamentoProcessor())
						.marshal().json(JsonLibrary.Jackson, PagamentoRepresentation.class)
						.to(endpoints.getPagamento())
						.process(new AtualizarSaldoContaCorrenteProcessor())
						.marshal().json(JsonLibrary.Jackson, ContaRepresentation.class)
						.to(endpoints.getContacorrente())
				.endChoice()
					.otherwise()
						.throwException(new SaldoIndisponivelException(HttpStatus.PRECONDITION_FAILED, "Seu saldo é menor que o minimo para esta operacao"))
				.end()
		.end();

	}
}
