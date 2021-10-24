package micronaut.orch.pagamento.configuration;

import io.micronaut.context.event.BeanCreatedEvent;
import io.micronaut.context.event.BeanCreatedEventListener;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import java.io.File;

@Singleton
public class TomcatServerCustomizer implements BeanCreatedEventListener<Tomcat> {
    private final TomcatServerConfiguration tomcatConfiguration;
    private static String WEB_APP_DIR = "src/main/webapp/";

    @Inject
    public TomcatServerCustomizer(TomcatServerConfiguration tomcatConfiguration) {
        this.tomcatConfiguration = tomcatConfiguration;
    }

    @Override
    public Tomcat onCreated(BeanCreatedEvent<Tomcat> event) {
        String webappDirLocation = WEB_APP_DIR;
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(tomcatConfiguration.getPort());
        try {
            StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        } catch (ServletException e) {
            e.printStackTrace();
        }
        System.out.println("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());
        try {
            tomcat.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
        tomcat.getServer().await();
        return tomcat;
    }
}