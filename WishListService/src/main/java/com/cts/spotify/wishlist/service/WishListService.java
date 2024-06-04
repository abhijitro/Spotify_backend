package com.cts.spotify.wishlist.service;

import com.cts.spotify.wishlist.dto.TrackDto;
import com.cts.spotify.wishlist.dto.WishListDto;

public interface WishListService {
    WishListDto getUserWishlist(String username);

    TrackDto saveTrackToWishlist(String username, TrackDto trackDto);

    String deleteTrackByUsernameAndTrackId(String username, String trackId);
}
