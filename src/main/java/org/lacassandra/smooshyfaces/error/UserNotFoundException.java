package org.lacassandra.smooshyfaces.error;

import javax.ws.rs.core.Response.Status;

public class UserNotFoundException extends APIException {
    public UserNotFoundException(String details) {
        super(Status.NOT_FOUND.getStatusCode(), details, APIErrorCode.ACCOUNT_DOES_NOT_EXIST);
    }

    public UserNotFoundException(String details, APIErrorCode code) {
        super(Status.NOT_FOUND.getStatusCode(), details, code);
    }
}
