package micronaut.orch.pagamento.common.exception;

import io.micronaut.http.HttpStatus;
import lombok.Getter;

@Getter
public class BaseException extends Exception {

    private String message;

    private HttpStatus httpStatus;


    public void setMessage(String message) {
        this.message = message;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
