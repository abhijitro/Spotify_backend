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
public class Album {
    @JsonProperty("album_type")
    private String albumType;

    @JsonProperty("artists")
    private List<Artist> artists;

    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;

    @JsonProperty("id")
    @Id
    private String id;

    @JsonProperty("images")
    private List<Image> images = new ArrayList<>();

    @JsonProperty("name")
    private String name;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("type")
    private String type;

    public void setUri(String s) {
    }
}