 package org.lacassandra.smooshyfaces.api.exceptionmappers;

 import org.lacassandra.smooshyfaces.error.APIError;
 import org.lacassandra.smooshyfaces.error.APIException;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;

 import javax.ws.rs.WebApplicationException;
 import javax.ws.rs.core.Response;
 import javax.ws.rs.ext.ExceptionMapper;
 import javax.ws.rs.ext.Provider;
 import java.util.HashMap;
 import java.util.Map;

 /**
  *
  */
 @Provider
 public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {

     final Logger logger = LoggerFactory.getLogger(WebApplicationExceptionMapper.class);

     public Response toResponse(WebApplicationException e) {
         logger.error("WebApplicationException [CAUGHT]", e);
         if (e instanceof APIException) {
             return e.getResponse();
         }

         Response currentResponse = e.getResponse();
         Object currentEntity = currentResponse.getEntity();
         APIError apiError = null;

         if (currentEntity instanceof APIError) {
             apiError = (APIError)currentEntity;
         }
         else {
             String details = currentEntity != null ? currentEntity.toString() : e.getMessage();
             apiError = new APIError(details);
         }

         Map<String, APIError> result = new HashMap<String, APIError>();
         result.put("error", apiError);

         return Response.status(currentResponse.getStatus()).entity(result).build();
     }
 }
