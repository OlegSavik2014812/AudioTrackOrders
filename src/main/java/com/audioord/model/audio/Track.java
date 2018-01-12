package com.audioord.model.audio;

import com.audioord.model.Entity;

import java.net.URI;
import java.util.List;

public class Track extends Entity<Long> {

  private String name;
  private List<String> artists;
  private String album;
  private int popularity;
  private long durationMs;
  private URI uri;

  public Track(String name, List<String> artists, String album) {
    this.name = name;
    this.artists = artists;
    this.album = album;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getArtists() {
    return artists;
  }

  public void setArtists(List<String> artists) {
    this.artists = artists;
  }

  public String getAlbum() {
    return album;
  }

  public void setAlbum(String album) {
    this.album = album;
  }

  public int getPopularity() {
    return popularity;
  }

  public void setPopularity(int popularity) {
    this.popularity = popularity;
  }

  public long getDurationMs() {
    return durationMs;
  }

  public void setDurationMs(long durationMs) {
    this.durationMs = durationMs;
  }

  public URI getUri() {
    return uri;
  }

  public void setUri(URI uri) {
    this.uri = uri;
  }
}
