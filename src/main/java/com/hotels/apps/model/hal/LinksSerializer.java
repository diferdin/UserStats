package com.hotels.apps.model.hal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

public class LinksSerializer extends JsonSerializer<Map<String, URI>> {

    @Override
    public void serialize(Map<String, URI> value, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeStartObject();
        for (Map.Entry<String, URI> entry : value.entrySet()) {
            generator.writeFieldName(entry.getKey());
            generator.writeStartObject();
            generator.writeStringField("href", link(entry.getValue()));
            generator.writeEndObject();
        }
        generator.writeEndObject();
    }

    private static String link(URI uri) {
        String path = uri.getPath();
        if (path.startsWith("/api/")) {
            path = path.substring(5);
        } else if (path.startsWith("/")) {
            path = path.substring(1);
        }

        return path + (uri.getQuery() == null ? "" : ("?" + uri.getQuery()));
    }

}
