package org.lacassandra.smooshyfaces.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class APIError {
    final Logger logger = LoggerFactory.getLogger(APIError.class);

    private String details;
    private APIErrorCode code = APIErrorCode.UNKNOWN;

    public APIError() {
        super();
    }

    public APIError(String details) {
        super();
        this.details = details;
    }

    @XmlElement
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @XmlElement
    public APIErrorCode getCode() {
        return code;
    }

    public void setCode(APIErrorCode code) {
        this.code = code;
    }
}
