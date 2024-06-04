package com.cts.spotify.wishlist.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Track {

    @JsonProperty("album")
    private Album album;

    @JsonProperty("artists")
    private List<Artist> artists = new ArrayList<>();

    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;

    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("preview_url")
    private String previewUrl;

    @JsonProperty("type")
    private String type;

    public void setUri(String s) {
    }
}
