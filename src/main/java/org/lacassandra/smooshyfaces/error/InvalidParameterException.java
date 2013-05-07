package org.lacassandra.smooshyfaces.error;

import javax.ws.rs.core.Response.Status;

public class InvalidParameterException extends APIException {
    public InvalidParameterException(String details) {
        super(Status.BAD_REQUEST.getStatusCode(), details);
    }

    public InvalidParameterException(String details, APIErrorCode code) {
        super(Status.BAD_REQUEST.getStatusCode(), details, code);
    }
}
