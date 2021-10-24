package micronaut.orch.pagamento.common.constants;

public class RouterConstants {

	// ROUTES IDs
	public static final String ID_ROTA_SOLICITAR_PAGAMENTO_ASYNC = "route_solicitar_pagamento_async";
	public static final String ID_ROTA_PROCESSAR_PAGAMENTO_ASYNC = "route_processar_pagamento_async";
	public static final String ID_ROTA_DEBITAR_PAGAMENTO_CORE = "route_debitar_pagamento_core";
	public static final String ID_ROTA_SOLICITAR_PAGAMENTO_SYNC = "route_solicitar_pagamento_sync";

	public static final String SOLICITAR_PAGAMENTO_ASYNC_FLOW = "direct:solicitarPagamentoAsyncFlow";
	public static final String PROCESSAR_PAGAMENTO_CORE_FLOW = "direct:processarPagamentoCoreFlow";
	public static final String SOLICITAR_PAGAMENTO_SYNC_FLOW = "direct:solicitarPagamentoSyncFlow";
	public static final String RABBIT_COMPONENT = "rabbitmq:";
	public static final String QUEUE = "queue";
	public static final String AUTO_DELETE_FALSE = "autoDelete=false";
	// EXCHANGE
    public static final String EXCHANGE_PAGAMENTO = "pagamentoExchange";

	// QUEUE
    public static final String QUEUE_SOLICITAR_PAGAMENTO = "queue.solicitarPagamento";
    public static final String QUEUE_RESPOSTA_PAGAMENTO = "queue.respostaPagamento";
	public static final String QUEUE_IN = "queue.input";
	public static final String QUEUE_OUT = "queue.output";
	public static final String QUEUE_PROCESS = "queue.process";



    //PROPERTIES
	public static final String CONTA_PAYLOAD = "contaPayload";
	public static final String INITIAL_PAYLOAD = "initialPayload";
}
