package com.cts.spotify.music.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @JsonProperty("added_at")
    private String addedAt;

    @JsonProperty("track")
    private Track track;



    // Getters and setters
}