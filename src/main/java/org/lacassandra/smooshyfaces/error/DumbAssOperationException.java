package org.lacassandra.smooshyfaces.error;

import javax.ws.rs.core.Response.Status;

public class DumbAssOperationException extends APIException {
    private static final long serialVersionUID = -452568425830612368L;

    public DumbAssOperationException() {
        super(Status.BAD_REQUEST.getStatusCode(), "Dumb ass operation");
    }

    public DumbAssOperationException(String details) {
        super(Status.BAD_REQUEST.getStatusCode(), details);
    }

    public DumbAssOperationException(String details, APIErrorCode code) {
        super(Status.BAD_REQUEST.getStatusCode(), details, code);
    }
}
