package com.audioord.model.audio;

import com.audioord.model.Entity;
import com.audioord.web.cart.CartItem;

import java.util.Objects;

/**
 * Class describing object for storing information about track, extends {@link Entity}, implements {@link CartItem}
 */
public class Track extends Entity<Long> implements CartItem {

  private String name;
  private String artist;
  private String album;
  private int popularity;
  private double price;
  private String uri;
  private long duration;

  /**
   * default constructor of {@link Track}
   */
  public Track() {
  }

  /**
   * constructor of {@link Track} with params
   *
   * @param name   required name of track
   * @param artist required artist name of track
   */
  public Track(String name, String artist) {
    this.name = name;
    this.artist = artist;
  }

  /**
   * @return duration of track
   */
  public long getDuration() {
    return duration;
  }

  /**
   * @param duration duration of track
   */
  public void setDuration(long duration) {
    this.duration = duration;
  }

  /**
   * @return name of track
   */
  public String getName() {
    return name;
  }

  /**
   * @param name name of track
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return artist name of track
   */
  public String getArtist() {
    return artist;
  }


  /**
   * @param artist artist name of track
   */
  public void setArtist(String artist) {
    this.artist = artist;
  }

  /**
   * @return album name of track
   */
  public String getAlbum() {
    return album;
  }

  /**
   * @param album album name of track
   */
  public void setAlbum(String album) {
    this.album = album;
  }

  /**
   * @return popularity of the track
   */
  public int getPopularity() {
    return popularity;
  }

  /**
   * @param popularity popularity of the track
   */
  public void setPopularity(int popularity) {
    this.popularity = popularity;
  }

  /**
   * @return price of the track
   */
  public double getPrice() {
    return price;
  }

  /**
   * @param price price of the track
   */
  public void setPrice(double price) {
    this.price = price;
  }

  /**
   * @return URI of the track
   */
  public String getUri() {
    return uri;
  }

  /**
   * @param uri URI of the track
   */
  public void setUri(String uri) {
    this.uri = uri;
  }

  /**
   * Override method of {@link CartItem} parametrized with Long type
   * returns CartItem id
   *
   * @return CartItem id
   */
  @Override
  public Long getItemId() {
    return getId();
  }

  /**
   * Override method of {@link CartItem}
   * format track name, artist name, album name and returns those Strings as one
   *
   * @return formatted information about track
   */
  @Override
  public String getDisplayName() {
    return String.format("%s - %s: %s", getName(), getArtist(), getAlbum());
  }

  /**
   * Override method of {@link CartItem}
   * returns cost of track
   *
   * @return track price
   */
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
