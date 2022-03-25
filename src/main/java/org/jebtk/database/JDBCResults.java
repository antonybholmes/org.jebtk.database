package org.jebtk.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCResults extends DatabaseResults {

  private ResultSet mResults;

  public JDBCResults(ResultSet results) throws SQLException {
    mResults = results;

    setup();
  }

  private void setup() throws SQLException {
    try {
      for (int i = 1; i <= mResults.getMetaData().getColumnCount(); ++i) {
        columnNames.add(mResults.getMetaData().getColumnName(i));
      }
    } catch (SQLException e) {
      throw new SQLException(e.getMessage());
    }
  }

  @Override
  public boolean next() throws SQLException {
    try {
      return mResults.next();
    } catch (SQLException e) {
      throw new SQLException(e.getMessage());
    }
  }

  @Override
  public String getString(int column) throws SQLException {
    try {
      return mResults.getString(column);
    } catch (SQLException e) {
      throw new SQLException(e.getMessage());
    }
  }

  @Override
  public int getInt(int column) throws SQLException {
    try {
      return mResults.getInt(column);
    } catch (SQLException e) {
      throw new SQLException(e.getMessage());
    }
  }

  @Override
  public double getDouble(int column) throws SQLException {
    try {
      return mResults.getDouble(column);
    } catch (SQLException e) {
      throw new SQLException(e.getMessage());
    }
  }

  @Override
  public void close() throws SQLException {
    try {
      mResults.close();
    } catch (SQLException e) {
      throw new SQLException(e.getMessage());
    }
  }

}
