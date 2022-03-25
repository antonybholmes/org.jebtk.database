package org.jebtk.database.query;

import java.sql.SQLException;

public class WhereQuery extends Query {
  protected BoolExp<WhereClause> mClauses;

  public WhereQuery(Query query, Column column, RelationType type) {
    super(query);

    mClauses = new BoolExp<WhereClause>(new WhereClause(column, type));
  }

  public WhereQuery(WhereQuery query) {
    super(query);

    mClauses = new BoolExp<WhereClause>(query.mClauses);
  }

  public WhereQuery and(String column) {
    return and(new Column(column));
  }

  public WhereQuery and(Column column) {
    return and(column, RelationType.EQ);
  }

  public WhereQuery and(Column column, RelationType type) {
    WhereQuery ret = new WhereQuery(this);

    ret.mClauses.and(new WhereClause(column, type));

    return ret;
  }

  public WhereQuery or(Column column, RelationType type) {
    WhereQuery ret = new WhereQuery(this);

    ret.mClauses.or(new WhereClause(column, type));

    return ret;
  }

  public ValuesQuery values(Object value, Object... values)
      throws SQLException {
    return new ValuesQuery(this, value, values);
  }

  @Override
  public void getSql(StringBuilder buffer) {
    getParent().getSql(buffer);

    buffer.append(" WHERE ");

    mClauses.getSql(buffer);
  }
}
