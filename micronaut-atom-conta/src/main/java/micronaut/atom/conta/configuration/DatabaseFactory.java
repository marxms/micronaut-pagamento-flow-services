package micronaut.atom.conta.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
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
                .register("micronaut.atom.conta.representation")
                .register(ObjectIdCodec.class)
                .build();
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(pojoCodecProvider));
        var creds = MongoCredential.createCredential("conta_user",
                "conta_db",
                "conta_pass".toCharArray());
        var settings =
                MongoClientSettings.builder()
                            .credential(creds)
                        .codecRegistry(pojoCodecRegistry)
                        .build();
        return MongoClients.create(settings);
    }
    public <T> MongoCollection<T> getCollection(String collectionName, Class<T> clazz) {
        return this.mongoClient()
                .getDatabase("conta_db")
                .getCollection(collectionName, clazz);
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_ABSENT);
        return objectMapper;
    }
}
