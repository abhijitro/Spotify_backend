package com.cts.spotify.music.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddedBy {

    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;

    @JsonProperty("href")
    private String href;

    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;

    @JsonProperty("uri")
    private String uri;

}
