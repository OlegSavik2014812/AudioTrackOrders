package com.audioord.dao;

import com.audioord.model.Entity;
import com.audioord.model.account.User;
import com.audioord.model.audio.Track;
import com.audioord.model.order.Order;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * Track DAO class, extends {@link BaseEntityDao}
 */
public final class TrackDAO extends BaseEntityDao<Track, Long> {
  private static final String TRACK_TABLE_COLUMN_NAME = "track";
  private static final String TRACK_TABLE_COLUMN_ARTIST = "artist";
  private static final String TRACK_TABLE_COLUMN_ALBUM = "album";
  private static final String TRACK_TABLE_COLUMN_POPULARITY = "popularity";
  private static final String TRACK_TABLE_COLUMN_URI = "uri";
  private static final String TRACK_TABLE_COLUMN_PRICE = "price";
  private static final String TRACK_TABLE_COLUMN_DURATION = "duration";
  private static final String TRACK_TABLE_COLUMN_ID = "id";
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
  private static final String SQL_GET_TRACKS_BY_PACK_ID =
  "select t.Id, t.Track, t.Artist, t.Album,t.Popularity,t.URI,t.Price,t.Duration from track t, packagetrack pt,package p where t.id = pt.IdTrack and pt.IdPackage=p.Id and p.id = ?";
  private static final String SQL_GET_TRACKS_BY_ALBUM_ID =
  "select t.Id, t.Track, t.Artist, t.Album,t.Popularity,t.URI,t.Price,t.Duration from track t, albumtrack aat,album a where t.id = aat.IdTrack and aat.IdAlbum=a.Id and a.id = ?";
  private static final String SQL_ADD_TRACK =
  "INSERT INTO Track (track, artist, album, popularity, uri, price, duration) VALUES (?, ?, ?, ?, ?, ?, ?)";

  private static final String SQL_SEARCH_BY_TRACK_NAME =
  "SELECT track, artist, album, popularity, uri, price, duration, id FROM track WHERE Track LIKE (?)";

  private static final String SQL_GET_ORDER_TRACKS =
  "SELECT  t.Track, t.Artist, t.Album, t.Popularity, t.URI,  t.Price, t.Duration, t.Id FROM TrackOrder o JOIN Track t ON o.IdTrack = t.Id WHERE o.IdOrder = ?";

  private static final String SQL_UPDATE_TRACK_BY_ID =
  "UPDATE Track SET Track = ?, Artist = ?, Album = ?, Popularity = ?, URI = ?, Price = ?, Duration = ? WHERE Id = ?";

  private static final String SQL_DELETE_TRACK_BY_ID =
  "DELETE FROM Track WHERE Id = ?";

  private static final String SQL_GET_USER_TRACKS =
  "SELECT t.Track, t.Artist, t.Album, t.Popularity, t.URI, t.Price, t.Duration, t.Id FROM `Order` o JOIN TrackOrder tr ON o.Id = tr.IdOrder JOIN Track t ON tr.IdTrack = t.Id WHERE IdUser = ? AND o.Status = 'COMPLETED'";


  private final EntityMapper<Track> mapper =
  new EntityMapper<Track>() {
    /**
     * Parse {@link ResultSet} and creates {@link Track} object
     * @param rs ResultSet {@link ResultSet}
     * @return {@link Track} object
     * @throws SQLException {@link SQLException}
     */
    @Override
    public Track parse(ResultSet rs) throws SQLException {
      Track track = new Track(rs.getString(TRACK_TABLE_COLUMN_NAME), rs.getString(TRACK_TABLE_COLUMN_ARTIST));
      track.setAlbum(rs.getString(TRACK_TABLE_COLUMN_ALBUM));
      track.setPopularity(rs.getInt(TRACK_TABLE_COLUMN_POPULARITY));
      track.setUri(rs.getString(TRACK_TABLE_COLUMN_URI));
      track.setPrice(rs.getInt(TRACK_TABLE_COLUMN_PRICE));
      track.setDuration(rs.getLong(TRACK_TABLE_COLUMN_DURATION));
      track.setId(rs.getLong(TRACK_TABLE_COLUMN_ID));
      return track;
    }

    /**
     * fill {@link PreparedStatement} with {@link Track} info
     *
     * @param st     {@link PreparedStatement}
     * @param entity {@link Track} object
     * @throws SQLException {@link SQLException}
     */
    @Override
    public void write(PreparedStatement st, Track entity) throws SQLException {
      st.setString(1, entity.getName());
      st.setString(2, entity.getArtist());
      st.setString(3, entity.getAlbum());
      st.setInt(4, entity.getPopularity());
      st.setObject(5, entity.getUri());
      st.setDouble(6, entity.getPrice());
      st.setLong(7, entity.getDuration());
      if (entity.getId() != null) {
        st.setLong(8, entity.getId());
      }
    }
  };

  /**
   * Allows to find {@link Track} object by id
   * check {@link BaseEntityDao#getById(Serializable, EntityMapper, String)}
   *
   * @param id {@link Track} id
   * @return {@link Track}
   * @throws DAOException {@link DAOException}
   */
  @Override
  public Track getById(Long id) throws DAOException {
    return getById(id, mapper, SQL_GET_TRACK_BY_ID);
  }

