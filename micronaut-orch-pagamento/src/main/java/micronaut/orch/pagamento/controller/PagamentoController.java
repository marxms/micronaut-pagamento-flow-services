package micronaut.orch.pagamento.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import micronaut.orch.pagamento.representation.SolicitarPagamentoRepresentation;
import micronaut.orch.pagamento.service.PagamentoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotBlank;

@Controller("/v1/pagamento")
public class PagamentoController {
    private final Logger log = LoggerFactory.getLogger(PagamentoController.class);
    private final PagamentoService service;

    public PagamentoController(PagamentoService service) {
        this.service = service;
    }

    @Post(consumes = "application/json", produces = "application/json")
    public HttpResponse<Object> notifyPayment(@NotBlank @Body SolicitarPagamentoRepresentation payment) {
        log.info("REQUEST --> {}", payment.toString());
        return HttpResponse.created(service.executePayment(payment));
    }

}
