package org.jebtk.database.query;

public class SelectFromQuery extends FromQuery {
  public SelectFromQuery(Query query, Table table) {
    super(query, table);
  }

  public SelectFromQuery(FromQuery query) {
    super(query);
  }

  public JoinQuery join(Table table,
      Column c1,
      RelationType relation,
      Column c2) {
    return new JoinQuery(this, table, c1, relation, c2);
  }

  public SelectWhereQuery where(String column, String... columns) {
    SelectWhereQuery ret = where(new Column(column));

    for (String c : columns) {
      ret.mClauses.and(new WhereClause(new Column(c), RelationType.EQ));
    }

    return ret;
  }

  public SelectWhereQuery where(Column column) {
    return where(column, RelationType.EQ);
  }

  public SelectWhereQuery where(Column column, RelationType type) {
    return new SelectWhereQuery(this, column, type);
  }

  public OrderByQuery order(String column, String... columns) {
    return new OrderByQuery(this, column, columns);
  }

  public OrderByQuery order(OrderBy column) {
    return new OrderByQuery(this, column);
  }
}
