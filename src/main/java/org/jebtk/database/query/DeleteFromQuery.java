package org.jebtk.database.query;

/**
 * Instance of FROM for use with DELETE. This this cannot perform joins or
 * orders.
 * 
 * @author Antony Holmes
 *
 */
public class DeleteFromQuery extends FromQuery {
  public DeleteFromQuery(Query query, Table table) {
    super(query, table);
  }

  public DeleteFromQuery(FromQuery query) {
    super(query);
  }

  public WhereQuery where(String column) {
    return where(new Column(column));
  }

  public WhereQuery where(Column column) {
    return where(column, RelationType.EQ);
  }

  public WhereQuery where(Column column, RelationType type) {
    return new WhereQuery(this, column, type);
  }
}
