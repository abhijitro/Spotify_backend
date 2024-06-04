package com.cts.spotify.wishlist.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.cts.spotify.wishlist.model.Artist;
import com.cts.spotify.wishlist.model.ExternalUrls;
import com.cts.spotify.wishlist.model.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumDto {
    @JsonProperty("album_type")
    private String albumType;

    @JsonProperty("artists")
    private List<Artist> artists = new ArrayList<>();

    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;


    @JsonProperty("id")
    private String id;

    @JsonProperty("images")
    private List<Image> images;

    @JsonProperty("name")
    private String name;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("type")
    private String type;

}