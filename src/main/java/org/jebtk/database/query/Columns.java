package org.jebtk.database.query;

import java.util.ArrayList;
import java.util.List;

public class Columns {
  protected List<Column> mColumns = new ArrayList<Column>();

  public void col(String c) {
    col(new Column(c));
  }

  public void col(Column c) {
    mColumns.add(c);
  }
}
