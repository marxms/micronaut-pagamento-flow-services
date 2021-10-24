package micronaut.atom.conta.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.reactivestreams.client.MongoCollection;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;
import micronaut.atom.conta.configuration.DatabaseFactory;
import micronaut.atom.conta.representation.Conta;
import org.bson.Document;

import javax.inject.Singleton;

import java.util.Map;

import static com.mongodb.client.model.Filters.eq;

@Singleton
@Slf4j
public class ContaRepository {

    private final DatabaseFactory databaseFactory;
    private final ObjectMapper objectMapper;

    public ContaRepository(DatabaseFactory databaseFactory, ObjectMapper objectMapper) {
        this.databaseFactory = databaseFactory;
        this.objectMapper = objectMapper;
    }

    public Single<Conta> insertAccount(Conta conta) {
        return Single.fromPublisher(getCollection().insertOne(conta))
                .map(result -> conta);
    }

    public Maybe<Conta> findContaByNumeroConta(String numeroConta) {
        return Flowable.fromPublisher(
                getCollection()
                        .find(eq("numeroConta", numeroConta))
                        .limit(1)
        ).firstElement();
    }

    public Maybe<Conta> updateAccountByNumberoConta(String numeroConta, Conta conta) {
        Map<String, Object> values = objectMapper.convertValue(conta, Map.class);
        return Flowable.fromPublisher(
                getCollection()
                        .updateOne(
                                eq("numeroConta", numeroConta),
                                new Document("$set", values)))
                .firstElement();
    }



    private MongoCollection getCollection() {
        return databaseFactory.getCollection("conta", Conta.class);
    }
}
