package org.lacassandra.smooshyfaces.error;

import javax.ws.rs.core.Response.Status;

public class UserNotAuthorizedException extends APIException {
    private static final long serialVersionUID = -1627268965606744065L;

    public UserNotAuthorizedException() {
        super(Status.UNAUTHORIZED.getStatusCode(), "Authenticated session is required for this call");
    }

    public UserNotAuthorizedException(String details) {
        super(Status.UNAUTHORIZED.getStatusCode(), details);
    }

    public UserNotAuthorizedException(Throwable cause) {
        super(cause, Status.UNAUTHORIZED.getStatusCode());
    }

    public UserNotAuthorizedException(String details, APIErrorCode code) {
        super(Status.UNAUTHORIZED.getStatusCode(), details, code);
    }
}
