package com.hotels.apps.model.hal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.ImmutableMap;

import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Map;

abstract class HalWrapper {

    @JsonProperty(value = "_links")
    @JsonSerialize(using = LinksSerializer.class)
    private final Map<String, URI> links;

    HalWrapper(UriInfo uriInfo) {
        this.links = ImmutableMap.of("self", uriInfo.getRequestUri());
    }
}
