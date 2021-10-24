package micronaut.orch.pagamento.common.exception;


import io.micronaut.http.HttpStatus;
import lombok.Getter;

@Getter
public class SaldoIndisponivelException extends BaseException {

	private ErrorResponseRepresentation response;

	  public SaldoIndisponivelException(HttpStatus httpStatus, String message) {
	    setHttpStatus(httpStatus);
		setMessage(message);
		response = ErrorResponseRepresentation.builder()
				.message(message)
				.status(httpStatus.getCode())
				.build();
	  }

}
