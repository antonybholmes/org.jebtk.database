package org.jebtk.database.query;

public class DeleteQuery extends Query {
  public DeleteQuery(TableQuery query) {
    super(query);
  }

  public DeleteFromQuery from(String table) {
    return from(new Table(table));
  }

  public DeleteFromQuery from(Table table) {
    return new DeleteFromQuery(this, table);
  }

  @Override
  public void getSql(StringBuilder buffer) {
    buffer.append("DELETE");
  }
}
