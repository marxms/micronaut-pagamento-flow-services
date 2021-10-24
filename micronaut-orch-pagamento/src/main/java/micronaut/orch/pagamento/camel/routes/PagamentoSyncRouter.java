package micronaut.orch.pagamento.camel.routes;

import lombok.extern.slf4j.Slf4j;
import micronaut.orch.pagamento.camel.processor.exception.BuildExceptionResponseProcessor;
import micronaut.orch.pagamento.common.exception.SaldoIndisponivelException;
import micronaut.orch.pagamento.representation.SolicitarPagamentoRepresentation;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

import javax.inject.Singleton;

import static io.micronaut.http.MediaType.APPLICATION_JSON;
import static micronaut.orch.pagamento.common.constants.RouterConstants.*;


@Slf4j
@Singleton
public class PagamentoSyncRouter extends RouteBuilder {

    public void configure() throws Exception {

/****************************************************************************************************************
 * REST PROPERTIES AND COMPONENTS CONFIGURATION 																*										   *
 * **************************************************************************************************************/

        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.json)
                .contextPath("services");

/****************************************************************************************************************
 * EXCEPTION HANDLING CONFIGURATION 																			*							   *
 ****************************************************************************************************************/

        onException(SaldoIndisponivelException.class)
                .handled(true)
                .process(new BuildExceptionResponseProcessor())
        .end();

/****************************************************************************************************************
 * INTERNAL ROUTES DEFINITIONS 																			        *
 ****************************************************************************************************************/

        from(SOLICITAR_PAGAMENTO_SYNC_FLOW)
                .routeId(ID_ROTA_SOLICITAR_PAGAMENTO_SYNC)
                .to(PROCESSAR_PAGAMENTO_CORE_FLOW)
        .end();

/****************************************************************************************************************
 * REST ENDPOINTS DEFINITIONS 																			        *
 ****************************************************************************************************************/

        rest("/v2/pagamento")
                .produces(APPLICATION_JSON)
                .post()
                .type(SolicitarPagamentoRepresentation.class)
                .route()
                .log(LoggingLevel.INFO, log, "INICIANDO SERIALIZE PARA DOMAIN${body}")
                .removeHeaders("CamelHttp*")
                .to(SOLICITAR_PAGAMENTO_SYNC_FLOW)
		.endRest();

    }
}
