package micronaut.orch.pagamento.service;

import lombok.extern.slf4j.Slf4j;
import micronaut.orch.pagamento.camel.CamelContextWrapper;
import micronaut.orch.pagamento.representation.SolicitacaoPagamentoResponseRepresentation;
import micronaut.orch.pagamento.representation.SolicitarPagamentoRepresentation;
import org.apache.camel.ProducerTemplate;

import javax.inject.Inject;
import javax.inject.Singleton;

import static micronaut.orch.pagamento.common.constants.RouterConstants.SOLICITAR_PAGAMENTO_ASYNC_FLOW;

@Singleton
@Slf4j
public class PagamentoService {

    private final CamelContextWrapper camelContextWrapper;

    private ProducerTemplate producerTemplate;


    @Inject
    PagamentoService(CamelContextWrapper camelContextWrapper) {
        this.camelContextWrapper = camelContextWrapper;
        producerTemplate = camelContextWrapper.createProducerTemplate();
    }

    public SolicitacaoPagamentoResponseRepresentation executePayment(SolicitarPagamentoRepresentation request) {
        return producerTemplate.requestBody(
                SOLICITAR_PAGAMENTO_ASYNC_FLOW,
                request,
                SolicitacaoPagamentoResponseRepresentation.class);
    }

}
