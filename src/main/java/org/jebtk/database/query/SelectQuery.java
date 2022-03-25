package org.jebtk.database.query;

public class SelectQuery extends ColumnsQuery {

  public SelectQuery(Query query, String column, String... columns) {
    super(query, column, columns);
  }

  public SelectQuery(ColumnsQuery query) {
    super(query);
  }

  public SelectQuery(SelectQuery query) {
    super(query.getParent());

    mColumns.addAll(query.mColumns);
  }

  public SelectQuery cols(String column, String... columns) {
    SelectQuery ret = new SelectQuery(this);

    ret.col(new Column(column));

    for (String c : columns) {
      ret.col(new Column(c));
    }

    return ret;
  }

  public SelectQuery col(String column) {
    return col(new Column(column));
  }

  public SelectQuery col(Column column) {
    SelectQuery ret = new SelectQuery(this);

    ret.mColumns.add(column);

    return ret;

  }

  public SelectFromQuery from(String table) {
    return from(new Table(table));
  }

  public SelectFromQuery from(Table table) {
    return new SelectFromQuery(this, table);
  }

  @Override
  public void getSql(StringBuilder buffer) {
    buffer.append("SELECT ");
    super.getSql(buffer);
  }
}
