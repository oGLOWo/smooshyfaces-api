package org.lacassandra.smooshyfaces.guice.modules;


import com.google.inject.servlet.ServletModule;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import org.lacassandra.smooshyfaces.api.PicturesResource;
import org.lacassandra.smooshyfaces.api.UsersResource;
import org.lacassandra.smooshyfaces.api.exceptionmappers.WebApplicationExceptionMapper;
import org.lacassandra.smooshyfaces.api.interceptors.*;
import org.lacassandra.smooshyfaces.api.resolvers.JacksonContextResolver;
import org.lacassandra.smooshyfaces.api.servlets.SmooshyFacesAPIServlet;
import org.lacassandra.smooshyfaces.api.servlets.filters.SetCharacterEncodingFilter;

import java.util.HashMap;
import java.util.Map;

public class RestEasyModule extends ServletModule {
    private static Map<String, String> initParams = new HashMap<>();

    private HttpServletDispatcher dispatcher = new SmooshyFacesAPIServlet();

    @Override
    protected void configureServlets() {
        bind(UsersResource.class);
        bind(PicturesResource.class);
        bind(WebApplicationExceptionMapper.class);
        bind(AddSessionCookieInterceptor.class);
        bind(RemoveSessionCookieInterceptor.class);
        bind(UserAuthenticatedInterceptor.class);
        bind(AddUTF8CharsetInterceptor.class);
        bind(SetDefaultInputPartContentTypeInterceptor.class);
        bind(JacksonContextResolver.class);

        filter("/*").through(new SetCharacterEncodingFilter("UTF-8"));
        serve("/*").with(dispatcher, initParams);
    }
}
