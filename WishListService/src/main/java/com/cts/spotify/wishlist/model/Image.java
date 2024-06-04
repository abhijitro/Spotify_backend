package com.cts.spotify.wishlist.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Image {
    @JsonProperty("height")
    private Integer height;
    @JsonProperty("url")
    private String url;
    @JsonProperty("width")
    private Integer width;

    // Getters and setters
}