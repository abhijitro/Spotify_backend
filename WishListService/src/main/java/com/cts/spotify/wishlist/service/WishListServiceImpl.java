package com.cts.spotify.wishlist.service;

import com.cts.spotify.wishlist.dto.TrackDto;
import com.cts.spotify.wishlist.dto.WishListDto;
import com.cts.spotify.wishlist.exception.ResourceNotFoundException;
import com.cts.spotify.wishlist.model.Track;
import com.cts.spotify.wishlist.model.WishList;
import com.cts.spotify.wishlist.repository.WishListRepository;
import io.micrometer.observation.annotation.Observed;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Observed(name = "wishlist.service.impl")
public class WishListServiceImpl implements WishListService {

    private final WishListRepository wishlistRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public WishListServiceImpl(WishListRepository wishlistRepository, ModelMapper modelMapper) {
        this.wishlistRepository = wishlistRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Observed(name = "get.user.wishlist")
    public WishListDto getUserWishlist(String username){
        log.info("Service getUserWishList: "+ username);
        return modelMapper.map(wishlistRepository.findById(username).orElseThrow(() -> new ResourceNotFoundException("Entity not found with ID: " + username)), WishListDto.class);
    }

    @Override
    @Observed(name = "save.track.to.wishlist")
    public TrackDto saveTrackToWishlist(String username, TrackDto trackDto) {
        log.info("Service saveTrackToUsername: "+ username);
        Optional<WishList> wishListOptional = wishlistRepository.findById(username);
        Track track = modelMapper.map(trackDto, Track.class);
        WishList wishList;
        if (wishListOptional.isPresent()) {
            // User's wish list exists, add or update the track
            wishList = wishListOptional.get();
            addOrUpdateTrack(wishList, track);
        } else {
            // User's wish list doesn't exist, create a new wish list
            wishList = new WishList();
            wishList.setUsername(username);
            wishList.setTracks(List.of(track));
        }

        // Save the updated wish list
        WishList save = wishlistRepository.save(wishList);
        log.info(save.toString());
        log.info("Track added "+trackDto.toString());
        return trackDto;
    }

    @Override
    @Observed(name = "delete.track.by.username.and.track.id")
    public String deleteTrackByUsernameAndTrackId(String username, String trackId) {
        log.info("Service deleteTrackByUsername: " + username);

        WishList wishlist = wishlistRepository.findById(username)
                .orElseThrow(() -> new ResourceNotFoundException("Username not found"));

        // Create a new mutable list from the existing tracks
        List<Track> mutableTracks = new ArrayList<>(wishlist.getTracks());

        // Find and remove the track with the specified id
        mutableTracks.removeIf(track -> track.getId().equals(trackId));

        // Update the wishlist with the new list of tracks
        wishlist.setTracks(mutableTracks);

        // Save the updated wishlist
        wishlistRepository.save(wishlist);

        log.info("Service Track deleted with Id: " + trackId);
        return "Track with Id: " + trackId + " deleted.";
    }



    private void addOrUpdateTrack(WishList wishList, Track newTrack) {
        // Check if the track with the same ID already exists in the wish list
        boolean trackExists = wishList.getTracks().stream()
                .anyMatch(track -> track.getId().equals(newTrack.getId()));

        if (!trackExists) {
            // Track doesn't exist, add it to the wish list
            wishList.getTracks().add(newTrack);
        } else {
            // Track already exists, update it (if needed)
            wishList.getTracks().stream()
                    .filter(track -> track.getId().equals(newTrack.getId()))
                    .findFirst()
                    .ifPresent(existingTrack -> {
                        // Update track attributes if needed
                        existingTrack.setName(newTrack.getName());
                        existingTrack.setPreviewUrl(newTrack.getPreviewUrl());
                        existingTrack.setAlbum(newTrack.getAlbum());
                        existingTrack.setExternalUrls(newTrack.getExternalUrls());
                        existingTrack.setArtists(newTrack.getArtists());
                        existingTrack.setType(newTrack.getType());
                        // Update other attributes as needed
                    });
        }
    }
}
