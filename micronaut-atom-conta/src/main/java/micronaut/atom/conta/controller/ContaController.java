package micronaut.atom.conta.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.reactivex.Maybe;
import io.reactivex.Single;
import micronaut.atom.conta.representation.Conta;
import micronaut.atom.conta.service.ContaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.validation.constraints.NotBlank;

@Singleton
@Controller("/v1/conta")
public class ContaController {
    private final Logger log = LoggerFactory.getLogger(ContaController.class);
    private final ContaRepository repository;

    public ContaController(ContaRepository repository) {
        this.repository = repository;
    }

    @Post(consumes = "application/json", produces = "application/json")
    public HttpResponse<Single<Conta>> insertAccount(@NotBlank Conta conta) {
        log.info("REQUEST --> {}", conta.toString());
        return HttpResponse.created(repository.insertAccount(conta));

    }

    @Get(value = "/{numeroConta}", produces = "application/json")
    public HttpResponse<Maybe<Conta>> getAccountByOwnerDocumentNumber(@NotBlank @PathVariable String numeroConta) {
        log.info("Numero conta recebido --> {}", numeroConta);
        return HttpResponse.ok(repository.findContaByNumeroConta(numeroConta));
    }
    @Put(value = "/{numeroConta}", produces = "application/json")
    public HttpResponse<Maybe<Conta>> updateAccount(@NotBlank @PathVariable String numeroConta, @NotBlank Conta conta) {
        log.info("Numero conta recebido --> {}", numeroConta);
        return HttpResponse.ok(repository.updateAccountByNumberoConta(numeroConta, conta));
    }
}
