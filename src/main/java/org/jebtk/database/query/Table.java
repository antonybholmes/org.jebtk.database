package org.jebtk.database.query;

public class Table extends Columns {
  private String mName;
  private String mAsName;

  public Table(String name) {
    this(name, null);
  }

  public Table(String name, String asName) {
    mName = name;
    mAsName = asName;
  }

  @Override
  public String toString() {
    return getName();
  }

  public String getName() {
    if (mAsName != null) {
      return mName + " " + mAsName;
    } else {
      return mName;
    }
  }
}
