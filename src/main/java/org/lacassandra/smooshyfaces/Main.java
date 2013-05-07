package org.lacassandra.smooshyfaces;

import com.google.inject.servlet.GuiceFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.lacassandra.smooshyfaces.api.servlets.SmooshyFacesGuiceResteasyBootstrapServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.EnumSet;



/**
 *
 * This class launches the web application in an embedded Jetty container.
 * This is the entry point to your application. The Java command that is used for
 * launching should fire this main method.
 *
 */
public class Main {
    private static Filter createGuiceFilter() {
        return new GuiceFilter();
    }

    public static void main(String[] args) throws Exception{
        Logger logger = LoggerFactory.getLogger(Main.class);

        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.setContextPath("/");
        context.addServlet(DefaultServlet.class.getCanonicalName(), "/");
        context.addFilter(new FilterHolder(createGuiceFilter()), "/*", EnumSet.allOf(DispatcherType.class));
        // Holy shit this is one looooooong context listener class name
        context.addEventListener(new SmooshyFacesGuiceResteasyBootstrapServletContextListener());
        server.setHandler(context);

        try {
            server.start();
            logger.info(server.dump());
            server.join();
        }
        catch (Exception e) {
            logger.error("Error in server start/join routine", e);
        }
    }
}
