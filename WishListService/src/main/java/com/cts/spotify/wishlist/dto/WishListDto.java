package com.cts.spotify.wishlist.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishListDto {

    private String username;
    private List<TrackDto> tracks = new ArrayList<>();
}
