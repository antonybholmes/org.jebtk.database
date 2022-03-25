package org.jebtk.database.query;

public class SubBoolExp<T> extends BoolExp<T> {
  public SubBoolExp(T clause) {
    super(clause);
  }

  @Override
  public void getSql(StringBuilder buffer) {
    buffer.append("(");
    super.getSql(buffer);
    buffer.append(")");
  }
}
