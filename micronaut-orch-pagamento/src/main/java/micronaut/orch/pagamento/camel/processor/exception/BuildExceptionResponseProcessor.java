package micronaut.orch.pagamento.camel.processor.exception;

import micronaut.orch.pagamento.common.exception.BaseException;
import micronaut.orch.pagamento.common.exception.ErrorResponseRepresentation;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class BuildExceptionResponseProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        BaseException cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, BaseException.class);
        exchange.getMessage().setHeader(Exchange.HTTP_RESPONSE_CODE, cause.getHttpStatus().getCode());
        exchange.getMessage().setBody(
                ErrorResponseRepresentation.builder()
                        .message(cause.getMessage())
                        .status(cause.getHttpStatus().getCode())
                        .build());
    }
}
