package micronaut.orch.pagamento;

import io.micronaut.runtime.Micronaut;
import org.apache.catalina.LifecycleException;

import javax.servlet.ServletException;

public class Application {

    public static void main(String[] args) throws LifecycleException, ServletException {
        Micronaut.build(args)
                .eagerInitSingletons(true)
                .mainClass(Application.class)
                .start();
    }
}
