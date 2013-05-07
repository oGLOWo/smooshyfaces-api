package org.lacassandra.smooshyfaces.api.servlets;

import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Stage;
import org.jboss.resteasy.plugins.server.servlet.ResteasyBootstrap;
import org.jboss.resteasy.spi.Registry;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.lacassandra.smooshyfaces.guice.modules.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a custom implementation of org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener.
 * It doesn't extend the context listener from the resteasy-guice plugin because its methods are private so we
 * can't customize the behavior.
 * This removes the scanning of the context (web.xml) so that you configure everything in java.
 */
public class SmooshyFacesGuiceResteasyBootstrapServletContextListener extends ResteasyBootstrap implements ServletContextListener {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected Injector injector;

    public void contextInitialized(final ServletContextEvent event) {
        super.contextInitialized(event);
        final ServletContext context = event.getServletContext();
        final Registry registry = (Registry) context.getAttribute(Registry.class.getName());
        final ResteasyProviderFactory providerFactory = (ResteasyProviderFactory) context.getAttribute(ResteasyProviderFactory.class.getName());
        final SmooshyFacesModuleProcessor processor = new SmooshyFacesModuleProcessor(registry, providerFactory);
        final List<Module> modules = getModules(context);
        final Stage stage = getStage(context);
        if (stage == null) {
            injector = processor.process(modules);
        }
        else {
            injector = processor.process(stage, modules);
        }
    }

    protected Stage getStage(ServletContext context) {
        return Stage.PRODUCTION;
    }

    protected List<Module> getModules(final ServletContext context) {
        return new ArrayList<Module>() {{
            add(new RestEasyModule());
            add(new LocalSettingsModule());
            add(new PersistenceModule());
            add(new AnalyticsModule());
            add(new EventBusModule());
            add(new SLF4JLoggingModule());
        }};
    }

    public void contextDestroyed(final ServletContextEvent event) {

    }

    private String inputStreamToString(InputStream stream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder buffer = new StringBuilder();
        String currentLine = null;
        while ((currentLine = reader.readLine()) != null) {
            buffer.append(currentLine);
        }
        return buffer.toString();
    }

}
