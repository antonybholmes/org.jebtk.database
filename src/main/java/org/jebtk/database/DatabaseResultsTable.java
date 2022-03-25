package org.jebtk.database;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DatabaseResultsTable implements Iterable<List<Object>> {
  private List<String> mColumnNames = new ArrayList<String>();
  private Map<String, Integer> mColumnNameMap = new HashMap<String, Integer>();

  private List<List<Object>> mData = new ArrayList<List<Object>>();

  public static DatabaseResultsTable EMPTY_TABLE = new DatabaseResultsTable();

  public String getColumnName(int column) {
    return getColumnNames().get(column);
  }

  /**
   * Get an entry from the table.
   * 
   * @param row
   * @param column
   * @return
   */
  public Object getData(int row, int column) {
    return mData.get(row).get(column);
  }

  /**
   * Get an entry from the table by column name.
   * 
   * @param row
   * @param columnName
   * @return
   */
  public Object getData(int row, String columnName) {
    return getData(row, getColumnNameMap().get(columnName));
  }

  public String getString(int row, int column) {
    return (String) getData(row, column); // Integer.parseInt(getData(i, j));
  }

  public String getDataAsString(int row, String columnName) {
    return getString(row, getColumnNameMap().get(columnName));
  }

  public int getInt(int row, int column) {
    return (Integer) getData(row, column); // Integer.parseInt(getData(i, j));
  }

  public int getDataAsInt(int row, String columnName) {
    return getInt(row, getColumnNameMap().get(columnName));
  }

  public double getDataAsDouble(int row, int column) {
    return (Double) getData(row, column);
  }

  public double getDataAsDouble(int row, String columnName) {
    return getDataAsDouble(row, getColumnNameMap().get(columnName));
  }

  public File getDataAsFile(int row, int column) {
    return new File(getString(row, column));
  }

  public int getRowCount() {
    return getData().size();
  }

  public int getColumnCount() {
    if (getRowCount() > 0) {
      return getData().get(0).size();
    } else {
      return 0;
    }
  }

  public List<String> getColumnNames() {
    return mColumnNames;
  }

  public Map<String, Integer> getColumnNameMap() {
    return mColumnNameMap;
  }

  public List<List<Object>> getData() {
    return mData;
  }

  public void append(DatabaseResultsTable table) {
    for (List<Object> row : table) {
      mData.add(row);
    }
  }

  @Override
  public Iterator<List<Object>> iterator() {
    return mData.iterator();
  }

}
