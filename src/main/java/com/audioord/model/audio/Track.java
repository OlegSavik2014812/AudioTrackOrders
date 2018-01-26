package com.audioord.model.audio;

import com.audioord.model.Entity;
import com.audioord.web.cart.CartItem;

public class Track extends Entity<Long> implements CartItem {

  private String name;
  private String artist;
  private String album;
  private int popularity;
  private double price;
  private String uri;
  private double duration;

  public Track(String name, String artist) {
    this.name = name;
    this.artist = artist;
  }

  public double getDuration() {
    return duration;
  }

  public void setDuration(double duration) {
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
    return getName();
  }

  @Override
  public double getCost() {
    return getPrice();
  }
}
