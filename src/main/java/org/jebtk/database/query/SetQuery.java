package org.jebtk.database.query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jebtk.core.text.TextUtils;

public class SetQuery extends Query {
  protected List<String> mClauses = new ArrayList<String>();

  public SetQuery(Query query, Column column) {
    super(query);

    addColumn(column);
  }

  public SetQuery(SetQuery query) {
    super(query);

    mClauses.addAll(query.mClauses);
  }

  public SetQuery addColumn(Column column) {
    mClauses.add(column + " = ?");

    return this;
  }

  public WhereQuery where(String column) throws SQLException {
    return where(new Column(column), RelationType.EQ);
  }

  public WhereQuery where(Column column, RelationType type)
      throws SQLException {
    return new WhereQuery(this, column, type);
  }

  @Override
  public void getSql(StringBuilder buffer) {
    getParent().getSql(buffer);

    buffer.append(" SET ");
    buffer.append(TextUtils.commaJoin(mClauses));
  }
}
