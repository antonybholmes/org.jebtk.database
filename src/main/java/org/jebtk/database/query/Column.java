package org.jebtk.database.query;

public class Column {
  private String mName;
  private ColumnType mType;
  private String mTable;

  public Column(String name) {
    this(null, name);
  }

  public Column(String table, String name) {
    this(table, name, ColumnType.STRING);
  }

  public Column(String name, ColumnType type) {
    this(null, name, type);
  }

  public Column(String table, String name, ColumnType type) {
    mTable = table;
    mName = name;
    mType = type;
  }

  @Override
  public String toString() {
    return getName();
  }

  public String getName() {
    if (mTable != null) {
      return mTable + "." + mName;
    } else {
      return mName;
    }
  }

  public ColumnType getColumnType() {
    return mType;
  }
}
