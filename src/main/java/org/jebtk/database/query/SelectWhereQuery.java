package org.jebtk.database.query;

public class SelectWhereQuery extends WhereQuery {
  public SelectWhereQuery(Query query, Column column, RelationType type) {
    super(query, column, type);
  }

  public SelectWhereQuery(SelectWhereQuery query) {
    super(query);
  }

  public OrderByQuery orderBy(String column) {
    return orderBy(new OrderBy(column));
  }

  public OrderByQuery orderBy(OrderBy column) {
    return new OrderByQuery(this, column);
  }
}
