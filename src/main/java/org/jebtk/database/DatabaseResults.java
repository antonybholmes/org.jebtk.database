package org.jebtk.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DatabaseResults {

  protected List<String> columnNames = new ArrayList<String>();

  public List<String> getColumnNames() {
    return columnNames;
  }

  public abstract boolean next() throws SQLException;

  public abstract String getString(int column) throws SQLException;

  public abstract int getInt(int column) throws SQLException;

  public abstract double getDouble(int column) throws SQLException;

  public abstract void close() throws SQLException;
}
