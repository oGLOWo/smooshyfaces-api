package org.lacassandra.smooshyfaces.api.interceptors;

import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.interception.AcceptedByMethod;
import org.jboss.resteasy.spi.interception.PostProcessInterceptor;
import org.lacassandra.smooshyfaces.api.ProducesSessionCookie;
import org.lacassandra.smooshyfaces.entity.SmooshyFacesSession;
import org.lacassandra.smooshyfaces.entity.User;
import org.lacassandra.smooshyfaces.error.APIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

@Provider
@ServerInterceptor
public class AddSessionCookieInterceptor implements PostProcessInterceptor, AcceptedByMethod {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
    @Context
    UriInfo uriInfo;

    @Override
    public void postProcess(ServerResponse response) {
        Map<String, User> userMap = (Map<String, User>) response.getEntity();
        User user = userMap.get("user");
        try {
            String expires = DATE_FORMAT.format(user.getUserSession().getExpirationDate());
            String cookieValue;
            if (uriInfo.getBaseUri().getHost().contains(".yourserver.com")) {
                cookieValue = String.format("%s=%s; Domain=%s; Path=%s; Expires=%s; HttpOnly", SmooshyFacesSession.COOKIE_NAME, user.getUserSession().getToken(), ".yourserver.com", uriInfo.getBaseUri().getPath(), expires);
            }
            else {
                cookieValue = String.format("%s=%s; Path=%s; Expires=%s; HttpOnly", SmooshyFacesSession.COOKIE_NAME, user.getUserSession().getToken(), uriInfo.getBaseUri().getPath(), expires);
            }

            response.getMetadata().add("Set-Cookie", cookieValue);
        }
        catch (NullPointerException e) {
            throw new APIException(e);
        }
    }

    @Override
    @SuppressWarnings("rawtypes")
    public boolean accept(Class declaring, Method method) {
        boolean accept = false;

        if (method.isAnnotationPresent(ProducesSessionCookie.class)) {
            accept = true;
        }

        return accept;
    }
}
