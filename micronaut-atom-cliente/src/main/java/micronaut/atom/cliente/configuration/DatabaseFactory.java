package micronaut.atom.cliente.configuration;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoCollection;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Primary;
import org.bson.codecs.ObjectIdCodec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Factory
public class DatabaseFactory {

    public DatabaseFactory() {
    }

    /**
     * Factory Method for creating a client.
     * @return mongoClient
     */
    @Bean(preDestroy = "close")
    @Primary
    MongoClient mongoClient() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder()
                .register("micronaut.atom.cliente.domain")
                .register(ObjectIdCodec.class)
                .build();
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(pojoCodecProvider));
        var settings =
                MongoClientSettings.builder()
                        .credential(MongoCredential.createCredential("cliente_user","cliente_db", "cliente_pass".toCharArray()))
                        .codecRegistry(pojoCodecRegistry)
                        .build();
        return MongoClients.create(settings);
    }
    public <T> MongoCollection<T> getCollection(String collectionName, Class<T> clazz) {
        return this.mongoClient()
                .getDatabase("cliente_db")
                .getCollection(collectionName, clazz);
    }

}
