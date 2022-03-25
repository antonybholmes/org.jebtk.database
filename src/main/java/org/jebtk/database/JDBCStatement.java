package org.jebtk.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCStatement extends DatabaseStatement {
  private PreparedStatement mStatement;

  public JDBCStatement(PreparedStatement statement) throws SQLException {
    mStatement = statement;
  }

  @Override
  public DatabaseResults execute() throws SQLException {
    // System.err.println();

    try {
      return new JDBCResults(mStatement.executeQuery());
    } catch (SQLException e) {
      throw new SQLException(e.getMessage());
    }
  }

  @Override
  public void setInt(int index, int value) throws SQLException {
    try {
      mStatement.setInt(index, value);
    } catch (SQLException e) {
      throw new SQLException(e.getMessage());
    }
  }

  @Override
  public void setDouble(int index, double value) throws SQLException {
    try {
      mStatement.setDouble(index, value);
    } catch (SQLException e) {
      throw new SQLException(e.getMessage());
    }
  }

  @Override
  public void setString(int index, String value) throws SQLException {
    try {
      mStatement.setString(index, value);
    } catch (SQLException e) {
      throw new SQLException(e.getMessage());
    }
  }

  @Override
  public void close() throws SQLException {
    if (mStatement == null) {
      return;
    }

    try {
      mStatement.close();
    } catch (SQLException e) {
      throw new SQLException(e.getMessage());
    }
  }
}
