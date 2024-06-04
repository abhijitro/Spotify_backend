package com.cts.spotify.wishlist.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Artist {
    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;

    @JsonProperty("id")
    @Id
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type;

    public void setUri(String s) {
    }
}