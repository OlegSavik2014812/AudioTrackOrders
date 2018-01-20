package com.audioord.dao;

import com.audioord.model.account.User;
import com.audioord.model.audio.Track;
import com.audioord.model.order.TrackFeedback;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrackFeedbackDAO extends BaseEntityDao<TrackFeedback, Long> {
  private static final String SQL_CREATE_TRACK_FEEDBACK = "insert into TrackFeedback(Comment, IdTrack,IdUser)values(?, ?,?)";


  private final EntityMapper<TrackFeedback> mapper = new EntityMapper<TrackFeedback>() {
    @Override
    public TrackFeedback parse(ResultSet rs) throws SQLException, DAOException {
      String comment = rs.getString(1);
      User user = new UserDAO().getById((long) rs.getInt(4));
      Track track = new TrackDAO().getById((long) rs.getInt(3));
      return new TrackFeedback(user, comment, track);
    }

    @Override
    public void write(PreparedStatement st, TrackFeedback entity) throws SQLException {
      st.setString(1, entity.getComments());
      st.setObject(2, entity.getTrack());
      st.setObject(3, entity.getUser());
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
    return false;
  }

  @Override
  public boolean create(TrackFeedback entity) throws DAOException {
    return update(entity, mapper, SQL_CREATE_TRACK_FEEDBACK);
  }
}
