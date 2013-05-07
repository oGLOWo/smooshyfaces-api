package org.lacassandra.smooshyfaces.api.interceptors;

import org.apache.commons.lang.StringUtils;
import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.AcceptedByMethod;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;
import org.lacassandra.smooshyfaces.api.NeedsAuthentication;
import org.lacassandra.smooshyfaces.entity.SmooshyFacesSession;
import org.lacassandra.smooshyfaces.entity.UserSession;
import org.lacassandra.smooshyfaces.error.APIError;
import org.lacassandra.smooshyfaces.guice.annotations.Cassandra;
import org.lacassandra.smooshyfaces.persistence.DAOFactory;
import org.lacassandra.smooshyfaces.persistence.PersistenceException;
import org.lacassandra.smooshyfaces.persistence.UserSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;
import java.util.Map;

@Provider
@ServerInterceptor
public class UserAuthenticatedInterceptor implements PreProcessInterceptor, AcceptedByMethod {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    @Cassandra
    protected DAOFactory daoFactory;

    @SuppressWarnings("rawtypes")
    @Override
    public boolean accept(Class declaring, Method method) {
        return method.isAnnotationPresent(NeedsAuthentication.class);
    }

    protected boolean sessionFoundForCookie(final Cookie sessionCookie) {
        UserSessionDAO sessionDao = daoFactory.createUserSessionDAO();
        UserSession session = sessionDao.findByToken(sessionCookie.getValue());
        return session != null && session.getUser() != null && !session.getUser().getDeleted();
    }

    @Override
    public ServerResponse preProcess(HttpRequest request, ResourceMethod method) throws Failure,
            WebApplicationException {
        try {
            Map<String, Cookie> cookies = request.getHttpHeaders().getCookies();
            for (Map.Entry<String, Cookie> entry : cookies.entrySet()) {
                logger.debug("KKKKKKKOOOOOOOOKKKKKKIIIIIIIEEEEEESSSSSSS!!!! {} | {} = {} @ {}",
                        entry.getKey(),
                        entry.getValue().getName(),
                        entry.getValue().getValue(),
                        entry.getValue().getPath());
            }

            Cookie sessionCookie = cookies.get(SmooshyFacesSession.COOKIE_NAME);

            if (sessionCookie == null || StringUtils.isEmpty(sessionCookie.getValue()) || !sessionFoundForCookie(sessionCookie)) {
                ServerResponse response = new ServerResponse();
                response.setEntity(new APIError(SmooshyFacesSession.COOKIE_NAME + " is required"));
                response.setStatus(Status.UNAUTHORIZED.getStatusCode());
                return response;
            }

            return null;

        }
        catch (PersistenceException e) {
            ServerResponse response = new ServerResponse();
            response.setEntity(new APIError(e.getMessage()));
            response.setStatus(Status.INTERNAL_SERVER_ERROR.getStatusCode());
            return response;
        }
        finally {

        }
    }
}
