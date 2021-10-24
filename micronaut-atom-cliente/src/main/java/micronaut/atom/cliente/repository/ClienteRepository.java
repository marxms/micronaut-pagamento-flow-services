package micronaut.atom.cliente.repository;

import com.mongodb.reactivestreams.client.MongoCollection;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import micronaut.atom.cliente.configuration.DatabaseFactory;
import micronaut.atom.cliente.domain.Cliente;


import javax.inject.Singleton;

import static com.mongodb.client.model.Filters.eq;

@Singleton
public class ClienteRepository {

    private final DatabaseFactory databaseFactory;

    public ClienteRepository(DatabaseFactory databaseFactory) {
        this.databaseFactory = databaseFactory;
    }

    public Single<Cliente> insertCustomer(Cliente cliente) {
        return Single.fromPublisher(getCollection().insertOne(cliente))
                .map(result -> cliente);
    }

    public Maybe<Cliente> findClienteByNumeroDocumento(String numeroDocumento) {
        return Flowable.fromPublisher(
                getCollection()
                        .find(eq("numeroDocumento", numeroDocumento))
                        .limit(1)
        ).firstElement();
    }

    private MongoCollection getCollection() {
        return databaseFactory.getCollection("cliente", Cliente.class);
    }
}
