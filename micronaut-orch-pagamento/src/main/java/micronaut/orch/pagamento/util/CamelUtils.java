package micronaut.orch.pagamento.util;

import org.apache.camel.Message;

public class CamelUtils {

    public static void removeAllCamelHttpHeaders(Message message) {
        message.removeHeaders("CamelHttp*");
    }
}
