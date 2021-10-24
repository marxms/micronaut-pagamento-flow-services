package micronaut.orch.pagamento.configuration;

import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties("app.url")
public interface EndpointsConfiguration {
    String getContacorrente();
    String getPagamento();
}
