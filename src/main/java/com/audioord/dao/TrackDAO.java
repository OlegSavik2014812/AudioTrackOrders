package com.audioord.dao;

import com.audioord.model.audio.Track;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class TrackDAO extends BaseEntityDao<Track, Long> {

  private static final String SQL_COUNT_ALL =
  "SELECT count(id) FROM track";

  private static final String SQL_GET_BEST_SELLING_TRACKS =
  "SELECT track, artist, album, popularity, uri, price, duration, id, (SELECT count(Id) FROM TrackOrder WHERE Id = IdTrack) AS numOrdered FROM track ORDER BY numOrdered DESC LIMIT ?, ?";

  private static final String SQL_GET_BRAND_NEW_TRACK =
  "SELECT track, artist, album, popularity, uri, price, duration, id FROM track ORDER BY id DESC LIMIT ?, ?";

  private static final String SQL_GET_MOST_POPULAR_TRACKS =
  "SELECT track, artist, album, popularity, uri, price, duration, id FROM track ORDER BY Popularity DESC LIMIT ?, ?";

  private static final String SQL_GET_TRACK_BY_ID =
  "SELECT track, artist, album, popularity, uri, price, duration, id FROM track where track.id=?";

  private static final String SQL_GET_BY_IDS =
  "SELECT track, artist, album, popularity, uri, price, duration, id FROM track where track.id in (##)";

  private static final String SQL_ADD_TRACK =
  "INSERT INTO Track (track, artist, album, popularity, uri, price, duration, id) VALUES (?, ?, ?, ?, ?, ?, ?)";

  private static final String SQL_SEARCH_BY_TRACK_NAME =
  "SELECT track, artist, album, popularity, uri, price, duration, id FROM track WHERE Track LIKE (?)";

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
    return null; // TODO:
  }

  @Override
  public boolean delete(Long id) throws DAOException {
    return false; // TODO:
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
    String placeholder = String.join(",", Collections.nCopies(ids.size(), "?"));
    return getByIds(mapper, SQL_GET_BY_IDS.replace("##", placeholder), ids);
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
}
