package org.jebtk.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Pseudo table designed for iterating over once.
 * 
 * @author Antony Holmes
 *
 */
public class ResultsSetTable {
  private List<String> mColumnNames = new ArrayList<String>();
  private Map<String, Integer> mColumnNameMap = new HashMap<String, Integer>();
  private ResultSet mResults;
  private int mColumns;

  public ResultsSetTable(ResultSet results) throws SQLException {
    mResults = results;

    mColumns = results.getMetaData().getColumnCount();

    for (int i = 0; i < mColumns; ++i) {
      String name = results.getMetaData().getColumnName(i + 1);

      mColumnNameMap.put(name.toLowerCase(), i);
      mColumnNames.add(name);
    }
  }

  public String getColumnName(int column) {
    return getColumnNames().get(column);
  }

  /**
   * Get an entry from the table.
   * 
   * @param row
   * @param column
   * @return
   * @throws SQLException
   */
  public Object getData(int column) throws SQLException {
    int type = mResults.getMetaData().getColumnType(column);

    switch (type) {
    case Types.BIT:
    case Types.TINYINT:
    case Types.SMALLINT:
    case Types.INTEGER:
    case Types.BIGINT:
      return mResults.getInt(column);
    case Types.FLOAT:
    case Types.REAL:
    case Types.DOUBLE:
    case Types.NUMERIC:
    case Types.DECIMAL:
      return mResults.getDouble(column);
    default:
      return mResults.getString(column);
    }
  }

  /**
   * Get an entry from the table by column name.
   * 
   * @param row
   * @param columnName
   * @return
   * @throws SQLException
   */
  public Object getData(String columnName) throws SQLException {
    return getData(getIndex(columnName));
  }

  public String getString(int column) throws SQLException {
    return mResults.getString(column + 1);
  }

  public String getDataAsString(String columnName) throws SQLException {
    return getString(mColumnNameMap.get(columnName));
  }

  public int getInt(int column) throws SQLException {
    return mResults.getInt(column + 1);
  }

  public int getDataAsInt(String columnName) throws SQLException {
    return getInt(getIndex(columnName));
  }

  public double getDataAsDouble(int column) throws SQLException {
    return mResults.getDouble(column + 1);
  }

  public double getDataAsDouble(String columnName) throws SQLException {
    return getDataAsDouble(getIndex(columnName));
  }

  public int getColumnCount() {
    return mColumns;
  }

  public List<String> getColumnNames() {
    return mColumnNames;
  }

  public Map<String, Integer> getColumnNameMap() {
    return mColumnNameMap;
  }

  /**
   * Get the next record. The underlying sql connection is auto closed once the
   * end is reached.
   * 
   * @return
   * @throws SQLException
   */
  public boolean next() throws SQLException {
    boolean ret = mResults.next();

    // Auto close once finished
    if (!ret) {
      mResults.close();
    }

    return ret;
  }

  /**
   * Returns the index of a column or Integer.MIN_VALUE if the column does not
   * exist.
   * 
   * @param name The column name.
   * @return The column index.
   */
  public int getIndex(String name) {
    String ns = name.toLowerCase();

    if (mColumnNameMap.containsKey(ns)) {
      return mColumnNameMap.get(ns);
    } else {
      return Integer.MIN_VALUE;
    }
  }
}
