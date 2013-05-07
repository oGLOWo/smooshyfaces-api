package org.lacassandra.smooshyfaces.api.interceptors;

import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.interception.AcceptedByMethod;
import org.jboss.resteasy.spi.interception.PostProcessInterceptor;
import org.lacassandra.smooshyfaces.api.RemovesSessionCookie;
import org.lacassandra.smooshyfaces.entity.SmooshyFacesSession;
import org.lacassandra.smooshyfaces.entity.User;
import org.lacassandra.smooshyfaces.error.APIError;
import org.lacassandra.smooshyfaces.error.APIException;
import org.lacassandra.smooshyfaces.persistence.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;
import java.util.Map;

@Provider
@ServerInterceptor
public class RemoveSessionCookieInterceptor implements PostProcessInterceptor, AcceptedByMethod {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Context
    UriInfo uriInfo;

    @Override
    public void postProcess(ServerResponse response) {
        logger.debug("Post processing this response {}", response);

        Object responseEntity = response.getEntity();
        if (!(responseEntity instanceof APIError)) {
            Map<String, User> userMap = (Map<String, User>) response.getEntity();
            try {
                // Need to add expires info
                String cookieValue = String.format(
                                SmooshyFacesSession.COOKIE_NAME + "=deleted; Expires=Thu, 01 Jan 1970 00:00:01 GMT; Path=%s;", uriInfo
                                                .getBaseUri().getPath());
                response.getMetadata().add("Set-Cookie", cookieValue);
            }
            catch (PersistenceException | NullPointerException e) {
                logger.error("Error trying to postProcess", e);
                throw new APIException(e);
            }
        }
    }

    @Override
    @SuppressWarnings("rawtypes")
    public boolean accept(Class declaring, Method method) {
        boolean accept = false;

        if (method.isAnnotationPresent(RemovesSessionCookie.class)) {
            accept = true;
        }

        return accept;
    }
}
