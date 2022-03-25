package org.jebtk.database.query;

public class JoinClause {
  private RelationType mType;
  private Column mC1;
  private Column mC2;

  public JoinClause(Column c1, RelationType type, Column c2) {
    mC1 = c1;
    mType = type;
    mC2 = c2;
  }

  @Override
  public String toString() {
    return mC1 + " " + RelationType.toString(mType) + " " + mC2;
  }
}