  /**
   * Allows to update {@link Track} object info
   * check {@link BaseEntityDao#getById(Serializable)}
   *
   * @param entity {@link Track} object
   * @return updated {@link Track} object
   * @throws DAOException {@link DAOException}
   */
  @Override
  public Track update(Track entity) throws DAOException {
    update(entity, mapper, SQL_UPDATE_TRACK_BY_ID);
    return getById(entity.getId());
  }

  @Override
  public boolean delete(Long id) throws DAOException {
    return remove(id, SQL_DELETE_TRACK_BY_ID);
  }

  /**
   * Allows to create row, which have info of input {@link Track} object
   * check {@link BaseEntityDao#create(Entity, EntityMapper, String)}
   *
   * @param entity {@link Track}
   * @return if the number of changed rows is greater than 0 - true, otherwise - false
   * @throws DAOException {@link DAOException}
   */
  @Override
  public boolean create(Track entity) throws DAOException {
    Long id = create(entity, mapper, SQL_ADD_TRACK);
    if (id == null) {
      return false;
    }
    entity.setId(id);
    return true;
  }

  /**
   * Allows to get list of {@link Track} objects using input {@link Order} object
   * check {@link BaseEntityDao#findAll(EntityMapper, String, Object...)}
   *
   * @param order {@link Order} object
   * @return {@link List} of {@link Track}
   * @throws DAOException {@link DAOException}
   */
  public List<Track> getTracksByOrder(Order order) throws DAOException {
    return findAll(mapper, SQL_GET_ORDER_TRACKS, order.getId());
  }

  /**
   * Allows to get list of {@link Track} objects using the match of the input line and the name of the track
   * check {@link BaseEntityDao#findAll(EntityMapper, String, Object...)}
   *
   * @param trackName trackname
   * @return {@link List} of {@link Track}
   * @throws DAOException {@link DAOException}
   */
  public List<Track> findTracks(String trackName) throws DAOException {
    String s = trackName.replaceAll("%", "");
    if (!s.isEmpty()) {
      return findAll(mapper, SQL_SEARCH_BY_TRACK_NAME, s + "%");
    }
    return Collections.emptyList();
  }

  /**
   * Allows to get list of {@link Track} objects using {@link List} of ids
   * check {@link BaseEntityDao#getByIds(EntityMapper, String, List)}
   *
   * @param ids list of ids
   * @return list of {@link Track} objects
   * @throws DAOException {@link DAOException}
   */
  public List<Track> getTracksByIds(List<Long> ids) throws DAOException {
    String placeholder = String.join(",", Collections.nCopies(ids.size(), "?"));
    return getByIds(mapper, SQL_GET_BY_IDS.replace("##", placeholder), ids);
  }

  /**
   * Allows to get most popular list of {@link Track} objects
   * check {@link BaseEntityDao#findAll(EntityMapper, String, Object...)}
   *
   * @param page  number of row in a table "Track"
   * @param count quantity of rows
   * @return list of {@link Track} objects
   * @throws DAOException {@link DAOException}
   */
  public List<Track> getMostPopularTracks(int page, int count) throws DAOException {
    return findAll(mapper, SQL_GET_MOST_POPULAR_TRACKS, page, count);
  }

  /**
   * Allows to get best selling list of {@link Track} objects
   * check {@link BaseEntityDao#findAll(EntityMapper, String, Object...)}
   *
   * @param page  number of row in a table "Track"
   * @param count quantity of rows
   * @return list of {@link Track} objects
   * @throws DAOException {@link DAOException}
   */
  public List<Track> getBestSellingTracks(int page, int count) throws DAOException {
    return findAll(mapper, SQL_GET_BEST_SELLING_TRACKS, page, count);
  }

  /**
   * Allows to get brand new list of {@link Track} objects
   * check {@link BaseEntityDao#findAll(EntityMapper, String, Object...)}
   *
   * @param page  number of row in a table "Track"
   * @param count quantity of rows
   * @return list of {@link Track} object
   * @throws DAOException {@link DAOException}
   */
  public List<Track> getBrandNewTracks(int page, int count) throws DAOException {
    return findAll(mapper, SQL_GET_BRAND_NEW_TRACK, page, count);
  }

  /**
   * Allows you to count all tracks in database
   * check {@link BaseEntityDao#countAll(String)}
   *
   * @return number of tracks
   * @throws DAOException {@link DAOException}
   */
  public int countAllTracks() throws DAOException {
    return countAll(SQL_COUNT_ALL);
  }

  /**
   * Allows to get list of {@link Track} objects of {@link User} using id
   * check {@link BaseEntityDao#findAll(EntityMapper, String, Object...)}
   *
   * @param userId {@link User} id
   * @return list of {@link Track} objects
   * @throws DAOException {@link DAOException}
   */
  public List<Track> getAllUserTracks(Long userId) throws DAOException {
    return findAll(mapper, SQL_GET_USER_TRACKS, userId);
  }

  public List<Track> getPackageTracks(Long packId) throws DAOException {
    return findAll(mapper, SQL_GET_TRACKS_BY_PACK_ID, packId);
  }

  public List<Track> getAlbumTracks(Long albumId) throws DAOException {
    return findAll(mapper, SQL_GET_TRACKS_BY_ALBUM_ID, albumId);
  }
}
