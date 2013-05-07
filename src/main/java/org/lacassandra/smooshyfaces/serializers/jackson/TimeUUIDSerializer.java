package org.lacassandra.smooshyfaces.serializers.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.UUID;

public class TimeUUIDSerializer extends StdSerializer<UUID> {
    public TimeUUIDSerializer() {
        super(UUID.class);
    }

    @Override
    public void serialize(UUID value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        jgen.writeString(value.toString());
    }
}
