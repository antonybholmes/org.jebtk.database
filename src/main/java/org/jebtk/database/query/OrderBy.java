package org.jebtk.database.query;

public class OrderBy {
  private SortOrder mOrder;
  private Column mColumn;

  public OrderBy(String column) {
    this(new Column(column));
  }

  public OrderBy(Column column) {
    this(column, SortOrder.ASC);
  }

  public OrderBy(Column column, SortOrder order) {
    mColumn = column;
    mOrder = order;
  }

  @Override
  public String toString() {
    return mColumn + " " + mOrder;
  }
}
