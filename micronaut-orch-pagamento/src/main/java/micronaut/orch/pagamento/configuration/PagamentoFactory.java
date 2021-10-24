package micronaut.orch.pagamento.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.ConnectionFactory;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import micronaut.orch.pagamento.camel.CamelContextWrapper;
import org.apache.camel.builder.RouteBuilder;

import javax.inject.Inject;

@Factory
public class PagamentoFactory {


    @Inject
    private ObjectMapper mapper;

    @Bean
    public CamelContextWrapper camelContextWrapper(ConnectionFactory connectionFactory, RouteBuilder... routes) throws Exception {
        return new CamelContextWrapper(connectionFactory, routes);
    }

}
