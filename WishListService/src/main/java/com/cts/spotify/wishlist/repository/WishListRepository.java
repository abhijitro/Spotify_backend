package com.cts.spotify.wishlist.repository;

import com.cts.spotify.wishlist.model.WishList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WishListRepository extends MongoRepository<WishList, String> {
    void deleteTrackByUsernameAndTracksId(String username, String trackId);
}
