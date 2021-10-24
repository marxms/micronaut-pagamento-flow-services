package micronaut.atom.conta.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.reactivestreams.client.MongoCollection;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;
import micronaut.atom.conta.configuration.DatabaseFactory;
import micronaut.atom.conta.representation.Pagamento;

import javax.inject.Singleton;

import static com.mongodb.client.model.Filters.eq;

@Singleton
@Slf4j
public class PagamentoRepository {

    private final DatabaseFactory databaseFactory;
    private final ObjectMapper objectMapper;

    public PagamentoRepository(DatabaseFactory databaseFactory, ObjectMapper objectMapper) {
        this.databaseFactory = databaseFactory;
        this.objectMapper = objectMapper;
    }

    public Single<Pagamento> insertPayment(Pagamento pagamento) {
        return Single.fromPublisher(getCollection().insertOne(pagamento))
                .map(result -> pagamento);
    }

    public Maybe<Pagamento> findPaymentByNumber(String numeroPagamento) {
        return Flowable.fromPublisher(
                getCollection()
                        .find(eq("numeroPagamento", numeroPagamento))
                        .limit(1)
        ).firstElement();
    }


    private MongoCollection getCollection() {
        return databaseFactory.getCollection("pagamento", Pagamento.class);
    }
}
