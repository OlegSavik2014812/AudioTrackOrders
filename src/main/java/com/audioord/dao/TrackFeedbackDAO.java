package com.audioord.dao;

import com.audioord.model.account.User;
import com.audioord.model.audio.Track;
import com.audioord.model.order.TrakcFeedback;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrackFeedbackDAO extends BaseEntityDao<TrakcFeedback, Long> {
  private static final String SQL_CREATE_TRACK_FEEDBACK = "insert into TrackFeedback(Comment, IdTrack,IdUser)values(?, ?,?)";


  private EntityMapper<TrakcFeedback> mapper = new EntityMapper<TrakcFeedback>() {
    @Override
    public TrakcFeedback parse(ResultSet rs) throws SQLException, DAOException {
      String comment = rs.getString(1);
      User user = new UserDAO().getById((long) rs.getInt(4));
      Track track = new TrackDAO().getById((long) rs.getInt(3));
      return new TrakcFeedback(user, comment, track);
    }

    @Override
    public void write(PreparedStatement st, TrakcFeedback entity) throws SQLException {
      st.setString(1, entity.getComments());
      st.setObject(2, entity.getTrack());
      st.setObject(3, entity.getUser());
    }
  };

  @Override
  public TrakcFeedback getById(Long id) throws DAOException {
    return null;
  }

  @Override
  public TrakcFeedback update(TrakcFeedback entity) throws DAOException {
    return null;
  }

  @Override
  public boolean delete(Long id) throws DAOException {
    return false;
  }

  @Override
  public boolean create(TrakcFeedback entity) throws DAOException {
    return update(entity, mapper, SQL_CREATE_TRACK_FEEDBACK);
  }
}
