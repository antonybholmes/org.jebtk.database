package org.jebtk.database.query;

import java.util.ArrayList;
import java.util.List;

import org.jebtk.core.text.TextUtils;

public abstract class ColumnsQuery extends Query {
  protected List<Column> mColumns = new ArrayList<Column>();

  public ColumnsQuery(Query query, String column, String... columns) {
    super(query);

    mColumns.add(new Column(column));

    for (String c : columns) {
      mColumns.add(new Column(c));
    }
  }

  public ColumnsQuery(Query query) {
    super(query);
  }

  public ColumnsQuery(ColumnsQuery query) {
    super(query);

    mColumns.addAll(query.mColumns);
  }

  /*
   * protected ColumnsQuery col(String column) { return col(new Column(column));
   * }
   * 
   * protected ColumnsQuery col(Column column) { mColumns.add(column);
   * 
   * return this; }
   */

  @Override
  public void getSql(StringBuilder buffer) {
    getParent().getSql(buffer);

    buffer.append(TextUtils.join(mColumns, TextUtils.FORMATTED_LIST_DELIMITER));
  }
}
