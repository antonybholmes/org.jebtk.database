package org.jebtk.database.query;

import java.util.ArrayList;
import java.util.List;

import org.jebtk.core.text.TextUtils;

public abstract class FromQuery extends TableQuery {
  private List<Table> mTables = new ArrayList<Table>();

  public FromQuery(Query query, Table table) {
    super(query);

    addTable(table);
  }

  public FromQuery(FromQuery query) {
    super(query);

    mTables.addAll(query.mTables);
  }

  public FromQuery addTable(Table name) {
    mTables.add(name);

    return this;
  }

  public String getSql() {
    StringBuilder buffer = new StringBuilder();

    getSql(buffer);

    return buffer.toString();
  }

  public void getSql(StringBuilder buffer) {
    getParent().getSql(buffer);

    buffer.append(" FROM ");
    buffer.append(TextUtils.commaJoin(mTables));
  }
}
