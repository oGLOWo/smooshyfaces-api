package org.lacassandra.smooshyfaces.api.resolvers;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.lacassandra.smooshyfaces.serializers.jackson.TimeUUIDSerializer;

import javax.ws.rs.Produces;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
@Produces({"application/*+json", "text/json"})
public class JacksonContextResolver implements ContextResolver<ObjectMapper>{
    private ObjectMapper mapper;

    public JacksonContextResolver() {
        Module module = new SimpleModule("TypeThisJacksonModule").addSerializer(new TimeUUIDSerializer());
        this.mapper = new ObjectMapper()
                .registerModule(module);

    }

    @Override
    public ObjectMapper getContext(Class<?> aClass) {
        return mapper;
    }
}
