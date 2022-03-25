package org.jebtk.database.query;

public enum RelationType {
  EQ, NE, LT, GT, LE, GE;

  public static String toString(RelationType type) {
    switch (type) {
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
