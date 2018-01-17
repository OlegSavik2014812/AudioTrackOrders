package com.audioord.dao;

import com.audioord.model.audio.Track;

import java.net.URI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TrackDAO extends BaseEntityDao<Track, Long> {
  private static final String SQL_GET_ALL = "select count(id) from track";
  private static final String SQL_GET_BEST_SELLING_TRACKS =
  "select track.track, track.artist, track.album , track.popularity , track.uri , track.price, track.duration from track,trackorder where track.Id = trackorder.IdTrack group by track.Track order by count(trackorder.IdTrack) desc limit ?, ?";

  private static final String SQL_GET_BRAND_NEW_TRACK =
  "select track, artist, album , popularity , uri , price, duration from track order by id desc limit ?, ?";

  private static final String SQL_GET_MOST_POPULAR_TRACKS =
  "select track, artist, album , popularity , uri , price, duration from track order by Popularity desc limit ?, ?";
  private static final String SQL_GET_USER_ORDERED_TRACKS = "select track.Track, track.artist, track.album , track.popularity , track.uri , track.price, track.duration from track,purchase,user,trackorder where track.Id = purchase.IdUser and trackorder.IdPurchase = purchase.Id and track.Id = trackorder.IdTrack and user.UserName = ? order by Popularity desc";

  private EntityMapper<Track> mapper =
  new EntityMapper<Track>() {
    @Override
    public Track parse(ResultSet rs) throws SQLException {
      Track track = new Track(rs.getString(1), rs.getString(2), rs.getString(3));
      track.setPopularity(rs.getInt(4));
      track.setUri(URI.create(rs.getString(5)));
      track.setPrice(rs.getInt(6));
      track.setDuration(rs.getLong(7));
      return track;
    }

    @Override
    public void write(PreparedStatement st, Track entity) throws SQLException {
    }
  };

  @Override
  public Track getById(Long id) throws DAOException {
    return null;
  }

  @Override
  public Track update(Track entity) throws DAOException {
    return null;
  }

  @Override
  public boolean delete(Long id) throws DAOException {
    return false;
  }

  @Override
  public boolean create(Track entity) throws DAOException {
    return false;
  }

  public List<Track> getMostPopularTracks(int page, int count) throws DAOException {
    return findAll(mapper, SQL_GET_MOST_POPULAR_TRACKS, page, count);
  }

  public List<Track> getBestSellingTracks(int page, int count) throws DAOException {
    return findAll(mapper, SQL_GET_BEST_SELLING_TRACKS, page, count);
  }

  public List<Track> getBrandNewTracks(int page, int count) throws DAOException {
    return findAll(mapper, SQL_GET_BRAND_NEW_TRACK, page, count);
  }

  public List<Track> getUserTracks(Object... params) throws DAOException {
    return findAll(mapper, SQL_GET_USER_ORDERED_TRACKS, params);
  }

  public int countTracks() {
    return count(SQL_GET_ALL);
  }
}
