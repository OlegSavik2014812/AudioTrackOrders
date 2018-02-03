package com.audioord.dao;

import com.audioord.model.account.Role;
import com.audioord.model.account.User;
import com.audioord.model.audio.Track;
import com.audioord.model.order.TrackFeedback;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TrackFeedbackDAO extends BaseEntityDao<TrackFeedback, Long> {
  private static final String FEEDBACK_TABLE_COLUMN_FEEDBACK_ID = "FeedbackId";
  private static final String FEEDBACK_TABLE_COLUMN_COMMENT = "Comment";
  private static final String FEEDBACK_TABLE_COLUMN_DATE_CREATED = "Created";
  private static final String FEEDBACK_TABLE_COLUMN_USER_ID = "UserId";
  private static final String FEEDBACK_TABLE_COLUMN_USERNAME = "UserName";
  private static final String FEEDBACK_TABLE_COLUMN_ROLE = "Role";
  private static final String FEEDBACK_TABLE_COLUMN_FIRST_NAME = "FirstName";
  private static final String FEEDBACK_TABLE_COLUMN_LAST_NAME = "LastName";
  private static final String FEEDBACK_TABLE_COLUMN_TRACK_ID = "TrackId";
  private static final String FEEDBACK_TABLE_COLUMN_TRACK = "Track";
  private static final String FEEDBACK_TABLE_COLUMN_ARTIST = "Artist";
  private static final String FEEDBACK_TABLE_COLUMN_ALBUM = "Album";
  private static final String FEEDBACK_TABLE_COLUMN_POPULARITY = "Popularity";
  private static final String FEEDBACK_TABLE_COLUMN_URI = "URI";
  private static final String FEEDBACK_TABLE_COLUMN_PRICE = "Price";
  private static final String FEEDBACK_TABLE_COLUMN_DURATION = "Duration";

  private static final String SQL_CREATE_FEEDBACK =
  "insert into feedback(Comment, IdTrack,IdUser,Created) values(?,?,?,?)";
  private static final String SQL_GET_ALL_FEEDBACKS_BY_TRACK_ID =
  "select f.Id as FeedbackId , f.Comment, f.Created, u.Id as UserId, u.UserName, u.Role , u.FirstName , u.LastName,\n" +
  "t.Id as TrackId, t.Track, t.Artist,t.Album ,t.Popularity , t.URI,t.Price,t.Duration \n" +
  "from feedback f join user u on f.IdUser=u.Id join track t on f.IdTrack = t.Id\n" +
  "where t.Id = ?";
  private static final String SQL_DELETE_FEEDBACK = "delete from feedback where Id = ?";

  EntityMapper<TrackFeedback> mapper = new EntityMapper<TrackFeedback>() {
    @Override
    public TrackFeedback parse(ResultSet rs) throws SQLException, DAOException {
      User user = new User(rs.getString(FEEDBACK_TABLE_COLUMN_USERNAME), Role.fromString(rs.getString(FEEDBACK_TABLE_COLUMN_ROLE)));
      user.setId(rs.getLong(FEEDBACK_TABLE_COLUMN_USER_ID));
      user.setFirstName(rs.getString(FEEDBACK_TABLE_COLUMN_FIRST_NAME));
      user.setLastName(rs.getString(FEEDBACK_TABLE_COLUMN_LAST_NAME));

      Track track = new Track(rs.getString(FEEDBACK_TABLE_COLUMN_TRACK), rs.getString(FEEDBACK_TABLE_COLUMN_ARTIST));
      track.setId(rs.getLong(FEEDBACK_TABLE_COLUMN_TRACK_ID));
      track.setAlbum(rs.getString(FEEDBACK_TABLE_COLUMN_ALBUM));
      track.setUri(rs.getString(FEEDBACK_TABLE_COLUMN_URI));
      track.setPopularity(rs.getInt(FEEDBACK_TABLE_COLUMN_POPULARITY));
      track.setPrice(rs.getDouble(FEEDBACK_TABLE_COLUMN_PRICE));
      track.setDuration(rs.getLong(FEEDBACK_TABLE_COLUMN_DURATION));

      TrackFeedback trackFeedback = new TrackFeedback(user, rs.getString(FEEDBACK_TABLE_COLUMN_COMMENT), track);
      trackFeedback.setCreatedAt(rs.getDate(FEEDBACK_TABLE_COLUMN_DATE_CREATED));
      trackFeedback.setId(rs.getLong(FEEDBACK_TABLE_COLUMN_FEEDBACK_ID));
      return trackFeedback;
    }

    @Override
    public void write(PreparedStatement st, TrackFeedback entity) throws SQLException {
      st.setString(1, entity.getComments());
      st.setLong(2, entity.getTrack().getId());
      st.setLong(3, entity.getUser().getId());
      st.setDate(4, new java.sql.Date(entity.getCreatedAt().getTime()));
    }
  };

  @Override
  public TrackFeedback getById(Long id) throws DAOException {
    return null;
  }

  @Override
  public TrackFeedback update(TrackFeedback entity) throws DAOException {
    return null;
  }

  @Override
  public boolean delete(Long id) throws DAOException {
    return remove(id, SQL_DELETE_FEEDBACK);
  }

  @Override
  public boolean create(TrackFeedback entity) throws DAOException {
    return update(entity, mapper, SQL_CREATE_FEEDBACK);
  }

  public List<TrackFeedback> getFeedbacksByTrackId(Long trackId) throws DAOException {
    return findAll(mapper, SQL_GET_ALL_FEEDBACKS_BY_TRACK_ID, trackId);
  }

}
