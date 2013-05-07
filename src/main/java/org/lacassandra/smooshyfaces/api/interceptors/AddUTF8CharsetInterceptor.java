package org.lacassandra.smooshyfaces.api.interceptors;


import org.apache.commons.lang.StringUtils;
import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.interception.AcceptedByMethod;
import org.jboss.resteasy.spi.interception.PostProcessInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;

@ServerInterceptor
@Provider
public class AddUTF8CharsetInterceptor implements PostProcessInterceptor, AcceptedByMethod {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("rawtypes")
    @Override
    public boolean accept(Class declaring, Method method) {
        return true;
    }

    @Override
    public void postProcess(ServerResponse response) {
        String userAgent = Thread.currentThread().getName();
        logger.debug("$$$$$$$$$$$$ YO THREAD NAME IS: {}", userAgent);

        try {
            MediaType mediaType = (MediaType)response.getMetadata().getFirst("Content-Type");
            if (mediaType != null) {
                String contentType = mediaType.getType() + "/" + mediaType.getSubtype();
                if (!StringUtils.isEmpty(contentType)) {
                    logger.debug("The content type is {}", contentType);
                    // Thank you http://refiddle.com/ for making regex tests easy
                    if (contentType.matches("^application/(json|vnd\\.yourcompany\\.yourproduct\\.api-v.*json)$")) {
                        response.getMetadata().remove("Content-Type");
                        response.getMetadata().add("Content-Type", contentType + ";charset=utf-8");
                    }
                }
                else {
                    logger.debug("Blah! content type is empty!!");
                }
            }
        }
        catch (Exception e) {
            logger.error("BOO BOO adding utf8 charset", e);
        }
    }
}
