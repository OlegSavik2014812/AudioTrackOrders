package com.audioord.dao;

import com.audioord.model.audio.Track;
import com.audioord.model.order.OrderStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class TrackDAO extends BaseEntityDao<Track, Long> {

  private static final String SQL_COUNT_ALL =
  "select count(id) from track";

  private static final String SQL_GET_BEST_SELLING_TRACKS =
  "SELECT t.track, t.artist, t.album, t.popularity, t.uri, t.price, t.duration, t.id, (SELECT count(o.Id) FROM Order o WHERE t.Id = o.IdTrack) AS numOrdered FROM track t ORDER BY numOrdered DESC limit ?, ?";

  private static final String SQL_GET_BRAND_NEW_TRACK =
  "select track, artist, album, popularity, uri , price, duration, id from track order by id desc limit ?, ?";

  private static final String SQL_GET_MOST_POPULAR_TRACKS =
  "select track, artist, album, popularity, uri, price, duration, id from track order by Popularity desc limit ?, ?";

  private static final String SQL_GET_TRACK_BY_ID = "select track.Track, track.artist, track.album , track.popularity , track.uri , track.price, track.duration ,track.id from track where track.id=?";

  private static final String SQL_GET_BY_IDS =
  "select track.Track, track.artist, track.album , track.popularity , track.uri , track.price, track.duration ,track.id from track where track.id in ?";

  private static final String SQL_ADD_TRACK =
  "insert into Track(Track,Artist, Album, Popularity,URI,Price,Duration)values(?,?,?,?,?,?,?)";

  private static final String SQL_SEARCH_BY_TRACK_NAME =
  "select track.Track, track.artist, track.album , track.popularity , track.uri , track.price, track.duration, track.id from track where Track like(?)";

  private static final String SQL_COUNT_ALL_USER_TRACKS = "select count(track.Id)\n" +
  "from track,purchase,trackorder,user \n" +
  "where Track.Id = trackorder.IdTrack and trackorder.IdPurchase = purchase.Id \n" +
  "and purchase.IdUser = user.Id and user.UserName =?";

  private static final String SQL_COUNT_USER_TRACK_WITH_STATUS = "select count(track.Id)\n" +
  "from track,purchase,trackorder,user \n" +
  "where Track.Id = trackorder.IdTrack and trackorder.IdPurchase = purchase.Id \n" +
  "and purchase.IdUser = user.Id and user.UserName =? and purchase.Status= ?";


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
    return getById(id, mapper, SQL_GET_TRACK_BY_ID);
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

  public List<Track> findTracks(String trackName) throws DAOException {
    String s = trackName.replaceAll("%", "");
    if (!s.isEmpty()) {
      return findAll(mapper, SQL_SEARCH_BY_TRACK_NAME, s + "%");
    }
    return Collections.emptyList();
  }

  public List<Track> getTracksByIds(List<Long> ids) throws DAOException {
    return getByIds(mapper, SQL_GET_BY_IDS, ids);
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

  public int countAllTracks() throws DAOException {
    return countAll(SQL_COUNT_ALL);
  }

  public int countAllUserTracks(String name) throws DAOException {
    return countSpecial(SQL_COUNT_ALL_USER_TRACKS, name);
  }

  public int countUserSpecialTracks(String username, OrderStatus status) throws DAOException {
    return countSpecial(SQL_COUNT_USER_TRACK_WITH_STATUS, username, status);
  }
}
