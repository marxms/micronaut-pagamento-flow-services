package micronaut.atom.cliente.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.reactivex.Maybe;
import io.reactivex.Single;
import micronaut.atom.cliente.domain.Cliente;
import micronaut.atom.cliente.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.constraints.NotBlank;

@Singleton
@Controller("/v1/clientes")
public class ClienteController {
    private final Logger log = LoggerFactory.getLogger(ClienteController.class);
    private final ClienteRepository repository;

    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    @Post(consumes = "application/json", produces = "application/json")
    public HttpResponse<Single<Cliente>> insertCustomer(@NotBlank Cliente cliente) {
        log.info("REQUEST --> {}", cliente.toString());
        return HttpResponse.created(repository.insertCustomer(cliente));

    }

    @Get(value = "/documento/{numeroDocumento}", produces = "application/json")
    public HttpResponse<Maybe<Cliente>> getClienteByNumeroDocumento(@NotBlank @PathVariable String numeroDocumento) {
        log.info("Numero documento --> {}", numeroDocumento);
        return HttpResponse.ok(repository.findClienteByNumeroDocumento(numeroDocumento));
    }
//    private static HttpRequest<Object> getRequestContext() {
//        return ServerRequestContext.currentRequest().get();
//    }
}
