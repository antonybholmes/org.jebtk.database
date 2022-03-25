package org.jebtk.database.query;

public class WhereClause {
  private Column mColumn;
  private RelationType mType;

  public WhereClause(Column column, RelationType type) {
    mColumn = column;
    mType = type;
  }

  @Override
  public String toString() {
    return mColumn + " " + RelationType.toString(mType) + " ?";
  }
}
