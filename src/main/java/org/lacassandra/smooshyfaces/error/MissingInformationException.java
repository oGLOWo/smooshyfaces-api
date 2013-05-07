package org.lacassandra.smooshyfaces.error;

public class MissingInformationException extends APIException {
    public MissingInformationException(String details) {
        super(details, APIErrorCode.INVALID_DATA);
    }

    public MissingInformationException(String details, APIErrorCode code) {
        super(details, code);
    }
}
