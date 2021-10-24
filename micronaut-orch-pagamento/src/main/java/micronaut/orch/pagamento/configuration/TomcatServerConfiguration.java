package micronaut.orch.pagamento.configuration;

import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties("server")
public interface TomcatServerConfiguration {

    public Integer getPort();

}
