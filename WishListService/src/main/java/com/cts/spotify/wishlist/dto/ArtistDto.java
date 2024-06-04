package com.cts.spotify.wishlist.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.cts.spotify.wishlist.model.ExternalUrls;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistDto {
    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;


    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type;

}