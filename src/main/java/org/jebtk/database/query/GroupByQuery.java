package org.jebtk.database.query;

public class GroupByQuery extends ColumnsQuery {
  public GroupByQuery(Query query, String column, String... columns) {
    super(query, column, columns);
  }

  public GroupByQuery(ColumnsQuery query) {
    super(query);
  }

  public GroupByQuery(GroupByQuery query) {
    super(query.getParent());

    mColumns.addAll(query.mColumns);
  }

  public GroupByQuery cols(String... columns) {
    GroupByQuery ret = new GroupByQuery(this);

    for (String col : columns) {
      ret.mColumns.add(new Column(col));
    }

    return ret;
  }

  public GroupByQuery col(String column) {
    return col(new Column(column));
  }

  public GroupByQuery col(Column column) {
    GroupByQuery ret = new GroupByQuery(this);

    ret.mColumns.add(column);

    return ret;
  }

  @Override
  public void getSql(StringBuilder buffer) {
    getParent().getSql(buffer);

    buffer.append(" GROUP BY ");

    super.getSql(buffer);
  }

  public OrderByQuery orderBy(OrderBy column) {
    return new OrderByQuery(this, column);
  }
}
