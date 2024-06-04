package com.cts.spotify.wishlist.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.cts.spotify.wishlist.model.Album;
import com.cts.spotify.wishlist.model.Artist;
import com.cts.spotify.wishlist.model.ExternalUrls;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackDto {

    @JsonProperty("album")
    private Album album;

    @JsonProperty("artists")
    private List<Artist> artists = new ArrayList<>();

    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("preview_url")
    private String previewUrl;

    @JsonProperty("type")
    private String type;

    public void setHref(String s) {
    }

    public void setUri(String s) {
    }
}
