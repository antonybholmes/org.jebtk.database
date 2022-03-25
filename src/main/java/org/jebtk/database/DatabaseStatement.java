package org.jebtk.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper for statements from different database interfaces providing a simple
 * subset of features.
 * 
 * @author Antony Holmes
 *
 */
public abstract class DatabaseStatement {
  public abstract void setInt(int index, int value) throws SQLException;

  public abstract void setDouble(int index, double value) throws SQLException;

  public abstract void setString(int index, String value) throws SQLException;

  public abstract DatabaseResults execute() throws SQLException;

  public List<Integer> getIntList() throws SQLException {

    List<Integer> ids = new ArrayList<Integer>();

    DatabaseResults results = execute();

    while (results.next()) {
      ids.add(results.getInt(1));
    }

    return ids;
  }

  public List<String> getStringList() throws SQLException {

    List<String> ids = new ArrayList<String>();

    DatabaseResults results = execute();

    while (results.next()) {
      ids.add(results.getString(1));
    }

    return ids;
  }

  public abstract void close() throws SQLException;
}