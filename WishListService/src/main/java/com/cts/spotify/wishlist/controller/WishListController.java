package com.cts.spotify.wishlist.controller;

import com.cts.spotify.wishlist.dto.TrackDto;
import com.cts.spotify.wishlist.dto.WishListDto;
import com.cts.spotify.wishlist.exception.UnAuthorizedException;
import com.cts.spotify.wishlist.service.AuthService;
import com.cts.spotify.wishlist.service.WishListService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1.0/wishList")
public class WishListController {

    private final WishListService wishListService;

    private final AuthService authService;

    @Autowired
    public WishListController(WishListService wishListService, AuthService authService) {
        this.wishListService = wishListService;
        this.authService = authService;
    }

    @Cacheable(value = "wishlist", key = "#username")
    @Operation(summary = "Get User's Favorite Playlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Top Hits  Playlist",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = WishListDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Wishlist not found",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized user",
                    content = @Content) })
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/getUserWishList")
    public ResponseEntity<Object> getUserWishLisl(@RequestHeader("Authorization") String token, @RequestParam String username){
        log.trace("Controller getUserWishList: "+username);
        Map<String,String> info= authService.validateToken(token);
        log.info(info+"------inside method getUserWishList-----");
        if(info.containsKey(username)) {
            log.error(token + "inside method getUserWishList-----__---");
            return new ResponseEntity<>(wishListService.getUserWishlist(username), HttpStatus.OK);

        }
        throw new UnAuthorizedException("Please check User details");

    }

    @Caching(evict = {
            @CacheEvict(value = "wishlist", key = "#username"),
            @CacheEvict(value = "wishlist", key = "'all'")
    })
    @Operation(summary = "Save track to User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Track saved to favorite list",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TrackDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Track not found",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized user",
                    content = @Content) })
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/saveTrackToWishlist")
    //@CircuitBreaker(name="authenticationWishlistBreaker"
    //,fallbackMethod="saveTrackToWishListFallback")
    public ResponseEntity<Object> saveTrackToWishlist(@RequestHeader("Authorization") String token, @RequestParam String username, @RequestBody TrackDto trackDto){
        log.trace("Controller saveTrackToWishList: "+username);
        Map<String,String> info= authService.validateToken(token);
        log.info(info+"------inside method saveTrackToWishList-----");
        if(info.containsKey(username)) {
            log.error(token + "inside method get all-----__---");
            return new ResponseEntity<>(wishListService.saveTrackToWishlist(username,trackDto),HttpStatus.CREATED);
        }
        throw new UnAuthorizedException("Please check User Id: "+username);

    }

	/*
	 * //creating fallback method for circuitbreaker public ResponseEntity<Object>
	 * saveTrackToWishListFallback(String username,TrackDto trackDto){
	 * //logger.info("fallback is executed because server is down"); return new
	 * ResponseEntity<>(wishListService.saveTrackToWishlist(username,
	 * trackDto),HttpStatus.CREATED);
	 * 
	 * }
	 */
    @Caching(evict = {
            @CacheEvict(value = "wishlist", key = "#username"),
            @CacheEvict(value = "wishlist", key = "'all'")
    })
    @Operation(summary = "Delete track from favorite Playlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Track deleted from favorite",
                    content = { @Content(mediaType = "text/plain",
                            schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "404", description = "Track not found",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized user",
                    content = @Content) })
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/removeTrack")
    public  ResponseEntity<Object> deleteTrackByUsernameAndTrackId(@RequestHeader("Authorization") String token,@RequestParam String username, @RequestParam String trackId){
        log.trace("Controller deleteTrackByUsernameAndTrackId: "+trackId);
        log.trace("Controller getUserWishList: "+username);
        log.info(token+"from front end");
        Map<String,String> info= authService.validateToken(token);
        log.info(info+"------inside method get all-----");
        if(info.containsKey(username)) {
            log.error(token + "inside method get all-----__---");
            return new ResponseEntity<>(wishListService.deleteTrackByUsernameAndTrackId(username, trackId), HttpStatus.OK);
        }
        throw new UnAuthorizedException("Please check User details");

    }

}
