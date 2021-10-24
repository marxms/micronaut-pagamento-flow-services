package micronaut.orch.pagamento.camel;

import com.rabbitmq.client.ConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpComponent;
import org.apache.camel.component.rabbitmq.RabbitMQComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelContextWrapper {

	   private final CamelContext context;

	    public CamelContextWrapper(ConnectionFactory connectionFactory, RouteBuilder... routes) throws Exception {

	        this.context = new DefaultCamelContext();
			RabbitMQComponent rabbitComponent = new RabbitMQComponent();
			rabbitComponent.setConnectionFactory(connectionFactory);
			rabbitComponent.setSkipQueueBind(true);
			rabbitComponent.setSkipQueueDeclare(true);
			rabbitComponent.setDeclare(false);
			HttpComponent httpComponent = new HttpComponent();
	        this.context.addComponent("rabbitmq", rabbitComponent);
	        this.context.addComponent("http", httpComponent);
	        this.context.setAllowUseOriginalMessage(true);
	        this.context.getGlobalOptions().put("CamelJacksonEnableTypeConverter", "true");
	        this.context.getGlobalOptions().put("CamelJacksonTypeConverterToPojo", "true");

	        for (RouteBuilder route :routes) {
	            this.context.addRoutes(route);
	        }
	        this.context.start();
	    }

	    public ProducerTemplate createProducerTemplate() {
	        return this.context.createProducerTemplate();
	    }

	    public ConsumerTemplate consumerTemplate() {
	        return this.context.createConsumerTemplate();
	    }

}
