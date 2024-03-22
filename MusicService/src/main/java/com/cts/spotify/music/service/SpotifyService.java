package com.cts.spotify.music.service;

import com.cts.spotify.music.dto.SpotifyPlaylist;
import com.cts.spotify.music.dto.Track;
import com.cts.spotify.music.model.JwtToken;

public interface SpotifyService {
    String getSpotifyAccessToken();

    SpotifyPlaylist getBillBoard100Playlist();

    SpotifyPlaylist getTodayTopHitsPlaylist();

    SpotifyPlaylist getDiscoverWeeklyPlaylist();

    Track getTrack(String trackId);

    Object search(String query);

}
