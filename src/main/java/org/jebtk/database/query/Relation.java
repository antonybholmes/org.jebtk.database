package org.jebtk.database.query;

public class Relation {
  private RelationType mType;

  public Relation(RelationType type) {
    mType = type;
  }

  @Override
  public String toString() {
    switch (mType) {
    case NE:
      return "!=";
    case LT:
      return "<";
    case GT:
      return ">";
    case LE:
      return "<=";
    case GE:
      return ">";
    default:
      return "=";
    }
  }
}
