package com.audioord.model.audio;

import com.audioord.model.Entity;
import com.audioord.web.cart.CartItem;

import java.sql.Time;
import java.util.Objects;

public class Track extends Entity<Long> implements CartItem {

  private String name;
  private String artist;
  private String album;
  private int popularity;
  private double price;
  private String uri;
  private Time duration;

  public Track() {
  }

  public Track(String name, String artist) {
    this.name = name;
    this.artist = artist;
  }

  public Time getDuration() {
    return duration;
  }

  public void setDuration(Time duration) {
    this.duration = duration;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getArtist() {
    return artist;
  }

  public void setArtist(String artist) {
    this.artist = artist;
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

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  @Override
  public Long getItemId() {
    return getId();
  }

  @Override
  public String getDisplayName() {
    return String.format("%s - %s: %s", getName(), getArtist(), getAlbum());
  }

  @Override
  public double getCost() {
    return getPrice();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Track)) return false;
    if (!super.equals(o)) return false;
    Track track = (Track) o;
    return popularity == track.popularity &&
    Double.compare(track.price, price) == 0 &&
    Objects.equals(name, track.name) &&
    Objects.equals(artist, track.artist) &&
    Objects.equals(album, track.album) &&
    Objects.equals(uri, track.uri) &&
    Objects.equals(duration, track.duration);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), name, artist, album, popularity, price, uri, duration);
  }

  @Override
  public String toString() {
    return "Track{" +
    "name='" + name + '\'' +
    ", artist='" + artist + '\'' +
    ", album='" + album + '\'' +
    ", popularity=" + popularity +
    ", price=" + price +
    ", uri='" + uri + '\'' +
    ", duration=" + duration +
    '}';
  }
}
