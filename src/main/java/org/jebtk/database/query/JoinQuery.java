package org.jebtk.database.query;

public class JoinQuery extends TableQuery {
  private Table mTable;
  private BoolExp<JoinClause> mClauses;

  public JoinQuery(TableQuery query, Table table, Column c1,
      RelationType relation, Column c2) {
    super(query);

    mTable = table;

    mClauses = new BoolExp<JoinClause>(new JoinClause(c1, relation, c2));
  }

  public JoinQuery(JoinQuery query) {
    super(query);

    mTable = query.mTable;
    mClauses = new BoolExp<JoinClause>(query.mClauses);
  }

  public JoinQuery and(Column c1, RelationType relation, Column c2) {
    mClauses.and(new JoinClause(c1, relation, c2));

    return this;
  }

  public JoinQuery or(Column c1, RelationType relation, Column c2) {
    mClauses.or(new JoinClause(c1, relation, c2));

    return this;
  }

  public WhereQuery where(Column column, RelationType type) {
    return new WhereQuery(this, column, type);
  }

  @Override
  public void getSql(StringBuilder buffer) {
    getParent().getSql(buffer);

    buffer.append(" JOIN ");
    buffer.append(mTable);
    buffer.append(" ON ");
    mClauses.getSql(buffer);

  }
}
