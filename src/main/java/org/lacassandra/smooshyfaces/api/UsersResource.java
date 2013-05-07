package org.lacassandra.smooshyfaces.api;

import org.lacassandra.smooshyfaces.api.params.UUIDParam;
import org.lacassandra.smooshyfaces.entity.MeUser;
import org.lacassandra.smooshyfaces.entity.SmooshyFacesSession;
import org.lacassandra.smooshyfaces.entity.User;
import org.lacassandra.smooshyfaces.entity.util.UserSessionUtil;
import org.lacassandra.smooshyfaces.error.APIException;
import org.lacassandra.smooshyfaces.error.UserNotFoundException;
import org.lacassandra.smooshyfaces.guice.annotations.Cassandra;
import org.lacassandra.smooshyfaces.guice.annotations.InjectSLF4JLog;
import org.lacassandra.smooshyfaces.persistence.DAOFactory;
import org.lacassandra.smooshyfaces.persistence.EntityNotFoundException;
import org.lacassandra.smooshyfaces.persistence.UserDAO;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Path("/users")
public class UsersResource {
    @InjectSLF4JLog
    private Logger logger;

    @Inject
    @Cassandra
    protected DAOFactory daoFactory;

    // you should implement a POST method on "/users" to create a new account

    // you should implement a PUT method on "/users/{user-id}" to update a user account

    @GET
    @Path("/me")
    @Produces("application/json")
    @NeedsAuthentication
    public java.util.Map<String, MeUser> getMe(@CookieParam(SmooshyFacesSession.COOKIE_NAME) String sessionId) {
        try {
            User me = UserSessionUtil.getUserFromSession(daoFactory.createUserSessionDAO(), sessionId);

            Map<String, MeUser> result = new HashMap<>();
            result.put("user", MeUser.fromUser(me));
            return result;
        }
        catch (APIException e) {
            throw e;
        }
        catch (Exception e) {
            throw new APIException(e, Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        }
        finally {

        }
    }

    @GET
    @Path("/{user-id}")
    @Produces("application/json")
    public Map<String, User> getUser(@PathParam("user-id") final UUIDParam userIdParam) {
        UUID userId = userIdParam.get();

        try {
            UserDAO userDao = daoFactory.createUserDAO();
            User user = userDao.findById(userId);

            if (user.getDeleted()) {
                throw new UserNotFoundException("User with id " + userId + " does not exist");
            }

            Map<String, User> result = new HashMap<>();
            result.put("user", user);

            return result;
        }
        catch (APIException e) {
            throw e;
        }
        catch (EntityNotFoundException e) {
            throw new UserNotFoundException(e.getMessage());
        }
        catch (Exception e) {
            throw new APIException(e, Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        }
        finally {

        }
    }
}
