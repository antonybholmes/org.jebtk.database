package org.jebtk.database.query;

import java.sql.SQLException;

import org.jebtk.core.text.TextUtils;

public class InsertQuery extends ColumnsQuery {
  private Table mTable;

  public InsertQuery(InsertQuery query) {
    super(query.getParent());

    mTable = query.mTable;
    mColumns.addAll(query.mColumns);
  }

  public InsertQuery(Query query, String table) {
    this(query, new Table(table));
  }

  public InsertQuery(Query query, Table table) {
    super(query);

    mTable = table;
  }

  public InsertQuery cols(String column, String... columns) {
    InsertQuery ret = new InsertQuery(this);

    ret.mColumns.add(new Column(column));

    for (String c : columns) {
      ret.mColumns.add(new Column(c));
    }

    return ret;
  }

  public InsertQuery col(String column) {
    return col(new Column(column));
  }

  public InsertQuery col(Column column) {
    InsertQuery ret = new InsertQuery(this);

    ret.mColumns.add(column);

    return ret;

  }

  public ValuesQuery values(Object value, Object... values)
      throws SQLException {
    return new ValuesQuery(this, value, values);
  }

  @Override
  public void getSql(StringBuilder buffer) {
    buffer.append("INSERT INTO ");
    buffer.append(mTable);
    buffer.append(" (");
    super.getSql(buffer);
    buffer.append(") VALUES (");

    buffer.append(TextUtils
        .repeat("?", TextUtils.FORMATTED_LIST_DELIMITER, mColumns.size()));

    buffer.append(")");
  }
}
