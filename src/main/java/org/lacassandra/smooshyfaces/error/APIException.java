package org.lacassandra.smooshyfaces.error;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.HashMap;
import java.util.Map;

public class APIException extends WebApplicationException {
    private static final long serialVersionUID = -2666589140223870351L;

    private final static int DEFAULT_STATUS_CODE = Status.BAD_REQUEST.getStatusCode();
    private APIError error = new APIError();
    private int status = DEFAULT_STATUS_CODE;

    public APIException() {
        super();
    }

    public APIException(Throwable cause) {
        super(cause);
    }

    public APIException(Throwable cause, int status, String details) {
        super(cause);
        this.status = status;
        error.setDetails(details);
    }

    public APIException(Throwable cause, String details) {
        super(cause);
        error.setDetails(details);
    }

    public APIException(Throwable cause, int status) {
        super(cause);
        this.status = status;
        error.setDetails(cause.getMessage());
    }

    public APIException(int status, String details) {
        super();
        this.status = status;
        error.setDetails(details);
    }

    public APIException(int status, String details, APIErrorCode code) {
        super();
        this.status = status;
        error.setDetails(details);
        error.setCode(code);
    }

    public APIException(Throwable cause, APIErrorCode code) {
        super(cause);
        error.setCode(code);
        error.setDetails(cause.getMessage());
    }

    public APIException(String details) {
        super();
        error.setDetails(details);
    }

    public APIException(String details, APIErrorCode code) {
        super();
        error.setDetails(details);
        error.setCode(code);
    }

    @Override
    public Response getResponse() {
        Map<String, APIError> errorEntity = new HashMap<>();
        errorEntity.put("error", error);
        return Response.status(status).entity(errorEntity).build();
    }

    @Override
    public String getMessage() {
        return error.getDetails();
    }
}
