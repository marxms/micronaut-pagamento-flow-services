package micronaut.atom.conta.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.reactivex.Single;
import micronaut.atom.conta.representation.Pagamento;
import micronaut.atom.conta.service.PagamentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.validation.constraints.NotBlank;

@Singleton
@Controller("/v1/pagamento")
public class PagamentoController {
    private final Logger log = LoggerFactory.getLogger(PagamentoController.class);
    private final PagamentoRepository repository;

    public PagamentoController(PagamentoRepository repository) {
        this.repository = repository;
    }

    @Post(consumes = "application/json", produces = "application/json")
    public HttpResponse<Single<Pagamento>> insertPayment(@NotBlank Pagamento pagamento) {
        log.info("REQUEST --> {}", pagamento.toString());
        return HttpResponse.created(repository.insertPayment(pagamento));

    }

}
