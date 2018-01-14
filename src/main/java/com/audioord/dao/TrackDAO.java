package com.audioord.dao;

import com.audioord.model.audio.Track;

import java.net.URI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TrackDAO extends BaseEntityDao<Track, Long> {

  private static final String GET_ALL_TRACKS_SQL = "select track, artist, album , popularity , uri , price, duration from track order by Popularity desc";

  private EntityMapper<Track> mapper = new EntityMapper<Track>() {
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
}
