package com.cts.spotify.wishlist.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;
@Document("wishlist")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishList {

    @Id
    private String username;
    private List<Track> tracks = new ArrayList<>();
}
