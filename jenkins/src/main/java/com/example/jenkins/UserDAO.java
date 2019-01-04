package com.example.jenkins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletionStage;

@Repository
public class UserDAO implements RowMapper<User> {
  private final String TABLE_NAME = "user";
  private final String COL_NAME = "name";
  private final String COL_EMAIL_ID = "email_id";
  private final String INSERT_USER =
      String.format(
          "insert into " + TABLE_NAME + "(%1$s,%2$s) values (:%1$s,:%2$s)", COL_NAME, COL_EMAIL_ID);
  @Autowired DAOTempltae daoTempltae;

  public CompletionStage<Integer> insert(User user) {
    Map<String, Object> paramMap = new HashMap<>();
    paramMap.put(COL_NAME, user.getName());
    paramMap.put(COL_EMAIL_ID, user.getEmailID());
    return daoTempltae.save(INSERT_USER, paramMap);
  }

  @Override
  public User mapRow(ResultSet resultSet, int i) throws SQLException {
    return User.builder()
        .emailID(resultSet.getString(COL_EMAIL_ID))
        .name(resultSet.getString(COL_NAME))
        .build();
  }
}
