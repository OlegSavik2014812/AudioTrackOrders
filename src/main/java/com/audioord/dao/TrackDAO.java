package com.audioord.dao;

import com.audioord.model.audio.Track;
import com.audioord.model.order.OrderStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TrackDAO extends BaseEntityDao<Track, Long> {
  private static final String SQL_GET_ALL = "select count(id) from track";
  private static final String SQL_GET_BEST_SELLING_TRACKS =
  "select track.track, track.artist, track.album , track.popularity , track.uri , track.price, track.duration,track.id from track,trackorder where track.Id = trackorder.IdTrack group by track.Track order by count(trackorder.IdTrack) desc limit ?, ?";

  private static final String SQL_GET_BRAND_NEW_TRACK =
  "select track, artist, album , popularity , uri , price, duration,track.id from track order by id desc limit ?, ?";

  private static final String SQL_GET_MOST_POPULAR_TRACKS =
  "select track, artist, album , popularity , uri , price, duration,track.id from track order by Popularity desc limit ?, ?";
  private static final String SQL_GET_ALL_USER_ORDERED_TRACKS = "select track.Track, track.artist, track.album , track.popularity , track.uri , track.price, track.duration ,track.id from track,purchase,trackorder,user where Track.Id = trackorder.IdTrack and trackorder.IdPurchase = purchase.Id and purchase.IdUser = user.Id and user.UserName = ? order by Popularity desc";
  private static final String SQL_GET_USER_TRACKS = "select track.Track, track.artist, track.album , track.popularity , track.uri , track.price, track.duration ,track.id from track,purchase,trackorder,user where Track.Id = trackorder.IdTrack and trackorder.IdPurchase = purchase.Id and purchase.IdUser = user.Id and user.UserName =? and purchase.Status=? order by Popularity desc";
  private static final String SQL_ADD_TRACK = "insert into Track(Track,Artist, Album, Popularity,URI,Price,Duration)values(?,?,?,?,?,?,?)";
  private static final String SQL_SEARCH_BY_TRACK_NAME = "select track.Track, track.artist, track.album , track.popularity , track.uri , track.price, track.duration, track.id from track where Track = ?";
  private static final String SQL_ADD_ORDERED_TRACK = "insert into TrackOrder(IdTrack,IdPurchase)values(?,?)";

  private final EntityMapper<Track> mapper =
  new EntityMapper<Track>() {
    @Override
    public Track parse(ResultSet rs) throws SQLException {
      Track track = new Track(rs.getString(1), rs.getString(2));
      track.setAlbum(rs.getString(3));
      track.setPopularity(rs.getInt(4));
      track.setUri(rs.getString(5));
      track.setPrice(rs.getInt(6));
      track.setDuration(rs.getLong(7));
      track.setId(rs.getLong(8));
      return track;
    }

    @Override
    public void write(PreparedStatement st, Track entity) throws SQLException {
      st.setString(1, entity.getName());
      st.setString(2, entity.getArtist());
      st.setString(3, entity.getAlbum());
      st.setInt(4, entity.getPopularity());
      st.setObject(5, entity.getUri());
      st.setDouble(6, entity.getPrice());
      st.setDouble(7, entity.getDuration());
    }
  };

  @Override
  public Track getById(Long id) throws DAOException {
    return getById(id, mapper, SQL_SEARCH_BY_TRACK_NAME);
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
    return update(entity, mapper, SQL_ADD_TRACK);
  }

  public List<Track> getByParam(String param) throws DAOException {
    return findAll(mapper, SQL_SEARCH_BY_TRACK_NAME, param);
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

  public List<Track> getAllUserTracks(String username) throws DAOException {
    return findAll(mapper, SQL_GET_ALL_USER_ORDERED_TRACKS, username);
  }

  public int countTracks() throws DAOException {
    return count(SQL_GET_ALL);
  }

  public List<Track> getUserTracks(String username, OrderStatus status) throws DAOException {
    return findAll(mapper, SQL_GET_USER_TRACKS, username, status.name());
  }


}
