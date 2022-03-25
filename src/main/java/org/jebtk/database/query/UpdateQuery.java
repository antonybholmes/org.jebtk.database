package org.jebtk.database.query;

public class UpdateQuery extends Query {
  private Table mTable;

  public UpdateQuery(Query query, String table) {
    this(query, new Table(table));
  }

  public UpdateQuery(Query query, Table table) {
    super(query);

    mTable = table;
  }

  public SetQuery set(String column) {
    return set(new Column(column));
  }

  public SetQuery set(Column column) {
    return new SetQuery(this, column);
  }

  @Override
  public void getSql(StringBuilder buffer) {
    buffer.append("UPDATE ");
    buffer.append(mTable);
  }
}
